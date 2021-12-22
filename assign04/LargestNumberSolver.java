package assign04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class determines the largest formatted number possible from a given integer array.
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
public class LargestNumberSolver {

	/**
	 * This method sorts an array using insertion sort and a comparator object
	 * 
	 * @param <T> - the object type
	 * @param arr - the given array
	 * @param cmp - the comparator object to be compared
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		// make a loop to search from beginning to end
		for (int i = 1; i < arr.length; i++) {
			// loop back to search the index to insert (or don't move)
			for (int j = i - 1; j >= 0; j--) {
				if (cmp.compare(arr[j + 1], arr[j]) < 0) {
					T moveToRhs = arr[j];
					T moveToLhs = arr[j + 1];
					arr[j] = moveToLhs;
					arr[j + 1] = moveToRhs;
				} else if (cmp.compare(arr[j + 1], arr[j]) > 0) {
					break;
				}
			}
		}
	}

	/**
	 * This protected class has a comparator that compares two integers in a given
	 * array
	 * 
	 * @author Cobi Toeun and Hongxuan Zhu
	 *
	 */
	protected class CompareTwoInt implements Comparator<Integer> {

		/**
		 * This compare method compares two integers
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			// convert number to array
			int[] arr1 = intToArray(o1);
			int[] arr2 = intToArray(o2);

			// get the size of larger array and the size of smaller array
			int arr1Size = arr1.length;
			int arr2Size = arr2.length;
			int bigSize = Math.max(arr1Size, arr2Size);
			int smallSize = Math.min(arr1Size, arr2Size);

			// loop to go over the numbers and return result
			for (int i = 0; i < bigSize; i++) {
				// compare when both have enough nums
				if (i < smallSize) {
					if (arr1[i] < arr2[i])
						return 1;
					if (arr1[i] > arr2[i])
						return -1;
				} else {
					// compare when one don't have enough nums
					if (arr1.length == smallSize) {
						if (arr1[smallSize - 1] < arr2[i])
							return 1;
						if (arr1[smallSize - 1] > arr2[i])
							return -1;
					} else if (arr2.length == smallSize) {
						if (arr1[i] < arr2[smallSize - 1])
							return 1;
						if (arr1[i] > arr2[smallSize - 1])
							return -1;
					}
				}
			}
			return 0;
		}
	}

	/**
	 * This private helper method converts an integer to an array
	 * 
	 * @param num - the given Integer from array
	 * @return - the number converted to an array
	 */
	private int[] intToArray(Integer num) {
		// convert to string to get the size
		String numStr = num.toString();
		int numSize = numStr.length();

		// create the array use the size
		int[] numArr = new int[numSize];

		// put the num into loop and fill the array
		for (int i = numSize; i > 0; i--) {
			// Divide the num by 10^(i-1), convert to int
			int numToDiv = (int) Math.pow(10, i - 1);
			int singleNum = (int) (num / numToDiv);

			// get the mod and set it to the num
			num = num % numToDiv;

			// put the convert result into array
			numArr[numSize - i] = singleNum;
		}
		return numArr;
	}

	/**
	 * This method creates the largest number that can formatted from a given array.
	 * 
	 * @param arr - the given array
	 * @return - the largest number than can be formed. If array is empty, method
	 *         returns 0
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		String resultStr = "";

		// check if length is 0
		if (arr.length == 0) {
			String s = "0";
			return new BigInteger(s);
		}

		// clone the array so there shouldn't have any change in arr
		Integer[] tempArr = arr.clone();

		// sort the tempArr
		LargestNumberSolver lns = new LargestNumberSolver();
		insertionSort(tempArr, lns.new CompareTwoInt());

		// use a loop to convert the tempArr to string
		for (int i = 0; i < tempArr.length; i++) {
			String tempStr = "" + tempArr[i];
			resultStr += tempStr;
		}

		// convert the string to bigInteger
		BigInteger result = new BigInteger(resultStr);

		return result;
	}

	/**
	 * This method creates the largest int value that can formatted from a given
	 * array.
	 * 
	 * @param arr - the given array
	 * @return - the int value
	 * @throws OutOfRangeException - throws exception if largest int is greater than
	 *                             "2147483647"
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		BigInteger result = findLargestNumber(arr);
		BigInteger compareNum = new BigInteger("" + Integer.MAX_VALUE);
		// Throw out of range exception if result is greater than "2147483647"
		if (result.compareTo(compareNum) > 0) {
			throw new OutOfRangeException("int");
		}
		return result.intValue();
	}

	/**
	 * This method creates the largest long value that can be formatted from a given
	 * array.
	 * 
	 * @param arr - the given array
	 * @return - the long value
	 * @throws OutOfRangeException - throws exception if largest long is greater
	 *                             than "9223372036854775807"
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		BigInteger result = findLargestNumber(arr);
		BigInteger compareNum = new BigInteger("" + Long.MAX_VALUE);
		// Throw out of range exception if result is greater than "9223372036854775807"
		if (result.compareTo(compareNum) > 0) {
			throw new OutOfRangeException("long");
		}
		return result.longValue();
	}

	/**
	 * This method determines the sum of the largest numbers in a given list.
	 * 
	 * @param list - the list of arrays
	 * @return - the sum of the largest numbers in a list
	 */
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger result = new BigInteger("0");
		// use for loop to get all the sums
		for (Integer[] array : list) {
			result = result.add(findLargestNumber(array));
		}
		return result;
	}

	/**
	 * This method finds the largest array in the Kth position within a given list.
	 * 
	 * @param list - the list of arrays
	 * @param k    - the position of the largest array to be found in the list
	 * @return - the largest array in the Kth position of the list
	 * @throws IllegalArgumentException - if k is larger than the list size
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		// Check if k is larger than the list size
		if (k > list.size() - 1) {
			throw new IllegalArgumentException("k is larger than list size");
		}

		// sort the list
		Integer[][] sortArray = list.toArray(new Integer[0][]);
		LargestNumberSolver.insertionSort(sortArray,
				(a, b) -> ((findLargestNumber((Integer[]) b).subtract(findLargestNumber((Integer[]) a)))
						.remainder(new BigInteger("100"))).intValue());
		// select the given index from the list
		return sortArray[k];
	}

	/**
	 * This method generates a list of integers from a given text file
	 * 
	 * @param filename - the file to be generated into a list
	 * @return - the array list from the file or an empty array if there is no file
	 * @throws IOException
	 */
	public static List<Integer[]> readFile(String filename) {
		// try-catch statement
		try {
			File f = new File(filename);

			// we get the grammar of the BufferedReader from website
			// https://blog.csdn.net/u010889616/article/details/51477037
			// reference used from
			FileInputStream input = new FileInputStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			// reference ends here
			List<Integer[]> list = new ArrayList<Integer[]>();

			// read the file line by line
			while (true) {
				// put every line into a string
				String s = "";
				s = reader.readLine();
				if (s == null) {
					break;
				}
				Integer[] tempArray = stringsToArray(s);
				// append the array into the list
				list.add(tempArray);
			}
			// return the list
			return list;
			
		} catch (Exception e) {
			// return empty list if fine not found
			return new ArrayList<Integer[]>();
		}
	}

	/**
	 * This private method converts string within the text line into an Integer
	 * array
	 * 
	 */
	private static Integer[] stringsToArray(String s) {
		// initialize two ints that hold the left and right index
		int leftIndex = 0;
		int rightIndex = 0;
		List<Integer> newList = new ArrayList<Integer>();

		// use for loop
		for (int i = 0; i < s.length(); i++) {
			String str = s.substring(i, i + 1);

			// check if the number in current index is " "(space)
			if (str.equals(" ") | (i == s.length() - 1)) {
				// if true, we set the rightIndex to the index - 1
				rightIndex = i;
			} else { // if false, continue
				continue;
			}

			if (i == s.length() - 1) {
				rightIndex = i + 1;
			}
			// get the value from leftIndex to rightIndex
			String intString = s.substring(leftIndex, rightIndex);

			// convert the string to int
			int stringToInt = Integer.parseInt(intString);

			// put int into the arrayList
			newList.add(stringToInt);

			// change leftIndex to index + 1
			leftIndex = i + 1;
		}
		// Convert Integer to array
		Integer[] result = newList.toArray(new Integer[0]);

		// convert the arrayList to array and return
		return result;
	}
}
