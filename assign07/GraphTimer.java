package assign07;

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
public class GraphTimer {
	
	public static void main(String[] args) {
		int probSize = 300;
		int IncreaseRate = 8;
		for (int i = 5; i < probSize; i += IncreaseRate) {
			List<List<String>> result =generGraph(i);
			List<String> src = result.get(0);
			List<String> dst =  result.get(1);
			String start = i-1 + " " + 0;
			long stopTime, midpointTime, startTime = System.nanoTime();
	
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
	
			// Collect running times.
			startTime = System.nanoTime();	
			GraphUtility.shortestPath(src, dst, "0 0", start);
			midpointTime = System.nanoTime();
	
			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			
			stopTime = System.nanoTime();
	
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = (midpointTime - startTime);
			Graph graph = GraphUtility.generateGraph(src, dst);
			int size = src.size() + graph.getVertexNum();
			System.out.println(size + "  " + averageTime);
		}
	}
	 
	public static List<List<String>> generGraph(int level){
		List<String> src = new ArrayList<String>();
		List<String> dst = new ArrayList<String>();
		List<List<String>> result = new ArrayList<List<String>>();
		int trackIndex = 0;
		for(int i = 0; i < level; i++) {
			for(int j = 0; j <= Math.pow(i, 2); j++) {
				String name = i + " " + j;
				dst.add(name);
				if (j % 2 == 0) {
					src.add(dst.get(trackIndex));
					
				}else {
					src.add(dst.get(trackIndex));
					trackIndex++;
				}
			}
		}
		src.remove(0);
		dst.remove(0);
		result.add(src);
		result.add(dst);
		/*for (int i = 0; i < src.size(); i++) {
			String srcName = src.get(i);
			String dstName = dst.get(i);
			System.out.println('"' + srcName + '"' + "->" + '"' + dstName + '"' + ";");
		}*/
		return result;
	}
}
