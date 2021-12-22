package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a generic vertex (AKA node) in a directed graph.
 * 
 * @author Erin Parker & Hongxuan Zhu & Cobi Toeun
 * @version October 12, 2020 (Edited March 10, 2021)
 */
public class Vertex<E> {

	// used to id the Vertex
	private E name;
	private boolean visited = false;
	
	private int inDegree = 0;

	// adjacency list
	private LinkedList<Edge> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(E name) {
		this.name = name;
		this.adj = new LinkedList<Edge>();
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public E getName() {
		return name;
	}

	/**
	 * Gets the edge (adjacency list)
	 * 
	 * @return - the edge
	 */
	public LinkedList<Edge> getEdge() {
		return this.adj;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<E> otherVertex) {
		adj.add(new Edge(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the
	 *         source
	 */
	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	/**
	 * Check if vertex has been visited
	 * 
	 * @return - true if it has, false otherwise
	 */
	public boolean getVisited() {
		return this.visited;
	}

	/**
	 * Sets the visited value
	 * 
	 * @param visit - true or false
	 */
	public void setVisited(boolean visit) {
		this.visited = visit;
	}

	/**
	 *  Increases degree
	 */
	public void increaseIndegree() {
		this.inDegree++;
	}

	/**
	 * Decreases degree
	 */
	public void decreaseIndegree() {
		this.inDegree--;
	}

	/**
	 * Gets the degree
	 * 
	 * @return - the degree value
	 */
	public int getIndegree() {
		return this.inDegree;
	}

}