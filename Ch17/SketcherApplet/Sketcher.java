// Sketching application
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JApplet;

public class Sketcher extends JApplet {
  public static void main(String[] args) {
    theApp = new Sketcher();              // Create the application object
    theApp.init();                        // ...and initialize it
  }

  public void init() {
    window = new SketchFrame("Sketcher");        // Create the app window
    Toolkit theKit = window.getToolkit();        // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();  // Get screen size

    // Set the position to screen center & size to half screen size
    window.setBounds(wndSize.width/4, wndSize.height/4,        // Position
                     wndSize.width/2, wndSize.height/2);       // Size

    // The field theApp will be null when Sketcher is an applet
    if(theApp != null) {
      window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
    } 
    window.setVisible(true);
  }

  private static SketchFrame window;              // The application window
  private static Sketcher theApp;                 // The application object
}
