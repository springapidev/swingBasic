import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.util.Observer;                  
import java.util.Observable;                  
import java.awt.Graphics;                                
import java.awt.Graphics2D;
import java.awt.Point; 
import java.awt.Rectangle;                               
import java.awt.Component;                               
import java.awt.Font;
                               
import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.PrinterException;

import java.awt.font.TextLayout;  
import java.awt.geom.Line2D;    
import java.awt.event.MouseEvent;    
import java.awt.event.ActionEvent;    
import java.awt.event.ActionListener;    
import javax.swing.event.MouseInputAdapter;    
import static Constants.SketcherConstants.*;

class SketchView extends JComponent implements Observer, ActionListener, Printable, Pageable {
  public SketchView(Sketcher theApp) {
    this.theApp = theApp;
    MouseHandler handler = new MouseHandler();        // create the mouse listener
    addMouseListener(handler);                        // Listen for button events
    addMouseMotionListener(handler);                  // Listen for motion events

    // Add the pop-up menu items
    moveItem = elementPopup.add(new JMenuItem("Move"));
    deleteItem = elementPopup.add(new JMenuItem("Delete"));
    rotateItem = elementPopup.add(new JMenuItem("Rotate"));
    sendToBackItem = elementPopup.add(new JMenuItem("Send-to-back"));

    // Add the menu item listeners
    moveItem.addActionListener(this);
    deleteItem.addActionListener(this);
    rotateItem.addActionListener(this);
    sendToBackItem.addActionListener(this);
  }

  // Method to return page count - always two pages
  public int getNumberOfPages() {
    return 2;
  }

  // Method to return the Printable object that will render the page
  public Printable getPrintable(int pageIndex) {
    if(pageIndex == 0) {                         // For the first page
      return new SketchCoverPage(theApp);        // return the cover page
    } else {
      return this;                               
    }
  }

  // Method to return a PageFormat object to suit the page
  public PageFormat getPageFormat(int pageIndex) {
    if(pageIndex==0) {               // If it's the cover page...
                                     // ...make the margins twice the size
      // Create a duplicate of the current page format
      PageFormat pageFormat = (PageFormat)(theApp.getWindow().getPageFormat().clone());
      Paper paper = pageFormat.getPaper();

      // Get top and left margins - x & y coordinates of top-left corner
      // of imageable area are the left & top margins  
      double leftMargin = paper.getImageableX();
      double topMargin = paper.getImageableY();

      // Get right and bottom margins
      double rightMargin = paper.getWidth()-paper.getImageableWidth()-leftMargin;
      double bottomMargin = paper.getHeight()-paper.getImageableHeight()-topMargin;

      // Double the margin sizes
      leftMargin *= 2.0;
      rightMargin *= 2.0;
      topMargin *= 2.0;
      bottomMargin *= 2.0;

      // Set new printable area for the paper
      paper.setImageableArea(leftMargin, topMargin,
                           paper.getWidth()-leftMargin-rightMargin,
                           paper.getHeight()-topMargin-bottomMargin);

      pageFormat.setPaper(paper);                // Restore the paper
      pageFormat.setOrientation(PageFormat.LANDSCAPE);      
      return pageFormat;                             // Return the page format
    }
    // For pages after the first, use the object from the app window
    return theApp.getWindow().getPageFormat();
  }

  // Method to print the sketch
  public int print(Graphics g,              // Graphics context for printing
                   PageFormat pageFormat,   // The page format
                   int pageIndex)           // Index number of current page
             throws PrinterException {
    Graphics2D g2D = (Graphics2D) g;
    // Get sketch bounds
    Rectangle rect = theApp.getModel().getModelExtent();

    // Calculate the scale to fit sketch to page
    double scaleX = pageFormat.getImageableWidth()/rect.width;
    double scaleY = pageFormat.getImageableHeight()/rect.height;

    // Get minimum scale factor
    double scale = Math.min(scaleX, scaleY);  

    // Move origin to page printing area corner
    g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

    g2D.scale(scale, scale);              // Apply scale factor

    g2D.translate(-rect.x, -rect.y);      // Move origin to rect top left

    paint(g2D);                           // Draw the sketch
    return PAGE_EXISTS;
  }

  // Method called by Observable object when it changes
  public void update(Observable o, Object rectangle) {
    if(rectangle != null & rectangle instanceof Rectangle) {
      repaint((Rectangle)rectangle);
    } else {
      repaint();
    }
  }

  // Handle context menu events
  public void actionPerformed(ActionEvent e ) {
    Object source = e.getSource();
    if(source == moveItem) {
      mode = MOVE;
      selectedElement = highlightElement;
    } else if(source == deleteItem) {
      if(highlightElement != null) {                    // If there's an element
        theApp.getModel().remove(highlightElement);     // then remove it
        highlightElement = null;                        // Remove the reference
      }

    } else if(source == rotateItem) {
      mode = ROTATE;
      selectedElement = highlightElement;
    } else if(source == sendToBackItem) {
      if(highlightElement != null) {
        theApp.getModel().remove(highlightElement);
        theApp.getModel().add(highlightElement);
        highlightElement.setHighlighted(false);
        highlightElement = null;
        repaint();
      }
    }
  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D)g;                     // Get a 2D device context
    for(Element element : theApp.getModel()) {          // Go through the list
      element.draw(g2D);                                // Element draws itself
    }
  }

  private Sketcher theApp;                              // The application object
  private Element highlightElement;                     // Highlighted element
  private JPopupMenu elementPopup = new JPopupMenu("Element");
  private JMenuItem moveItem, deleteItem,rotateItem, sendToBackItem;
  private int mode = NORMAL;
  private Element selectedElement;
  
  class MouseHandler extends MouseInputAdapter {
      // Process pop-up trigger event
      public void processPopupTrigger(MouseEvent e) {
        start = e.getPoint();                           // Save the cursor position in start
        if(highlightElement == null) {
          theApp.getWindow().getPopup().show((Component)e.getSource(),start.x, start.y);
        } else {
          elementPopup.show((Component)e.getSource(), start.x, start.y);
        }    
        start = null;
      }

    public void mousePressed(MouseEvent e)  {
      if(e.isPopupTrigger()) {
        processPopupTrigger(e);
      } else if((button1Down = (e.getButton() == MouseEvent.BUTTON1))) {
        start = e.getPoint();                  // Save the cursor position in start
        g2D = (Graphics2D)getGraphics();                // Get graphics context
        g2D.setXORMode(getBackground());                // Set XOR mode
      }
    } 

    public void mouseDragged(MouseEvent e) {
      last = e.getPoint();                              // Save cursor position
      if(button1Down && (theApp.getWindow().getElementType() != TEXT)&& (mode == NORMAL)) {
        if(tempElement == null) {                       // Is there an element?
          tempElement = createElement(start, last);     // No, so create one
        } else {
          tempElement.draw(g2D);                        // Yes - draw to erase it
          tempElement.modify(start, last);              // Now modify it
        }
        tempElement.draw(g2D);                          // and draw it
      } else if(button1Down && mode == MOVE && selectedElement != null) {
        selectedElement.draw(g2D);                    // Draw to erase the element
        selectedElement.move(last.x-start.x, last.y-start.y);  // Move it
        selectedElement.draw(g2D);                     // Draw in its new position
        start = last;                                  // Make start current point
      } else if(button1Down && mode == ROTATE && selectedElement != null) {
        selectedElement.draw(g2D);                   // Draw to erase the element
        selectedElement.rotate(getAngle(selectedElement.getPosition(),
                                                                    start, last));
        selectedElement.draw(g2D);                  // Draw in its new position
        start = last;                               // Make start current point
      }
    }

    // Helper method for calculating the rotation angle
    double getAngle(Point position, Point start, Point last) {
      // Get perpendicular distance from last to the line from position to start
      double perp = Line2D.ptLineDist(position.x, position.y,
                                      last.x, last.y, start.x, start.y);
      // Get the distance from position to start
      double hypotenuse = position.distance(start);
      if(hypotenuse == 0.0) {                      // Make sure it's
        hypotenuse = 1.0;                          // non-zero
      }

      // Angle is the arc sine of perp/hypotenuse. Clockwise is positive angle
      return -Line2D.relativeCCW(position.x, position.y,
                                 start.x, start.y,
                                 last.x, last.y)*Math.asin(perp/hypotenuse);
    }

    public void mouseReleased(MouseEvent e) {
      if(e.isPopupTrigger()) {
        processPopupTrigger(e);
      } else if((e.getButton()==MouseEvent.BUTTON1) && (theApp.getWindow().getElementType() != TEXT) && mode == NORMAL) {
        button1Down = false;                     // Reset the button 1 flag
        if(tempElement != null) {
       theApp.getModel().add(tempElement);       // Add element to the model
          tempElement = null;                    // No temporary element now stored
        }
      } else if((e.getButton()==MouseEvent.BUTTON1) &&
                (mode == MOVE || mode == ROTATE)) {
        button1Down = false;                     // Reset the button 1 flag
        if(selectedElement != null) {
          repaint();
        }
        mode = NORMAL;
      }

      if(g2D != null) {
        g2D.dispose();                           // Release graphic context resource
        g2D = null;                              // Set it to null
      }
      start = last = null;                       // Remove the points
      selectedElement = tempElement = null;      // Reset elements
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

    // Handle mouse moves
    public void mouseMoved(MouseEvent e) {
      Point currentCursor = e.getPoint();  // Get current cursor position

      for(Element element : theApp.getModel())  {  // Go through the list
        if(element.getBounds().contains(currentCursor)) { // Under the cursor?
          if(element==highlightElement) {          // If it's already highlighted
            return;                                // we are done
          }
          // The element under the cursor is not highlighted
          g2D = (Graphics2D)getGraphics();         // Get graphics context
 
          // Un highlight any old highlighted element
          if(highlightElement!=null)   {           // If an element is highlighted
            highlightElement.setHighlighted(false);// un-highlight it and
            highlightElement.draw(g2D);            // draw it normal color
          }

          element.setHighlighted(true);           // Set highlight for new element
          highlightElement = element;             // Store new highlighted element
          element.draw(g2D);                      // Draw it highlighted 
          g2D.dispose();                          // Release graphic context resources
          g2D = null;
          return;
        }
      }

      // Here there is no element under the cursor so...
      if(highlightElement!=null)   {            // If an element is highlighted
        g2D = (Graphics2D)getGraphics();        // Get graphics context
        highlightElement.setHighlighted(false); // ...turn off highlighting
        highlightElement.draw(g2D);             // Redraw the element
        highlightElement = null;                // No element highlighted
        g2D.dispose();                          // Release graphic context resources
        g2D = null;
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
