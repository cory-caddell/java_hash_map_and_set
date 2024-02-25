// CoryCaddellHashSet.java

import java.util.*;

public class CoryCaddellHashSet<E> implements Collection<E> {
	
	// Define the default has-table size.  Must be a power of 2
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	
	// Define the maximum hash-table size. 1 << 30 is same 2^30
	private static int MAXIMUM_CAPACITY = 1 << 30;
	
	// Current hash-table capacity.  Capacity is a power of 2
	private int capacity;
	
	// Define default load factor
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
	
	// Specify a load-factor threshold used in the has table
	private float loadFactorThreshold;
	
	// The number of elements in the set
	private int size = 0;
	
	// Has table is an array with each cell being a linked list
	private LinkedList<E>[] table;
	
	/** Construct a set with the default capacity and load factor */
	public CoryCaddellHashSet() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/** Construct a set with the specified initial capacity and 
	 * default load factor */
	public CoryCaddellHashSet(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/** Construct a set with the specified initial capacity 
	 * and load factor */
	public CoryCaddellHashSet(int initialCapacity, float loadFactorThreshold) {
		if (initialCapacity > MAXIMUM_CAPACITY) {
			this.capacity = MAXIMUM_CAPACITY;
		}
		else {
			this.capacity = trimToPowerOf2(initialCapacity);
		}
		
		this.loadFactorThreshold = loadFactorThreshold;
		table = new LinkedList[capacity];
	}
	
	@Override
	/** Return true if the element is in the set */
	public void clear() {
		size = 0;
		removeElements();
	}
	
	@Override
	/** Return true if the element is in the set */
	public boolean contains(Object e) {					// changed from E to Object
		int bucketIndex = hash(e.hashCode());
		if (table[bucketIndex] != null) {
			LinkedList<E> bucket = table[bucketIndex];
			return bucket.contains(e);
		}
		
		return false;
	}
	
	@Override
	/** Add an element to the set */
	public boolean add(E e) {
		if (contains(e)) {	// Duplicate element not stored
			return false;
		}
		
		if (size + 1 > capacity * loadFactorThreshold) {
			if (capacity == MAXIMUM_CAPACITY) {
				throw new RuntimeException("Exceeding maximum capacity");
			}
			
			rehash();
		}
		
		int bucketIndex = hash(e.hashCode());
		
		// Create a linked list for the bucket if not already created
		if (table[bucketIndex] == null) {
			table[bucketIndex] = new LinkedList<E>();
		}
		
		// Add e to hasTable[index]
		table[bucketIndex].add(e);
		
		size++;	// Increase size
		
		return true;
	}
	
	@Override
	/** Remove the element from the set */
	public boolean remove(Object e) {	// I change E to Object
		if (!contains(e) ) {
			return false;
		}
		
		int bucketIndex = hash(e.hashCode());
		
		// Create a linked list for the bucket if not already created
		if (table[bucketIndex] != null) {
			LinkedList<E> bucket = table[bucketIndex];
			bucket.remove(e);
		}
		
		size--;	// Decrease size
		
		return true;
	}
	
	@Override
	/** Return true if set contains no elements */
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	/** Return the number of elements in the set */
	public int size() {
		return size;
	}
	
	@Override
	/** Return an iterator for the element in this set */
	public Iterator<E> iterator() {
		return new CoryCaddellHashSetIterator(this);
	}
	
	/** Inner class for iterator */
	private class CoryCaddellHashSetIterator implements Iterator<E> {
		// Store the elements in a list
		private ArrayList<E> list;
		private int current = 0;	// point to the current element in list
		
		private CoryCaddellHashSet<E> set;
		
		/** Create a list from the set */
		public CoryCaddellHashSetIterator(CoryCaddellHashSet<E> set) {
			this.set = set;
			list = setToList();
		}
		
		@Override
		/** Next element for traversing? */
		public boolean hasNext() {
			return current < list.size();
		}
		
		@Override
		/** Get current element and move cursor to the next */
		public E next() {
			return list.get(current++);
		}
		
		/** Remove the current element returned by the last next() */
		public void remove() {
			if (current > 0) {
				E e = list.get(current - 1);
				CoryCaddellHashSet.this.remove(e);	// remove from set
				list.remove(current - 1 );			// remove from list

				current--;
			}
		}
	}
	
	/** Hash function */
	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity -1);
	}
	
	/** Ensure the hashing is evenly distributed */
	private static int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h  >>> 7) ^ (h >>> 4);
	}
	
	/** Return a power of 2 for initialCapacity */
	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<=1;	// Same as capacity *= 2. <== is more efficient
		}
		
		return capacity;
	}
	
	/** Remove all e from each bucket */
	private void removeElements() {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				table[i].clear();
			}
		}
	}
	
	/** Rehash the set */
	private void rehash() {
		ArrayList<E> list = setToList();
		capacity <<= 1;	// Same as capcity *= 2. <= is more efficient
		table = new LinkedList[capacity];	// Create a new has table
		size = 0;
		
		for (E element: list) {
			add(element);	// Add from the old table to the new table
		}
	}
	
	/** Copy elements in the has set to an array list */
	private ArrayList<E> setToList() {
		ArrayList<E> list = new ArrayList<>();
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				for (E e: table[i]) {
					list.add(e);
				}
			}
		}
		
		return list;
	}
	
	@Override
	/** Return a string representation for this set */
	public String toString() {
		ArrayList<E> list = setToList();
		StringBuilder builder = new StringBuilder("[");
		
		// Add the elements except the last one to the string builder
		for (int i = 0; i < list.size() -1; i++) {
			builder.append(list.get(i) + ", ");
		}
		
		// Add the last element in the list to the string builder
		if (list.size() == 0) {
			builder.append("]");
		}
		else {
			builder.append(list.get(list.size() -1) + "]");
		}
		
		return builder.toString();
	}
	
	@Override
	/** Add each element of collection to set */
	public boolean addAll(Collection<? extends E> arg0) {
		
		if (arg0.size() > 0) {
			for(E e: arg0) {	// iterate through collection
				add(e);			// add each element of collection to set
			}
			return true;		// all of collection was added
		}
		else {
			return false;		// collection was empty
		}
	}
	
	@Override
	/** Return true if all elements of collection are in set */
	public boolean containsAll(Collection<?> arg0) {
		
		if(arg0.size() > 0) {	
			for (Object e: arg0) {	// iterate through collection
				if(!contains(e)) {	// if element in collection is not in set
					return false;	// return false
				}
			}
			return true;			// all elements of collection are in set
		}
		else {
			return false;			// collection is empty
		}
	}
	
	@Override
	/** Remove all elements of collection that are also in set */
	public boolean removeAll(Collection<?> arg0) {
		if (arg0.size() > 0) {
			for(Object e: arg0) {	// iterate through collection
				remove(e);			// remove element
			}
			return true;			// all elements in collection were removed from set
		}
		else {
			return false;			// collection is empty
		}
	}
	
	@Override
	/** Remove elements of set not in collection */ 
	public boolean retainAll(Collection<?> arg0) {
		ArrayList<E> temp = new ArrayList<>();	// temp array to store common elements
		
		if(arg0.size() > 0) {
			for (Object e: arg0) {		// iterate through collection
				if(contains(e)) {	
					temp.add((E) e);	// add to element of collection found in set to temp
				}
			}
			
			clear();					// remove all element of set
			addAll(temp);				// add all element of temp to set
			return true;				// success
		}
		else {
			return false;				// collection is empty
		}
	}
	
	@Override
	/** Return an array of Objects containing each element of the set */
	public Object[] toArray() {
		
		if(isEmpty()) {
			return null;		// set is empty
		}
		
		E[] newArray = (E[])new Object[size()];
		
		int i = 0;				// iteration counter
		for (E e: this) {		// cycle through tree
			newArray[i] = e;	// add elements to array
			i++;
		}
		
		return newArray;
	}
	
	@Override
	/** Return an array of T containing each element of the set */
	public <T> T[] toArray(T[] arg0) {
		
		if(isEmpty()) {
			return null;		// set is empty
		}
						
		int i = 0;				// iteration counter
		for (E e: this) {		// cycle through tree
			arg0[i] = (T)e;		// add elements to array
			i++;
		}
		
		return arg0;
	}
	
	/** Return a user-friendly string of set contents */
	public String CoryCaddellOutput() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("%-30s\n", "State/Territory")
					 + String.format("%-30s\n", "---------------"));
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null && table[i].size() > 0) {
				for (E e: table[i]) {
					builder.append(String.format("%-30s\n", e));
				}
			}
		}

		return builder.toString();	
	}
}

