import java.util.ArrayList;
import java.util.List;


public class MatrixGraph extends Graph {
	
	private boolean [][] a;
	private Double [][] w = new Double[100][100];
	
	public MatrixGraph(int n) {
		super(n);
		AdjacencyMatrix(n);
		
	}
	public void AdjacencyMatrix(int n0) {
		num_nodes = n0;
		a = new boolean[num_nodes][num_nodes];
	}
	
	public void addEdge(int i, int j) {
		a[i][j] = true;
	}
	public void removeEdge(int i, int j) {
		a[i][j] = false;
	}
	public boolean hasEdge(int i, int j) {
		return a[i][j];
	}
	public List<Integer> outEdges(int i) {
		List<Integer> edges = new ArrayList<Integer>();
		for (int j = 0; j < num_nodes; j++){
			if (a[i][j]) {
				edges.add(j);
			}
		}
		return edges;
		
	}


	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (hasEdge(i, j)) {
			if (w[i][j] != null) {
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
	 * @see Graph#setWeight(int, int, java.lang.Double)
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

	/* (non-Javadoc)
	 * @see Graph#adjacentNodes(int)
	 */
	@Override
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		int otherVertex;
		Double weight;
		ArrayList<Pair<Integer, Double>> adjacent = new ArrayList<Pair<Integer, Double>>();
		if (i > num_nodes) {
			throw new IndexOutOfBoundsException();
		}
		else {
			for ( int k = 0; k < num_nodes; k++) {
				if (a[i][k]) {
					weight = getWeight(i, k);
					Pair a = new Pair(k, weight);
					adjacent.add(a);
				}
			}
		}
		return adjacent;
		
		
	}

	/* (non-Javadoc)
	 * @see Graph#degree(int)
	 */
	@Override
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
