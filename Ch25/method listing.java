  private void setTableColumnWidths() {
    Component component = null;
    TableColumn column = null;
    int headerWidth = 0;
    int cellWidth = 0;
    TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

    for(int i = 0; i < tableModel.getColumnCount() ; i++) {
      column = table.getColumnModel().getColumn(i);

      component = headerRenderer.getTableCellRendererComponent(
                                 null, 
                                 column.getHeaderValue(),
                                 false, false, 0, 0);
      headerWidth = component.getPreferredSize().width;

      component = table.getDefaultRenderer(tableModel.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, tableModel.maxColumnCell[i],
                                 false, false, 0, i);
      cellWidth = component.getPreferredSize().width;
      System.out.println("Col = "+i+" width = "+Math.max(headerWidth, cellWidth));
      column.setPreferredWidth(Math.max(headerWidth, cellWidth));
    }
  }
