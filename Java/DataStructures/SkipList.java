/**
 *
 */
package data_structures;
import java.util.Random;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

// ID: swartzkr


/**
 * SLNode: A helper class: the nodes in the skip list.  Since this is
 * only intended for internal use, all properties and methods have been
 * made public.  We are not bothering with getters and setters, as the properties
 * can be accessed directly.
 *
 * @author swartzkr
 *
 */
class SLNode<key_type, value_type> {
	public SLNode<key_type,value_type> next;
	public SLNode<key_type,value_type> down;
	public key_type key;
	public value_type value;
	public int height;

	public SLNode(key_type key, value_type value, int height) {
		this(key, value, height, null, null);
	}
	public SLNode(key_type key, value_type value, int height, SLNode<key_type,value_type> next, SLNode<key_type,value_type> down) {
		this.next = next;
		this.down = down;
		this.key = key;
		this.value = value;
		this.height = height;
	}

}

/**
 * Skip list class, as described in Morin, Section 4.1.
 *
 * @param <key_type>      The dictionary key type.
 * @param <value_type>    The dictionary value type.
 */
public class SkipList<key_type extends Comparable<key_type>,value_type> extends Dictionary<key_type, value_type> {

	SLNode<key_type,value_type> headStack [] ; // Left Tower
	SLNode<key_type,value_type> head;
	SLNode<key_type,value_type> tailStack [] ; // Right Tower
	SLNode<key_type,value_type> tail;



	int nodes = 0;
	double p;        // Probability of increasing the height by 1.  (p = 1.0 / expected_height).
	int max_height;  // Maximum allowed height.
	Random rng;      // Random number generator.

	key_type min_key;  // Lower bound on values allowed in the skip list.
	key_type max_key;  // Upper bound on values allowed in the skip list.

	/**
	 * Helper function: Compare the key values of two node.  noOps will be incremented
	 *                  in the compareKeys call.
	 * @param n1   First node
	 * @param n2   Second node
	 * @return     -1: n1 has smaller key; 0: keys equal; 1: n2 has smaller key
	 */
	int compareNodes(SLNode<key_type,value_type> n1, SLNode<key_type,value_type> n2) {
		return compareKeys(n1.key, n2.key);
	}

	/**
	 * Compare two keys and increment numOps.
	 * @param k1   First key
	 * @param k2   Second key
	 * @return     -1: k1 smaller; 0: elements equal; 1: k22 smaller
	 */
	int compareKeys(key_type k1, key_type k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Skip list constructor.
	 * @param expected_height    Expected height of each "tower".
	 * @param max_height         Maximum allowed height of a tower.
	 * @param min_key            Minimum allowed value of any key.
	 * @param max_key            Maximum allowed value of any key.
	 */
	public SkipList(int expected_height, int max_height, key_type min_key, key_type max_key) {
		this(expected_height, max_height, min_key, max_key, null);
	}

	/**
	 * Skip list constructor.
	 * @param expected_height    Expected height of each "tower".  (p = 1/expected_height)
	 * @param max_height         Maximum allowed height of a tower.
	 * @param min_key            Minimum allowed value of any key.
	 * @param max_key            Maximum allower value of any key.
	 * @param rng_seed           Seed for the random number generator.
	 */
	public SkipList(int expected_height, int max_height, key_type min_key, key_type max_key, int rng_seed) {
		this(expected_height, max_height, min_key, max_key, new Random(rng_seed));
	}

	/**
	 * Skip list constructor.
	 * @param expected_height    Expected height of each "tower".
	 * @param max_height         Maximum allowed height of a tower.
	 * @param min_key            Minimum allowed value of any key.
	 * @param max_key            Maximum allowed value of any key.
	 * @param rng                Random number generator object.  (If null, create a new rng().)
	 */
	public SkipList(int expected_height, int max_height, key_type min_key, key_type max_key, Random rng) {
		super();
		this.p = 1.0 / expected_height;
		if (rng != null) {
			this.rng = rng;
		}
		else {
			this.rng = new Random();
		}

		this.min_key = min_key;
		this.max_key = max_key;
		this.max_height = max_height;

		headStack = new SLNode [10];
		tailStack = new SLNode [10];

		int realMaxH = max_height - 1;

		for (int i = max_height, j = 0; i > 0; i--, j++) {
			headStack[i-1] = new SLNode(min_key, rng, realMaxH - j);
			tailStack[i-1] = new SLNode(max_key, rng, realMaxH- j);
		}
		for (int k = max_height - 1; k > 0; k--) {
			headStack[k].down = headStack[k-1];
			tailStack[k].down = tailStack[k-1];
			headStack[k].next = tailStack[k];
			tailStack[k].next = null;
		}
		headStack[0].next = tailStack[0];
		tailStack[0].next = null;

		head = headStack[realMaxH];
		headStack[0].down = null;
		tail = tailStack[realMaxH];
		tailStack[0].down = null;

	}

	//Finds the node that the newly inserted node will come after
	public SLNode [] findPosNode(key_type key) {
		key_type key1 = key;
		SLNode [] finder = new SLNode [10];
		SLNode finderNode = head;
		boolean searching = true;

		while (searching) {

			for (int i = 0; searching; i++) {
				if (i >= finder.length) {
					return finder;
				}

				if (finderNode.next != null) {
					 key1 = (key_type) finderNode.next.key;
				}

				while (finderNode.next != tail && this.compareKeys(key1, key) <= 0) {
					finderNode = finderNode.next;
					key1 = (key_type) finderNode.key;
				}

					finder[i] = finderNode;



				if (finderNode.down != null) {
					finderNode = finderNode.down;
				}
				else {
					searching = false;
				}
			}

		}

		return finder;
	}

	/**
	 * Reset the SkipList to an "empty" state.
	 */
	public void clear() {
		for (int i = 0; i < 10; i++) {
			headStack[i].next = tailStack[i];
			tailStack[i].next = null;
		}
		n = 0;
	}



	/**
	 * Searches for a key.
	 * @param key     Key being searched for.
	 * @return The corresponding value if the key is contained; null otherwise.
	 */
	public value_type find(key_type key) {

		value_type valueTest = head.value;
		SLNode finder = head;
		while (finder.down != null) {
			finder = finder.down;
		}
		value_type returnValue = null;

		while (finder.next != null) {
			finder = finder.next;
				if (this.compareKeys((key_type) finder.key, key) == 0) {
					returnValue = (value_type) finder.value;
					return returnValue;
				}

		}
		return null;

	}

	/**
	 * Inert a new key/value pair into the skiplist.
	 * @param key   The key being inserted.
	 * @param value The value being inserted into the skiplist.
	 * @exception   Throw IndexOutOfBoundsException if key <= min_value, >= max_value, or
	 *              already in the list.
	 */
	public void insert(key_type key, value_type value) {

		value_type findCheck = find(key);
		if (findCheck != null) {
			throw new IndexOutOfBoundsException();
		}
		SLNode [] nodeFind = findPosNode(key);
		int h = 0;
		for (int x = 0; x < nodeFind.length - 1; x++) {
			if (nodeFind[x] != null) {
				h++;
			}
		}
		int levels = 1;

			SLNode [] finder = headStack;
			SLNode [] stack = new SLNode [10];
			SLNode [] stack2 = stack;
			int r = h;
			int comp = 2;
			while (r >= 0) {
				SLNode findNode = finder[r];

				while (findNode.next.key != null && this.compareKeys((key_type) findNode.next.key, key) < 0) {
					numOps++;
					findNode = findNode.next;
				}

				stack[r] = findNode;
				r--;
			}
			while (Math.random() < 0.5) {
				levels++;
				nodes++;
			}

			SLNode [] newNodes = new SLNode[levels];
			int increase = 0;
			boolean change = false;
			while (h < levels) {
				increase++;
				h++;
				change = true;
			}
			if (change) {
				stack2 = new SLNode [levels];
				for (int i = 0; i < stack.length; i++) {
					stack2[i] = stack[i];
				}
				for (int i = levels - 1; i >= stack.length; i--) {
					stack2[i] = head;
				}
			}

			stack = stack2;

			for (int j = levels, k = 1; j > 0; j--, k++) {
				newNodes[j-1] = new SLNode (key, value, levels - k);
			}
			for (int l = levels; l > 1; l--) {
				newNodes[l-1].down = newNodes[l-2];
			}

			for (int i = newNodes.length - 1; i >= 0; i--) {
				SLNode newNode1 = newNodes[i];
				SLNode NextNode = stack[i].next;
				newNode1.next = NextNode;
				stack[i].next = newNode1;
			}
			newNodes[0].down = null;
			n++;



	}

	/**
	 * Remove a key/value pair if present.  (Do nothing otherwise.)
	 * @param key   Key of the key/value pair to be removed.
	 */
	public void remove(key_type key) {
		SLNode [] nodeFind = findPosNode(key);
		boolean removed = false;
		int h = 0;
		for (int x = 0; x < nodeFind.length - 1; x++) {
			if (nodeFind[x] != null) {
				h++;
			}
		}

		SLNode [] finder = headStack;
		int r = h;
		int comp = 0;
		while (r >= 0) {
			SLNode findNode = finder[r];

			while (findNode.next != null && (comp = this.compareKeys((key_type) findNode.next.key, key)) < 0) {
				findNode = findNode.next;
			}
			if (findNode.next != null && comp == 0) {
				removed = true;
				findNode.next = findNode.next.next;
				if (findNode == head && findNode.next == null) {
					h--;
				}
			}
			r--;

		}
		if (removed) n--;
	}


	//****************************
	// The following functions are purely for debugging and analysis purposes.
	/**
	 * Number of nodes in the tree.  (Nodes, not keys.)
	 * @return  int specifying the number of nodes in the tree.
	 */
	public int num_nodes() {

		return n + nodes;

	}


	/**
	 * Print out a string representation of your data structure
	 * to help wihth debugging
	 * DO NOT MODIFY THIS CODE!!!
	 */
	public void print_structure() {
		SLNode<key_type,value_type> q = head;
		while (q != null) {
			SLNode<key_type,value_type> q2 = q;
			while (q2 != null) {
				key_type key1 = q2.key;
				System.out.print(q2.key + "(" + q2.height + ")" + "\t");
				q2 = q2.next;
			}
			System.out.println("");
			q = q.down;
		}
	}


	/**
	 * Check to make sure it contains a legitimate skip-list structures.
	 * DO NOT MODIFY THIS CODE!!!
	 */
	public boolean check_structure() {
		SLNode<key_type,value_type> p = head;
		if (p.height != max_height-1) {
			System.err.println("Head node not at correct height");
			return false;
		}


		for (int h=max_height-1; h >= 0; h--) {
			SLNode<key_type,value_type> q = p;

			while (q.next != null) {
				if (q.height != h) {
					System.err.println("Height " + h + ": Node at wrong height (" + q.height + ")");
					return false;
				}
				if (this.compareNodes(q, q.next)  > 0) {
					System.err.println("Height " + h + ": Values out of order");
					return false;
				}
				if (compareNodes(q, q.next) == 0) {
					System.err.println("Height " + h + ": Duplicate value");
					return false;
				}
				if (q.down != null && q.key != null && compareNodes(q, q.down) != 0) {
					System.err.println("Height " + h + ": Tower values not the same");
					return false;
				}
				if (h == 0 && q.down != null) {
					System.err.println("Bottom node without null down pointer.");
					return false;
				}
				if (h > 0 && q.height != q.down.height+1) {
					System.err.println("Height " + h + ": Height skipped");
					return false;
				}
				q = q.next;
			}

			if (q.next != null) {
				System.err.println("Height " + h + ": Right-most node does not have a null pointer");
				return false;
			}
			if (h == 0 && q.down != null) {
				System.err.println("Bottom node without null down pointer.");
				return false;
			}
			if (h > 0 && q.height != q.down.height+1) {
				System.err.println("Height " + h + ": Height skipped");
				return false;
			}
			p=p.down;

		}
		return true;

	}
}
