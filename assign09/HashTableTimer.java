package assign09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * This class collects running times for methods of HashTable.
 * 
 * @author Erin Parker, Zhu, Hongxuan and Cobi Toeun
 * @version April 8, 2021
 */
public class HashTableTimer {
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		int probSize = 50001;
		int IncreaseRate = 2500;
		for (int i = 10000; i < probSize; i += IncreaseRate) {
			//int colls = 0;
			HashMap<StudentMediumHash,Integer> studentHashTable = new HashMap<StudentMediumHash, Integer>();
			//HashTable<StudentGoodHash, Integer> studentHashTable = new HashTable<StudentGoodHash, Integer>();
			//HashTable<StudentMediumHash, Integer> studentHashTable = new HashTable<StudentMediumHash, Integer>();
			//HashTable<StudentBadHash, Integer> studentHashTable = new HashTable<StudentBadHash, Integer>();
			String[] aToz = {"a","b","c","d","e","f","g","h","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
			
			for(int j = 1; j < i; j++) {
				String name = "";
				for (int k = 0; k < rand.nextInt(6); k++) {
					name += aToz[rand.nextInt(26)];
				}
				int uid = 10000000 + j;
				//StudentGoodHash student = new StudentGoodHash(uid, name, name);
				StudentMediumHash student = new StudentMediumHash(uid, name, name);
				//StudentBadHash student = new StudentBadHash(uid, name, name);
				int score = j % 100;
				//colls += studentHashTable.put(student, score);
			}
			
			String name = "";
			for (int k = 0; k < rand.nextInt(6); k++) {
				name += aToz[rand.nextInt(26)];
			}
			int uid = 10000000 + rand.nextInt(300000);
			//StudentGoodHash student = new StudentGoodHash(uid, name, name);
			StudentMediumHash student = new StudentMediumHash(uid, name, name);
			//StudentBadHash student = new StudentBadHash(uid, name, name);
			int score = rand.nextInt(100);
			
		
			long stopTime, midpointTime, startTime = System.nanoTime();
	
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
	
			// Collect running times.
			startTime = System.nanoTime();	
			studentHashTable.put(student, score);
			midpointTime = System.nanoTime();
	
			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			
			stopTime = System.nanoTime();
	
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = (midpointTime - startTime);
			
			
			System.out.println(i + " " + averageTime /*+ " " + colls*/);
			
		}
	}
}
