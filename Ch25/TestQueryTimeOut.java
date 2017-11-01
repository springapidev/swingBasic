import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class TestQueryTimeOut {
  public static void main(String[] args) {
    Statement statement = null;
    try {
      String url = "jdbc:odbc:technical_library";
      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
      String username = "guest";
      String password = "guest";
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, username, password);
      statement = connection.createStatement();
      System.out.println("Driver          :  " + driver);
    } catch (ClassNotFoundException cnfe) {
      System.out.println(cnfe);
    } catch (SQLException sqle) {
      System.out.println(sqle);
    }
    // Put each method call in a separate try block to execute them all
    try {
      System.out.print("\nMaximum rows    :");
      int maxRows = statement.getMaxRows();
      System.out.print(maxRows == 0 ? " No limit" : " " + maxRows);
    } catch (SQLException sqle) {
      System.err.print(sqle);
    }
    try {
      System.out.print("\nMax field size  :");
      int maxFieldSize = statement.getMaxFieldSize();
      System.out.print(maxFieldSize == 0 ? " No limit" : " " + maxFieldSize);
    } catch (SQLException sqle) {
      System.err.print(sqle);
    }
    try {
      System.out.print("\nTimeout          :" );
      int queryTimeout = statement.getQueryTimeout();
      System.out.print(queryTimeout == 0 ? " No limit" : " " + queryTimeout);
    } catch (SQLException sqle) {
      System.err.print(sqle);
    }
  }
}
