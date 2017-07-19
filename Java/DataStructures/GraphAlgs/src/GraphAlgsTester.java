import java.util.ArrayList;

public class GraphAlgsTester extends GraphAlgs {

	public static void main(String[] args) {
		MatrixGraph Dtest = new MatrixGraph(7);
	
		Dtest.addEdge(0, 1);
		Dtest.addEdge(1, 0);
		Dtest.addEdge(1, 3);
		Dtest.addEdge(2, 1);
		Dtest.addEdge(1, 2);
		Dtest.addEdge(3, 1);
		Dtest.addEdge(2, 0);
		Dtest.addEdge(0, 2);
		Dtest.addEdge(2, 3);
		Dtest.addEdge(3, 2);
		Dtest.addEdge(2, 4);
		Dtest.addEdge(4, 2);
		Dtest.addEdge(3, 4);
		Dtest.addEdge(4, 3);
		Dtest.addEdge(3, 5);
		Dtest.addEdge(5, 3);
		Dtest.addEdge(4, 5);
		Dtest.addEdge(5, 4);
		
		Dtest.setWeight(0, 1, 4.0);
		Dtest.setWeight(1, 0, 4.0);
		Dtest.setWeight(1, 3, 5.0);
		Dtest.setWeight(3, 1, 5.0);
		Dtest.setWeight(2, 0, 2.0);
		Dtest.setWeight(0, 2, 2.0);
		Dtest.setWeight(2, 3, 8.0);
		Dtest.setWeight(3, 2, 8.0);
		Dtest.setWeight(2, 4, 10.0);
		Dtest.setWeight(4, 2, 10.0);
		Dtest.setWeight(3, 4, 2.0);
		Dtest.setWeight(4, 3, 2.0);
		Dtest.setWeight(3, 5, 6.0);
		Dtest.setWeight(5, 3, 6.0);
		Dtest.setWeight(4, 5, 3.0);
		Dtest.setWeight(5, 4, 3.0);
		Dtest.setWeight(1, 2, 1.0);
		Dtest.setWeight(2, 1, 1.0);


		
		System.out.println(dijkstra(Dtest, 0));
		
		ListGraph Ptest = new ListGraph(7);
		
		Ptest.addEdge(0, 1);
		Ptest.addEdge(1, 0);
		Ptest.addEdge(1, 2);
		Ptest.addEdge(2, 1);
		Ptest.addEdge(2, 3);
		Ptest.addEdge(3, 2);
		Ptest.addEdge(3, 1);
		Ptest.addEdge(1,3);
		Ptest.addEdge(3, 0);
		Ptest.addEdge(0, 3);
		
		Ptest.setWeight(0, 1, 1.0);
		Ptest.setWeight(1, 0, 1.0);
		Ptest.setWeight(1, 2, 1.0);
		Ptest.setWeight(2, 1, 1.0);
		Ptest.setWeight(2, 3, 1.0);
		Ptest.setWeight(3, 2, 1.0);
		Ptest.setWeight(3, 1, 3.0);
		Ptest.setWeight(1, 3, 3.0);
		Ptest.setWeight(0, 3, 3.0);
		Ptest.setWeight(3, 0, 3.0);
		
		//System.out.print(prim(Dtest));

















	}

}
