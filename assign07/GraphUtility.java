package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 * 
 * @author Erin Parker & Hongxuan Zhu & Cobi Toeun
 * @version March 10, 2021
 */
public class GraphUtility {

	/**
	 * Driver method for depth-first search
	 * 
	 * @param <Type>       - type to be passed
	 * @param sources      - list that contains src vertices
	 * @param destinations - list that contains dst vertices
	 * @param srcData      - the src vertex
	 * @param dstData      - the dst vertex
	 * @return - true if there is a connection, false otherwise
	 * @throws IllegalArgumentException - if there does not exist a vertex in the
	 *                                  graph with srcData, and likewise for
	 *                                  dstData.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		Graph<Type> newGraph = generateGraph(sources, destinations);

		return checkConnection(newGraph, srcData, dstData);
	}

	/**
	 * This private method uses depth-first search and determines if there is a path
	 * from the vertex with srcData to the vertex with dstData in the graph.
	 * 
	 * @param <Type>  - type to be passed
	 * @param graph   - created graph with source and destinations list
	 * @param srcData - the src vertex
	 * @param dstData - the dst vertex
	 * @return - returns true if there is a connection, false otherwise
	 */
	private static <Type> boolean checkConnection(Graph<Type> graph, Type srcData, Type dstData)
			throws IllegalArgumentException {
		if ((!graph.hasVertex(srcData) || (!graph.hasVertex(dstData)))) {
			throw new IllegalArgumentException();
		}
		// get all edges
		int edgeCount = graph.getEdges(srcData).size();

		// use for loop
		for (int i = 0; i < edgeCount; i++) {
			// if distance == infinity
			Edge<Type> newEdge = graph.getEdges(srcData).get(i);
			if (!newEdge.getOtherVertex().getVisited()) { //
				// set distance to 1
				newEdge.getOtherVertex().setVisited(true);
				// Check if another vertex is equal to dstData
				Vertex newVertex = newEdge.getOtherVertex();
				if (newVertex.equals(dstData)) {
					return true;
				}
				// if recursion is true
				if (checkConnection(graph, (Type) newVertex.getName(), dstData)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Driver method for breadth-first search
	 * 
	 * @param <Type>       - type to be passed
	 * @param sources      - list that contains src vertices
	 * @param destinations - list that contains dst vertices
	 * @param srcData      - the src vertex
	 * @param dstData      - the dst vertex
	 * @return - true if there is a connection, false otherwise
	 * @throws IllegalArgumentException - if there does not exist a path between the
	 *                                  two vertices.
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData,
			Type dstData) throws IllegalArgumentException {
		Graph<Type> newGraph = generateGraph(sources, destinations);
		return findShortestPath(newGraph, srcData, dstData);
	}

	/**
	 * Private helper method for breadth-first search that converts dictionary to list
	 * 
	 * @param <Type>  - type to be passed
	 * @param graph   - created graph with source and destinations list
	 * @param srcData - the src vertex
	 * @param dstData - the dst vertex
	 * @return - the final path into a list
	 */
	private static <Type> List<Type> findShortestPath(Graph<Type> graph, Type srcData, Type dstData) {
		// call findShortestPathDic and set value into a dictionary
		HashMap<Vertex, Vertex> dic = findShortestPathDic(graph, srcData, dstData);
		ArrayList<Type> track = new ArrayList<Type>();
		Vertex dstVertex = graph.getVertex(dstData);
		track.add(dstData);
		Vertex preVertex = new Vertex(null);
		// use while loop
		while (true) {
			// get name from the dstData
			preVertex = dic.get(dstVertex);
			dstVertex = preVertex;
			// put name into list
			track.add((Type) preVertex.getName());
			if (((Type) preVertex.getName()).equals(srcData)) {
				break;
			}
		}
		Collections.reverse(track);
		// return list
		return track;

	}

	/**
	 * Private helper method for breadth-first search that finds sorted path using dictionary/hashmap
	 * 
	 * @param <Type>  - type to be passed
	 * @param graph   - created graph with source and destinations list
	 * @param srcData - the src vertex
	 * @param dstData - the dst vertex
	 * @return - returns dictionary/hashmap of shortest path
	 * @throws IllegalArgumentException - if there is no shortest path
	 */
	private static <Type> HashMap<Vertex, Vertex> findShortestPathDic(Graph<Type> graph, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// create a empty queue and empty dictionary
		Queue<Vertex> trackQueue = new LinkedList<Vertex>();
		HashMap<Vertex, Vertex> dic = new HashMap<Vertex, Vertex>();
		// put srcData into queue
		if ((!graph.hasVertex(srcData) || (!graph.hasVertex(dstData)))) {
			throw new IllegalArgumentException();
		}
		trackQueue.offer(graph.getVertex(srcData));
		// while the queue is not empty
		while (!trackQueue.isEmpty()) {
			// pull element curVertex out from queue
			Vertex curVertex = trackQueue.poll();
			// set curVertex visited
			curVertex.setVisited(true);
			// use for loop to loop through all queueVertex connected from the curVertex
			int edgeCount = curVertex.getEdge().size();
			for (int i = 0; i < edgeCount; i++) {
				Vertex queueVertex = ((Edge) curVertex.getEdge().get(i)).getOtherVertex();
				// check if vertex is not visited
				if (!queueVertex.getVisited()) {
					// put element into the dictionary, queueVertex is key, curVertex is value
					dic.put(queueVertex, curVertex);
					// check if queueVertex is equal to the dstData
					if (queueVertex.getName().equals(dstData)) {
						// true, return the dictionary
						return dic;
					}
					// put queueVertex into queue
					trackQueue.offer(queueVertex);
				}
			}
		}
		// throw IllegalArgumentException
		throw new IllegalArgumentException();
	}

	/**
	 * Driver method for topographical sort
	 * 
	 * @param <Type>       - type to be passed
	 * @param sources      - list that contains src vertices
	 * @param destinations - list that contains dst vertices
	 * @param srcData      - the src vertex
	 * @param dstData      - the dst vertex
	 * @return - true if there is a connection, false otherwise
	 * @throws IllegalArgumentException - if the graph contains a cycle
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		Graph<Type> newGraph = generateGraph(sources, destinations);
		List<Type> topoList = useTopoSort(newGraph);
		return topoList;
	}

	/**
	 * Private helper method that puts the sorted list into another list.
	 * 
	 * @param <Type> - the type to be passed
	 * @param graph - the generated graph
	 * @return - a sorted order of the vertices in the graph
	 */
	private static <Type> List<Type> useTopoSort(Graph<Type> graph) {
		List<Vertex> vertexList = topoSort(graph);
		List<Type> nameList = new ArrayList<Type>();
		for (int i = 0; i < vertexList.size(); i++) {
			nameList.add((Type) vertexList.get(i).getName());
		}
		return nameList;
	}

	/**
	 * Private helper method that generates a sorted ordering of the vertices in the graph.
	 * 
	 * @param <Type> - the type to be passed
	 * @param graph - the generated graph
	 * @return - a sorted order of the vertices in the graph
	 */
	private static <Type> List<Vertex> topoSort(Graph<Type> graph) {
		HashMap<Type, Vertex> vertices = graph.getVertices();
		Queue<Vertex> trackQueue = new LinkedList<Vertex>();
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		// for loop
		for (Map.Entry<Type, Vertex> entry : vertices.entrySet()) {
			// if inDegree is 0, inqueue the vertex
			if (entry.getValue().getIndegree() == 0) {
				trackQueue.offer(entry.getValue());
			}
		}
		if (trackQueue.isEmpty()) {
			throw new IllegalArgumentException();
		}
		// while queue is not empty
		while (!trackQueue.isEmpty()) {
			// pull out the vertex
			Vertex curVer = trackQueue.poll();
			// set vertex into list
			vertexList.add(curVer);
			// for loop
			for (int i = 0; i < curVer.getEdge().size(); i++) {
				// get the next vertex
				Vertex nextVertex = ((Edge) curVer.getEdge().get(i)).getOtherVertex();
				// decrease the inDegree
				nextVertex.decreaseIndegree();
				// if inDegree = 0
				if (nextVertex.getIndegree() == 0) {
					// inqueue the vertex
					trackQueue.offer(nextVertex);
				}
			}
			// if list size > vertex number
			if (vertexList.size() > graph.getVertexNum()) {
				// throw
				throw new IllegalArgumentException();
			}
		}
		// return the list
		return vertexList;
	}

	/**
	 * This private method generates a graph from the sources and destinations lists
	 * 
	 * @param <Type> - the type to be passed
	 * @param sources - the vertices on the left hand side 
	 * @param destinations - the vertices on the right hand side
	 * @return - the generated graph
	 */
	static <Type> Graph<Type> generateGraph(List<Type> sources, List<Type> destinations)
			throws IllegalArgumentException {
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}

		Graph<Type> newGraph = new Graph<Type>();

		// use for loop to add edge
		for (int i = 0; i < sources.size(); i++) {
			// loop over the index of src list and dst list
			Type tmpSrc = sources.get(i);
			Type tmpDst = destinations.get(i);
			// add edge to graph
			newGraph.addEdge(tmpSrc, tmpDst);
		}
		// return new graph
		return newGraph;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges specified in
	 * the given DOT file (e.g., "a -> b"). Assumes that the vertex data type is
	 * String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments --accepts one edge per line or edges terminated
	 * with ; --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename     - name of the DOT file
	 * @param sources      - empty ArrayList, when method returns it is a valid
	 *                     "sources" list that can be passed to the public methods
	 *                     in this class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *                     "destinations" list that can be passed to the public
	 *                     methods in this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while (scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if (edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while (scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}
