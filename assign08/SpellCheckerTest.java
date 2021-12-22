package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SpellCheckerTest {

	@Test
	void testClearAndEmpty() {
		BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
		BST.add(1);
		BST.add(2);
		BST.add(3);

		BST.clear();

		assertTrue(BST.size() == 0);
		assertTrue(BST.isEmpty());
	}
	
	@Test
	void testRemoveBST() {
		BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
		BST.add(1);
		BST.add(2);
		BST.add(3);
		
		BST.remove(3);
	}

	@Test
	void testAddAndContains() {
		List<String> list = new ArrayList<String>();
		List<String> newList = new ArrayList<String>();
		list.add("hi");
		list.add("hola");
		list.add("meme");

		newList.addAll(list);
		assertTrue(newList.contains("hola"));
		assertTrue(newList.containsAll(list));
	}

	@Test
	void testAddAndRemoveToDictionary() {
		SpellChecker dictionary = new SpellChecker();
		dictionary.addToDictionary("hi");
		dictionary.addToDictionary("hello");
		dictionary.addToDictionary("meme");

		dictionary.removeFromDictionary("meme");
		dictionary.removeFromDictionary("hello");
	}
	
//	@Test
//	void testToArrayList() {
//		BinarySearchTree<Integer> dictionary = new BinarySearchTree<Integer>();
//		dictionary.add(1);
//		dictionary.add(2);
//		dictionary.add(3);
//		ArrayList<Integer> list = dictionary.toArrayList();
//		
//		assertTrue(list.contains(2));
//	}
}
