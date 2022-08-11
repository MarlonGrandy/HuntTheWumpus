/* Marlon Grandy
CS231
File: Vertex.Java
use: implements methods to define a vertex (a single node within a graph)
4/29/2022
*/

import java.util.ArrayList;
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;

public class Vertex implements Comparable<Vertex> {

    // field variables
    private ArrayList<Vertex> adjecent;
    private int x;
    private int y;
    private boolean isVis;
    private boolean hasVisited;
    private Vertex parent;
    private double cost;

    public Vertex(int x, int y) { // constructor initializing field varaibles
        adjecent = new ArrayList<Vertex>();
        for (int i = 0; i < 4; i++) {
            adjecent.add(null);
        }
        this.x = x;
        this.y = y;
        hasVisited = false;
        isVis = false;
    }

    public double getCost() { // getter for cost
        return cost;
    }

    public void setCost(double cost) { // setter for cost
        this.cost = cost;
    }

    public int getX() { // getter for x position
        return x;
    }

    public int getY() { // getter for y position
        return y;
    }

    public boolean getIsVis() { // getter for visibility attribute
        return isVis;
    }

    public boolean getHasVisited() { // getter for hasVisited
        return hasVisited;
    }

    public Vertex getParent() { // returns parent
        return parent;
    }

    public void setX(int x) { // sets x field
        this.x = x;
    }

    public void setY(int y) { // sets y field
        this.y = y;
    }

    public void setIsVis(boolean isVis) { // sets visibility field
        this.isVis = isVis;
    }

    public void setHasVisited(boolean visit) { // sets if a node has been visited
        this.hasVisited = visit;
    }

    public void setParent(Vertex p) { // sets parent node
        this.parent = p;
    }

    public double distance(Vertex other) {// returns the Euclidean distance between this vertex and the other vertex
                                          // based on their x and y positions.
        return Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));

    }

    public void connect(Vertex other, Direction d) { // updates this vertex' adjacency list/map so that it connects with
                                                     // the other
                                                     // Vertex. This is a uni-directional link.
        adjecent.set(d.ordinal(), other);
        // if (d.ordinal() == 0) {
        // other.setY(y + 1);
        // }
        // if (d.ordinal() == 1) {
        // other.setX(x + 1);
        // }
        // if (d.ordinal() == 2) {
        // other.setY(y - 1);
        // }
        // if (d.ordinal() == 3) {
        // other.setX(x - 1);
        // }
        System.out.println(adjecent);
    }

    public Vertex getNeighbor(int x, int y) {// returns the Vertex at position (x, y) if the Vertex is in the adjacency
                                             // list, otherwise null.
        for (Vertex v : adjecent) {
            if (v != null) {
                if (v.getX() == x && v.getY() == y) {
                    return v;
                }
            }
        }
        return null;

    }

    public ArrayList<Vertex> getNeighbors() { // returns an ArrayList of type Vertex which contains all of this Vertex'
                                              // neighbors.
        return this.adjecent;
    }

    public int numNeighbors() { // returns the number of connected vertices.
        return adjecent.size();
    }

    public String toString() { // returns a String containing (at least) the number of neighbors, this Vertex'
                               // cost, and the marked flag.
        return "Neighbors:" + numNeighbors() + " Cost for (" + getX() + "," + getY() + ") :" + getCost() + " Marked:"
                + hasVisited + "\n";
    }

    public int compareTo(Vertex other) { // returns a value < 0 if this vertex comes before other, 0 if this is equal to
                                         // other, and a value > 0 if this comes after other.
        if (cost < other.getCost()) {
            return -1;
        }
        if (cost > other.getCost()) {
            return 1;
        } else {
            return 0;
        }

    }

    public void draw(Graphics g, int scale) {
        if (!this.getIsVis())
            return;
        int xpos = (int) this.getX() * scale;
        int ypos = (int) this.getY() * scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;

        // draw rectangle for the walls of the room
        if (this.getCost() <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);

        g.drawRect(xpos + border, ypos + border, scale - 2 * border, scale - 2 * border);

        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.getNeighbor(this.getX(), this.getY() - 1) != null)
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.getNeighbor(this.getX(), this.getY() + 1) != null)
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth),
                    eighth, eighth + sixteenth);
        if (this.getNeighbor(this.getX() - 1, this.getY()) != null)
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.getNeighbor(this.getX() + 1, this.getY()) != null)
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth,
                    eighth + sixteenth, eighth);
    }

    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public static Direction opposite(Direction d) {
        // returns direction opposite of parameter direction

        // index the slot 2 to the right (in a circular array)
        int i = (d.ordinal() + 2) % Direction.values().length;
        return Direction.values()[i];
    }

    public static void main(String[] args) { // test method for vertex class
        Vertex v1 = new Vertex(5, 5);
        Vertex v2 = new Vertex(3, 3);
        Vertex v3 = new Vertex(2, 2);
        Vertex v4 = new Vertex(10, 10);

        System.out.println(v1.distance(v2));
        // v1.connect(v2);
        System.out.println(v1.getNeighbor(3, 3).getX());
        System.out.println(v1.numNeighbors());
        System.out.println(v1.compareTo(v2));

    }

}
