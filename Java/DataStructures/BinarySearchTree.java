/**
 *
 */
package data_structures;
import java.lang.IndexOutOfBoundsException;

/**
 * @author swartzkr
 *
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	protected TreeNode<K,V> root;

	/**
	 *
	 */
	public BST() {
		super();
		root = null;
	}

	/**
	 * Compare two keys and increment numOps.
	 * @param k1   First key
	 * @param k2   Second key
	 * @return     -1: k1 smaller; 0: elements equal; 1: k22 smaller
	 */
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 * @return
	 */
	public TreeNode<K,V> getRoot() {
		return root;
	}


	/* (non-Javadoc)
	 * @see data_structures.Dictionary#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		TreeNode<K,V> p = findLast(key);
		TreeNode<K, V> newNode = new TreeNode<K, V>(key, value);
		this.addChild(p, newNode);
	}

	public TreeNode<K,V> findLast (K Key) {
		TreeNode<K,V> w = root, prev = null;
		while (w != null ) {
			prev = w;
			int compare = this.compareKeys(Key, w.key);
			if (compare < 0) {
				w = w.left;
			}
			else if (compare > 0) {
				w = w.right;

			}
			else return w;
		}
		return prev;

	}

	public boolean addChild(TreeNode<K, V> p, TreeNode<K, V> u) {
		if (p == null) {
			root = u;
		}
		else {
			int comp = this.compareKeys(u.key, p.key);
			if (comp < 0) {
				p.left = u;
			}
			else if (comp > 0) {
				p.right = u;
			}
			else {
				return false;
			}

		}
		n++;
		return true;
	}



	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) {
		V v = this.find(key);
		TreeNode<K, V> node = new TreeNode<K, V> (key, v);
		if (node.left == null || node.right == null) {
			splice(node);
		}
		else {
			TreeNode<K, V> w = node.right;
			while (w.left != null) {
				w = w.left;
			}
			node.key = w.key;
			splice(w);
		}
	}


	public void splice(TreeNode<K, V> u) {


		TreeNode<K, V> s, p;
		if (u.left != null) {
			s = u.left;
		}
		else {
			 s = u.right;
		}
		if (u == root) {
			root = s;
			p = null;
		}
		else {
			 p = u;
			 if (p.left == u) {
				 p.left = s;
			 }
			 else {
				 p.right = s;
			 }

		}
		if (s != null) {
			s = p;
		}
		n--;
	}




	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public V find(K key) {
		TreeNode<K, V> u = root;
		while (u!= null) {
			int comp = this.compareKeys(key, u.key);
			if (comp < 0) {
				u = u.left;
			}
			else if (comp > 0) {
				u = u.right;
			}
			else {
				return u.value;
			}
		}
		return null;
	}




	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K min() {
		TreeNode<K, V> node = root;
		while (node.left != null) {
			node = node.left;
		}
		return node.key;
	}

	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K max() {
		TreeNode<K, V> node = root;
		while (node.right != null) {
			node = node.right;
		}
		return node.key;
	}



	/**
	 * Return the height of the tree.
	 * Definition:
	 *    The *depth* of a node is number of edges from the root to that node.
	 *    The *height* of a tree is equal to the depth of the node with the
	 *        greatest depth of all the nodes.
	 * @return int
	 */
	public int height() {
		int h = 0;
		TreeNode<K, V> node = root;
		while (node.right != null || node.left != null) {
			h++;
			node = node.left;
		}
		return h;



	}


	boolean isBSTHelper(TreeNode<K,V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0) || (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;

		return isBSTHelper(root.left, min_value, root.key) && isBSTHelper(root.right, root.key, max_value);
	}

	/**
	 * Check that the tree is a BST.
	 * @param root    Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K,V> root) {
		return isBSTHelper(root, null, null);
	}


	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isBST(root);
	}


	void print_structure_helper(TreeNode<K,V> root, int indent) {
		for (int i=0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(root.key + ": " + root.value);
		print_structure_helper(root.left, indent+1);
		print_structure_helper(root.right, indent+1);
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper(root, 0);
	}

	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		root = null;
	}

}
