package assign06;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * 
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
class LinkedListStackTest {

	@Test
	void testIsEmpty() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		assertTrue(list.isEmpty());
	}
	
	@Test
	void testSize() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		assertEquals(list.size(), 0);
		list.push(2);
		list.push(5);
		list.push(4);
		list.push(6);
		assertEquals(list.size(), 4);
	}
	
	@Test
	void testClear() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		assertEquals(list.size(), 0);
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);
		assertEquals(list.size(), 4);
		list.clear();
		assertEquals(list.size(), 0);
		assertTrue(list.isEmpty());
	}
	
	@Test
	void testPeek() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		list.push(2);
		list.push(5);
		list.push(4);
		list.push(6);
		list.push(1);
		assertEquals(list.peek(), 1);
	}
	
	@Test
	void testPeekException() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		assertTrue(list.isEmpty());
		assertThrows(NoSuchElementException.class, () -> list.peek());
	}
	
	@Test
	void testPop() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		list.push(2);
		list.push(5);
		list.push(4);
		list.push(6);
		list.push(1);
		list.pop();
		assertEquals(list.peek(), 6);
		list.pop();
		assertEquals(list.peek(), 4);
	}
	
	@Test
	void testPopException() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		assertTrue(list.isEmpty());
		assertThrows(NoSuchElementException.class, () -> list.pop());
	}
	
	@Test
	void testPush() {
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		list.push(2);
		list.push(3);
		list.push(10);
		assertFalse(list.peek().equals(3));
		assertEquals(list.peek(), 10);
	}

}
