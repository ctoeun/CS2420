package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This generic class represents a singly linked list and implements the list
 * interface
 * 
 * @authors Cobi Toeun and Hongxuan Zhu
 *
 * @param <E>
 */
public class SinglyLinkedList<E> implements List<E> {
	private int size;
	private Node head;

	/**
	 * 
	 * @authors Cobi Toeun and Hongxuan Zhu
	 *
	 */
	private class Node {
		public E data;
		public Node next;

		public Node(E arr, Node next) {
			this.data = arr;
			this.next = next;
		}
	}

	/**
	 * 
	 */
	public SinglyLinkedList() {
		size = 0;
		head = null;
	}

	/**
	 * Inserts an element at the beginning of the list. O(1) for a singly-linked
	 * list.
	 * 
	 * @param arr - the element to add
	 */
	@Override
	public void insertFirst(E arr) {
		head = new Node(arr, head);
		size++;

	}

	/**
	 * Inserts an element at a specific position in the list. O(N) for a
	 * singly-linked list.
	 * 
	 * @param index   - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index > size())
	 */
	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if (index == 0) {
			insertFirst(element);
		} else {
			Node preNode = getNode(index - 1);
			insertion(element, preNode);
		}

	}

	/**
	 * 
	 * @param element
	 * @param preNode
	 */
	private void insertion(E element, Node preNode) {
		preNode.next = new Node(element, preNode.next);
		size++;
	}

	/**
	 * Gets the first element in the list. O(1) for a singly-linked list.
	 * 
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("List is empty");
		}
		return head.data;
	}

	/**
	 * Gets the element at a specific position in the list. O(N) for a singly-linked
	 * list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		return getNode(index).data;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is out of range");
		}

		Node temp = head;

		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * Deletes and returns the first element from the list. O(1) for a singly-linked
	 * list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E deleteFirst() throws NoSuchElementException {
		E firstItem = getFirst();
		head = head.next;
		size--;
		return firstItem;
	}

	/**
	 * Deletes and returns the element at a specific position in the list. O(N) for
	 * a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		E element = null;
		if (index == 0) {
			element = this.deleteFirst();
		} else {
			Node preNode = this.getNode(index-1);
			element = preNode.next.data;
			preNode.next = preNode.next.next;
			size--;
		}
		return element;
	}
	
	/**
	 * Determines the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * singly-linked list.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	@Override
	public int indexOf(E element) {
		Node tempNode = head;

		for (int i = 0; i < size; i++) {
			if (tempNode.data.equals(element)) {
				return i;
			}
			tempNode = tempNode.next;
		}
		return -1;
	}

	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return true if this collection contains no elements; false, otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a singly-linked list.
	 */
	@Override
	public void clear() {
		head = null; // set first element to null
		size = 0;

	}

	/**
	 * Generates an array containing all of the elements in this list in proper
	 * sequence (from first element to last element). O(N) for a singly-linked list.
	 * 
	 * @return an array containing all of the elements in this list, in order
	 */
	@Override
	public Object[] toArray() {
		Node tempNode = head;

		Object[] array = new Object[size()];

		for (int i = 0; i < size; i++) {
			array[i] = tempNode.data;
			tempNode = tempNode.next;
		}
		return array;
	}

	/**
	 * @return an iterator over the elements in this list in proper sequence (from
	 *         first element to last element)
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> returnValue = new Iterator<E>() {
			int index = -1;
			boolean nextIsCalled;

			/**
			 * 
			 */
			@Override
			public boolean hasNext() {
				if (index + 2 <= size) {
					return true;
				} else {
					return false;
				}
			}

			/**
			 * 
			 */
			@Override
			public E next() throws NoSuchElementException {
				if (hasNext()) {
					index++;
					E element = get(index);
					nextIsCalled = true;
					return element;
				} else {
					throw new NoSuchElementException("There are no more elements");
				}
			}

			/**
			 * 
			 */
			@Override
			public void remove() throws IllegalStateException {
				if (!nextIsCalled) {
					throw new IllegalStateException("Next has not been called");
				} else {
					delete(index);
					index--;
					nextIsCalled = false;
				}
			}
		};
		return returnValue;
	}

}
