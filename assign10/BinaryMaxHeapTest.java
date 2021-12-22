package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * This class tests the BinaryMaxHeap class
 * 
 * @authors Cobi Toeun & Hongxuan Zhu
 *
 */
class BinaryMaxHeapTest {

	@Test
	void testAddPeekAndExtractMax() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		heap.add(4);
		heap.add(3);
		heap.add(10);
		assertEquals(heap.peek(), 10);
		heap.add(5);
		heap.add(12);
		assertEquals(heap.peek(), 12);
		heap.extractMax();
		assertEquals(heap.peek(), 10);
	}

	@Test
	void testLargerAddPeekAndExtractMax() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		for (int i = 0; i < 100; i++) {
			heap.add(i);
		}

		assertEquals(heap.peek(), 99);
		heap.extractMax();
		assertEquals(heap.peek(), 98);
		heap.extractMax();
		heap.extractMax();
		heap.extractMax();
		assertEquals(heap.peek(), 95);

	}

	@Test
	void testPeekThrows() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		assertThrows(NoSuchElementException.class, () -> heap.peek());
	}

	@Test
	void testExtractMaxThrows() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		assertThrows(NoSuchElementException.class, () -> heap.extractMax());
	}

	@Test
	void testSize() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		assertEquals(heap.size(), 0);
		heap.add(1);
		heap.add(20);
		heap.add(50);
		heap.add(24);
		assertEquals(heap.size(), 4);
	}

	@Test
	void testLargerSize() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		for (int i = 0; i < 100; i++) {
			heap.add(i);
		}

		assertEquals(heap.size(), 100);
		heap.extractMax();
		assertEquals(heap.size(), 99);
	}

	@Test
	void testIsEmpty() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		assertTrue(heap.isEmpty());
		heap.add(1);
		heap.add(20);
		heap.add(50);
		heap.add(24);
		assertFalse(heap.isEmpty());
	}

	@Test
	void testClear() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		assertTrue(heap.isEmpty());
		heap.add(1);
		heap.add(20);
		heap.add(50);
		heap.add(24);
		assertFalse(heap.isEmpty());
		heap.clear();
		assertEquals(heap.size(), 0);
		assertTrue(heap.isEmpty());
	}

	@Test
	void testToArray() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		heap.add(1);
		heap.add(20);
		heap.add(50);
		heap.add(24);

		assertEquals(Arrays.toString(heap.toArray()), "[50, 24, 20, 1]");
	}

	@Test
	void testLargerToArray() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();
		for (int i = 0; i < 10; i++) {
			heap.add(i);
		}

		assertEquals(Arrays.toString(heap.toArray()), "[9, 8, 5, 6, 7, 1, 4, 0, 3, 2]");
	}
}
