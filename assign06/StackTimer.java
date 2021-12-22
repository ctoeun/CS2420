package assign06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class collects running times for methods of ArrayListSorter.
 * 
 * @author Erin Parker, Zhu, Hongxuan and Cobi Toeun
 * @version February 9, 2021
 */
public class StackTimer {
	
	public static void main(String[] args) {
		int probSize = 100000;
		
		for (int i = 1000; i < probSize; i += 5000) {
			LinkedListStack<Integer> linkStack = new LinkedListStack<Integer>();
			ArrayStack<Integer> arrStack = new ArrayStack<Integer>();
//			int[] arr = new int[i];
			for (int j = 0; j < i; j++) {
				linkStack.push(j);
			}	
			
			long stopTime, midpointTime, startTime = System.nanoTime();
			
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
	
			// Collect running times.
			startTime = System.nanoTime();
			for (int j = 0; j < i; j++) {
				linkStack.peek();
			}
			midpointTime = System.nanoTime();
	
			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int j = 0; j < i; j++) {
				
			}
			
			stopTime = System.nanoTime();
	
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime));
			System.out.println(averageTime);
		
		}
	}
}
