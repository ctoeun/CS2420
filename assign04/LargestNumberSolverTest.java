package assign04;

import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This class tests the LargestNumberSolver class.
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
class LargestNumberSolverTest {

	@Test
	void testInsertionSort() {
		Integer[] arr = { 4,6,7,3,5,2,1,6,9 };
		LargestNumberSolver.insertionSort(arr, ((Integer a, Integer b) -> b - a));
	}
	
	@Test
	void testInsertionSortString() {
		String[] arr = { "hippo", "bat", "cat", "monkey", "bird", "elephant"};
		LargestNumberSolver.insertionSort(arr, ((String a, String b) -> b.length() - a.length()));
		assertTrue(arr[0].equals("elephant"));
		assertTrue(arr[2].equals("hippo"));
	}
	
	@Test
	void testInsertionSortDouble() {
		Double[] arr = { 2.0, 5.9, 25.6, 1.0, 13.5};
		LargestNumberSolver.insertionSort(arr, ((Double a, Double b) -> (int) (b - a)));
		assertTrue(arr[0].equals(25.6));
		assertTrue(arr[2].equals(5.9));
	}
	
	@Test
	void testInsertionSortArray() {
		Double[] arr = { 2.0, 5.9, 25.6, 1.0, 13.5};
		Double[] arr2 = { 1.0, 2.0, 3.0, 4.0};
		Double[] arr3 = { 2.0, 25.6, 1.0};
		List<Double[]> list = new ArrayList<Double[]>();
		list.add(arr);
		list.add(arr2);
		list.add(arr3);
		LargestNumberSolver.insertionSort(list.toArray(new Double[0][]), ((Double[] a, Double[] b) -> (b.length - a.length)));
		assertTrue(list.get(0).equals(arr));
		assertTrue(list.get(1).equals(arr2));
	}

	@Test
	void testFindLargestNumber() {
		Integer[] arr = { 4, 54, 56, 23 };
		assertTrue(new BigInteger("5654423").equals(LargestNumberSolver.findLargestNumber(arr)));
	}

	@Test
	void testFindLargestNumberFail() {
		Integer[] arr = { 4, 54, 56, 23 };
		assertFalse(new BigInteger("4545623").equals(LargestNumberSolver.findLargestNumber(arr)));
	}

	@Test
	void testFindLargestNumberZero() {
		Integer[] arr = {};
		assertTrue(new BigInteger("0").equals(LargestNumberSolver.findLargestNumber(arr)));
	}

	@Test
	void testFindLargestInt() {
		Integer[] arr = { 1, 99, 639, 13, 7 };
		assertTrue(new BigInteger("997639131").equals(LargestNumberSolver.findLargestNumber(arr)));
	}

	@Test
	void testFindLargestIntThrow() {
		Integer[] arr = { 999, 639, 1, 7, 58, 9 };
		assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestInt(arr));
	}

	@Test
	void testFindLargestLong() {
		Integer[] arr = { 999, 639, 1, 7, 58, 9 };
		assertTrue(new BigInteger("99997639581").equals(LargestNumberSolver.findLargestNumber(arr)));
	}

	@Test
	void testFindLargestLongThrow() {
		Integer[] arr = { 999, 639, 1, 7, 58, 9, 100, 455, 24, 45, 542, 154, 50, 45 };
		assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestLong(arr));
	}

	@Test
	void testFindSum() {
		List<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] arr1 = { 34, 11, 22 };
		Integer[] arr2 = { 45, 32, 5 };
		LargestNumberSolver.findLargestNumber(arr1);
		LargestNumberSolver.findLargestNumber(arr2);
		list.add(arr1);
		list.add(arr2);
		assertTrue(new BigInteger("396743").equals(LargestNumberSolver.sum(list)));
	}

	@Test
	void testFindBiggerSum() {
		List<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] arr1 = { 4, 54, 56, 23 };
		Integer[] arr2 = { 45, 32, 5 };
		Integer[] arr3 = { 34, 11, 22 };
		Integer[] arr4 = { 1, 99, 639, 13, 7 };
		LargestNumberSolver.findLargestNumber(arr1);
		LargestNumberSolver.findLargestNumber(arr2);
		LargestNumberSolver.findLargestNumber(arr3);
		LargestNumberSolver.findLargestNumber(arr4);
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		list.add(arr4);
		assertTrue(new BigInteger("1003690297").equals(LargestNumberSolver.sum(list)));
	}

	@Test
	void testFindKthLargest() {
		List<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] arr1 = { 34, 11, 22 };
		Integer[] arr2 = { 1, 99, 639, 13, 7 };
		LargestNumberSolver.findLargestNumber(arr1);
		LargestNumberSolver.findLargestNumber(arr2);
		list.add(arr1);
		list.add(arr2);
		assertTrue(arr1.equals(LargestNumberSolver.findKthLargest(list, 1)));
		assertTrue(arr2.equals(LargestNumberSolver.findKthLargest(list, 0)));

	}

	@Test
	void testFindBiggerKthLargest() {
		List<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] arr1 = { 4, 54, 56, 23 };
		Integer[] arr2 = { 45, 32, 5 };
		Integer[] arr3 = { 34, 11, 22, 1 };
		Integer[] arr4 = { 1, 99, 639, 13, 7 };
		Integer[] arr5 = { 1, 5, 2 };
		LargestNumberSolver.findLargestNumber(arr1);
		LargestNumberSolver.findLargestNumber(arr2);
		LargestNumberSolver.findLargestNumber(arr3);
		LargestNumberSolver.findLargestNumber(arr4);
		LargestNumberSolver.findLargestNumber(arr5);
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		list.add(arr4);
		list.add(arr5);
		assertTrue(arr1.equals(LargestNumberSolver.findKthLargest(list, 1)));
		assertTrue(arr2.equals(LargestNumberSolver.findKthLargest(list, 3)));
		assertTrue(arr3.equals(LargestNumberSolver.findKthLargest(list, 2)));
		assertTrue(arr4.equals(LargestNumberSolver.findKthLargest(list, 0)));
		assertTrue(arr5.equals(LargestNumberSolver.findKthLargest(list, 4)));

	}

	@Test
	void testFindKthLargestThrow() {
		List<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] arr1 = { 4, 54, 56, 23 };
		Integer[] arr2 = { 45, 32, 5 };
		LargestNumberSolver.findLargestNumber(arr1);
		LargestNumberSolver.findLargestNumber(arr2);
		list.add(arr1);
		list.add(arr2);
		assertThrows(IllegalArgumentException.class, () -> LargestNumberSolver.findKthLargest(list, 2));
	}

	@Test
	void testEmptyReadFile() throws IOException {
		List<Integer[]> testList = LargestNumberSolver.readFile("emptyFile.txt");
		assertTrue(testList.isEmpty());
	}

	@Test
	void testEmptyReadFileFalse() throws IOException {
		List<Integer[]> testList = LargestNumberSolver.readFile("src/assign04/integers.txt");
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(3);
		test.add(15);
		test.add(8);
		assertFalse(testList.isEmpty());
		assertFalse(testList.equals(test));
	}

	@Test
	void checkIfTestFileExists() {
		File file = new File("src/assign04/testFile.txt");
		assertTrue(file.exists());
	}

	@Test
	void checkIfIntegerFile2Exists() {
		File file = new File("src/assign04/testFile2.txt");
		assertTrue(file.exists());
	}

	@Test
	void checkIfIntegerFileExists() {
		File file = new File("src/assign04/integers.txt");
		assertTrue(file.exists());
	}

	@Test
	void testTestReadFile() throws IOException {
		List<Integer[]> testList = LargestNumberSolver.readFile("src/assign04/testFile.txt");
		Integer[][] cmpArray = testList.toArray(new Integer[0][]);
		assertTrue(cmpArray[0].equals(LargestNumberSolver.findKthLargest(testList, 0)));
	}

	@Test
	void testSmallerReadFile() throws IOException {
		List<Integer[]> testList = LargestNumberSolver.readFile("src/assign04/testFile2.txt");
		Integer[][] cmpArray = testList.toArray(new Integer[0][]);
		assertTrue(cmpArray[109].equals(LargestNumberSolver.findKthLargest(testList, 109)));
	}

	@Test
	void testLargerReadFile() throws IOException {
		List<Integer[]> testList = LargestNumberSolver.readFile("src/assign04/integers.txt");
		Integer[][] cmpArray = testList.toArray(new Integer[0][]);
		assertTrue(cmpArray[903].equals(LargestNumberSolver.findKthLargest(testList, 904)));
	}

}
