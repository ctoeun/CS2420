package assign09;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign09.HashTable;

/**
 * This class tests the Hash Table class
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
class HashTableTest {

	HashTable<Integer, Integer> test1 = new HashTable<Integer, Integer>();

	@BeforeEach
	void setUp() throws Exception {
		for (int i = 1; i < 10; i++) {
			test1.put(i, i);
		}
	}

	@Test
	void testClear() {
		test1.clear();
		assertEquals(test1.size(), 0);
		assertEquals(test1.get(1), null);
	}

	@Test
	void testContainsKey() {
		for (int i = 1; i < 10; i++) {
			assertTrue(test1.containsKey(i));
		}
	}

	@Test
	void testContainsValue() {
		for (int i = 1; i < 10; i++) {
			assertTrue(test1.containsValue(i));
		}
	}

	@Test
	void testEntries() {
		ArrayList<MapEntry> temp = (ArrayList) test1.entries();
		for (int i = 1; i < 10; i++) {
			assertEquals(temp.get(i - 1).getValue(), i);

		}
	}

	@Test
	void testGet() {
		for (int i = 1; i < 10; i++) {
			assertEquals(test1.get(i), i);
		}
	}

	@Test
	void testIsEmpty() {
		assertFalse(test1.isEmpty());
		test1.clear();
		assertTrue(test1.isEmpty());
	}

	@Test
	void testPutInt() {
		for (int i = 1; i < 10; i++) {
			assertEquals(test1.put(i, i), i);
			assertEquals(test1.put(i + 20, i + 20), null);
		}
	}

	@Test
	void testPutString() {
		HashTable<String, Integer> test2 = new HashTable<String, Integer>();
		test2.put("a", 1);
		assertEquals(test2.get("a"), 1);
	}

	@Test
	void testPutDouble() {
		HashTable<Double, Integer> test2 = new HashTable<Double, Integer>();
		test2.put(1.5, 1);
		assertEquals(test2.get(1.5), 1);
	}

	@Test
	void testPutBoolean() {
		HashTable<Boolean, Integer> test2 = new HashTable<Boolean, Integer>();
		test2.put(true, 1);
		assertEquals(test2.get(true), 1);
	}

	@Test
	void testPutList() {
		HashTable<ArrayList, Integer> test2 = new HashTable<ArrayList, Integer>();
		ArrayList temp = new ArrayList();
		test2.put(temp, 1);
		assertEquals(test2.get(temp), 1);
	}

	@Test
	void testRemove() {
		for (int i = 1; i < 10; i++) {
			assertEquals(test1.remove(i), i);
			assertEquals(test1.remove(i), null);
		}
	}

	@Test
	void testSize() {
		assertEquals(test1.size(), 9);
	}

}
