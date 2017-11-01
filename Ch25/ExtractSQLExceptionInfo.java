import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtractSQLExceptionInfo {
  public static void main(String[] args) {
    String url = "jdbc:odbc:technical_library";
    String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    String user = "guest";
    String password = "guest";

    // Following statement contains a deliberate mistake
    String theStatement = "SELECT lastname, firstname FROM autors";

    try {
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement queryAuthors = connection.createStatement();
      ResultSet theResults =  queryAuthors.executeQuery(theStatement);

      queryAuthors.close();
    } catch (ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    } catch (SQLException sqle) {
      String sqlMessage = sqle.getMessage();
      String sqlState  = sqle.getSQLState();
      int vendorCode = sqle.getErrorCode();
      System.err.println("Exception occurred:");
      System.err.println("Message: " + sqlMessage);
      System.err.println("SQL state: " + sqlState);
      System.err.println("Vendor code: " + vendorCode +
                         "\n----------------");
    }
  }
}
