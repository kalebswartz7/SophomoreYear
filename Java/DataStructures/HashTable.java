/**
 *
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * @author swartzkr
 *
 */
public class IntHash<value_type> extends Dictionary<Integer, value_type> {
	int a;
	int b;
	int m;

	ArrayList<Pair<Integer, value_type>> table;

	/**
	 * Hashing function
	 *
	 * @param key
	 * @return hash value
	 */
	private int hash(Integer key) {
		return (((int) key * a) + b) % m;
	}

	/**
	 * Default constructor
	 */
	public IntHash() {
		this(7, 1, 25014);
	}

	/**
	 * Constructor -- hash values specified.
	 */
	public IntHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<Integer, value_type>>(Collections.nCopies(m, null));
	}

	/**
	 * Insert a value/key pair into the dictionary. Do not allow duplicate or
	 * null values.
	 *
	 * @param key
	 *            key to be inserted
	 * @param value
	 *            value to be inserted
	 * @exception Throw
	 *                IndexOutOfBoundsException if key already present.
	 * @exception Throw
	 *                IllegalArgumentException if value is null.
	 * @exception Throw
	 *                IllegalArgumentException if key < 0. (Makes life easier.
	 */
	@Override
	public void insert(Integer key, value_type value) {
		if (value == null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n == m)
			throw new ArrayIndexOutOfBoundsException("Duplicate values not allowed");
		if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");

		if (this.find(key) != null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Pair hashPair = new Pair(key, value);

		int initialIndex = this.hash(key);
		while (table.get(initialIndex) != null) {
			numOps++;
			initialIndex = (initialIndex + 1) % m;
		}
		table.set(initialIndex, hashPair);
		numOps++;
		n++;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(Integer key) {
		int location = this.hash(key);

		while (table.get(location) != null) {
			numOps++;
			Integer y = table.get(location).first;
			numOps++;

			if (key.equals(y)) {
				table.set(location, null);
				numOps++;
				n--;
			}
			location = (location + 1) % m;
		}


	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		for (int i = 0; i < table.size(); i++) {
			table.set(i, null);
			numOps++;
		}
		n = 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public value_type find(Integer key) {
		int initialIndex = this.hash(key);

		while (table.get(initialIndex) != null) {
			numOps++;
			if (table.get(initialIndex).first.equals(key)) return table.get(initialIndex).second;
			numOps++;
			initialIndex = (initialIndex + 1) % m;
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#check_structure() This is not useful for
	 * this class -- we will just always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		for (int i = 0; i < m; i++) {
			Pair<Integer, value_type> p = table.get(i);
			if (p != null && p.first >= 0)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
	}

}
