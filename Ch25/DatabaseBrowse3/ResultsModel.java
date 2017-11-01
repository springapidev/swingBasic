import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

class ResultsModel extends AbstractTableModel {
  public void setResultSet(ResultSet results) {
    if(results == null) {
      columnNames = new String[0];        // Reset the columns names
      dataRows.clear();                   // Remove all entries in the Vector
      fireTableChanged(null);             // Tell the table there is new model data
      return;
    }

    try {
      ResultSetMetaData metadata = results.getMetaData();

      int columns =  metadata.getColumnCount();    // Get number of columns
      columnNames = new String[columns];           // Array to hold names
      
      // Get the column names
      for(int i = 0; i < columns; i++) {
        columnNames[i] = metadata.getColumnLabel(i+1);
        if(columnNames[i] != null) {
          columnNames[i].trim();
        }
      }

      // Get all rows.
      dataRows.clear();                          // Empty the vector
      String[] rowData = null;                   // Stores one row
      while(results.next()) {                    // For each row...
        rowData = new String[columns];           // create array to hold the data
        for(int i = 0; i < columns; i++) {       // For each column
          rowData[i] = results.getString(i+1);   // retrieve the data item
          if(rowData[i] != null) {
            rowData[i].trim();
          }
        }

        dataRows.addElement(rowData);              // Store the row in the vector
      }
      fireTableChanged(null);           // Signal the table there is new model data
    } catch (SQLException sqle) {
      System.err.println(sqle);
    }
  }

  public int getColumnCount() {
    return columnNames.length; 
  }

  public int getRowCount() {
    if(dataRows == null) {
      return 0;
    } else {
      return dataRows.size();
    }
  }

  public String getValueAt(int row, int column) {
    return dataRows.elementAt(row)[column]; 
  }

  public String getColumnName(int column) {
    return columnNames[column] == null ? "No Name" : columnNames[column];
  }

  public Class getColumnClass(int columnIndex) {
    return String.class;
  }

  private String[] columnNames = new String[0]; 
  private Vector<String[]> dataRows = new Vector<String[]>(); // Empty vector of rows 
  String[] maxColumnCell = null;
}

