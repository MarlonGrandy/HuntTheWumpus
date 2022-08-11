/* Marlon Grandy
CS231
File: Graph.Java
use: implements a graph data structure with all necesarry methods
4/29/2022
*/

//import statements
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    ArrayList<Vertex> verticies; // list of all vgerticies in the graph

    public Graph() { // constructor assigning verticies list
        verticies = new ArrayList<Vertex>();
    }

    public int vertexCount() {// returns the number of vertices in the graph.
        return verticies.size();
    }

    public boolean inGraph(Vertex query) { // return true if the query Vertex is in the graph's vertex list.
        for (Vertex v : verticies) {
            if (v == query) {
                return true;
            }
        }
        return false;
    }

    public void addUniEdge(Vertex v1, Vertex v2, Vertex.Direction d) { // adds v1 and v2 to the graph (if necessary) and
                                                                       // adds an edge
        // connecting v1 to v2, creating a uni-directional link.
        if (!inGraph(v1)) {
            verticies.add(v1);

        }
        if (!inGraph(v2)) {
            verticies.add(v2);
        }

        v1.connect(v2, d);

    }

    public void addBiEdge(Vertex v1, Vertex v2, Vertex.Direction d, Vertex.Direction d1) {// adds v1 and v2 to the graph
                                                                                          // (if necessary), adds an
                                                                                          // edge connecting
        // v1 to v2, and adds a second edge connecting v2 to v1, creating a
        // bi-directional link.
        if (!inGraph(v1)) {
            verticies.add(v1);

        }
        if (!inGraph(v2)) {
            verticies.add(v2);
        }

        v1.connect(v2, d);
        v2.connect(v1, d1);

    }

    public void shortestPath(Vertex v0) {// implements a single-source shortest-path algorithm for the Graph, Dijkstra's
                                         // algorithm.
        // Initialize all vertices in G to be unmarked, have a large cost, and a null
        // parent
        for (Vertex i : verticies) {
            if (i != null) {
                i.setCost(1000000);
                i.setParent(null);
                i.setHasVisited(false);
            }
        }
        // System.out.println(verticies);
        // System.out.println(verticies);
        // Create a priority queue, pq, to hold objects of type Vertex
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();

        // Set the cost of v0 to 0 and add it to pq
        v0.setCost(0);
        pq.add(v0);

        // while q is not empty:
        while (pq.size() != 0) {

            // remove v from pq where v is the vertex with lowest cost
            Vertex v = pq.remove();
            // if v is already marked as visited, continue
            if (!v.getHasVisited()) {

                v.setHasVisited(true);

                for (Vertex w : v.getNeighbors()) {
                    if (w != null) {
                        // compute the distance between v and w
                        Double d = v.distance(w);
                        if (!w.getHasVisited() && v.getCost() + d < w.getCost()) {

                            w.setIsVis(true);
                            // System.out.println(
                            // v.getCost() + " " + d + " " + w.getX() + w.getY() + " " + v.getX() +
                            // v.getY()); // w.cost:

                            w.setCost(v.getCost() + d);
                            w.setParent(v); // make v the parent of w
                            pq.add(w);
                        }
                    }
                }
            }
        }
    }

}
