import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.soap.Node;

import data_structures.BST;
import data_structures.IntBST;
import data_structures.TreeNode;

public class TreeTransversal {

	public static void main (String [] args) {
		ArrayList <Integer> list = new ArrayList <Integer>();



		BST tree = new BST();
		tree.insert(4, 10);
		tree.insert(2, 30);
		tree.insert(10, 69);
		tree.insert(36, 69);
		tree.insert(68, 69);
		tree.insert(50, 69);
		tree.insert(79, 69);


		TreeNode root = tree.getRoot();
		ArrayList <Integer> printList = new ArrayList <Integer>();
		printList = inOrder(root);
		for (int i = 0; i < printList.size(); i++) {
			System.out.println(printList.get(i));
		}
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary search tree,
	 * return an ArrayList<Integer> holding the keys in order from a post-order transversal.
	 * RESTRICTION: Use recursion for this implementation.
	 * @param r
	 * @return Array list
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer,Integer> r) {
		ArrayList <Integer> list = new ArrayList <Integer>();

		postOrderHelp(r, list);
		return list;


	}

	public static void postOrderHelp(TreeNode<Integer,Integer> r, ArrayList <Integer> list ) {
		if (r != null) {
			postOrderHelp(r.left, list);
			postOrderHelp(r.right, list);

			list.add(r.key);
		}
	}


	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a BFS transversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Queue.  (Or
	 * use your Linked List class as a queue.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root) {
		ArrayList <Integer> list = new ArrayList <Integer>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();

		if (root != null) q.add(root);
		while (!q.isEmpty()) {
			TreeNode u = q.remove();
			list.add((Integer) u.key);
			if (u.left != null) q.add(u.left);
			if (u.right != null) q.add(u.right);


		}

        return list;

	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a pre-order transversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root) {
		ArrayList <Integer> list = new ArrayList <Integer>();

		if (root == null) {
			return null;
		}
		Stack <TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {

			TreeNode<Integer, Integer> n = stack.pop();
			list.add(n.key);

			if (n.right != null) {
				stack.push(n.right);
			}
			if (n.left != null) {
				stack.push(n.left);
			}
 		}


		return list;

	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from an in-order transversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root) {
		ArrayList <Integer> list = new ArrayList <Integer>();
		if (root == null) {
			return null;
		}

		Stack <TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;

		while (!stack.isEmpty() || node != null) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			}
			else {
				TreeNode top = stack.pop();
				list.add((Integer) top.key);
				node = top.right;

			}
		}

		return list;

	}

}
