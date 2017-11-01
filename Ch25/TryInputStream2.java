import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TryInputStream2 {
  public static void main(String[] args) {
    try {
      String url = "jdbc:odbc:technical_library";
      String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
      String user = "guest";
      String password = "guest";

      FileInputStream fis = new FileInputStream("TryInputStream2.java");

      Class.forName(driver);
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement createTable = connection.createStatement();
      createTable.executeUpdate(
      "CREATE TABLE source_code (name char(20), source LONGTEXT)");
      String ins = "INSERT INTO source_code VALUES(?,?)";
      PreparedStatement statement = connection.prepareStatement(ins);
 
      statement.setString(1, "TryInputStream2");
      statement.setAsciiStream(2, fis, fis.available());

      int rowsUpdated = statement.executeUpdate();
      System.out.println("Rows affected: " + rowsUpdated);
      // Create a statement object and execute a SELECT
      Statement getCode = connection.createStatement();
      ResultSet theCode = getCode.executeQuery(
                                 "SELECT name,source FROM source_code");
      BufferedReader reader = null;                  // Reader for a column
      String input = null;                           // Stores an input line

      while(theCode.next()) {                        // For each row
        // Create a buffered reader from the stream for a column
        reader = new BufferedReader(
                                new InputStreamReader(theCode.getAsciiStream(2)));

        // Read the column data from the buffered reader
        while((input = reader.readLine()) != null) { // While there is a line
          System.out.println(input);                 // display it
        }
      }
      connection.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
