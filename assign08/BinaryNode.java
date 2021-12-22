package assign08;

/**
 * This class represents a generic Binary Node.
 * 
 * @authors Hongxuan Zhu & Cobi Toeun
 *
 * @param <Type> - generic type
 */
public class BinaryNode<Type> {

	private Type content;
	private BinaryNode<Type> left;
	private BinaryNode<Type> right;

	/**
	 * Constructor for empty binary node
	 * 
	 * @param info - the data to be passed
	 */
	public BinaryNode(Type info) {
		content = info;
		left = null;
		right = null;
	}

	/**
	 * Sets content data
	 * 
	 * @param info - the data being set
	 */
	public void setContent(Type info) {
		this.content = info;
	}

	/**
	 * Gets and returns the content data
	 * 
	 * @return - the content data
	 */
	public Type getContent() {
		return this.content;
	}

	/**
	 * Sets and returns the left child node
	 * 
	 * @param item - the left child node being set
	 */
	public void setLeft(BinaryNode<Type> item) {
		this.left = item;
	}

	/**
	 * Gets and returns the left child node
	 * 
	 * @return - the left child node
	 */
	public BinaryNode<Type> getLeft() {
		return this.left;
	}

	/**
	 * Sets and returns the right child node
	 * 
	 * @param item - the right child node to being set
	 */
	public void setRight(BinaryNode<Type> item) {
		this.right = item;
	}

	/**
	 * Gets and returns the right child node
	 * 
	 * @return - the right child node
	 */
	public BinaryNode<Type> getRight() {
		return this.right;
	}

	/**
	 * Checks if the left or right node has a child
	 * 
	 * @return - true if either node has a child, false otherwise
	 */
	public boolean haveChild() {
		return ((this.left != null) || (this.right != null));
	}
}
