import java.sql.DriverManager;                  
import java.sql.Connection;                  
import java.sql.Statement;                  
import java.sql.PreparedStatement;                  
import java.sql.SQLException;
import java.io.FileInputStream;

public class TryInputStream {
  public static void main(String[] args) {
    try {
      String url = "jdbc:odbc:technical_library";
      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
      String user = "guest";
      String password = "guest";
      FileInputStream fis = new FileInputStream("TryInputStream.java");
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement createTable = connection.createStatement();

      // Execute the SQL to create the table
      createTable.executeUpdate(
                  "CREATE TABLE source_code (name CHAR(20), source LONGTEXT)");

      // Create a PreparedStatement to INSERT a row in the table
      String ins = "INSERT INTO source_code VALUES(?,?)";
      PreparedStatement statement = connection.prepareStatement(ins);

      // Set values for the placeholders
      statement.setString(1, "TryInputStream");              // Set first field
      statement.setAsciiStream(2, fis, fis.available());     // Stream is source

      int rowsUpdated = statement.executeUpdate();
      System.out.println("Rows affected: " + rowsUpdated);
      connection.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
