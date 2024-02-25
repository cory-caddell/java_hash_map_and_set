// CoryCaddellMap.java

import java.util.*;

public interface CoryCaddellMap<K,V> {
	
	/** Remove all entries from this map */
	public void clear();
	
	/** Return true if specified key is in this map */
	public boolean containsKey(K key);
	
	/** Return true if this map contains specified value */
	public boolean containsValue(V value);
	
	/** Return a set of entries in this map */
	public Set<Entry<K,V>> entrySet();
	
	/** Return value that matches the specified key */
	public V get (K key);
	
	/** Return true if this map doesn't contain any entries */
	public boolean isEmpty();
	
	/** Return a set consisting of keys in this map */
	public Set<K> keySet();
	
	/** Add an entry (key, value) into the map */
	public V put(K key, V value);
	
	/** Remove an entry for the specified key */
	public void remove(K key);
	
	/** Return the number of mapping in this map */
	public int size();
	
	/** Return a set consisting of values in this map */
	public Set<V> values();
	
	/** Define an inner class for Entry */
	public static class Entry<K, V> {
		K key;
		V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return "[" + key + ", " + value + "]";
		}
	}	
}
