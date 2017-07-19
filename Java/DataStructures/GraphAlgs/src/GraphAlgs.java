import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class GraphAlgs {

	/** Author: Kaleb Swartz
	 * Create a graph from a file.
	 * 
	 * @param file
	 * @param list_graph
	 * @return
	 */

	static public Graph readGraph(String file, boolean list_graph) {
		Graph G;
		try {
			Scanner S = new Scanner(new File(file));
			int n = S.nextInt();
			G = list_graph ? new ListGraph(n) : new MatrixGraph(n);

			while (S.hasNext()) {
				int u = S.nextInt();
				int v = S.nextInt();
				Double w = S.nextDouble();
				G.setWeight(u, v, w);
			}
			S.close();
			return G;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return null;
	}

	/**
	 * Write a graph to a file.
	 * 
	 * @param G
	 * @param file
	 */
	static public void writeGraph(Graph G, String file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(G.numNodes());
			for (int i = 0; i < G.numNodes(); i++) {
				ArrayList<Pair<Integer, Double>> P = G.adjacentNodes(i);
				for (Pair<Integer, Double> j : P) {
					if (i < j.first) {
						out.println(i + " " + j.first + " " + j.second);
					}
				}

			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Dijkastra's algorithm. Return an array list A such that A.get(i) is the
	 * length of the shortest path in G from the source node to node i. You may
	 * assume G is connected and has positive edge weights.
	 * 
	 * @param G
	 * @param source
	 * @return ArrayList<int>
	 */
	static public ArrayList<Double> dijkstra(Graph G, int source) {
		int size = getAllNodes(G).size();
		ArrayList<Double> returnList = new ArrayList<Double>(size);
		for (int q = 0; q < size; q++) {
			returnList.add(0.0);
		}
		ArrayList<Double> vertexList = getAllNodes(G);

		AugmentedMinHeap heap = new AugmentedMinHeap();

		// Creates the heap w all nodes
		for (int i = 0; i < size - 1; i++) {
			double vertex = vertexList.get(i);
			if (i == source) {
				heap.push(0, (double) source);
			} else {
				heap.push(10000, vertex);
			}
		}

		// Creates an ArrayList havePath, that keep tracks of which nodes have been visited 
		ArrayList<Integer> havePath = new ArrayList<Integer>();
		Double[] weights = new Double[size];
		weights[0] = 0.0;
		for (int k = 1; k < size; k++) {
			weights[k] = 10000.0;
		}
		int value = heap.peek();
		int newValue = -1;
		double prevWeight = 0;
		double getLeast = 100.0;

		// Starts with a given value, which is changed based on which node is found to have the least weight
		// Values of the weights of the adjacent nodes are updated every iteration
		while (havePath.size() != size && !havePath.contains(value)) {
			boolean changed = false;
			ArrayList<Pair<Integer, Double>> adjacentNodes = G.adjacentNodes(value);
			getLeast = 100.0;
			for (int i = 0; i < adjacentNodes.size(); i++) {
				//Checks to make sure the new node hasn't already been added to havePath
				if (havePath.contains(adjacentNodes.get(i).first)) {

				} else {
					double newWeight = G.getWeight(value, adjacentNodes.get(i).first);
					weights[adjacentNodes.get(i).first] = newWeight;

					//Checks if weight of node is less than weight of other adj node
					if (newWeight < getLeast) {
						getLeast = newWeight;
						newValue = adjacentNodes.get(i).first;
						changed = true;
					}
				}
			}
			
			havePath.add(value);
			if (getLeast != 100)
				prevWeight += getLeast;

			//Set the new Weight (length) for the specific value 
			returnList.set(newValue, prevWeight);
			value = newValue;
		}

		return returnList;
	}
	
	// Method to return all nodes in the graph 
	public static ArrayList<Double> getAllNodes(Graph g) {
		ArrayList<Double> nodes = new ArrayList<Double>();
		for (int i = 0; i < g.num_nodes; i++) {
			for (int j = 0; j < g.num_nodes; j++) {
				if (((MatrixGraph) g).hasEdge(i, j)) {
					if (!nodes.contains((double) i)) {
						nodes.add((double) i);
					}
					if (!nodes.contains((double) j)) {
						nodes.add((double) j);
					}
				}
			}
		}
		return nodes;

	}

	/**
	 * Prim's algorithm. Return the length of a MST for G. You may assume G is
	 * connected.
	 * 
	 * @param G
	 * @return int
	 */
	static public int prim(Graph G) {
		int returnLength = 0;
		ArrayList<Double> vertexList = getAllNodes(G);
		int size = vertexList.size();
		ArrayList<Integer> havePath = new ArrayList<Integer>();

		AugmentedMinHeap heap = new AugmentedMinHeap();

		//Push all values onto the heap 
		for (int i = 0; i < size - 1; i++) {
			heap.push(0, vertexList.get(i));
		}
		//Get lowest value to be starting Node 
		int value = heap.peek();

		double weight = 0;
		double newWeight = 10000;
		double totalWeight = 0;
		double isSelected = 0.0;

		havePath.add(value);

		// Checks for adjacent nodes of all nodes already in havePath
		while (havePath.size() != size) {
			newWeight = 1000;
			for (int i = 0; i < havePath.size(); i++) {
				ArrayList<Pair<Integer, Double>> adjacentNodes = G.adjacentNodes(havePath.get(i));
				for (int j = 0; j < adjacentNodes.size(); j++) {
					if (!havePath.contains(adjacentNodes.get(j).first)) {
						weight = G.getWeight(havePath.get(i), adjacentNodes.get(j).first);
						if (weight < newWeight) {
							isSelected = adjacentNodes.get(j).first;
							newWeight = weight;
						}
					}

				}

			}
			// Increase totalWeight by the new weight value found from the new
			// edge
			if (newWeight < 1000) {
				totalWeight += newWeight;
			}
			value = (int) isSelected;
			havePath.add(value);

		}
		return (int) totalWeight;

	}

	/**
	 * Return the number of connected components in G.
	 * 
	 * @param G
	 * @return int
	 */
	static public int numComponents(Graph G) {
		int numOfComponents = 0;
		ArrayList<Double> vertexList = getAllNodes(G);
		int size = vertexList.size();
		ArrayList<Double> havePath = new ArrayList<Double>();

		AugmentedMinHeap heap = new AugmentedMinHeap();

		//Add all nodes to the heap 
		for (int i = 0; i < size - 1; i++) {
			heap.push(0, vertexList.get(i));
		}

		int value = heap.peek();

		// Checks for all adjacent nodes of all nodes and increases the value of numOfComponents 
		while (havePath.size() < size) {
			ArrayList<Pair<Integer, Double>> adjacentNodes = G.adjacentNodes(value);

			for (int i = 0; i < adjacentNodes.size(); i++) {
				numOfComponents++;
				havePath.add(adjacentNodes.get(i).second);
			}
			value++;
		}

		return numOfComponents;
	}

}
