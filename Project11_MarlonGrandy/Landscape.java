
/*
Marlon Grandy
Project 6
03/28/2022
File name Landscape.java
Class use: defines the landscape by having fields and methiods to hold checkout agents and finished customers
as well as landscape dimensions. Draws all checkoutagents and has a histogram making function.
*/
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Landscape {
    ArrayList<Vertex> verts = new ArrayList<Vertex>();
    Hunter h;
    Wumpus w;
    int height;
    int width;

    public Landscape(int h, int w) { // constructor for Landscape class
        // this.verts = verts;
        height = h;
        width = w;

    }

    public void addBackgroundAgent(Vertex v) {
        verts.add(v);
        System.out.println(verts);
    }

    public void addHunter(Hunter h) {
        this.h = h;

    }

    public void addWumpus(Wumpus w) {
        this.w = w;

    }

    public int getHeight() { // returns the height of the Landscape.
        return height;
    }

    public int getWidth() { // returns the width of the Landscape.
        return width;
    }

    public void draw(Graphics g, int scale) {
        for (Vertex v : verts) {
            v.draw(g, scale);
        }
        h.draw(g, scale);
        w.draw(g, scale);
        g.setColor(Color.BLACK);
        g.drawString(h.getGame(), 350, 100);
    }

}
