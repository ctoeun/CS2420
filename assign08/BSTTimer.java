package assign08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * This class collects running times for methods of SimplePriorityQueue.
 * 
 * @author Erin Parker, Zhu, Hongxuan and Cobi Toeun
 * @version February 9, 2021
 */
public class BSTTimer {

	public static void main(String[] args) {
		Random rand = new Random();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		int probSize = 200000;
		int IncreaseRate = 10000;
		for (int i = 100; i < probSize; i += IncreaseRate) {
			BinarySearchTree<Integer> Btree = new BinarySearchTree<Integer>();
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int j = 1; j < i; j++) {
				temp.add(j);
			}
			Collections.shuffle(temp);
			for (Integer item : temp) {
				Btree.add(item);
			}

			long stopTime, midpointTime, startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			Btree.add(i + 10);
			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = (midpointTime - startTime);

			System.out.println(i + "  " + averageTime);

		}
	}

	public static List<List<String>> generGraph(int level) {
		List<String> src = new ArrayList<String>();
		List<String> dst = new ArrayList<String>();
		List<List<String>> result = new ArrayList<List<String>>();
		int trackIndex = 0;
		for (int i = 0; i < level; i++) {
			for (int j = 0; j <= Math.pow(i, 2); j++) {
				String name = i + " " + j;
				dst.add(name);
				if (j % 2 == 0) {
					src.add(dst.get(trackIndex));

				} else {
					src.add(dst.get(trackIndex));
					trackIndex++;
				}
			}
		}
		src.remove(0);
		dst.remove(0);
		result.add(src);
		result.add(dst);
		/*
		 * for (int i = 0; i < src.size(); i++) { String srcName = src.get(i); String
		 * dstName = dst.get(i); System.out.println('"' + srcName + '"' + "->" +
		 * '"' + dstName + '"' + ";"); }
		 */
		return result;
	}
}
