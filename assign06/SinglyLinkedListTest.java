package assign06;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * This class tests the singly linked list class
 * 
 * @authors Cobi Toeun and Hongxuan Zhu
 *
 */
class SinglyLinkedListTest {

	@Test
	void testIsEmpty() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		assertTrue(list.isEmpty());
	}

	@Test
	void testSize() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		assertEquals(list.size(), 0);
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(0, 6);
		assertEquals(list.size(), 4);
	}

	@Test
	void testClear() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		assertEquals(list.size(), 0);
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(0, 6);
		assertEquals(list.size(), 4);
		list.clear();
		assertEquals(list.size(), 0);
		assertTrue(list.isEmpty());

	}

	@Test
	void testToArray() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(3, 6);
		list.insertFirst(1);
		assertEquals(Arrays.toString(list.toArray()), "[1, 5, 2, 4, 6]");
	}

	@Test
	void testInsertFirst() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(2);
		list.insertFirst(3);
		assertEquals(list.get(0), 3);
		list.insertFirst(10);
		assertFalse(list.get(0).equals(3));
		assertEquals(list.get(0), 10);
	}

	@Test
	void testInsert() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(0, 6);
		assertEquals(list.get(0), 6);
		assertEquals(list.get(1), 5);
		assertEquals(list.get(2), 2);
		assertEquals(list.get(3), 4);
	}

	@Test
	void testInsertException() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insert(1, 3);
		assertThrows(IndexOutOfBoundsException.class, () -> list.insert(3, 4));
		assertThrows(IndexOutOfBoundsException.class, () -> list.insert(-1, 1));
	}

	@Test
	void testGetFirst() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(3, 6);
		list.insertFirst(1);
		assertEquals(list.getFirst(), 1);
	}

	@Test
	void testGetFirstException() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		assertTrue(list.isEmpty());
		assertThrows(NoSuchElementException.class, () -> list.getFirst());
	}

	@Test
	void testGet() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(0, 6);
		list.insertFirst(10);
		list.insert(5, 11);
		assertEquals(list.get(0), 10);
		assertEquals(list.get(1), 6);
		assertEquals(list.get(2), 5);
		assertEquals(list.get(3), 2);
		assertEquals(list.get(4), 4);
		assertEquals(list.get(5), 11);
	}

	@Test
	void testDeleteFirst() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(3, 6);
		list.insertFirst(1);
		list.deleteFirst();
		assertEquals(list.getFirst(), 5);
		list.deleteFirst();
		assertEquals(list.getFirst(), 2);
	}
	
	@Test
	void testDeleteFirstException() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		assertTrue(list.isEmpty());
		assertThrows(NoSuchElementException.class, () -> list.deleteFirst());
	}

	@Test
	void testDelete() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2); // 1 5 2 4 6 7 8
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(3, 6);
		list.insertFirst(1);
		list.insert(5, 7);
		list.insert(6, 8);
		list.delete(3);
		list.delete(0);
		assertEquals(list.get(0), 5);
		assertEquals(list.get(1), 2);
		assertEquals(list.get(2), 6);
		assertEquals(list.get(3), 7);
		assertEquals(list.get(4), 8);
	}

	
	
	@Test
	void testIndexOf() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insert(0, 2);
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(3, 6);
		list.insertFirst(1);
		assertEquals(list.indexOf(0), -1);
		assertEquals(list.indexOf(1), 0);
		assertEquals(list.indexOf(5), 1);
		assertEquals(list.indexOf(2), 2);
		assertEquals(list.indexOf(10), -1);
		assertEquals(list.indexOf(4), 3);
		assertEquals(list.indexOf(6), 4);
		assertEquals(list.indexOf(3), -1);
	}

	@Test
	void testIterator() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		Iterator<Integer> it = list.iterator();
		assertFalse(it.hasNext());
		list.insert(0, 2); // 1 5 2 4 6
		list.insertFirst(5);
		list.insert(2, 4);
		list.insert(3, 6);
		list.insertFirst(1);
		assertThrows(IllegalStateException.class, () -> it.remove());
		assertTrue(it.hasNext());
		assertEquals(it.next(), 1);
		it.remove();
		assertEquals(it.next(), 5);
		it.next();
		it.next();
		it.next();
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertFalse(it.hasNext());

	}
}
