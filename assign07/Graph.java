package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents a generic, sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * 
 * @author Erin Parker & Hongxuan Zhu & Cobi Toeun
 * @version October 12, 2020 (Edited March 10, 2021)
 */
public class Graph <E> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<E, Vertex> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<E, Vertex>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(E name1, E name2) {
		Vertex<E> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<E>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<E> vertex2;
		if(vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex<E>(name2);
			vertices.put(name2, vertex2);
		}
		vertex2.increaseIndegree();
		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}
	
	/**
	 * Gets edges from vertices and returns a linked list
	 * 
	 * @param name - the string name for a vertex
	 * @return - a linked list
	 */
	public LinkedList<Edge> getEdges(E name) {
		return vertices.get(name).getEdge();
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex<?> v : vertices.values()) {
			// for every edge
			Iterator<Edge> edges = v.edges();
			while(edges.hasNext()) 
				dot.append("\t" + v.getName() + " -> " + edges.next() + "\n");
			
		}
		
		return dot.toString() + "}";
	}
	
	/**
	 * Gets the vertex name
	 * 
	 * @param name - the string name for a vertex
	 * @return - the vertex
	 */
	public Vertex getVertex(E name) {
		return this.vertices.get(name);
	}
	
	/**
	 * Check if vertex contains key from hashmap
	 * 
	 * @param name - the string name for a vertex
	 * @return - true if it contains key, false otherwise
	 */
	public boolean hasVertex(E name) {
		return (this.vertices.containsKey(name)) ;
	}
	
	/**
	 * Gets the vertices size
	 * 
	 * @return - the size of vertices value
	 */
	public int getVertexNum() {
		return this.vertices.size();
	}
	
	/**
	 * Gets the vertices from graph
	 * 
	 * @return - the vertices
	 */
	public HashMap<E, Vertex> getVertices(){
		return this.vertices;
	}
}