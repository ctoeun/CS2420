package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class collects running times for methods of SimplePriorityQueue.
 * 
 * @author Erin Parker, Zhu, Hongxuan and Cobi Toeun
 * @version February 9, 2021
 */
public class LargestNumberSolverTimer {
	
	public static void main(String[] args) {
		Random rng = new Random();
		LargestNumberSolver lns = new LargestNumberSolver();

		int incr = 10;
		for(int probSize = 100; probSize <= 400; probSize += incr) {
			List<Integer[]> testList = new ArrayList<Integer[]>();
			for(int j = 0; j < probSize; j++) {
				Integer[] Arr = new Integer[j];
				for (int i = 0; i < j; i++) {
					Arr[i] = i;
				}
				Collections.shuffle(Arrays.asList(testList));
				testList.add(Arr);
			}
			
			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			LargestNumberSolver.findKthLargest(testList, 0);

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < probSize; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - 
						(stopTime - midpointTime));
			System.out.println(probSize + "  " + averageTime);
			
		}
	}
}
