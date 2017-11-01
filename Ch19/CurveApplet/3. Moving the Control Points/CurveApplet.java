import javax.swing.JApplet;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.Graphics;

import java.awt.geom.Point2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.event.MouseInputAdapter;

import java.awt.event.MouseEvent;

public class CurveApplet extends JApplet {
  // Initialize the applet
  public void init() {
    pane = new CurvePane();                       // Create pane containing curves
    Container content = getContentPane();         // Get the content pane

    // Add the pane displaying the curves to the content pane for the applet
    content.add(pane);                  // BorderLayout.CENTER is default position
    
    MouseHandler handler = new MouseHandler();    // Create the listener
    pane.addMouseListener(handler);               // Monitor mouse button presses
    pane.addMouseMotionListener(handler);         // as well as movement
  }

  // Class defining a pane on which to draw
  class CurvePane extends JComponent {
    // Constructor
    public CurvePane() {
      quadCurve = new QuadCurve2D.Double(               // Create quadratic curve
                       startQ.x, startQ.y,              // Segment start point
                       control.x, control.y,            // Control point
                       endQ.x, endQ.y);                 // Segment end point

      cubicCurve = new CubicCurve2D.Double(             // Create cubic curve
                       startC.x, startC.y,              // Segment start point
                       controlStart.x, controlStart.y,  // Control point for start
                       controlEnd.x, controlEnd.y,      // Control point for end
                           endC.x, endC.y);             // Segment end point
    }

    public void paint(Graphics g) {
      Graphics2D g2D = (Graphics2D)g;                   // Get a 2D device context

      // Update the curves with the current control point positions
      quadCurve.ctrlx = ctrlQuad.getCenter().x;
      quadCurve.ctrly = ctrlQuad.getCenter().y;
      cubicCurve.ctrlx1 = ctrlCubic1.getCenter().x;
      cubicCurve.ctrly1 = ctrlCubic1.getCenter().y;
      cubicCurve.ctrlx2 = ctrlCubic2.getCenter().x;
      cubicCurve.ctrly2 = ctrlCubic2.getCenter().y;

      // Draw the curves
      g2D.setPaint(Color.BLUE);
      g2D.draw(quadCurve);
      g2D.draw(cubicCurve);
      
      // Create and draw the markers showing the control points
      g2D.setPaint(Color.RED);                   // Set the color
      ctrlQuad.draw(g2D);                       
      ctrlCubic1.draw(g2D);
      ctrlCubic2.draw(g2D);

      // Draw tangents from the curve end points to the control marker centers
      Line2D.Double tangent = new Line2D.Double(startQ, ctrlQuad.getCenter());
      g2D.draw(tangent);
      tangent = new Line2D.Double(endQ, ctrlQuad.getCenter());
      g2D.draw(tangent);

      tangent = new Line2D.Double(startC, ctrlCubic1.getCenter());
      g2D.draw(tangent);
      tangent = new Line2D.Double(endC, ctrlCubic2.getCenter());
      g2D.draw(tangent);

    }
  }
  
  // Points for quadratic curve
  Point2D.Double startQ = new Point2D.Double(50, 75);         // Start point
  Point2D.Double endQ = new Point2D.Double(150, 75);          // End point
  Point2D.Double control = new Point2D.Double(80, 25);        // Control point

  // Points for cubic curve
  Point2D.Double startC = new Point2D.Double(50, 150);         // Start point
  Point2D.Double endC = new Point2D.Double(150, 150);          // End point
  Point2D.Double controlStart = new Point2D.Double(80, 100);  // 1st control point
  Point2D.Double controlEnd = new Point2D.Double(160, 100);   // 2nd control point

   // Markers for control points
   Marker ctrlQuad = new Marker(control);
   Marker ctrlCubic1 = new Marker(controlStart);
   Marker ctrlCubic2 = new Marker(controlEnd);

  QuadCurve2D.Double quadCurve;                          // Quadratic curve
  CubicCurve2D.Double cubicCurve;                        // Cubic curve
  CurvePane pane = new CurvePane();                      // Pane to contain curves


  // Inner class defining a control point marker
  class Marker {
    public Marker(Point2D.Double control)  {
      center = control;                   // Save control point as circle center

      // Create circle around control point
      circle = new Ellipse2D.Double(control.x-radius, control.y-radius,
                                    2.0*radius, 2.0*radius); 
    }

      // Draw the marker
      public void draw(Graphics2D g2D) {
        g2D.draw(circle);
      }

     // Get center of marker - the control point position
      Point2D.Double getCenter() {
        return center;
    }

    // Test if a point x,y is inside the marker
    public boolean contains(double x, double y) {
      return circle.contains(x,y); 
    }

    // Sets a new control point location
    public void setLocation(double x, double y) {
      center.x = x;                      // Update control point
      center.y = y;                      // coordinates
      circle.x = x-radius;               // Change circle position
      circle.y = y-radius;               // correspondingly
    }

    Ellipse2D.Double circle;               // Circle around control point
    Point2D.Double center;                 // Circle center - the control point
    static final double radius = 3;        // Radius of circle
  }

  class MouseHandler extends MouseInputAdapter {
    public void mousePressed(MouseEvent e) {
      // Check if the cursor is inside any marker
      if(ctrlQuad.contains(e.getX(), e.getY()))
        selected = ctrlQuad;
      else if(ctrlCubic1.contains(e.getX(), e.getY()))
        selected = ctrlCubic1;
      else if(ctrlCubic2.contains(e.getX(), e.getY()))
        selected = ctrlCubic2;
    }

    public void mouseReleased(MouseEvent e) {
      selected = null;                             // Deselect any selected marker
    }
    
    public void mouseDragged(MouseEvent e) {
      if(selected != null) {                       // If a marker is selected
        // Set the marker to current cursor position
        selected.setLocation(e.getX(), e.getY());
        pane.repaint();                             // Redraw pane contents
      }
    }

    Marker selected = null;                 // Stores reference to selected marker
  }
}
