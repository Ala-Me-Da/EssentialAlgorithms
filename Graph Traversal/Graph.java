import java.util.*;

/**
 *  Graph implementation with a new set of methods.
 *  Modified from Graph.java located in the dataStructs/Graph directory.
 * @param <E> generic object type to allow for any object for graph vertices
 */
public class Graph<E> {
     private static class Vertex<E> {
        E element;
        boolean visited;
        ArrayList<Vertex<E>> adjVertices;

        Vertex(E elem, boolean v) {
            this.element = elem;
            this.visited = v;
            adjVertices = new ArrayList<Vertex<E>>();
        }
    }

    // Adjacency List representation using a Hash Map
    // Assumes E is an Object with hashcode() and equals() overridden
    private Map<E, Vertex<E>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    /**
     *  Method to add a new vertex to the graph.
     *  A vertex with the same name (i.e. key) will
     *  overwritten with the most recently inserted
     *  instance.
     * @param name - a String key to refer to a Vertex object in the graph.
     * @param eleemnt - Generic object to be stored within the vertex.
     */
    public void addVertex(E element) {
        Vertex<E> newVertex = new Vertex<E>(element, false);
        adjList.put(element, newVertex);
    }

    public void addEdge(E source, E dest) {
        if(dest != null && source != null) {
            Vertex<E> u = adjList.get(source);
            Vertex<E> v = adjList.get(dest);
            u.adjVertices.add(v);
        }
    }

    /** Breadth-First Search traversal of a Graph.
     *  Outputs the order each node was visited.
     * @param source - Source vertex to start the BFS
     */
    public void breadthSearch(E source) {
            resetGraphVisits();
            Vertex<E> sourceVertex = adjList.get(source);
            Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();
            queue.add(sourceVertex);
            while(queue.peek() != null) {
                Vertex<E> u = queue.poll();
                u.visited = true;
                System.out.println("Element: " + u.element);
                Vertex<E> vertices = adjList.get(u.element);
                for(Vertex<E> v : vertices.adjVertices) {
                    if(!v.visited && !queue.contains(v))
                        queue.add(v);
                }
            }
    }

    /** Non-recusrive version of depth-first search. Processes adjaceny lists in opposite order.
     * @param source - Starting vertex for DFS algorithm.
     */
    public void depthSearch(E source) {
        resetGraphVisits();
        Stack<Vertex<E>> stack = new Stack<>();
        Vertex<E> sourceVertex = adjList.get(source);
        stack.push(sourceVertex);
        while(!stack.empty()) {
            Vertex<E> u = stack.pop();
            if(!u.visited) {
                u.visited = true;
                System.out.println("Element: " + u.element);
                Vertex<E> vertices = adjList.get(u.element);
                for(Vertex<E> v : vertices.adjVertices) {
                    stack.push(v);
                }
            }
        }
    }

    public void depthSearchRecursive(E source) {
            resetGraphVisits();
            depthSearchRecursiveHelper(source);
    }

    private void depthSearchRecursiveHelper(E source) {
        Vertex<E> u = adjList.get(source);
        if(u == null) return;
        u.visited = true;
        System.out.println("Element: " + u.element); // Add to R is replaced with a print out
        Vertex<E> vert = adjList.get(u.element);
        for(Vertex<E> v : vert.adjVertices) {
            if(!v.visited) {
                depthSearchRecursiveHelper(v.element);
            }
        }

    }

    @SuppressWarnings("Unchecked")
    private void resetGraphVisits() {
        E[] keys = (E[]) adjList.keySet().toArray();
        for(E key : keys) {
            Vertex<E> vertices = adjList.get(key);
            vertices.visited = false;
            for(Vertex<E> v : vertices.adjVertices) {
                v.visited = false;
            }
        }
    }
}

