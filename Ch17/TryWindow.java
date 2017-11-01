import javax.swing.JFrame;

public class TryWindow {
  // The window object
  static JFrame aWindow = new JFrame("This is the Window Title"); 

  public static void main(String[] args) {
    int windowWidth = 400;                              // Window width in pixels
    int windowHeight = 150;                             // Window height in pixels
    aWindow.setBounds(50, 100,                          // Set position
                      windowWidth, windowHeight);       // and size
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    aWindow.setVisible(true);                           // Display the window
  }
}
