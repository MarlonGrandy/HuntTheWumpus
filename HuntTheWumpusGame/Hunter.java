

/*
Marlon Grandy
03/28/2022
File name Hunter.java
Class use: defines the hunter that hunts the wumpus with all essential methods.
*/
import java.awt.Color;
import java.awt.Graphics;


public class Hunter {
    Vertex position;
    boolean arrow;
    String gameBox;
    int numArrow;

    public Hunter(Vertex p) {
        position = p;
        arrow = false;
        gameBox = " ";
        numArrow = 5;
    }

    public void setPosition(Vertex p) {
        position = p;
    }

    public boolean getArrow() {
        return arrow;
    }

    public void setArrow(boolean a) {
        arrow = a;
    }

    public Vertex getPosition() {
        return position;
    }

    public void setGame(String s) {
        gameBox = s;
    }

    public String getGame() {
        return gameBox;
    }

    public int getArrowNum() {
        return numArrow;
    }

    public void setArrow(int a) {
        numArrow = a;
    }

    public void draw(Graphics g, int scale) {
        g.setColor(Color.BLUE);
        g.fillRect(position.getX() * scale + 25, position.getY() * scale + 25, 15, 15);
    }

}
