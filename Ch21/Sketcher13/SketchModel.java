import java.util.Observable;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.Serializable;
import java.awt.Rectangle;

class SketchModel extends Observable implements Iterable<Element>, Serializable {
  public boolean remove(Element element) {
    boolean removed = elements.remove(element);
    if(removed) {
      setChanged();
      notifyObservers(element.getBounds());
    }

    return removed;
  }
  
  public void add(Element element) {
    elements.add(element);
    setChanged();
    notifyObservers(element.getBounds());
  }

  public Iterator<Element> iterator() {
    return elements.iterator();  
  }

  // Get the rectangle enclosing an entire sketch
  Rectangle getModelExtent() {
    Rectangle rect = new Rectangle();                // An empty rectangle
    for(Element element : elements) {                // Go through the list
      rect.add(element.getBounds());                 // Expand union
    }
    if(rect.width == 0) {                            // Make sure width
      rect.width = 1;                                // is non-zero
    }
    if(rect.height == 0) {                           // and the height
      rect.height = 1;
    }
    return rect;
  }

  protected LinkedList<Element> elements = new LinkedList<Element>();
}
