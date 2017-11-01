import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.BorderFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

class DatabaseBrowse extends JFrame implements ActionListener, TreeSelectionListener {
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
    dbURLLabel.setDisplayedMnemonic('D');          // Set accelerator
    dbURLLabel.setLabelFor(database);              // Connect label to field
    database.setFocusAccelerator('D');             // Identify accelerator to field

    JLabel userIDLabel = new JLabel( "User ID:", JLabel.RIGHT);
    userIDLabel.setDisplayedMnemonic('U');         // Set accelerator
    userIDLabel.setLabelFor(userIDInput);          // Connect label to field
    userIDInput.setFocusAccelerator('U');          // Identify accelerator to field

    userIDLabel.setPreferredSize(dbURLLabel.getPreferredSize()); // Set same size
    JLabel passwordLabel = new JLabel("Password: ");
    passwordLabel.setDisplayedMnemonic('P');       // Set accelerator
    passwordLabel.setLabelFor(passwordInput);      // Connect label to field
    passwordInput.setFocusAccelerator('P');        // Identify accelerator to field


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
    status.setEditable(false);                    // No user input
    status.setLineWrap(true);                     // Lines wrap
    status.setWrapStyleWord(true);                // on word boundaries
    status.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    getContentPane().add(status, BorderLayout.SOUTH);

    // Create tree to go in left split pane
    dbNode = new DefaultMutableTreeNode("No database");
    dbTreeModel = new DefaultTreeModel(dbNode);
    dbTree = new JTree(dbTreeModel);
    treePane = new JScrollPane(dbTree);
    treePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));


    // Create table to go in right split pane
    tableModel = new ResultsModel();
    JTable table = new JTable(tableModel);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
    tablePane = new JScrollPane(table);
    tablePane.setBorder(BorderFactory.createLineBorder(Color.darkGray)); 

    JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          true,        // Continuous relayout
                                          treePane,    // Left pane content
                                          tablePane);  // Right pane content
    getContentPane().add(splitpane, BorderLayout.CENTER);
    splitpane.setDividerLocation(200);           // Left pane 200 pixels wide
     
    // Add event listeners
    database.addActionListener(this);
    userIDInput.addActionListener(this);
    passwordInput.addActionListener(this);
    dbTree.addTreeSelectionListener(this);

    pack();
    setVisible(true);                            // Set window visible
    database.requestFocus();                     // Focus to the url input field
    
    // Attempt to load all drivers
    for(String driver : drivers) {
      try {
        Class.forName(driver);
      } catch(ClassNotFoundException cnfe) {
        System.err.println(cnfe);
        status.setText("Driver load failed: " + cnfe.getMessage());
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();               // Get source of the event
    if(source == database    ||                  // If its URL input,
       source == userIDInput ||                  // or userID input,
       source == passwordInput) {                // or password input...
      // ...try for a connection
      url = database.getText();                  // Get database URL
      userID = userIDInput.getText();            // Get user ID

      char[] pw = passwordInput.getPassword();   // Get password
      if(pw != null) {
        password = new String(pw);
      }
      if(url == null || url.length() == 0) {
        status.setText("Please specify a database URL ");
        return;
      }
      openConnection();
      password = null;                           // For security
    }
  }

  public void openConnection() {
    try {
      if(connection != null) {                   // If there is a connection
        connection.close();                      // close it

        // Reset the table data 
        tableModel.setResultSet(null);
        tablePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        // Reset the tree displaying metadata
        dbNode = new DefaultMutableTreeNode("No database");
        dbTreeModel.setRoot(dbNode);
        dbTree.setRootVisible(true);
        treePane.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        dbTreeModel.reload();
      }

      // Now open the new connection
      connection = DriverManager.getConnection(url, userID, password);
      status.setText("Database connection established");
      statement = connection.createStatement();  // Create statement for query

      dbNode = new DefaultMutableTreeNode(url);  // Root node is URL
      dbTreeModel.setRoot(dbNode);               // Set root in model
      setupTree(connection.getMetaData());       // Set up tree with metadata

      treePane.setBorder(BorderFactory.createTitledBorder(
                         BorderFactory.createLineBorder(Color.darkGray),
                         url,
                         TitledBorder.CENTER,
                         TitledBorder.DEFAULT_POSITION));
      dbTree.setRootVisible(false);              // Now hide the root node
      dbTreeModel.reload();                      // Get the tree redisplayed
    } catch(SQLException sqle) {
      status.setText(sqle.getMessage());              // Display first message
      do {                                            // loop through exceptions
        System.err.println("Exception occurred:\nMessage: " + sqle.getMessage());
        System.err.println("SQL state: " + sqle.getSQLState());
        System.err.println("Vendor code: " + sqle.getErrorCode() +
                           "\n----------------");
      } while((sqle = sqle.getNextException()) != null);
    }
  }

  private void setupTree(DatabaseMetaData metadata) throws SQLException {
    String[] tableTypes = { "TABLE"};                   // We want only tables
    ResultSet tables = metadata.getTables(              // Get the tables info
                                          null,
                                          null,
                                          null,
                                          tableTypes);

    String tableName;                            // Stores a table name
    DefaultMutableTreeNode tableNode;            // Stores a tree node for a table
    while(tables.next()) {                         // For each table
      tableName = tables.getString("TABLE_NAME");  // get the table name
      tableNode = new DefaultMutableTreeNode(tableName);
      dbNode.add(tableNode);                     // Add the node to the tree

      // Get all the columns for the current table
      ResultSet columnNames = metadata.getColumns(null, null, tableName, null);

      // Add nodes for the columns as children of the table node
      while(columnNames.next()) {
        tableNode.add(
                 new DefaultMutableTreeNode(columnNames.getString("COLUMN_NAME")));
      }
    }
  }

  public void valueChanged(TreeSelectionEvent e) {
    TreePath[] paths = dbTree.getSelectionPaths();
    if(paths == null) {
      return;
    }

    boolean tableSelected = false;             // Set true if a table is selected
    String column = null;                      // Stores a column name from a path
    String tableName = null;                   // Stores a table name from a path
    String columnsParam = null;                // Column names in SQL SELECT
    String tableParam = null;                  // Table name in SQL SELECT
    String message = null;                     // Message for status area
    for(TreePath path : paths) {
      switch(path.getPathCount()) {
        case 2:                                // We have a table selected
          tableParam = (String)
                          (((DefaultMutableTreeNode)
                                  (path.getPathComponent(1))).getUserObject());
          columnsParam = "*";               // Select all columns
          tableSelected = true;             // Set flag for a table selected
          message = "Complete " + tableParam + " table displayed";
          break;

        case 3:                               // Column selected
          tableName = (String)
                      (((DefaultMutableTreeNode)
                                  (path.getPathComponent(1))).getUserObject());
          if(tableParam == null) {
            tableParam = tableName;

          } else if(tableParam != tableName) {
            break;
          }
          column = (String)
                       (((DefaultMutableTreeNode)
                                  (path.getPathComponent(2))).getUserObject());
          if(columnsParam == null) {         // If no previous columns
            columnsParam = column;           // add the column
          } else {                           // otherwise
            columnsParam += "," + column;    // we need a comma too
          }
          message = columnsParam + " displayed from " + tableParam + " table.";
          break;
      }
      if(tableSelected) {                    // If a table was selected
        break;                               // we are done
      }
    }
    try {
      // Display the columns and change the scroll pane border
      tableModel.setResultSet(
           statement.executeQuery("SELECT " + columnsParam+" FROM " + tableParam));
      tablePane.setBorder(BorderFactory.createTitledBorder(
                         BorderFactory.createLineBorder(Color.darkGray),
                         tableParam,
                         TitledBorder.CENTER,
                         TitledBorder.DEFAULT_POSITION));
    } catch(SQLException sqle) {
      message = "Selection event Error\n" + sqle.getMessage();
      System.err.println(message);
    }
    if(message != null) {
      status.setText(message);
    }
  }

  private Connection connection;
  private Statement statement;

  private String userID = "guest";
  private String password = "guest";
  private String url = "jdbc:odbc:technical_library";

  private JTextField database = new JTextField(url);
  private JTextField userIDInput = new JTextField(userID);
  private JPasswordField passwordInput = new JPasswordField(password);
  private JTextArea status = new JTextArea(3,30);

  private DefaultMutableTreeNode dbNode;         // Root node for the database tree
  private DefaultTreeModel dbTreeModel;          // Model for the database metadata
  private JTree dbTree;                          // Tree to display the metadata
  private JScrollPane treePane;                  // Scroll pane holding the tree

  private ResultsModel tableModel;               // Model for table
  private JTable table;                          // Table holding table data
  private JScrollPane tablePane;                 // Scroll pane holding the table

  private String[] drivers = {
                               "sun.jdbc.odbc.JdbcOdbcDriver",      // ODBC bridge
                              "com.imaginary.sql.msql.MsqlDriver"   // mSQL driver
                               // Add other drivers here if you have them installed
                             };
}
