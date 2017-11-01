import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.util.Observer;                  
import java.util.Observable;                  
import java.awt.Graphics;                                
import java.awt.Graphics2D;
import java.awt.Point; 
import java.awt.Rectangle;                               
import java.awt.Component;                               
import java.awt.Font;                               
import java.awt.font.TextLayout;  
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
      element.draw(g2D);                                // Element draws itself
    }
  }

  private Sketcher theApp;                              // The application object

  class MouseHandler extends MouseInputAdapter {
    public void mousePressed(MouseEvent e)  {
      start = e.getPoint();                             // Save the cursor position in start
      if((button1Down = (e.getButton() == MouseEvent.BUTTON1)) && 
                         (theApp.getWindow().getElementType() != TEXT)) {
        g2D = (Graphics2D)getGraphics();                // Get graphics context
        g2D.setXORMode(getBackground());                // Set XOR mode
      }
    }

    public void mouseDragged(MouseEvent e) {
      last = e.getPoint();                              // Save cursor position

      if(button1Down && (theApp.getWindow().getElementType() != TEXT)) {
        if(tempElement == null) {                       // Is there an element?
          tempElement = createElement(start, last);     // No, so create one
        } else {
          tempElement.draw(g2D);                        // Yes - draw to erase it
          tempElement.modify(start, last);              // Now modify it
        }
        tempElement.draw(g2D);                          // and draw it
      }
    }

    public void mouseReleased(MouseEvent e) {
      if(button1Down = (e.getButton()==MouseEvent.BUTTON1) &&
                             (theApp.getWindow().getElementType() != TEXT)) {
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

    public void mouseClicked(MouseEvent e) {
      if((e.getButton()== MouseEvent.BUTTON1) && (theApp.getWindow().getElementType() == TEXT)) {
  
        start = e.getPoint();                  // Save cursor position - start of text
        String text = JOptionPane.showInputDialog(
                   (Component)e.getSource(),   // Used to get the frame
                   "Enter Text:",              // The message
                   "Dialog for Text Element",  // Dialog title
                   JOptionPane.PLAIN_MESSAGE); // No icon
   
        if(text != null && text.length()!= 0)  { // If we have text        
                                                 // create the element
          g2D = (Graphics2D)getGraphics();
          Font font = theApp.getWindow().getCurrentFont();
          tempElement = new Element.Text(
            font,                                    // The font
            text,                                    // The text
            start,                                   // Position of the  text
            theApp.getWindow().getElementColor(),    // The text color
            new java.awt.font.TextLayout(text, font, // The bounding rectangle
            g2D.getFontRenderContext()).getBounds().getBounds()
                                         );

          if(tempElement != null) {                  // If we created one
            theApp.getModel().add(tempElement);      // add it to the model
            tempElement = null;                      // and reset the field
          }
          g2D.dispose();                             // Release context resources
          g2D = null;
          start = null;
        }
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
          return new Element.Curve(start, end, theApp.getWindow().getElementColor());

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
