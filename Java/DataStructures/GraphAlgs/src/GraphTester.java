import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GraphTester {
	
	static public boolean list_graph = true;
	
	private Graph newGraph(int n) {
		return GraphFactory.make_graph(n, list_graph);
	}
	
	@Test
	public void test01() {
		Graph G = newGraph(0);
		assertEquals(G.numNodes(), 0);
		assertEquals(G.numEdges(), 0);
	}
	

	@Test
	public void test02() {
		Graph G = newGraph(5);
		assertEquals(G.numNodes(), 5);
		assertEquals(G.numEdges(), 0);		
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test03() {
		Graph G = newGraph(2);
		G.getWeight(5, 0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test04() {
		Graph G = newGraph(2);
		G.setWeight(5, 0, 1.0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test05() {
		Graph G = newGraph(2);
		G.adjacentNodes(5);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test06() {
		Graph G = newGraph(2);
		G.degree(5);
	}
	
	
	
	@Test
	public void test07() {
		Graph G = newGraph(3);
		G.setWeight(0, 1, 5.5);
		assertEquals(G.getWeight(0,1), new Double(5.5));
		assertNull(G.getWeight(1,0));
		assertNull(G.getWeight(0,2));
		assertNull(G.getWeight(1,2));
	}
	
	@Test
	public void test08() {
		Graph G = newGraph(3);
		G.setWeight(0, 1, 3.14);
		G.setWeight(0, 2, 2.71);;
		assertEquals(G.getWeight(0,1), new Double(3.14));
		assertNull(G.getWeight(1,0));
		assertEquals(G.getWeight(0,2), new Double(2.71));
		assertNull(G.getWeight(2,0));
		assertNull(G.getWeight(1,2));
		assertNull(G.getWeight(2,1));
	}
	
	@Test
	public void test09() {
		Graph G = newGraph(5);
		G.setWeight(0, 1, 1.0);
		G.setWeight(0, 2, 2.0);
		G.setWeight(0, 3, 3.0);
		G.setWeight(0, 4, 4.0);
		assertEquals(G.degree(0), 4);
		assertEquals(G.degree(1), 0);
	}
	
	
	private boolean searchList(ArrayList<Pair<Integer,Double>> L, int i, Double w) {
		for (int k = 0; k < L.size(); k++) {
			if (L.get(k).first.equals(i))
				return L.get(k).second.equals(w);
		}
		return false;
	}
	
	@Test
	public void test10() {
		Graph G = newGraph(5);
		G.setWeight(0, 1, 1.0);
		G.setWeight(0, 2, 2.0);
		G.setWeight(0, 3, 3.0);
		G.setWeight(0, 4, 4.0);

		ArrayList<Pair<Integer,Double>> L = G.adjacentNodes(0);
		assertEquals(L.size(), 4);
		assertTrue(searchList(L, 1, 1.0));
		assertTrue(searchList(L, 2, 2.0));
		assertTrue(searchList(L, 3, 3.0));
		assertTrue(searchList(L, 4, 4.0));
	}
	
	@Test
	public void test11() {
		Graph G = newGraph(5);
		G.setWeight(0, 1, 1.0);
		G.setWeight(0, 2, 2.0);
		G.setWeight(3, 0, 3.0);
		G.setWeight(0, 4, 4.0);

		ArrayList<Pair<Integer,Double>> L = G.adjacentNodes(3);
		assertEquals(L.size(), 1);
		assertTrue(searchList(L, 0, 3.0));
	}
	
}
