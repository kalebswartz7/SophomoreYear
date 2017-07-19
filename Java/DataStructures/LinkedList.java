/**
 *
 */
package data_structures;

import org.omg.CORBA.Current;

/**
 * @author swartzkr
 *
 */

/**
 * The ListNode<value_type> is a helper class for your LinkedList
 * <value_type> class. As its not intended for use outside the LinkeList class,
 * we are keeping it simple -- the two properties will be access directly,
 * instead of going through inspectors and mutators.
 *
 * DO NOT MODIFY THIST CLASS.
 *
 * @param <value_type>
 *            The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next;

	public ListNode(value_type v) {
		value = v;
		next = null;
	}

	public ListNode(value_type v, ListNode<value_type> n) {
		value = v;
		next = n;
	}

}

/*
 * We will implement this as a single linked list.
 */
public class LinkedList<value_type> extends Sequence<value_type> {

	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;
	private ListNode<value_type> tail;

	/**
	 * List constructor: must call the superclass constructor.
	 */
	public LinkedList() {
		super();
		head = new ListNode(null);
		tail = new ListNode(null);
		// TO DO: Finish constructor.
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#get(int)
	 */
	@Override
	public value_type get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		ListNode find = head;
		for (int j = 0; j < i; j++) {
			find = find.next;
			numOps++;

		}
		return (value_type) find.value;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#set(int, java.lang.String)
	 */
	@Override
	public value_type set(int i, value_type value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();

		ListNode find = head;
		for (int j = 0; j < i; j++) {
			find = find.next;
			numOps++;
		}
		value_type old = (value_type) find.value;
		numOps++;
		find.value = value;
		numOps++;
		return old;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#add(int, java.lang.String)
	 */
	@Override
	public void add(int i, value_type value) {
		value_type v = null;
		int size1 = this.size();
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		ListNode newNode = new ListNode(value);
		if (i == 0) {
			newNode.next = head;
			numOps++;
			head = newNode;
		} else {
			ListNode prev = head;
			int count = 0;
			for (int j = 0; j < i - 1; j++) {
				prev = prev.next;
				numOps++;

			}

			ListNode node1 = prev.next;
			numOps++;
			newNode.next = node1;
			numOps++;
			prev.next = newNode;
			numOps++;

		}

		n++;

		// TO DO: Finish method

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#remove(int)
	 */
	@Override
	public value_type remove(int i) {
		value_type v = null;
		int size1 = this.size();
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		n--;

		if (i == 0) {
			ListNode newNode = head;
			head = head.next;
			numOps++;
			value_type returnValue = (value_type) newNode.value;
			numOps++;
			newNode.next = null;
			numOps++;
			return returnValue;
		} else {
			ListNode prev = head;
			int count = 0;
			for (int j = 0; j < i - 1; j++) {
				prev = prev.next;
				numOps++;
			}

			ListNode delete = prev.next;
			numOps++;
			value_type returnValue = (value_type) delete.value;
			numOps++;
			prev.next = delete.next;
			numOps++;
			delete.next = null;
			numOps++;
			return returnValue;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#clear()
	 */
	@Override
	public void clear() {
		for (int i = 0; i < this.size(); i++) {
			n = 0;
		}
	}

	public boolean in(value_type value) {
		ListNode find = head;

		for (int i = 0; i < this.size(); i++) {
			find = find.next;
			value_type v = (value_type) find.value;
			if (v == null) return false;

			if (v == value || v.equals(value))
				return true;
			}

		return false;
	}


}
