import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakingTheConnection {
  public static void main(String[] args) {
    // Load the driver
    try {
      // Load the driver class
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

      // Define the data source for the driver
      String sourceURL = "jdbc:odbc:technical_library";

      // Create a connection through the DriverManager 
      Connection databaseConnection = DriverManager.getConnection(sourceURL);
      System.out.println("Connection is: "+databaseConnection);
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    } catch(SQLException sqle) {
      System.err.println(sqle);
    }
  }
}
