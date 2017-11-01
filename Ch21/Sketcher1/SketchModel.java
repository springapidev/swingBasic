import java.util.Observable;
import java.util.LinkedList;
import java.util.Iterator;

class SketchModel extends Observable implements Iterable<Element> {
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

  protected LinkedList<Element> elements = new LinkedList<Element>();
}
