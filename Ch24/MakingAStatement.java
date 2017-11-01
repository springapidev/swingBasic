import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MakingAStatement {
  public static void main(String[] args) {
    // Load the driver
    try {
      // Load the driver class
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

      // This defines the data source for the driver
      String sourceURL = new String("jdbc:odbc:technical_library");

      // Create connection through the DriverManager 
      Connection databaseConnection = 
                            DriverManager.getConnection(sourceURL);

      Statement statement = databaseConnection.createStatement();
      ResultSet authorNames = statement.executeQuery(
                                     "SELECT lastname, firstname FROM authors");

      // Output the resultset data
      while(authorNames.next()) {
        System.out.println(authorNames.getString("lastname")+" "+
                                               authorNames.getString("firstname"));
      }
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    } catch(SQLException sqle) {
      System.err.println(sqle);
    }
  }
}
