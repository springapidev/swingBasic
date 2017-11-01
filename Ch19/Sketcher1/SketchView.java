import javax.swing.JComponent;
import java.util.Observer;                  
import java.util.Observable;                  

class SketchView extends JComponent implements Observer {
  public SketchView(Sketcher theApp) {
    this.theApp = theApp;
  }

  // Method called by Observable object when it changes
  public void update(Observable o, Object rectangle) {
    // Code to respond to changes in the model...
  }

  private Sketcher theApp;           // The application object
}
