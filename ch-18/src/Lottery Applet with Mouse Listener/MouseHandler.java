// Mouse event handler for a selection button
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class MouseHandler extends MouseAdapter {
  Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
  Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

  // Handle mouse entering the selection button
  public void mouseEntered(MouseEvent e) {
    e.getComponent().setCursor(handCursor);    // Switch to hand cursor
  }

  // Handle mouse exiting the selection button
  public void mouseExited(MouseEvent e) {
    e.getComponent().setCursor(defaultCursor); // Change to default cursor
  }
}
