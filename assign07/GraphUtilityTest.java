package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This class tests the GraphUtility class
 * 
 * @author Cobi Toeun & Hongxuan Zhu
 *
 */
class GraphUtilityTest {

	@Test
	void testElementsSizeDoNotMatch() {
		List<String> sources = new ArrayList<String>();
		List<String> destinations = new ArrayList<String>();

		sources.add("a");
		sources.add("b");
		sources.add("c");

		destinations.add("d");
		destinations.add("a");
		destinations.add("b");
		destinations.add("e");

		assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "a", "e"));
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "a", "e"));
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));

	}

	@Test
	void testAreConnected() {
		List<Integer> sources = new ArrayList<Integer>();
		List<Integer> destinations = new ArrayList<Integer>();

		sources.add(1); destinations.add(2);
		sources.add(2); destinations.add(3);
		sources.add(2); destinations.add(4);

		GraphUtility.areConnected(sources, destinations, 1, 4);
		
	}

	@Test
	void testAreConnectedThrows() {
		List<String> sources = new ArrayList<String>();
		List<String> destinations = new ArrayList<String>();

		sources.add("a"); destinations.add("d");
		sources.add("b"); destinations.add("a");
		sources.add("c"); destinations.add("b");

		assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "a", "e"));
	}

	@Test
	void testShortestPath() {
		List<Integer> sources = new ArrayList<Integer>();
		List<Integer> destinations = new ArrayList<Integer>();
		sources.add(1); destinations.add(2);
		sources.add(2); destinations.add(3);
		sources.add(3); destinations.add(4);
		sources.add(4); destinations.add(7);
		sources.add(7); destinations.add(1);

		List<Integer> finalList = GraphUtility.shortestPath(sources, destinations, 2, 7);
		
		assertEquals(finalList.get(0), 2);
		assertEquals(finalList.get(1), 3);
		assertEquals(finalList.get(2), 4);
		assertEquals(finalList.get(3), 7);

	}

	@Test
	void testShortestPathThrows() {
		List<Integer> sources = new ArrayList<Integer>();
		List<Integer> destinations = new ArrayList<Integer>();
		sources.add(1); destinations.add(2);
		sources.add(3); destinations.add(4);
		sources.add(5); destinations.add(6);

		assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, 1, 5));
	}

	@Test
	void testTopologicalSort() {
		List<Integer> sources = new ArrayList<Integer>();
		List<Integer> destinations = new ArrayList<Integer>();
		sources.add(1); destinations.add(2);
		sources.add(2); destinations.add(3);
		sources.add(2); destinations.add(4);
		sources.add(3); destinations.add(6);
		sources.add(4); destinations.add(6);

		List<Integer> finalList = GraphUtility.sort(sources, destinations);
		
		assertEquals(finalList.get(0), 1);
		assertEquals(finalList.get(1), 2);
		assertEquals(finalList.get(2), 3);
		assertEquals(finalList.get(3), 4);
		assertEquals(finalList.get(4), 6);
	}

	@Test
	void testTopologicalSortThrows() {
		List<Integer> sources = new ArrayList<Integer>();
		List<Integer> destinations = new ArrayList<Integer>();
		sources.add(1); destinations.add(2);
		sources.add(2); destinations.add(3);
		sources.add(2); destinations.add(4);
		sources.add(4); destinations.add(1);
		sources.add(4); destinations.add(3);

		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

}
