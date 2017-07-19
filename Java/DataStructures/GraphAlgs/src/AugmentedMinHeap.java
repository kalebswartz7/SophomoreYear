import java.util.ArrayList;

public class AugmentedMinHeap {
	int size;
	int numOps;
	ArrayList<Pair<Integer,Double>> H;
	ArrayList<Integer> L;
	ArrayList<Integer> map;
	
	public AugmentedMinHeap() {
		size = 0;
		H = new ArrayList<>();
		map = new ArrayList<>();
		numOps = 0;
	}

	
	private boolean less(int i, int j) {
		return H.get(i).second.compareTo(H.get(j).second) < 0;
	}
	
	private void swap(int i, int j) {
		Pair<Integer,Double> tmp = H.get(i);
		H.set(i, H.get(j));
		H.set(j, tmp);
		map.set(H.get(i).first, i);
		map.set(H.get(j).first, j);
	}
		
	
	private void bubble_up(int i) {
		while (i > 0) {
			int j = i/2;
			if (less(i,j)) {
				swap(i,j);
				i = j;
			}
			else 
				break;
		}
	}
	
	private void bubble_down(int i) {
		while (true) {
			if (2*i+1 >= size)
				break;
			if (2*i+1 == size-1) {
				if (less(2*i+1,i))
					swap(i, 2*i+1);
				break;
			}
			if (less(2*i+1, 2*i+2)) {
				if (less(2*i+1, i)) {
					swap(i, 2*i+1);
					i = 2*i+1;
				}
				else {
					break;
				}
			}
			else {
				if (less(2*i+2, i)) {
					swap(i, 2*i+2);
					i = 2*i+2;
				}
				else {
					break;
				}
			}
		}
	}

	
	/**
	 * Push new element on the heap.
	 * @param e  element to be pushed
	 * @param p  priority of element
	 */
	public void push(Integer e, Double p) {
		while (map.size() <= e)
			map.add(null);
		
		H.add(new Pair<>(e,p));
		map.set(e,  size());
		size++;
		
		bubble_up(size-1);
	}

	/**
	 * Remove element of least priority from heap.
	 * @return element of least priority.
	 */
	public Integer pop() {
		if (size == 0)
			throw new ArrayIndexOutOfBoundsException();
		
		Pair<Integer,Double> result = H.get(0);
		map.set(H.get(0).first, null);
		
		H.set(0, H.get(size-1));
		map.set(H.get(0).first, 0);
		size--;
		
		bubble_down(0);
		
		return result.first;
	}
	
	/**
	 * Change priorty of element on the heap.
	 * @param e   Element e.
	 * @param p   Priority p.
	 */
	public void changePriority(Integer e, Double p) {
		int i = map.get(e);
		Double old_p = H.get(i).second;
		H.get(i).second = p;
		if (old_p.compareTo(p) < 0)
			bubble_down(i);
		else 
			bubble_up(i);
	}

	
	/**
	 * Return element of least priority.  (Does not modify the heap.)
	 * @return  element.
	 */
	public Integer peek() {
		numOps++;
		return H.get(0).first;
	}

	
	/**
	 * Return number of elements in the heap.
	 * @return number of elements
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Determine if heap is empty.
	 * @return  boolean
	 */
	public boolean empty() {
		return size == 0;
	}	

	/**
	 * Determine if an element is on the heap.
	 * @param n  Element in question.
	 * @return   boolean.
	 */
	public boolean onHeap(int n) {
		return map.get(n) != null;
	}
	
	/**
	 * Return priority of element on heap.
	 * @param n   Element in question.
	 * @return    priority value.
	 */
	public Double getPriority(int n) {
		Integer p = map.get(n);
		if (p == null)
			return new Double(-1);
		return H.get(p).second;
	}
	
	/**
	 * Clear the heap.
	 */
	public void clear() {
		size = 0;
		H = new ArrayList<>();
	}

}
