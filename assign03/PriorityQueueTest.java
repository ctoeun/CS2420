package assign03;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * This class tests the SimplePriorityQueue class
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
class PriorityQueueTest {

	@Test
	void testEmptySize() {
		SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>();
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

	@Test
	void testClear() {
		SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>((Integer a, Integer b) -> a - b);
		queue.insert(3);
		queue.insert(4);
		queue.insert(1);
		queue.insert(2);
		queue.clear();
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}
	
	@Test
	void testInsertAll() {
		ArrayList<Integer> list = new ArrayList<>();
		SimplePriorityQueue<Integer> queueList = new SimplePriorityQueue<Integer>((Integer a, Integer b) -> a - b);
		list.add(1);
		list.add(-4);
		list.add(5);
		list.add(10);
		list.add(-6);
		
		queueList.insertAll(list);
		assertEquals(5, queueList.size());
		assertEquals(-6, queueList.findMin());
		queueList.deleteMin();
		assertEquals(-4, queueList.findMin());
	}
	
	@Test
	void testInteger() {
		SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>((Integer a, Integer b) -> a - b);
		queue.insert(2);
		queue.insert(1);
		queue.insert(4);
		queue.insert(3);
		assertEquals(4, queue.size());
		assertEquals(1, queue.findMin(), 0);
		queue.deleteMin();
		assertEquals(2, queue.findMin(), 0);
		assertEquals(3, queue.size());
		queue.insert(4);
		assertEquals(2, queue.findMin(), 0);
		queue.insert(4);
		queue.insert(15);
		queue.insert(0);
		queue.insert(-5);
		queue.insert(-2);
		queue.insert(10);
		queue.insert(-4);
		queue.insert(20);
		assertEquals(12, queue.size());
	}
	
	@Test
	void testDouble() {
		SimplePriorityQueue<Double> doubleQueue = new SimplePriorityQueue<Double>((Double a, Double b) -> (int) (a - b));
		SimplePriorityQueue<Double> newDoubleQueue = new SimplePriorityQueue<Double>((Double a, Double b) -> (int) (a - b));
		doubleQueue.insert(2.0);
		doubleQueue.insert(1.0);
		doubleQueue.insert(4.0);
		doubleQueue.insert(3.0);
		assertEquals(4, doubleQueue.size());
		assertEquals(1.0, doubleQueue.findMin(), 0);
		doubleQueue.deleteMin();
		assertEquals(2.0, doubleQueue.findMin(), 0);
		assertEquals(3, doubleQueue.size());
		doubleQueue.insert(4.0);
		assertEquals(2.0, doubleQueue.findMin(), 0);
		doubleQueue.insert(4.0);
		doubleQueue.insert(15.0);
		doubleQueue.insert(0.0);
		doubleQueue.insert(-5.0);
		doubleQueue.insert(-2.0);
		doubleQueue.insert(10.0);
		doubleQueue.insert(-4.0);
		doubleQueue.insert(20.0);
		assertEquals(12, doubleQueue.size());
		
	}
	
	@Test
	void testStrings() {
		SimplePriorityQueue<String> stringsQueue = new SimplePriorityQueue<String>((String a, String b) -> a.length() - b.length());
		stringsQueue.insert("Wassup");
		stringsQueue.insert("Hi");
		stringsQueue.insert("Bye");
		stringsQueue.insert("Later");
		assertEquals(4, stringsQueue.size());
		assertEquals("Hi", stringsQueue.findMin());
		stringsQueue.deleteMin();
		assertEquals(3, stringsQueue.size());
		assertEquals("Bye", stringsQueue.findMin());
		stringsQueue.clear();
		assertEquals(0, stringsQueue.size());
	}
	
	@Test
	void testException() {
		SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>();
		assertTrue(queue.isEmpty());
		assertThrows(NoSuchElementException.class, () -> queue.findMin());
		assertThrows(NoSuchElementException.class, () -> queue.deleteMin());
	}

}
