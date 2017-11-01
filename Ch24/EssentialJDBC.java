import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class EssentialJDBC {

  public static void main (String[] args) {
    EssentialJDBC SQLExample = new EssentialJDBC();   // Create application object

    SQLExample.getResultsByColumnName();
    SQLExample.getResultsByColumnPosition();
    SQLExample.getAllColumns();
    SQLExample.closeConnection();
  }

  public EssentialJDBC() {
    try {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      connection = DriverManager.getConnection(sourceURL);
      statement = connection.createStatement();
    } catch(SQLException sqle) {
      System.err.println("Error creating connection");
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe.toString());
    }
  }

  void getResultsByColumnName() {
    try {
      ResultSet authorResults = statement.executeQuery(queryWildcard);
      int row = 0;

      while(authorResults.next()) {
        System.out.println("Row " + (++row) + ") "+
                           authorResults.getString("authid")+ " " + 
                           authorResults.getString("lastname")+ " , "+
                           authorResults.getString("firstname"));
      }
      authorResults.close();
    } catch (SQLException sqle) {
      System.err.println ("\nSQLException-------------------\n");
      System.err.println ("SQLState: " + sqle.getSQLState());
      System.err.println ("Message : " + sqle.getMessage());
    }
  }

  void getResultsByColumnPosition() {
    try {
      ResultSet authorResults = statement.executeQuery(queryIDAndName);

      int row = 0;
      while (authorResults.next()) {
        System.out.print("\nRow " + (++row) + ") ");
        for(int i = 1 ; i<=3 ; i++) {
          System.out.print((i>1?", ":" ")+authorResults.getString(i));
        }
      }
      authorResults.close();                            // Close the result set
    } catch (SQLException ex) {
      System.err.println("\nSQLException-------------------\n");
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("Message : " + ex.getMessage());
    }
  }

  void getAllColumns() {
    try {
      ResultSet authorResults = statement.executeQuery(queryWildcard);

      ResultSetMetaData metadata = authorResults.getMetaData();
      int columns = metadata.getColumnCount();          // Column count
      int row = 0;
      while (authorResults.next()) {
        System.out.print("\nRow " + (++row) + ") ");
        for(int i = 1 ; i<=columns ; i++) {
          System.out.print((i>1?", ":" ")+authorResults.getString(i));
        }
      }
      authorResults.close();                            // Close the result set
    } catch (SQLException ex) {
      System.err.println("\nSQLException-------------------\n");
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("Message : " + ex.getMessage());
    }
  }
  // Close the connection
  void closeConnection() {
    if(connection != null) {
      try {
        connection.close();
        connection = null;
      } catch (SQLException ex) {
        System.out.println("\nSQLException-------------------\n");
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("Message : " + ex.getMessage());
      }
    }
  }

  Connection connection;
  Statement statement;
  String sourceURL = "jdbc:odbc:technical_library";
  String queryIDAndName = "SELECT authid, lastname, firstname FROM authors";
  String queryWildcard = "SELECT * FROM authors";          // Select all columns
}
