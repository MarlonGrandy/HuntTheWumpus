import java.awt.Color;
import java.awt.Graphics;

public class Wumpus {
    private Vertex home;
    private boolean isVis;

    public Wumpus(Vertex v) {
        home = v;
        isVis = false;
    }

    public void draw(Graphics g, int scale) {
        if (isVis) {
            g.setColor(Color.BLACK);
            g.fillRect(home.getX() * scale + 25, home.getY() * scale + 25, 15, 15);
        }

    }

    public Vertex getHome() {
        return home;
    }

    public void setHome(Vertex h) {
        home = h;
    }

    public boolean getVis() {
        return isVis;
    }

    public void setVis(Boolean h) {
        isVis = h;
    }
}
