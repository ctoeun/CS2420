package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This class tests the FindKthLargest class
 * 
 * @authors Cobi Toeun & Hongxuan Zhu
 *
 */
class FindKLargestTest {

	@Test
	void testFindKLargestHeap() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(41);
		list.add(10);
		list.add(15);

		List<Integer> newList = FindKLargest.findKLargestHeap(list, 0);
	}

	@Test
	void testFindKLargestHeapThrows() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(41);
		list.add(10);
		list.add(15);

		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 10));
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, -10));
	}

	@Test
	void testFindKLargestHeapComparator() {
		fail("Not yet implemented");
	}

	@Test
	void testFindKLargestHeapComparatorThrows() {
		fail("Not yet implemented");
	}

	@Test
	void testFindKLargestSort() {
		fail("Not yet implemented");
	}

	@Test
	void testFindKLargestSortThrows() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(41);
		list.add(10);
		list.add(15);

		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(list, 50));
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(list, -5));
	}

	@Test
	void testFindKLargestSortComparator() {
		fail("Not yet implemented");
	}

	@Test
	void testFindKLargestSortComparatorThrows() {
		fail("Not yet implemented");
	}

}
