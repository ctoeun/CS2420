package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

/**
 * This class tests the ArrayListSorter method
 * 
 * @authors Cobi Toeun and Hongxuan Zhu
 *
 */
class ArrayListSorterTest {
	
	private boolean isShuffled = false;
	
	/**
	 * Private method that checks if list is sorted
	 * 
	 * @param <T>  - the array list type
	 * @param list - the array list to be passed in
	 * @return - returns true if the array list is sorted and false otherwise
	 */
	private static <T extends Comparable<? super T>> boolean isSorted(ArrayList<T> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			// If the fist element is greater than the element next to it at any position then return false
			if (list.get(i).compareTo(list.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Private method that shuffles a list
	 * 
	 * @param <T> - the array list type
	 * @param list - list to to be passed in
	 */
	private <T> void shuffle(ArrayList<T> list) {
		Collections.shuffle(list);
		isShuffled = true;
	}
	
	/**
	 * Private method that checks if a list is shuffled
	 * 
	 * @return - returns true is the list is shuffled
	 */
	private boolean isShuffled() {
		return isShuffled;
	}
	
	@Test
	void testGenerateAscending() {
		int size = 100;
		ArrayList<Integer> list = ArrayListSorter.generateAscending(size);
		ArrayList<Integer> compareList = new ArrayList<Integer>(list.size());
		for (int i = 1; i < list.size() + 1; i++) {
			compareList.add(i);
		}
		assertEquals(compareList, list);
	}
	
	@Test
	void testGeneratePermutted() {
		int size = 10;
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(size);
		ArrayList<Integer> compareList = new ArrayList<Integer>(list.size());
		for (int i = 1; i < list.size() + 1; i++) {
			compareList.add(i);
		}
		shuffle(compareList);
		assertTrue(isShuffled());
	}

	@Test
	void testGenerateDescending() {
		int size = 500;
		ArrayList<Integer> list = ArrayListSorter.generateDescending(size);
		ArrayList<Integer> compareList = new ArrayList<Integer>(list.size());
		for (int i = size; i >= 1; i--) {
			compareList.add(i);
		}
		assertEquals(compareList, list);
	}

	@Test
	void testMergeSortAscending() {
		ArrayList<Integer> list = ArrayListSorter.generateAscending(20);
		ArrayListSorter.mergesort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testMergeSortPermutted() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(10);
		ArrayListSorter.mergesort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testMergeSortDescending() {
		ArrayList<Integer> list = ArrayListSorter.generateDescending(30);
		ArrayListSorter.mergesort(list);
		assertTrue(isSorted(list));
	}

	@Test
	void testMergeSortManuel() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(2);
		list.add(5);
		list.add(6);
		ArrayListSorter.mergesort(list);
		assertEquals(list.get(0), 2);
		assertTrue(isSorted(list));
	}

	@Test
	void testMergeSortBigger() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(1000);
		ArrayListSorter.mergesort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testMergeSortEvenBigger() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(10000);
		ArrayListSorter.mergesort(list);
		assertTrue(isSorted(list));
	}

	@Test
	void testQuickSortAscending() {
		ArrayList<Integer> list = ArrayListSorter.generateAscending(45);
		ArrayListSorter.quicksort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testQuickSortPermuted() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(25);
		ArrayListSorter.quicksort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testQuickSortDescending() {
		ArrayList<Integer> list = ArrayListSorter.generateDescending(75);
		ArrayListSorter.quicksort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testQuickSort() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		ArrayListSorter.quicksort(list);
		assertEquals(list.get(0), 0);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testQuickSortBigger() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(1000);
		ArrayListSorter.quicksort(list);
		assertTrue(isSorted(list));
	}
	
	@Test
	void testQuickSortEvenBigger() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(10000);
		ArrayListSorter.quicksort(list);
		assertTrue(isSorted(list));
	}

	@Test
	void testQuicksortManuel() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			list.add(i);
		}
		Collections.shuffle(list);

		list.add(2);
		list.add(1);
		list.add(3);
		list.add(6);
		list.add(5);
		list.add(7);
		list.add(4);
		list.add(0);
		list.add(0);
		list.add(2);
		list.add(7);
		list.add(5);
		list.add(6);
		list.add(4);
		list.add(1);
		list.add(3);
		list.add(3);
		list.add(5);
		list.add(6);
		ArrayListSorter.quicksort(list);
		assertEquals(list.get(0), 0);
		assertTrue(isSorted(list));
	}
}
