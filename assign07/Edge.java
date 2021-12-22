package assign07;

/**
 * This class represents a generic edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker & Hongxuan Zhu & Cobi Toeun
 * @version October 12, 2020 (Edited March 10, 2021)
 */
public class Edge<E> {

	// destination of this directed edge
	private Vertex<E> dst;
	private int distance;

	/**
	 * Creates an Edge object, given the Vertex that is the destination. (The Vertex
	 * that stores this Edge object is the source.)
	 * Sets distance to max value (infinity)
	 * 
	 * @param dst - the destination Vertex
	 */
	public Edge(Vertex<E> dst) {
		this.dst = dst;
		this.distance = Integer.MAX_VALUE;
	}

	/**
	 * @return the destination Vertex of this Edge
	 */
	public Vertex<E> getOtherVertex() {
		return this.dst;
	}

	/**
	 * Gets the distance value
	 * 
	 * @return - the distance value
	 */
	public int getDistance() {
		return this.distance;
	}

	/**
	 * Sets the distance value
	 * 
	 * @param ds - the distance value
	 */
	public void setDistance(int ds) {
		this.distance = ds;
	}
}
