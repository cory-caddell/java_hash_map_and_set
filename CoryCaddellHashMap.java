// CoryCaddellHashMap.java

import java.util.*;

public class CoryCaddellHashMap<K, V> implements CoryCaddellMap<K, V> {
	
	// Define default has-table size.  Must be a power of 2
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	
	// Define maximum has-table size. 1 << 30 is the same as 2 ^ 30
	private static int MAXIMUM_CAPACITY = 1 << 30;
	
	// Current hash-table capacity.  Capacity is a power of 2
	private int capacity;
	
	// Define default load factor
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
	
	// Specify a load factor used in the has table
	private float loadFactorThreshold;
	
	// Number of entries in map
	private int size = 0;
	
	// Has table is an array with each cell being a linked list
	LinkedList<CoryCaddellMap.Entry<K,V>>[] table;
	
	/** Construct a map default capacity and load factor */
	public CoryCaddellHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/** Construct a map with specified initial capacity and default load factor */
	public CoryCaddellHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/** Construct a map with specified initial capacity and load factor */
	public CoryCaddellHashMap(int initialCapacity, float loadFactorThreshold) {
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
	/** Remove all entries from this map */
	public void clear() {
		size = 0;
		removeEntries();
	}
	
	@Override
	/** Return true if specified key is in map */
	public boolean containsKey(K key) {
		if (get(key) != null) {
			return true;
		}
		else {
			return false;
		}
	}	
		
	@Override
	/** Return true if map contains value */
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K,V>> bucket = table[i];
				for (Entry<K, V> entry: bucket) {
					if (entry.getValue().equals(value)) {
						return true;
					}
				}
			}
		}	
		return false;
	}
	
	@Override
	/** Return a set of entries in the map */
	public Set<CoryCaddellMap.Entry<K,V>> entrySet() {
		Set<CoryCaddellMap.Entry<K,V>> set = new HashSet<>();
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K,V>> bucket = table[i];
				for (Entry<K, V> entry: bucket) {
					set.add(entry);
				}
			}
		}
		
		return set;
	}
	
	@Override
	/** Return the value that matches the specified key */
	public V get(K key) {
		int bucketIndex = hash(key.hashCode());
		if (table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for (Entry<K, V> entry: bucket) {
				if (entry.getKey().equals(key)) {
					return entry.getValue();
				}
			}
		}
		
		return null;
	}
	
	@Override
	/** Return true if this map contains no entries */
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	/** Return a set consisting of keys in this map */
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K,V>> bucket = table[i];
				for (Entry<K, V> entry: bucket) {
					set.add(entry.getKey());
				}
			}
		}
		return set;
	}
	
	@Override
	/** Add an entry (key, value) into the map */
	public V put(K key, V value) {
		int bucketIndex = hash(key.hashCode());
		
		if (get(key) != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for (Entry<K, V> entry: bucket) {
				if (entry.getKey().equals(key)) {
					V oldValue = entry.getValue();
					entry.value = value;
					
					return oldValue;
				}
			}
		}
		
		// Check load factor
		if (size >= capacity * loadFactorThreshold) {
			if (capacity == MAXIMUM_CAPACITY) {
				throw new RuntimeException("Exceeding maximum capacity");
			}
			rehash();
		}
		
		// Create a linked list for the bucket if not alredy created
		if (table[bucketIndex] == null) {
			table[bucketIndex] = new LinkedList<Entry<K, V>>();
		}
		
		// Add a new entry (key, value) to hashTable[index]
		table[bucketIndex].add(new CoryCaddellMap.Entry<K, V>(key, value));
		
		size++;
		
		return value;
	}
	
	@Override
	/** Remove the entries for the specified key */
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());
		
		// Remove first entry that matches the key from a bucket
		if (table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for (Entry<K, V> entry: bucket) {
				if(entry.getKey().equals(key)) {
				bucket.remove(entry);
				size--;	// Decrease size
				break;	// Remove just one entry that matches the key
				}
			}
		}
	}
	
	@Override
	/** Return the number of entries in this map */
	public int size() {
		return size;
	}
	
	@Override
	/** Return a set consisting of value in this map */
	public Set<V> values() {
		Set<V> set = new HashSet<>();
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for (Entry<K, V> entry: bucket) {
					set.add(entry.getValue());
				}
			}
		}
		
		return set;
	}
	
	/** Hash function */
	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity -1);
	}
	
	/** Ensure the hashing is evenly distributed */
	private static int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	/** Return a power of 2 for initialCapacity */
	private int trimToPowerOf2( int initialCapacity ) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<=1;	// Same as capacity *= 2. <= is more efficient
		}
		
		return capacity;
	}
	
	/** Remove all entries from each bucket */
	private void removeEntries() {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				table[i].clear();
			}
		}
	}
	
	/** Rehash the map */
	private void rehash() {
		Set<Entry<K, V>> set = entrySet();
		capacity <<= 1;	// Same as capacity *= 2. <= is more efficient
		
		table = new LinkedList[capacity];	// Create a new has table
		size = 0; // Reset size to 0
		
		for (Entry<K, V> entry: set) {
			put(entry.getKey(), entry.getValue());	// Store to new table
		}
	}
	
	@Override
	/** Return a string representation for this map */
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null && table[i].size() > 0) {
				for (Entry<K, V> entry: table[i]) {
					builder.append(entry);
				}
			}
		}
		
		builder.append("]");
		return builder.toString();
	}
	
	/** Return a user-friendly string of map contents */
	public String CoryCaddellOutput() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("%-30s%s\n", "State/Territory\"", "Total Population")
					 + String.format("%-30s%s\n", "---------------", "----------------"));
		
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null && table[i].size() > 0) {
				for (Entry<K, V> entry: table[i]) {
					builder.append(String.format("%-30s%,d\n", entry.getKey(), entry.getValue()));
				}
			}
		}

		return builder.toString();	
	}	
}
