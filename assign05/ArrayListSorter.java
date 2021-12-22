package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * This class provides recursive sorting for ArrayLists. The sorting techniques
 * used are mergesort and quicksort.
 * 
 * Methods for generating ascending, permuted, and descending lists
 * are included.
 * 
 * @authors Cobi Toeun and Hongxuan Zhu
 *
 */
public class ArrayListSorter {

	// Static variable for determining the threshold for the sorting methods
	private static int threshold = 75;

	/**
	 * Recursive driver method for merge sort.
	 * 
	 * @param <T>  - the type of array list
	 * @param list - the array list to be passed in
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
		ArrayList<T> tempList = generateList(list.size());

		recursiveMerge(list, 0, list.size() - 1, tempList);
	}

	/**
	 * Private recursive method for merging. Switches to insertion sort if threshold
	 * is small enough.
	 * 
	 * @param <T>      - the type of array list
	 * @param list     - the array list to be passed in
	 * @param left     - left index of array list (first position)
	 * @param right    - right index of array list (last position)
	 * @param tempList - the temporary array list that will store temp values
	 */
	private static <T extends Comparable<? super T>> void recursiveMerge(ArrayList<T> list, int left, int right,
			ArrayList<T> tempList) {
		// Check the threshold size
		if (right - left < threshold) {
			// if small enough, call insertion sort
			insertionSort(list, left, right);
		} else {
			// else, call recursive
			// Get index of array
			int mid = (left + right) / 2;
			// Separate list into two and recall method
			recursiveMerge(list, left, mid, tempList);
			recursiveMerge(list, mid + 1, right, tempList);
			merge(list, left, right, mid, tempList);
		}
	}

	/**
	 * Private merge method to merge two array lists.
	 * 
	 * @param <T>      - the type of array list
	 * @param list     - the array list to be passed in
	 * @param left     - left index of array list (first position)
	 * @param right    - right index of array list (last position)
	 * @param mid      - the middle index of the array list
	 * @param tempList - the temporary array list that will store temp values
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, int left, int right, int mid,
			ArrayList<T> tempList) {

		// Get index of array list
		int leftIndex = left;
		int rightIndex = mid + 1;
		int tempIndex = left;

		int range = right + 1;
		int count = 0;

		// Compare and find min element then place into temp array list
		while (leftIndex <= mid && rightIndex <= right) {
			if (list.get(leftIndex).compareTo(list.get(rightIndex)) < 0) {
				tempList.set(tempIndex, list.get(leftIndex));
				// Increment positions
				tempIndex++;
				leftIndex++;
			} else {
				tempList.set(tempIndex, list.get(rightIndex));
				// Increment positions
				tempIndex++;
				rightIndex++;
			}
		}

		// if one side of a list is sorted, use statements below
		while (leftIndex <= mid) {
			tempList.set(tempIndex, list.get(leftIndex));
			// Increment positions
			tempIndex++;
			leftIndex++;
		}

		while (rightIndex <= right) {
			tempList.set(tempIndex, list.get(rightIndex));
			// Increment positions
			tempIndex++;
			rightIndex++;
		}

		// insert temp array values into array list
		while (count < range) {
			list.set(count, tempList.get(count));
			// Increment positions
			count++;
		}
	}

	/**
	 * Private method sorts an array list using insertion sort and a comparator
	 * object
	 * 
	 * @param <T>   - the array list type
	 * @param list  - the given array list
	 * @param left  - left index of array list (first position)
	 * @param right - right index of array list (last position)
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int left, int right) {
		// make a loop to search from beginning to end
		for (int i = left + 1; i <= right; i++) {
			// loop back to search the index to insert (or don't move)
			for (int j = i - 1; j >= left; j--) {
				T leftSide = list.get(j);
				T rightSide = list.get(j + 1);
				if (rightSide.compareTo(leftSide) < 0) {
					list.set(j, rightSide);
					list.set(j + 1, leftSide);
				} else if (rightSide.compareTo(leftSide) > 0) {
					break;
				}
			}
		}
	}

	/**
	 * Private method sorts an array list using insertion sort and a comparator
	 * 
	 * @param <T>   - the array list type
	 * @param list  - the given array list
	 * @param left  - left index of array list (first position)
	 * @param right - right index of array list (last position)
	 * @param cmp   - the comparator object to be compared
	 */
	private static <T> void insertionSort(ArrayList<T> list, int left, int right, Comparator<? super T> cmp) {
		// make a loop to search from beginning to end
		for (int i = left + 1; i < right; i++) {
			// loop back to search the index to insert (or don't move)
			for (int j = i - 1; j >= left; j--) {
				if (cmp.compare(list.get(j + 1), (list.get(j))) < 0) {
					T moveToRhs = list.get(j);
					T moveToLhs = list.get(j + 1);
					list.set(j, moveToLhs);
					list.set(j + 1, moveToRhs);
				} else if (cmp.compare(list.get(j + 1), (list.get(j))) > 0) {
					break;
				}
			}
		}
	}

	/**
	 * Recursive driver method for quick sort.
	 * 
	 * @param <T>  - the array list type
	 * @param list - the array list to be passed in
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
		recursiveQuickSort(list, 0, list.size() - 1);
	}

	/**
	 * Private recursive method for quick sorting.
	 * 
	 * @param <T>   - the array list type
	 * @param list  - the array list to be passed in
	 * @param left  - left index of array list (first position)
	 * @param right - right index of array list (last position)
	 */
	private static <T extends Comparable<? super T>> void recursiveQuickSort(ArrayList<T> list, int left, int right) {
		// use if statement to check distance between left and right
		if (right - left >= 1) {
			// call selectPivot method
			Integer pivot = selectMedianPivot(list, left, right);
			// Move smaller numbers to left, larger numbers to right
			pivot = move(list, left, right, pivot);
			// call method again in left side and right side
			recursiveQuickSort(list, pivot + 1, right);
			recursiveQuickSort(list, left, pivot - 1);

		}

	}

	/**
	 * Private method for selecting the median pivot point in an array list
	 * 
	 * @param <T>   - the array list type
	 * @param list  - the array list to be passed in
	 * @param left  - left index of array list (first position)
	 * @param right - right index of array list (last position)
	 * @return - returns the median pivot point of a given position
	 */
	private static <T extends Comparable<? super T>> Integer selectMedianPivot(ArrayList<T> list, int left, int right) {
		ArrayList<Integer> tempList = generateList(3);
		tempList.set(0, left);
		tempList.set(1, right - 1);
		tempList.set(2, ((int) (right / 2)));
		insertionSort(tempList, 0, 2, (a, b) -> list.get(a).compareTo(list.get(b)));
		return tempList.get(1);
	}

	/**
	 * Private method for selecting a random pivot point in an array list
	 * 
	 * @param <T>   - the array list type
	 * @param list  - the array list to be passed in
	 * @param left  - left index of array list (first position)
	 * @param right - right index of array list (last position)
	 * @return - returns a random pivot point of a given position
	 */
	private static <T extends Comparable<? super T>> Integer selectRandomPivot(ArrayList<T> list, int left, int right) {
		// we learn the random code using from:
		// [https://dzone.com/articles/random-number-generation-in-java], in next two
		// lines
		Random r = new Random();
		return r.nextInt((right - left) + 1) + left;
	}

	/**
	 * Private method for selecting the first position of an array list as the pivot
	 * point
	 * 
	 * @param <T>   - the array list type
	 * @param list  - the array list to be passed in
	 * @param left  - left index of array list (first position)
	 * @param right - right index of array list (last position)
	 * @return - returns the first position in an array list as the pivot point
	 */
	private static <T extends Comparable<? super T>> Integer selectfirstPivot(ArrayList<T> list, int left, int right) {
		return left;
	}

	/**
	 * Private method that moves the elements to left or right size of the pivot
	 * 
	 * @param <T>        - the array list type
	 * @param list       - the array list to be passed in
	 * @param left       - left index of array list (first position)
	 * @param right      - right index of array list (last position)
	 * @param pivotIndex - the pivot point
	 * @return - the pivot
	 */
	private static <T extends Comparable<? super T>> int move(ArrayList<T> list, int left, int right,
			Integer pivotIndex) {
		int leftIndex = left;
		int rightIndex = right - 1;
		T pivot = list.get(pivotIndex);
		swap(list, pivotIndex, right);
		while (true) {
			while (list.get(leftIndex).compareTo(pivot) < 0) {
				leftIndex++;
			}
			while (rightIndex >= 0 && list.get(rightIndex).compareTo(pivot) >= 0) {
				rightIndex--;
			}
			if (leftIndex > rightIndex) {
				swap(list, leftIndex, right);
				break;
			} else {
				swap(list, leftIndex, rightIndex);
				leftIndex++;
			}
		}

		return leftIndex;
	}

	/**
	 * Private method that swaps two numbers
	 * 
	 * @param <T>    - the array list type
	 * @param list   - the array list to be passed in
	 * @param index1 - the first index (to be swapped with second)
	 * @param index2 - the second index (to be swapped with first)
	 */
	private static <T extends Comparable<? super T>> void swap(ArrayList<T> list, int index1, int index2) {
		T tempItem1 = list.get(index1);
		T tempItem2 = list.get(index2);
		list.set(index1, tempItem2);
		list.set(index2, tempItem1);
	}

	/**
	 * Method that generates an ascending ordered list
	 * 
	 * @param size - the array list size
	 * @return - the generated list
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < size + 1; i++) {
			list.add(i);
		}
		return list;
	}

	/**
	 * Method that generates a permuted ordered list
	 * 
	 * @param size - the array list size
	 * @return - the generated list
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < size + 1; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		return list;
	}

	/**
	 * Method that generates a descending ordered list
	 * 
	 * @param size - the array list size
	 * @return - the generated list
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = size; i >= 1; i--) {
			list.add(i);
		}
		return list;
	}

	/**
	 * Method that generates a list with null values
	 * 
	 * @param <T>  - the array list type
	 * @param size - the array list size
	 * @return - the generated list
	 */
	private static <T> ArrayList<T> generateList(int size) {
		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			list.add(null);
		}
		return list;

	}
}
