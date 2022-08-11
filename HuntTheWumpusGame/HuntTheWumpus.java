import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Random;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

public class HuntTheWumpus {

    // These four fields are as in the LandscapeDisplay class (though
    // they are now all private)
    private JFrame win;
    private LandscapePanel canvas;
    private Landscape scape;
    private int scale;

    /** fields related to demo of mouse interaction **/
    // Unless you have a good reason to report the mouse position in
    // HuntTheWumpus, you should remove these fields and all the
    // code that affects them.
    // There here to demonstrate how you would add a message to the bottom
    // of the window. For HuntTheWumpus, you may want to use it to indicate
    // that the Hunter is armed or close to the Wumpus, or dead.
    JLabel fieldX; // Label field 1, displays the X location of the mouse
    JLabel fieldY; // Label field 2, displays the Y location of the mouse

    // controls whether the game is playing or exiting
    private enum PlayState {
        PLAY, STOP
    }

    private PlayState state;

    /**
     * Creates the main window
     * 
     * @param scape the Landscape that will hold the objects we display
     * @param scale the size of each grid element
     **/
    public HuntTheWumpus() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY;

        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale * 10, scale * 7);
        // This is test code that adds a few vertices.
        // You will need to update this to make a Graph first, then
        // add the vertices to the Landscape.

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Vertex v = new Vertex(i, j);
                v.setIsVis(true);
                this.scape.addBackgroundAgent(v);
            }

        }

        Graph g = new Graph();
        Hunter h = new Hunter(scape.verts.get(0));
        scape.addHunter(h);
        Random rand = new Random();
        Wumpus w = new Wumpus(scape.verts.get(rand.nextInt(25)));
        scape.addWumpus(w);

        //adds graph edges
        g.addBiEdge(scape.verts.get(0), scape.verts.get(1), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(1), scape.verts.get(2), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(2), scape.verts.get(3), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(3), scape.verts.get(4), Vertex.Direction.EAST, Vertex.Direction.WEST);

        g.addBiEdge(scape.verts.get(0), scape.verts.get(5), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(1), scape.verts.get(6), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(2), scape.verts.get(7), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(3), scape.verts.get(8), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(4), scape.verts.get(9), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);

        g.addBiEdge(scape.verts.get(5), scape.verts.get(6), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(6), scape.verts.get(7), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(7), scape.verts.get(8), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(8), scape.verts.get(9), Vertex.Direction.EAST, Vertex.Direction.WEST);

        g.addBiEdge(scape.verts.get(5), scape.verts.get(10), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(6), scape.verts.get(11), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(7), scape.verts.get(12), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(8), scape.verts.get(13), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(9), scape.verts.get(14), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);

        g.addBiEdge(scape.verts.get(10), scape.verts.get(11), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(11), scape.verts.get(12), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(12), scape.verts.get(13), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(13), scape.verts.get(14), Vertex.Direction.EAST, Vertex.Direction.WEST);

        g.addBiEdge(scape.verts.get(10), scape.verts.get(15), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(11), scape.verts.get(16), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(12), scape.verts.get(17), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(13), scape.verts.get(18), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(14), scape.verts.get(19), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);

        g.addBiEdge(scape.verts.get(15), scape.verts.get(16), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(16), scape.verts.get(17), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(17), scape.verts.get(18), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(18), scape.verts.get(19), Vertex.Direction.EAST, Vertex.Direction.WEST);

        g.addBiEdge(scape.verts.get(15), scape.verts.get(20), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(16), scape.verts.get(21), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(17), scape.verts.get(22), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(18), scape.verts.get(23), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);
        g.addBiEdge(scape.verts.get(19), scape.verts.get(24), Vertex.Direction.SOUTH, Vertex.Direction.NORTH);

        g.addBiEdge(scape.verts.get(20), scape.verts.get(21), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(21), scape.verts.get(22), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(22), scape.verts.get(23), Vertex.Direction.EAST, Vertex.Direction.WEST);
        g.addBiEdge(scape.verts.get(23), scape.verts.get(24), Vertex.Direction.EAST, Vertex.Direction.WEST);

        g.shortestPath(w.getHome());
        System.out.println(g.verticies);

        // Make the main window
        this.win = new JFrame("Basic Interactive Display");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel(this.scape.getWidth(),
                this.scape.getHeight());
        this.win.add(this.canvas, BorderLayout.CENTER);
        this.win.pack();

        // make the labels and a button and put them into the frame
        // below the canvas.
        this.fieldX = new JLabel("X");
        this.fieldY = new JLabel("Y");
        JButton quit = new JButton("Quit");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(this.fieldX);
        panel.add(this.fieldY);
        panel.add(quit);
        this.win.add(panel, BorderLayout.SOUTH);

        // Add key and button controls.
        // We are binding the key control object to the entire window.
        // That means that if a key is pressed while the window is
        // in focus, then control.keyTyped will be executed.
        // Because we are binding quit (the button) to control, control.actionPerformed
        // will
        // be called if the quit button is pressed. If you make more than one button,
        // then the same function will be called. Use an if-statement in the function
        // to determine which button was pressed (check out Control.actionPerformed and
        // this advice should make sense)
        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();
        quit.addActionListener(control);

        // for mouse control
        // Make a MouseControl object and then bind it to the canvas
        // (the part that displays the Landscape). When the mouse
        // enters, exits, moves, or clicks in the canvas, the appropriate
        // method will be called.
        MouseControl mc = new MouseControl();
        this.canvas.addMouseListener(mc);
        this.canvas.addMouseMotionListener(mc);

        // The last thing to do is make it all visible.
        this.win.setVisible(true);

    }

    private class LandscapePanel extends JPanel {

        /**
         * Creates the drawing canvas
         * 
         * @param height the height of the panel in pixels
         * @param width  the width of the panel in pixels
         **/
        public LandscapePanel(int height, int width) {
            super();
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen. The supplied Graphics
         * object is used to draw.
         * 
         * @param g the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw(g, scale);
        }
    } // end class LandscapePanel

    // This is the class where you define functions that are
    // executed when certain mouse events take place.
    private class MouseControl extends MouseInputAdapter {
        public void mouseMoved(MouseEvent e) {
            fieldX.setText("" + e.getPoint().x);
            fieldY.setText("" + e.getPoint().y);
        }

        public void mouseDragged(MouseEvent e) {
            fieldX.setText("" + e.getPoint().x);
            fieldY.setText("" + e.getPoint().y);
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Pressed: " + e.getClickCount());
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Released: " + e.getClickCount());
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println("Entered: " + e.getPoint());
        }

        public void mouseExited(MouseEvent e) {
            System.out.println("Exited: " + e.getPoint());
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println("Clicked: " + e.getClickCount());
        }
    } // end class MouseControl

    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
            System.out.println("Key Pressed: " + e.getKeyChar());
            if (("" + e.getKeyChar()).equalsIgnoreCase("q")) {
             
            }
            String s1 = "w";
            String s2 = "a";
            String s3 = "s";
            String s4 = "d";
            if (e.getKeyChar() == s3.charAt(0) && scape.h.arrow == false
                    && scape.h.getPosition().getNeighbors().get(1) != null) {
                scape.h.setPosition(scape.h.getPosition().getNeighbors().get(1));
                if (scape.h.getPosition() == scape.w.getHome()) {
                    scape.w.setVis(true);
                    scape.h.setGame("You were eaten by the Wumpus");
                    

                }

            }
            if (e.getKeyChar() == s4.charAt(0) && scape.h.arrow == false
                    && scape.h.getPosition().getNeighbors().get(2) != null) {
                scape.h.setPosition(scape.h.getPosition().getNeighbors().get(2));
                if (scape.h.getPosition() == scape.w.getHome()) {
                    scape.w.setVis(true);
                    scape.h.setGame("You were eaten by the Wumpus");
                   
                }
            }
            if (e.getKeyChar() == s2.charAt(0) && scape.h.arrow == false
                    && scape.h.getPosition().getNeighbors().get(0) != null) {
                scape.h.setPosition(scape.h.getPosition().getNeighbors().get(0));
                if (scape.h.getPosition() == scape.w.getHome()) {
                    scape.w.setVis(true);
                    scape.h.setGame("You were eaten by the Wumpus");
                
                }
            }
            if (e.getKeyChar() == s1.charAt(0) && scape.h.arrow == false
                    && scape.h.getPosition().getNeighbors().get(3) != null) {
                scape.h.setPosition(scape.h.getPosition().getNeighbors().get(3));
                if (scape.h.getPosition() == scape.w.getHome()) {
                    scape.w.setVis(true);
                    scape.h.setGame("You were eaten by the Wumpus");
                   

                }
            }

            if (Character.isSpaceChar(e.getKeyChar()) && scape.h.arrow == false && scape.h.getArrowNum() != 0) {
                scape.h.arrow = true;
                scape.h.setGame("Arrow Armed");

            } else if (Character.isSpaceChar(e.getKeyChar()) && scape.h.arrow == true) {
                scape.h.arrow = false;
            }

            if (e.getKeyChar() == s3.charAt(0) && scape.h.arrow == true) {
                scape.h.setGame("Arrow Fired");
                scape.h.setArrow(scape.h.getArrowNum() - 1);
                scape.h.arrow = false;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (scape.h.getPosition().getNeighbors().get(1) == scape.w.getHome()) {
                    scape.h.setGame("You Killed The Wumpus and Won the Game!");
                    scape.w.setVis(true);
                } else {
                    scape.h.setGame("You missed and have " + scape.h.getArrowNum() + " arrows left!");
                }

            }
            if (e.getKeyChar() == s4.charAt(0) && scape.h.arrow == true) {
                scape.h.setGame("Arrow Fired");
                scape.h.setArrow(scape.h.getArrowNum() - 1);
                scape.h.arrow = false;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (scape.h.getPosition().getNeighbors().get(2) == scape.w.getHome()) {
                    scape.h.setGame("You Killed The Wumpus and Won the Game!");
                    scape.w.setVis(true);
                } else {
                    scape.h.setGame("You missed and have " + scape.h.getArrowNum() + " arrows left!");
                }
            }
            if (e.getKeyChar() == s2.charAt(0) && scape.h.arrow == true) {
                scape.h.setGame("Arrow Fired");
                scape.h.setArrow(scape.h.getArrowNum() - 1);
                scape.h.arrow = false;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (scape.h.getPosition().getNeighbors().get(0) == scape.w.getHome()) {
                    scape.h.setGame("You Killed The Wumpus and Won the Game!");
                    scape.w.setVis(true);
                } else {
                    scape.h.setGame("You missed and have " + scape.h.getArrowNum() + " arrows left!");
                }
            }
            if (e.getKeyChar() == s1.charAt(0) && scape.h.arrow == true) {
                scape.h.setGame("Arrow Fired");
                scape.h.setArrow(scape.h.getArrowNum() - 1);
                scape.h.arrow = false;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (scape.h.getPosition().getNeighbors().get(3) == scape.w.getHome()) {
                    scape.h.setGame("You Killed The Wumpus and Won the Game!");
                    scape.w.setVis(true);
                } else {
                    scape.h.setGame("You missed and have " + scape.h.getArrowNum() + " arrows left!");
                }
            }

        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed
            if (event.getActionCommand().equalsIgnoreCase("Quit")) {
                System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }

        }

    } // end class Control

    public void repaint() {
        this.win.repaint();

    }

    public void dispose() {
        this.win.dispose();
    }

    public static void main(String[] eargv) throws InterruptedException {
        HuntTheWumpus w = new HuntTheWumpus();
        while (w.state == PlayState.PLAY) {
            w.win.repaint();
            Thread.sleep(33);
            if (w.scape.w.getVis() == true) {
                break;
            }
        }
        Thread.sleep(10000);
        System.out.println("Disposing window");
        w.dispose();
    }

} // end class InteractiveLandscapeDisplay
