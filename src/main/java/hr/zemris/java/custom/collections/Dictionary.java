package hr.zemris.java.custom.collections;

import java.util.Objects;

/**
 * A custom implementation of the Map data structure. A map consists of multiple
 * entries, and the entries are essentially keys mapped to their values. 
 * @author 0036502252
 *
 */
public class Dictionary {
	/**
	 * The collection used to store the entries.
	 */
	private ArrayIndexedCollection col;	
	
	/**
	 * Represents a single entry in a map.
	 * @author 0036502252
	 *
	 */
	private static class Entry{
		/**
		 * Value of the entry.
		 */
		private Object value;
		/**
		 * Key of the entry. Each entry value is mapped to a unique key.
		 */
		private Object key;
		
		/**
		 * Creates a single entry. Null values are allowed for the entry, but 
		 * not for the key.
		 * @param value the value of the new entry
		 * @param key the key of the new entry
		 */
		public Entry(Object key, Object value) {
			this.value = value;
			this.key = Objects.requireNonNull(key);
		}
		
		/**
		 * Gets the key of the entry.
		 * @return the key of the entry
		 */
		public Object getKey() {
			return key;
		}
		
		/**
		 * Gets the value of the entry.
		 * @return the value of the entry
		 */
		public Object getValue() {
			return value;
		}
		
		/**
		 * Sets the value of the given entry.
		 * @param value the value to be set
		 */
		public void setValue(Object value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "Key: " + key + " Value: "+ value; 
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Entry))
				return false;
			Entry other = (Entry) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
		
	}
	
	/**
	 * Creates a new empty dictionary.
	 */
	public Dictionary() {
		this.col = new ArrayIndexedCollection();		
	}
	
	/**
	 * Checks if the dictionary is empty.
	 * @return true if the dictionary is empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.col.size() == 0;
	}
	
	/**
	 * Gets the size of the dictionary.
	 * @return the dictionary's size
	 */
	public int size() {
		return this.col.size();
	}
	
	/**
	 * Clears the dictionary of its entries.
	 */
	public void clear() {
		this.col.clear();
	}
	
	/**
	 * Puts the entry in the dictionary at the given key.
	 * @param key the key
	 * @param value the value to be assigned to the key
	 * @throws NullPointerException if the key is null
	 */
	public void put(Object key, Object value) {
		if(key == null) throw new NullPointerException("Key can't be a null value!");
		
		Entry entry = (Entry) this.getEntry(key);
		
		if(entry != null) {
			entry.setValue(value);
		} else {
			this.col.add(new Entry(key, value));		
		}
	}
	
	/**
	 * Gets the value of the entry at the given key.
	 * @param key the key of the desired object
	 * @return the object mapped by the given key, or null if the key is not 
	 * present in the dictionary.
	 * @throws NullPointerException if the key is null
	 */
	public Object get(Object key) {
		if(key == null) throw new NullPointerException("Key can't be a null value!");
		
		for(int i = 0, n = size(); i < n; i++) {
			Entry currentEntry = (Entry) this.col.get(i);
			if(key.equals(currentEntry.getKey())) {
				return currentEntry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Private utility method. Gets the dictionary entry at the given key.
	 * @param key the key of the desired object
	 * @return the entry mapped by the given key, or null if the key is not 
	 * present in the dictionary.
	 * @throws NullPointerException if the key is null
	 */
	private Object getEntry(Object key) {
		if(key == null) throw new NullPointerException("Key can't be a null value!");
		
		for(int i = 0, n = size(); i < n; i++) {
			Entry currentEntry = (Entry) this.col.get(i);
			if(key.equals(currentEntry.getKey())) {
				return currentEntry;
			}
		}
		return null;
	}

}
