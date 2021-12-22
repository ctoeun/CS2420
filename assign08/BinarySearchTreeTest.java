package assign08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	@Test
	void test() {
		BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
		
		BST.add(1);
		Integer num = BST.last();
		
		assertTrue(BST.contains(1));
		
	}

}
