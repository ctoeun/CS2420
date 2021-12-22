package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class implements the PriorityQueue interface and creates a priority
 * queue that supports access of the minimum element only.
 *
 * @author Zhu, Hongxuan; Cobi Toeun
 *
 * @param <E> the type of elements contained in this priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

	private E[] array;
	private int size = 0;
	private Comparator<? super E> cmp;

	/**
	 * the constructor creates a empty natural ordered SimplePriorityQueue
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {

		array = (E[]) new Object[10];
		this.cmp = null;
	}

	/**
	 * the constructor creates an empty descending ordered SimplePriorityQueue
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {

		array = (E[]) new Object[10];
		this.cmp = cmp;
	}

	/**
	 * this method retrieves, but does not remove, the minimum element in this
	 * priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E findMin() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.array[size - 1];
	}

	/**
	 * Retrieves and removes the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E deleteMin() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		E min = findMin();
		this.array[this.size - 1] = null;
		this.size--;
		return min;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@Override
	public void insert(E item) {
		if (this.array.length == this.size + 1) {
			this.array = extendArray(array);
		}

		if (this.size == 0) {
			this.array[0] = item;
			this.size++;
		} else {
			insertToPosition(item);
		}
	}

	/**
	 * this method insert the given item, compare it with the existed element inside
	 * this.array and put it into the correct position
	 * 
	 * @param item the object that going to be put into the correct position
	 */
	private void insertToPosition(E item) {
		int lowerIndex = 0;
		int higherIndex = this.size - 1;

		while (lowerIndex < higherIndex - 1) {
			int middleIndex = (int) ((higherIndex + lowerIndex) / 2);
			if (this.compare(item, this.array[middleIndex]) > 0) {
				higherIndex = middleIndex;
			} else {
				lowerIndex = middleIndex;
			}
		}

		if (this.compare(item, this.array[higherIndex]) <= 0) {
			this.insertObj(higherIndex + 1, item);
		} else if (this.compare(item, this.array[lowerIndex]) <= 0) {
			this.insertObj(lowerIndex + 1, item);
		} else if (this.compare(item, this.array[lowerIndex]) > 0) {
			this.insertObj(lowerIndex, item);
		}
	}

	/**
	 * this method is to shift the element(s) after the given index, then put the
	 * object into the given index position
	 * 
	 * @param inputIndex the index used to put the item into the correct position
	 * @param item       the object that going to be put into the correct position
	 */
	private void insertObj(int inputIndex, E item) {
		for (int i = this.size; i >= inputIndex; i--) {
			this.array[i + 1] = this.array[i];
		}
		this.array[inputIndex] = item;
		this.size++;
	}

	/**
	 * This method compares two objects.
	 * 
	 * @param item  one item engaged into the comparison
	 * @param other another item engaged into the comparison
	 * @return the compare result
	 */
	@SuppressWarnings("unchecked")
	private int compare(E item, E other) {
		if (cmp == null) {
			return ((Comparable<? super E>) item).compareTo(other);
		}
		return this.cmp.compare(item, other);
	}

	/**
	 * This method extends the array.
	 * 
	 * @param inputArray is the original array
	 * @return the extended new array
	 */
	@SuppressWarnings("unchecked")
	private E[] extendArray(E[] inputArray) {
		E[] newArray;
		int targetSize = inputArray.length * 2;

		// Doubles the array in size
		newArray = (E[]) new Object[targetSize];

		for (int i = 0; i < inputArray.length; i++) {
			newArray[i] = inputArray[i];
		}

		return newArray;

	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E item : coll) {
			this.insert(item);
		}
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be empty
	 * when this call returns.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		array = (E[]) new Object[10];
		this.size = 0;
	}

}
