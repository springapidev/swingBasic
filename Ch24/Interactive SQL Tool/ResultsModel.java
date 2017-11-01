import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

class ResultsModel extends AbstractTableModel {
  public void setResultSet(ResultSet results) {
    try {
      ResultSetMetaData metadata = results.getMetaData();

      int columns =  metadata.getColumnCount();    // Get number of columns
      columnNames = new String[columns];           // Array to hold names
      
      // Get the column names
      for(int i = 0; i < columns; i++) {
        columnNames[i] = metadata.getColumnLabel(i+1);
      }

      // Get all rows
      dataRows.clear();                          // Empty vector to store the data
      String[] rowData;                          // Stores one row
      while(results.next()) {                    // For each row...
        rowData = new String[columns];           // create array to hold the data
        for(int i = 0; i < columns; i++) {       // For each column
           rowData[i] = results.getString(i+1);  // retrieve the data item
        }
        dataRows.addElement(rowData);            // Store the row in the vector
      }
      fireTableChanged(null);           // Signal the table there is new model data
    }
    catch (SQLException sqle) {
      System.err.println(sqle);
    }
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public int getRowCount() {
    return dataRows == null ? 0 : dataRows.size();
  }

  public String getValueAt(int row, int column) {
    return dataRows.elementAt(row)[column];
  }

  public String getColumnName(int column) {
    return columnNames[column] == null ? "No Name" : columnNames[column];
  }

  private String[] columnNames = new String[0];               // Empty array of names
  private Vector<String[]> dataRows = new Vector<String[]>(); // Vector of rows
}
