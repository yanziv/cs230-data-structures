import java.util.LinkedList;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * AdjListsDiGraph.java
 * Implements the DiGraph Interface.
 * Uses a Vector of LinkedLists to keep track of the adjacent vertices.
 * 
 * @author vs2, yl102
 * @version (11/20/2021)
 */

public class AdjListsDiGraph<T> implements DiGraph<T>
{
    private Vector<T> vertices; //Vector to hold the vertices in the graph
    private Vector<LinkedList<T>> arcs;   // Lists of adjacent vertices

    /**
     * Constructor
     * Creates an empty graph
     */
    public AdjListsDiGraph() {
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
    }

    /**
     * Saves the current graph into a .tgf file.
     * @param fName The name of the file to write to
     */
    public void saveToTGF(String fName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fName));

            //write vertices by iterating through vector "vertices"
            for (int i = 0; i < vertices.size(); i++) {
                writer.print((i+1) + " " + vertices.get(i));
                writer.println("");
            }
            writer.println("#"); // # symbol separates the vertices from the arcs

            //write arcs by iterating through arcs vector
            for (int i = 0; i < arcs.size(); i++){ //for each adjacent list
                for (T destinationVertex :arcs.get(i)) { //for each destination vertex in that list
                    int destinationIndex = vertices.indexOf(destinationVertex); //find the index of that vertex
                    writer.println((i+1) + " " + (destinationIndex+1));
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("***ERROR***" +  fName + " could not be written");
        }
    }    

    /**
     * Returns true if the graph is empty and false otherwise.
     * @return boolean True iff the graph is empty
     * 
     */
    public boolean isEmpty() {
        return (vertices.size() == 0); 
    }

    /**
     * Returns the number of vertices in the graph
     * @return int The number of vertices in the graph
     * 
     */
    public int getNumVertices() {
        return vertices.size(); 
    }

    /**
     * Returns the number of arcs in the graph
     * @return int The number of arcs in the graph
     * 
     */
    public int getNumArcs() {
        return arcs.size(); 
    }

    /**
     * Adds the input vertex to the graph.
     * If the vertex already exists in the graph, the graph is not changed.
     * @param vertex The vertex to be added to the graph
     * 
     */
    public void addVertex (T vertex) {
        if (!vertices.contains(vertex)){
            vertices.add(vertex); 
            arcs.add(new LinkedList<T>());
        }
    }

    /**
     * Adds an arc to the graph, from source to destination.
     * If source or destination do not exist in the graph,
     * the graph is not changed.
     * Verifies that source and destination are valid vertices in the graph,
     * and that the newly added arc does not already belong in the graph.
     *
     * @param source The source of the arc
     * @param destination The destination of the arc
     * 
     */
    public void addArc (T vertex1, T vertex2){
        if ((vertices.contains(vertex1)) && (vertices.contains(vertex2))){
            int index = vertices.indexOf(vertex1); 
            if (!arcs.get(index).contains(vertex2)){
                arcs.get(index).add(vertex2); 
            }
        }
    }

    /** Inserts an edge between two vertices of this graph,
     * if the vertices exist. Else does not change the graph. 
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex 
     */
    public void addEdge (T vertex1, T vertex2){
        if ((vertices.contains(vertex1)) && (vertices.contains(vertex2))) //if both vertices exist in vertices
        { 
            int index1 = vertices.indexOf(vertex1); 
            int index2 = vertices.indexOf(vertex2);
            if ((!arcs.get(index1).contains(vertex2)) && (!arcs.get(index2).contains(vertex1))) //if there isn't already an edge between vertex1 and vertex2
            {
                arcs.get(index1).add(vertex2); 
                arcs.get(index2).add(vertex1);
            }
        }
    }

    /**
     *  Returns a string representation of the graph.
     *
     *  @return String a string representation of this graph
     */
    public String toString() {
        String s = "Vertices:\n";
        s += vertices + "\n"; 
        s += "Arcs:\n"; 
        for (int i = 0; i < arcs.size(); i++){
            s += "from " + vertices.get(i) + ": " + arcs.get(i) + "\n";
        }
        return s;
    }

    /**
     * Returns true iff a directed connection exists between the two input vertices
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @return boolean true iff a directed connection
     * exists from the first vertex to the second
     */
    public boolean isArc (T vertex1, T vertex2){
        if (vertices.contains(vertex1) && vertices.contains(vertex2)){
            int index1 = vertices.indexOf(vertex1); 
            int index2 = vertices.indexOf(vertex2);
            if ((arcs.get(index1).contains(vertex2))){
                return true; 
            }
        }
        return false; 
    }

    /** 
     * Returns true iff an edge exists between two given vertices
     * which means that two corresponding arcs exist in the graph 
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @return boolean true iff there exists an edge
     */
    public boolean isEdge (T vertex1, T vertex2){
        if (vertices.contains(vertex1) && vertices.contains(vertex2)){
            int index1 = vertices.indexOf(vertex1); 
            int index2 = vertices.indexOf(vertex2);
            if ((arcs.get(index1).contains(vertex2)) && (arcs.get(index2).contains(vertex1))){
                return true; 
            }
        }
        return false; 
    }

    /**
     * Removes the arc between vertex1 and vertex2.
     * If vertex1 or vertex2, or the arc from vertex1 to vertex2 does not exist,
     * the graph does not change.
     *
     * @param vertex1 The source of the arc to be removed
     * @param vertex2 The destination of the arc to be removed
     */
    public void removeArc (T vertex1, T vertex2) {
        if ((vertices.contains(vertex1)) && (vertices.contains(vertex2)) && isArc(vertex1, vertex2)){
            int index1 = vertices.indexOf(vertex1); 
            int index2 = vertices.indexOf(vertex2);
            arcs.get(index1).remove(vertex2);
        }
    }

    /** Removes an edge between two vertices of this graph,
     * if the vertices exist, else does not change the graph. 
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     */
    public void removeEdge (T vertex1, T vertex2){
        if ((vertices.contains(vertex1)) && (vertices.contains(vertex2)) && isEdge(vertex1, vertex2)){
            int index1 = vertices.indexOf(vertex1); 
            int index2 = vertices.indexOf(vertex2);
            arcs.get(index1).remove(vertex2);
            arcs.get(index2).remove(vertex1); 
        }
    }

    /** Removes a single vertex with the given value from this graph.
     * If the vertex does not exist, it does not change the graph. 
     * @param vertex The vertex to be removed 
     */
    public void removeVertex (T vertex) {
        if (vertices.contains(vertex)){
            int index = vertices.indexOf(vertex);
            
            for (int i = 0; i < arcs.size(); i ++){
                if (isArc(vertices.get(i), vertex)){
                    removeArc(vertices.get(i), vertex); 
                }
            }
            
            vertices.remove(vertex);
            arcs.remove(index);
        }
    }

    /**
     * main()
     * for testing 
     */

    public static void main (String args[]){
        System.out.println("TESTING METHODS");
        System.out.println("_________________");
        AdjListsDiGraph<String> g = new AdjListsDiGraph<String>();
        System.out.println("New graph created.");
        System.out.println("isEmpty() Expect true. Got: " + g.isEmpty());

        System.out.println();
        System.out.println("Testing addVertex()");
        g.addVertex("A"); g.addVertex("B");
        g.addVertex("C"); g.addVertex("D");
        g.addVertex("A"); //try adding existing vertex

        System.out.println("isEmpty() Expect false. Got: " + g.isEmpty());
        System.out.println("Printing graph with 4 verices and no arcs. \nGot:\n" + g);

        System.out.println("Testing addArc()");
        g.addArc("A", "B");
        g.addArc("B", "A");
        g.addArc("A", "C");
        g.addArc("C", "B");
        g.addArc("C", "Z"); //invalid destination
        g.addArc("C", "B"); //try adding existing arc
        System.out.println("New graph has (4):" + g.getNumVertices() + " vertices and (4):" + g.getNumArcs() + " arcs");
        System.out.println("Printing graph with vertices [A, B, C, D] and arcs: A->B, A->C, B->A, C->B. \nGot:\n" + g);

        
        System.out.println("isEdge A <--> B? (TRUE) " + g.isEdge("A", "B"));
        System.out.println("isEdge C <--> B? (FALSE) " + g.isEdge("C", "B"));
        System.out.println("isArc A --> C? (TRUE) " + g.isArc("A", "C"));
        System.out.println("isArc B --> C? (FALSE) " + g.isArc("B", "C"));
        System.out.println("isArc B --> D? (FALSE) " + g.isArc("B", "D"));
        
        System.out.println();
        System.out.println("Saving the graph into A-D.tgf");
        g.saveToTGF("out.tgf");
        
        System.out.println(); 
        System.out.println("Testing addEdge()");
        g.addEdge("B", "D"); 
        System.out.println("add an edge between B and D");
        System.out.println("Expect vertices [A, B, C, D] and arcs: A->B, A->C, B->A, B->D, C->B, D->B\nGot:");
        System.out.println(g);
        
        System.out.println(); 
        System.out.println("Testing removeEdge()");
        g.removeEdge("B", "D"); 
        System.out.println("try remove the edge between B and D");
        System.out.println("Expect vertices [A, B, C, D] and arcs: A->B, A->C, B->A, C->B. \nGot:");
        System.out.println(g);
        
        System.out.println(); 
        System.out.println("Testing removeVertex()");
        g.removeVertex("K"); //try remove invalid vertex
        System.out.println("try remove invalid vertex");
        System.out.println("Expect vertices [A, B, C, D] and arcs: A->B, A->C, B->A, C->B. \nGot:");
        System.out.println(g);

        g.removeVertex("A"); //try remove valid vertex
        System.out.println("try remove valid vertex A w 2 arcs");
        System.out.println("Expect vertices [B, C, D] and arcs: C->B. \nGot:");
        System.out.println(g);

        System.out.println("try remove vertex D w no arcs");
        System.out.println("Expect vertices [B, C] and arcs: C->B. \nGot:");
        g.removeVertex("D");
        System.out.println(g);

        System.out.println("try remove vertex from empty graph");
        System.out.println("Expect vertices [] and arcs: . \nGot:");
        g.removeVertex("B");
        g.removeVertex("C");
        g.removeVertex("C");
        System.out.println(g);

        
    }
}
