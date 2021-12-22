package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a generic binary heap and is backed by a basic array (Implements
 * the priority queue interface).
 * 
 * @authors Cobi Toeun & Hongxuan Zhu
 *
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	// private instance variables
	private E[] backingArray;
	private int size;
	Comparator<? super E> cmp;

	/**
	 * Constructor used to create empty binary heap.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		backingArray = (E[]) new Object[10];
		size = 0;
		cmp = null;
	}

	/**
	 * Constructor used to create empty binary heap. Elements are ordered using
	 * Comparator object.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		backingArray = (E[]) new Object[10];
		size = 0;
		this.cmp = cmp;
	}

	/**
	 * Constructor used to construct binary heap from given list. Elements are used
	 * using their natural ordering.
	 * 
	 * @param inputList - the inputed list of elements
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> inputList) {
		this.backingArray = (E[]) inputList.toArray();
		this.size = this.backingArray.length;
		buildHeap();
	}

	/**
	 * Constructor used to construct binary heap from given list. Elements are used
	 * using the Comparator object.
	 * 
	 * @param inputList - the inputed list of elements
	 * @param cmp       - the comparator defining how to compare items
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> inputList, Comparator<? super E> cmp) {
		this.backingArray = (E[]) inputList.toArray();
		this.cmp = cmp;
		this.size = this.backingArray.length;
		buildHeap();
	}

	/**
	 * Builds the binary heap
	 * 
	 */
	private void buildHeap() {
		int trackIndex = this.getParent(size);
		while (true) {
			this.percolateDown(trackIndex);
			if (this.getParent(trackIndex) == null) {
				break;
			}
			trackIndex--;
		}
	}

	/**
	 * Percolates the elements in the binary heap
	 * 
	 * @param index - the provided index in the heap
	 */
	private void percolateDown(int index) {
		// if left or right child index is null do nothing
		if (this.getLeft(index) == null && this.getRight(index) == null) {
			return;
		}
		if (this.getLeft(index) != null && this.getRight(index) == null) {
			// check if index is smaller than left child index and swap
			if (this.compare(this.backingArray[index], this.backingArray[this.getLeft(index)]) < 0) {
				this.swap(index, this.getLeft(index));
			}
			return;
		}
		if (this.getLeft(index) == null && this.getRight(index) != null) {
			// check if index is smaller than right child index and swap
			if (this.compare(this.backingArray[index], this.backingArray[this.getRight(index)]) < 0) {
				this.swap(index, this.getRight(index));
			}
			return;
		}
		// if the index is smaller than the left index swap else swap the right
		if ((this.compare(this.backingArray[this.getLeft(index)], this.backingArray[this.getRight(index)]) > 0)
				&& (this.compare(this.backingArray[index], this.backingArray[this.getLeft(index)]) < 0)) {
			this.swap(index, this.getLeft(index));
			// index = this.getLeft(index);
			Integer nextIndex = this.getLeft(index);
			percolateDown(nextIndex);
		} else if ((this.compare(this.backingArray[this.getLeft(index)], this.backingArray[this.getRight(index)]) < 0)
				&& (this.compare(this.backingArray[index], this.backingArray[this.getRight(index)]) < 0)) {
			this.swap(index, this.getRight(index));
			// index = this.getRight(index);
			Integer nextIndex = this.getRight(index);
			percolateDown(nextIndex);
		}
	}

	/**
	 * Adds the given item to this priority queue. O(1) in the average case, O(log
	 * N) in the worst case
	 * 
	 * @param item - the item to be added in the list
	 */
	@Override
	public void add(E item) {
		if (this.size == 0) {
			this.backingArray[0] = item;
			this.size++;
			return;
		}
		// increase the array if the size is greater than the backing array
		if (this.size + 10 >= this.backingArray.length) {
			this.increaseArray();
		}
		// look for the end index
		int targetIndex = this.size;
		// put the item in the end index
		this.backingArray[targetIndex] = item;

		// loop while item is greater than its parent
		while (this.compare(item, this.backingArray[this.getParent(targetIndex)]) > 0) {
			// compare and swap with the parent
			this.swap(targetIndex, this.getParent(targetIndex));
			targetIndex = this.getParent(targetIndex);
			if (this.getParent(targetIndex) == null) {
				break;
			}
		}
		// increment size
		this.size++;
	}

	/**
	 * Increases the array for the binary heap
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void increaseArray() {
		int arraySize = this.backingArray.length * 2; // double the array size
		E[] newArray = (E[]) new Object[arraySize];
		for (int i = 0; i < this.backingArray.length; i++) {
			// add the elements of the current array into new array
			newArray[i] = this.backingArray[i];
		}
		this.backingArray = newArray;
	}

	/**
	 * Swaps the indexes
	 * 
	 * @param lIndex - the left index
	 * @param rIndex - the right index
	 */
	private void swap(int lIndex, int rIndex) {
		// set variables of left and right and index
		E lValue = this.backingArray[lIndex];
		E rValue = this.backingArray[rIndex];

		// swap indexes
		this.backingArray[lIndex] = rValue;
		this.backingArray[rIndex] = lValue;
	}

	/**
	 * Compares the left and right objects
	 * 
	 * @param left  - the left object
	 * @param right - the right object
	 * @return - returns using the comparable or comparator object
	 */
	@SuppressWarnings("unchecked")
	private int compare(E left, E right) {
		if (cmp == null) {
			return ((Comparable<? super E>) left).compareTo(right); // use comparable
		}
		return this.cmp.compare(left, right); // use comparator
	}

	/**
	 * Gets the left child of the parent
	 * 
	 * @param parentIndex - the parent's index
	 * @return - the left child's index
	 */
	private Integer getLeft(int parentIndex) {
		int childIndex = parentIndex * 2 + 1;
		if (childIndex >= this.size) {
			return null;
		}
		return childIndex;
	}

	/**
	 * Gets the right child of the parent
	 * 
	 * @param parentIndex - the parent's index
	 * @return - the right child's index
	 */
	private Integer getRight(int parentIndex) {
		int childIndex = (parentIndex + 1) * 2;
		if (childIndex >= this.size) {
			return null;
		}
		return childIndex;
	}

	/**
	 * Gets the parent of a child
	 * 
	 * @param childIndex - the child's index
	 * @return - the parent of the child
	 */
	private Integer getParent(int childIndex) {
		int parentIndex = Math.round((childIndex - 1) / 2);
		if (childIndex == 0) {
			return null;
		}
		return parentIndex;
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue. O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.backingArray[0];
	}

	/**
	 * Returns and removes the maximum item this priority queue. O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		E element = this.peek();
		this.removeFirst();
		return element;
	}

	/**
	 * Removes the first element in the binary heap
	 */
	private void removeFirst() {
		// set variables
		int trackIndex = 0;
		this.backingArray[trackIndex] = null;

		if (this.getLeft(trackIndex) != null && this.getRight(trackIndex) == null) {
			if (this.compare(this.backingArray[trackIndex], this.backingArray[this.getLeft(trackIndex)]) < 0) {
				this.swap(trackIndex, this.getLeft(trackIndex));
			}
			return;
		}

		// compare if left child is greater than right index
		if (this.compare(this.backingArray[this.getLeft(trackIndex)],
				this.backingArray[this.getRight(trackIndex)]) > 0) {
			// while there are still elements in the heap
			while (this.getLeft(trackIndex) != null) {
				// swap left index with the track index
				int nextIndex = trackIndex;
				if (this.getRight(trackIndex) == null || this.compare(this.backingArray[this.getLeft(trackIndex)],
						this.backingArray[this.getRight(trackIndex)]) > 0) {
					nextIndex = this.getLeft(trackIndex);
				} else {
					nextIndex = this.getRight(trackIndex);
				}
				this.swap(trackIndex, nextIndex);
				trackIndex = nextIndex;
			}
			// swap track index with the last index
			this.swap(trackIndex, this.size - 1);
			if (trackIndex == this.size - 1) {
				this.size--;
				return;
			}
			// loop while the trackIndex position is greater than its parent's index
			while (this.compare(this.backingArray[trackIndex], this.backingArray[this.getParent(trackIndex)]) > 0) {
				// swap the track index with the parent index
				this.swap(trackIndex, this.getParent(trackIndex));
			}
		} else {
			// while there are still elements in the heap
			while (this.getRight(trackIndex) != null) {
				// swap the right index with the track index
				int nextIndex = trackIndex;
				if (this.getRight(trackIndex) == null || this.compare(this.backingArray[this.getLeft(trackIndex)],
						this.backingArray[this.getRight(trackIndex)]) > 0) {
					nextIndex = this.getLeft(trackIndex);
				} else {
					nextIndex = this.getRight(trackIndex);
				}
				this.swap(trackIndex, nextIndex);
				trackIndex = nextIndex;
			}
			// swap the trackIndex with the last element in the heap
			this.swap(trackIndex, this.size - 1);
			if (trackIndex == this.size - 1) {
				this.size--;
				return;
			}
			// loop while the trackIndex position is greater than its parent's index
			while (this.compare(this.backingArray[trackIndex], this.backingArray[this.getParent(trackIndex)]) > 0) {
				// swap the track index with the parent index
				this.swap(trackIndex, this.getParent(trackIndex));
			}
		}
		// decrement the size
		this.size--;
	}

	/**
	 * Returns the number of items in this priority queue. O(1)
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise. O(1)
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Empties this priority queue of items. O(1)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.backingArray = (E[]) new Object[0];
		this.size = 0;
	}

	/**
	 * Creates and returns an array of the items in this priority queue, in the same
	 * order they appear in the backing array. O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item must be
	 * stored at index 0 in the returned array, regardless of whether it is in
	 * stored there in the backing array.)
	 */
	@Override
	public Object[] toArray() {
		// Create new array with same size as backing array
		Object[] newArray = new Object[size];

		// loop and store backing array elements into new array
		for (int i = 0; i < size; i++) {
			newArray[i] = this.backingArray[i];
		}

		return newArray;
	}

}
