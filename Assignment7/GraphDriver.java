
/**
 * driver class of AdjListsDiGraph
 * GraphDriver.java
 *
 * @author (vs2, yl102)
 * @version (11/20/2021)
 */
public class GraphDriver
{
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
