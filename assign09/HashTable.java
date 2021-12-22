package assign09;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
/**
 * Represents a map of keys to values. It cannot contain duplicate keys, and
 * each key can map to at most one value.
 * 
 * @authors Cobi Toeun and Hongxuan Zhu
 *
 * @param <K> - the key
 * @param <V> - the value
 */
public class HashTable<K, V> implements Map<K, V> {

	// private instance variables
	private int size;
	private int arraySize;
	private ArrayList<LinkedList<MapEntry<K, V>>> entryArray = new ArrayList<LinkedList<MapEntry<K, V>>>();
	
	/**
	 * Constructor for hash table
	 */
	public HashTable() {
		int initialSize = 1000;
		this.arraySize = initialSize;
		for (int i = 0; i < initialSize; i++) {
			this.entryArray.add(new LinkedList<MapEntry<K, V>>());
		}
	}
	
	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length)
	 */
	@Override
	public void clear() {
		this.clearArray(1000);
	}
	
	/**
	 * Private helper method for clearing the array
	 * 
	 * @param newSize - the new size
	 */
	private void clearArray(int newSize) {
		this.size = 0;
		ArrayList<LinkedList<MapEntry<K, V>>> newEntryArray = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < newSize; i++) {
			newEntryArray.add(new LinkedList<MapEntry<K, V>>());
		}
		this.arraySize = newSize;
		this.entryArray = newEntryArray;
	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		if(this.get(key) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length)
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	@Override
	public boolean containsValue(V value) {
		ArrayList<V> newEntryArray = new ArrayList<V>();
		for (int i = 0; i < this.entryArray.size(); i++) {
			LinkedList<MapEntry<K, V>> tempLinkedList = entryArray.get(i);
			for (int j = 0; j < tempLinkedList.size(); j++) {
				newEntryArray.add(tempLinkedList.get(j).getValue());
			}
		}
		return newEntryArray.contains(value);
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * 
	 * O(table length)
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		ArrayList<MapEntry<K, V>> newEntryArray = new ArrayList<MapEntry<K, V>>();
		for (int i = 0; i < this.arraySize; i++) {
			LinkedList<MapEntry<K, V>> tempLinkedList = entryArray.get(i);
			for (int j = 0; j < tempLinkedList.size(); j++) {
				newEntryArray.add(tempLinkedList.get(j));
			}
		}
		return newEntryArray;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	@Override
	public V get(K key) {
		// get the index
		int index = this.hashFunction(key);
		// look into index and find item
		LinkedList<MapEntry<K, V>> targetLinkedList = entryArray.get(index);
		for (int j = 0; j < targetLinkedList.size(); j++) {
			if(targetLinkedList.get(j).getKey().equals(key)) {
				return targetLinkedList.get(j).getValue();
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value; 
	 * otherwise adds the specified key-value pair.)
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V put(K key, V value) {
		if (this.get(key) != null) {
			if (!this.get(key).equals(value)) {
				V tempValue = this.get(key);
				int index = hashFunction(key);
				LinkedList<MapEntry<K,V>> tempEntry = this.entryArray.get(index);
				int linkedIndex = 0;
				for(int i = 0; i < tempEntry.size(); i++) {
					if (!tempEntry.get(i).getKey().equals(key)) {
						linkedIndex++;
					}
				}
				tempEntry.remove(linkedIndex);
				tempEntry.addFirst(new MapEntry<K, V>(key, value));
				this.entryArray.set(index, tempEntry);
				return tempValue;
			}else {
				return this.get(key);
			}
		}
		// get index
		int index = hashFunction(key);
		// put the value into the array
		LinkedList<MapEntry<K,V>> tempEntry = this.entryArray.get(index);
		tempEntry.add(new MapEntry<K,V>(key, value));
		this.entryArray.set(index, tempEntry);
		// check increase
		checkIncrease();
		this.size++;
		
		return null;
	}
	
	/**
	 * Gathers the collisions from put method
	 * 
	 * Notice: Only set it to public when running the timer!
	 * 
	 * @param key   - the key
	 * @param value - the value
	 * @return - the amount of collisions
	 */
	@SuppressWarnings("unused")
	public int putColl(K key, V value) {
		// get index
		int index = hashFunction(key);
		// put the value into the array
		LinkedList<MapEntry<K,V>> tempEntry = this.entryArray.get(index);
		MapEntry<K,V> temp = new MapEntry<K,V>(key, value);
		tempEntry.add(temp);
		int coll = tempEntry.indexOf(temp);
		this.entryArray.set(index, tempEntry);
		// check increase
		checkIncrease();
		this.size++;
		return coll;
	}
	
	/**
	 * Checks if the lambda has been increased
	 */
	private void checkIncrease() {
		// check lambda
		double lambda = checkLambda();
		// if lambda is higher than 10
		if (lambda > 10) {
			// increase hash table
			extendMap();
		}
	}
	
	/**
	 * Gets the index from the hash function
	 */
	private int hashFunction(K key) {
		int size = this.entryArray.size();
		// get value from key
		int value = key.hashCode();
		// use value mod by size
		int index = value % size;

		return index;
	}
	
	/**
	 * Checks the lambda in the hash map
	 * 
	 * @return - the lambda
	 */
	private double checkLambda() {
		return this.size/this.entryArray.size();
	}
	
	/**
	 * Extends the hash map
	 * 
	 */
	private void extendMap() {
		// save all data into temp array
		ArrayList<MapEntry<K, V>> newEntryArray = (ArrayList<MapEntry<K, V>>) this.entries();
		// initialize the new entryArray with doubled size and all null, set this.entryArray to new entryArray
		this.clearArray(this.entryArray.size() * 2);
		// put all elements back to the new entryArray
		for(int i = 0; i < newEntryArray.size(); i++) {
			this.put(newEntryArray.get(i).getKey(), newEntryArray.get(i).getValue());
		}
	}
	
	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1)
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V remove(K key) {
		int index = this.hashFunction(key);
		LinkedList<MapEntry<K, V>> targetLinkedList = entryArray.get(index);
		for (int j = 0; j < targetLinkedList.size(); j++) {
			if(targetLinkedList.get(j).getKey().equals(key)) {
				V value = targetLinkedList.get(j).getValue();
				targetLinkedList.remove(j);
				this.size--;
				return value;
			}
		}
		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1)
	 * 
	 * @return the number of mappings in this map
	 */
	@Override
	public int size() {
		return this.size;
	}
	
}
