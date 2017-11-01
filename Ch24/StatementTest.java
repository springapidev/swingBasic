import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class StatementTest {
  public static void main(String[] args) {
    try {
      StatementTest SQLExample = new StatementTest();
      SQLExample.doStatement();
      SQLExample.doPreparedStatement();
    } catch(SQLException sqle) {
      System.err.println("SQL Exception: " + sqle);
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe.toString());
    }
  }

  // Constructor
  public StatementTest() throws SQLException, ClassNotFoundException {
    driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
    sourceURL = "jdbc:odbc:technical_library";

    Class.forName (driverName);
    databaseConnection = DriverManager.getConnection(sourceURL);
  }

  // Create and execute a statement
  public void doStatement() throws SQLException {
    Statement myStatement = databaseConnection.createStatement();
    ResultSet myResults = myStatement.executeQuery
                          ("SELECT authid, lastname, firstname FROM authors ORDER BY authid");

    showResults(myResults);
  }

  // Create and execute a prepared statement
  public void doPreparedStatement() throws SQLException {
    PreparedStatement myStatement = databaseConnection.prepareStatement
                   ("SELECT authid, lastname, firstname FROM authors ORDER BY authid");
    ResultSet myResults = myStatement.executeQuery();
    showResults(myResults);
  }

  // Display the contents of a ResultSet
  public void showResults(ResultSet myResults) throws SQLException {
    // Retrieve ResultSetMetaData object from ResultSet
    ResultSetMetaData myResultMetadata = myResults.getMetaData();

    // How many columns were returned?
    int numColumns = myResultMetadata.getColumnCount();

    System.out.println(" ---------------------Query Results-------------------------");
    // Loop through the ResultSet and get data
    while(myResults.next()) {
      System.out.printf("%-5d", myResults.getInt(1));    // 1st Column only
      for(int column = 2; column <= numColumns; column++) {
        System.out.print(myResults.getString(column)+"   ");
      }
      System.out.print("\n");
    }
    System.out.println("\n\n-----------Query Metadata----------------");
    System.out.println("ResultSet contains " + numColumns + " columns");
    for (int column = 1; column <= numColumns; column++) {
      System.out.println("\nColumn " + column);
      // Print the column name
      System.out.println("   column        : " +
                                     myResultMetadata.getColumnName(column));
      // Print the label name
      System.out.println("   label         : " + 
                                    myResultMetadata.getColumnLabel(column));
      // Print the column's display size
      System.out.println("   display width : " + 
                              myResultMetadata.getColumnDisplaySize(column) +
                               " characters");
      // Print the column's type
      System.out.println("   data type     : " + 
                                 myResultMetadata.getColumnTypeName(column));
    }
  }

  Connection databaseConnection;                 // Connection to the database
  String driverName;                             // Database driver name
  String sourceURL;                              // Database location
}
