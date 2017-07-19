import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ListGraph extends Graph {
    private List<Integer>[] adj;
	private Double [][] w = new Double[100][100];


	
	public ListGraph(int n) {
		super(n);
		AdjacencyLists(n);
	}
	
	public void AdjacencyLists(int n0) {
		num_nodes = n0;
		adj = (List<Integer>[]) new List[num_nodes];
		for (int i = 0; i < num_nodes; i++) {
			adj[i] = new Stack<Integer>();
		}
	}
	
	public void addEdge(int i, int j) {
		adj[i].add(j);
	}
	public boolean hasEdge(int i, int j) {
		return adj[i].contains(j);
	}
	List<Integer> outEdges (int i) {
		return adj[i];
	}


	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (hasEdge(i, j)) {
			if (!w[i].equals(null)) {
				return w[i][j];
			}
			else {
				throw new IndexOutOfBoundsException();
			}
		}
		else {
			return null;
		}
		
	}



	/* (non-Javadoc)
	 * @see Graph#addEdge(int, int)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		if (i > num_nodes || j > num_nodes) {
			throw new IndexOutOfBoundsException();
		}
		else {
			addEdge(i, j);
			w[i][j] = weight;
		}
	}
		
	/* (non-javadoc)
	 * @see Graph#AdjacentNodes
	 */
	ArrayList<Pair<Integer,Double>> adjacentNodes(int i) {
		Double weight;
		ArrayList<Pair<Integer, Double>> adjacent = new ArrayList<Pair<Integer, Double>>();
		if (i > num_nodes) {
			throw new IndexOutOfBoundsException();
		}
		else {
			for (int k = 0; k < num_nodes; k++) {
				if (hasEdge(i, k)) {
					weight = getWeight(i, k);
					Pair a = new Pair(k, weight);
					adjacent.add(a);
				}
			}
			
		}
		return adjacent;
	}
	 
	/* (non-javadoc)
	 * @see Graph#degree
	 */
	int degree(int i) {
		List<Integer> edges;
		if (i > num_nodes) {
			throw new IndexOutOfBoundsException();
		}
		else {
			edges = outEdges(i);
		}
		return edges.size();
	}

}
