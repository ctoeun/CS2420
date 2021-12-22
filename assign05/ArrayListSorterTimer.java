package assign05;

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
public class ArrayListSorterTimer {
	
	public static void main(String[] args) {
		int probSize = 6000;
		for (int size = 200; size < probSize; size += 200) {
			ArrayList<Integer> ascendingList = ArrayListSorter.generateAscending(size);
			ArrayList<Integer> descendingList = ArrayListSorter.generateDescending(size);
			ArrayList<Integer> permutedList = ArrayListSorter.generatePermuted(size);
			
			int timesToLoop = 100;
				
			long stopTime, midpointTime, startTime = System.nanoTime();
	
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
	
			// Collect running times.
			startTime = System.nanoTime();
			
			ArrayList<Integer> tempList = (ArrayList<Integer>) descendingList.clone();
			ArrayListSorter.quicksort(tempList);
			
	
			midpointTime = System.nanoTime();
	
			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			
			ArrayList<Integer> tempLists = (ArrayList<Integer>) permutedList.clone();
			
	
			stopTime = System.nanoTime();
	
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime));
			System.out.println(averageTime);
		
			
		
	}
	}
}
