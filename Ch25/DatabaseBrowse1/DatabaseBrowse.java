import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;

class DatabaseBrowse extends JFrame {
  public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
  }

  private static void createGUI() {
   DatabaseBrowse theApp = new DatabaseBrowse();     // Create application object
  }

  // Constructor
  public DatabaseBrowse() {
    super("Database Browser");
    
    setBounds(0, 0, 400, 300);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Create labels for input fields
    JLabel dbURLLabel  = new JLabel( "Database URL: ");
    JLabel userIDLabel = new JLabel( "User ID:", JLabel.RIGHT);
    userIDLabel.setPreferredSize(dbURLLabel.getPreferredSize()); // Set same size
    JLabel passwordLabel = new JLabel("Password: ");
    // Box for database URL input
    Box dbPane = Box.createHorizontalBox();    
    dbPane.add(dbURLLabel);
    dbPane.add(database);

    // Box for user ID and password input fields
    Box loginPane = Box.createHorizontalBox();
    loginPane.add(userIDLabel);
    loginPane.add(userIDInput);
    loginPane.add(passwordLabel);
    loginPane.add(passwordInput);
    
    Box inputPane = Box.createVerticalBox();
    inputPane.add(dbPane);
    inputPane.add(loginPane);
    getContentPane().add(inputPane, BorderLayout.NORTH);

    // Add message area
    status.setText("Enter a database URL and/or press Enter");
    status.setEditable(false);                   // No user input
    status.setLineWrap(true);                    // Lines wrap
    status.setWrapStyleWord(true);               // on word boundaries
    status.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    getContentPane().add(status, BorderLayout.SOUTH);

    setVisible(true);                            // Set window visible
    database.requestFocus();                     // Focus to the url input field
  }

  private String userID = "guest";
  private String password = "guest";
  private String url = "jdbc:odbc:technical_library";

  private JTextField database = new JTextField(url);
  private JTextField userIDInput = new JTextField(userID);
  private JPasswordField passwordInput = new JPasswordField(password);
  private JTextArea status = new JTextArea(3,30);
}
