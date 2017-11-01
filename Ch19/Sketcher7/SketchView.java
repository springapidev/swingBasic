import javax.swing.JComponent;
import java.util.Observer;                  
import java.util.Observable;                  
import java.awt.Graphics;                                
import java.awt.Graphics2D;
import java.awt.Point; 
import java.awt.Rectangle;                               
import java.awt.event.MouseEvent;    
import javax.swing.event.MouseInputAdapter;    
import static Constants.SketcherConstants.*;

class SketchView extends JComponent implements Observer {
  public SketchView(Sketcher theApp) {
    this.theApp = theApp;
    MouseHandler handler = new MouseHandler();        // create the mouse listener
    addMouseListener(handler);                        // Listen for button events
    addMouseMotionListener(handler);                  // Listen for motion events
  }

  // Method called by Observable object when it changes
  public void update(Observable o, Object rectangle) {
    if(rectangle != null & rectangle instanceof Rectangle) {
      repaint((Rectangle)rectangle);
    } else {
      repaint();
    }
  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D)g;                     // Get a 2D device context
    for(Element element : theApp.getModel()) {          // Go through the list
      g2D.setPaint(element.getColor());                 // Set the element color
      g2D.draw(element.getShape());                     // Draw its shape
    }
  }

  private Sketcher theApp;                              // The application object

  class MouseHandler extends MouseInputAdapter {
    public void mousePressed(MouseEvent e)  {
      start = e.getPoint();                             // Save the cursor position in start
      if(button1Down = (e.getButton() == MouseEvent.BUTTON1)) {
        g2D = (Graphics2D)getGraphics();                    // Get graphics context
        g2D.setXORMode(getBackground());                    // Set XOR mode
        g2D.setPaint(theApp.getWindow().getElementColor()); // Set color
      }
    }

    public void mouseDragged(MouseEvent e) {
      last = e.getPoint();                              // Save cursor position

      if(button1Down) {
        if(tempElement == null) {                       // Is there an element?
          tempElement = createElement(start, last);     // No, so create one
        } else {
          g2D.draw(tempElement.getShape());             // Yes - draw to erase it
          tempElement.modify(start, last);              // Now modify it
        }
        g2D.draw(tempElement.getShape());               // and draw it
      }
    }

    public void mouseReleased(MouseEvent e) {
      if(button1Down = (e.getButton()==MouseEvent.BUTTON1)) {
        button1Down = false;                     // Reset the button 1 flag

        if(tempElement != null) {                // If there is an element...
          theApp.getModel().add(tempElement);    // ...add it to the model...
          tempElement = null;                    // ...and reset the field
        }
        if(g2D != null) {                        // If there's a graphics context
          g2D.dispose();                         // ...release the resource...
          g2D = null;                            // ...and reset field to null
        }
        start = last = null;                     // Remove the points
      }
    }

    private Element createElement(Point start, Point end) {
      switch(theApp.getWindow().getElementType()) {
        case LINE:
          return new Element.Line(start, end, theApp.getWindow().getElementColor());   

        case RECTANGLE:
          return new Element.Rectangle(start, end, theApp.getWindow().getElementColor());

        case CIRCLE:
          return new Element.Circle(start, end, theApp.getWindow().getElementColor());

        case CURVE:
//          return new Element.Curve(start, end, theApp.getWindow().getElementColor());

        default:
          assert false;                     // We should never get to here
       }
       return null;
    }

    private Point start;                     // Stores cursor position on press
    private Point last;                      // Stores cursor position on drag
    private Element tempElement;             // Stores a temporary element
    private boolean button1Down = false;     // Flag for button 1 state
    private Graphics2D g2D;                  // Temporary graphics context
  }
}
