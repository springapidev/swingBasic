// Sketching application
import java.awt.Toolkit;
import java.awt.Dimension;

public class Sketcher {
  public static void main(String[] args) {
    window = new SketchFrame("Sketcher");        // Create the app window
    Toolkit theKit = window.getToolkit();        // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();  // Get screen size

    // Set the position to screen center & size to half screen size
    window.setBounds(wndSize.width/4, wndSize.height/4,        // Position
                      wndSize.width/2, wndSize.height/2);      // Size

    window.setVisible(true);
  }

  private static SketchFrame window;             // The application window
}
