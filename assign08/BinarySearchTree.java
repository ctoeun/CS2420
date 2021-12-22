package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * This class represents a Binary Search Tree and implements the SortedSet
 * class. Used for SpellChecker class to spell-check a document and store
 * strings in a dictionary.
 * 
 * @authors Hongxuan Zhu & Cobi Toeun
 *
 * @param <Type> - generic type
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private BinaryNode<Type> head;
	private int size;

	/**
	 * Constructor for empty Binary Search Tree
	 */
	public BinarySearchTree() {
		head = new BinaryNode<Type>(null);
		size = 0;
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	@Override
	public boolean add(Type item) {
		BinaryNode<Type> temp = this.head;

		while (temp.haveChild()) {
			if (temp.getContent().equals(item)) {
				return false;
			} else if (temp.getContent().compareTo(item) > 0) {
				if (temp.getLeft() == null) {
					break;
				}
				temp = temp.getLeft();
			} else if (temp.getContent().compareTo(item) < 0) {
				if (temp.getRight() == null) {
					break;
				}
				temp = temp.getRight();
			}
		}
		if (temp.getContent() == (null)) {
			temp.setContent(item);
		} else if (temp.getContent().compareTo(item) > 0) {
			temp.setLeft(new BinaryNode<Type>(item));
		} else if (temp.getContent().compareTo(item) < 0) {
			temp.setRight(new BinaryNode<Type>(item));
		}
		this.size++;

		return true;
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean allIn = false;

		for (Type item : items) {
			if (add(item)) {
				allIn = true;
			}
		}
		return allIn;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		this.head.setContent(null);
		this.head.setLeft(null);
		this.head.setRight(null);
		this.size = 0;

	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	@Override
	public boolean contains(Type item) {
		BinaryNode<Type> temp = this.head;

		while (true) {
			if (temp.getContent().equals(item)) {
				return true;
			}
			if (!temp.haveChild()) {
				break;
			}
			if (temp.getContent().compareTo(item) > 0) {
				if (temp.getLeft() == null) {
					break;
				}
				temp = temp.getLeft();
			} else if (temp.getContent().compareTo(item) < 0) {
				if (temp.getRight() == null) {
					break;
				}
				temp = temp.getRight();
			}

		}
		return false;
	}

	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		boolean allIn = true;

		for (Type item : items) {
			if (!contains(item)) {
				allIn = false;
			}
		}
		return allIn;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		BinaryNode<Type> temp = this.head;

		while (!(temp.getLeft() == (null))) {
			temp = temp.getLeft();
		}
		return temp.getContent();
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		BinaryNode<Type> temp = this.head;

		while (!(temp.getRight() == (null))) {
			temp = temp.getRight();
		}
		return temp.getContent();
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 */
	@Override
	public boolean remove(Type item) {
		BinaryNode<Type> targetNode = getNode(item);

		if (targetNode == null) {
			return false;
		}

		if (!(targetNode.getLeft() == (null)) && !(targetNode.getRight() == (null))) {
			removeTwoSon(item, targetNode);
		} else if (!(targetNode.getLeft() == (null)) || !(targetNode.getRight() == (null))) {
			removeOneSon(item, targetNode);
		} else if (!targetNode.haveChild()) {
			removeLeaf(item, targetNode);
		}

		return true;
	}

	/**
	 * Gets the node from the BST
	 * 
	 * @param item - the item type
	 * @return - the temporary node
	 */
	private BinaryNode<Type> getNode(Type item) {
		BinaryNode<Type> temp = this.head;
		if (this.size == 0) {
			return null;
		}

		while (true) {
			if (temp.getContent().equals(item)) {
				return temp;
			}
			if (!temp.haveChild()) {
				break;
			} else if (item.compareTo(temp.getContent()) < 0) {
				temp = temp.getLeft();
			} else if (item.compareTo(temp.getContent()) > 0) {
				temp = temp.getRight();
			}
		}

		return null;
	}

	/**
	 * Gets the previous node from the BST
	 * 
	 * @param item - the item type
	 * @return - the previous node
	 */
	private BinaryNode<Type> getPreNode(Type item) {
		BinaryNode<Type> temp = this.head;

		if (temp.getContent().equals(item)) {
			return null;
		}

		while (true) {
			if (temp.getLeft().getContent().equals(item) || temp.getRight().getContent().equals(item)) {
				return temp;
			} else if (!temp.getLeft().haveChild() && !temp.getRight().haveChild()) {
				break;
			} else if (item.compareTo(temp.getContent()) < 0) {
				temp = temp.getLeft();
			} else if (item.compareTo(temp.getContent()) > 0) {
				temp = temp.getRight();
			}
		}
		return null;
	}

	/**
	 * Removes the leaf from the BST
	 * 
	 * @param item       - the item type
	 * @param targetNode - the target node
	 * @return - true when the leaf is removed
	 */
	private boolean removeLeaf(Type item, BinaryNode<Type> targetNode) {
		BinaryNode<Type> preNode = this.getPreNode(item);
		if (preNode.getLeft().getContent().equals(item)) {
			preNode.setLeft(null);
		} else if (preNode.getRight().getContent().equals(item)) {
			preNode.setRight(null);
		}
		this.size--;
		return true;
	}

	/**
	 * Removes one child from the BST
	 * 
	 * @param item       - the item type
	 * @param targetNode - the target node
	 * @return - true when one child is removed
	 */
	private boolean removeOneSon(Type item, BinaryNode<Type> targetNode) {
		BinaryNode<Type> preNode = getPreNode(item);
		BinaryNode<Type> nextNode = new BinaryNode<Type>(null);

		if (!(targetNode.getLeft() == (null))) {
			nextNode = targetNode.getLeft();
		} else if (!(targetNode.getRight() == null)) {
			nextNode = targetNode.getRight();
		}

		if (preNode.getLeft().getContent().equals(item)) {
			preNode.setLeft(nextNode);
		} else if (preNode.getRight().getContent().equals(item)) {
			preNode.setRight(nextNode);
		}
		this.size--;
		return true;
	}

	/**
	 * Removes two children from the BST
	 * 
	 * @param item       - the item type
	 * @param targetNode - the target node
	 * @return - true when two children are removed
	 */
	private boolean removeTwoSon(Type item, BinaryNode<Type> targetNode) {
		BinaryNode<Type> nextNode = targetNode.getRight();

		while (!(nextNode.getLeft() == null)) {
			nextNode = nextNode.getLeft();
		}

		if (targetNode.getContent().equals(this.head.getContent())) {
			Type nextNodeCont = nextNode.getContent();
			this.remove(nextNodeCont);
			this.head.setContent(nextNodeCont);
		} else {
			Type nextNodeCont = nextNode.getContent();
			this.remove(nextNodeCont);
			targetNode.setContent(nextNodeCont);

		}
		return true;
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean allIn = false;

		for (Type item : items) {
			if (remove(item)) {
				allIn = true;
			}
		}
		return allIn;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> sortList = new ArrayList<Type>();
		BinaryNode<Type> tempNode = this.head;
		ArrayList<Type> sortedList = inOrder(sortList, tempNode);
		return sortedList;
	}

	/**
	 * Helper method for returning the ArrayList in order
	 * 
	 * @param preList - the previous list
	 * @param curNode - the current node type
	 * @return - the new ArrayList in order
	 */
	private ArrayList<Type> inOrder(ArrayList<Type> preList, BinaryNode<Type> curNode) {
		if (this.size == 0) {
			return preList;
		}

		while (!(curNode.getLeft() == null)) {
			inOrder(preList, curNode.getLeft());
		}

		preList.add(curNode.getContent());

		while (!(curNode.getRight() == null)) {
			inOrder(preList, curNode.getRight());
		}

		return preList;
	}
}
