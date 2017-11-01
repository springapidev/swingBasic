import javax.swing.JApplet;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.Graphics;

import java.awt.geom.Point2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;

public class CurveApplet extends JApplet {
  // Initialize the applet
  public void init() {
    pane = new CurvePane();                       // Create pane containing curves
    Container content = getContentPane();         // Get the content pane

    // Add the pane displaying the curves to the content pane for the applet
    content.add(pane);                  // BorderLayout.CENTER is default position
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

      // Draw the curves
      g2D.setPaint(Color.BLUE);
      g2D.draw(quadCurve);
      g2D.draw(cubicCurve);
    }
  }
  // Points for quadratic curve
  Point2D.Double startQ = new Point2D.Double(50, 75);         // Start point
  Point2D.Double endQ = new Point2D.Double(150, 75);          // End point
  Point2D.Double control = new Point2D.Double(80, 25);        // Control point

  // Points for cubic curve
  Point2D.Double startC = new Point2D.Double(50, 150);         // Start point
  Point2D.Double endC = new Point2D.Double(150, 150);          // End point
  Point2D.Double controlStart = new Point2D.Double(80, 100);   // 1st control point
  Point2D.Double controlEnd = new Point2D.Double(160, 100);    // 2nd control point

  QuadCurve2D.Double quadCurve;                          // Quadratic curve
  CubicCurve2D.Double cubicCurve;                        // Cubic curve
  CurvePane pane = new CurvePane();                      // Pane to contain curves
}
