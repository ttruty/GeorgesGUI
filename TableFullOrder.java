import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableFullOrder extends JPanel {
    private boolean DEBUG = false;
    JTable table;
    MyTableModel tableModel=new MyTableModel();
    private String[] columnNames;

    public TableFullOrder() {
        super(new GridLayout(1,0));

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(10, 70));

        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
//        private String[] columnNames ;
        //public Object[][] data;
        public ArrayList<FinalOrder> data = new ArrayList<>();

        MyTableModel(){
            // column are hardcode here with
            columnNames=new String[] {"Items","Name","Order Cost", "Discount", "Final Total"};
        }
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            //return data.length;
            return data.size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int rowIndex, int colIndex) {
            Object value = null;
            FinalOrder oneItem = data.get(rowIndex);
            switch(colIndex){
                case 0:
                    value = oneItem.getItemCount();
                    break;
                case 1:
                    value = oneItem.getItemName();
                    break;
                case 2:
                    value = oneItem.getRegCost();
                    break;
                case 3:
                    value = oneItem.getDiscountAmount();
                    break;
                case 4:
                    value = oneItem.getFinalTotal();
                    break;
                case 5:
                    value = oneItem.getOrder();
                    break;
            }
            return value;
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            FinalOrder oneItem = data.get(row);
            switch (col) {
                case 0:
                    if (value instanceof Integer) {
                        oneItem.setItemCount((Integer)value);
                    }
                    break;
                case 1:
                    if (value instanceof String) {
                        oneItem.setItemName((String)value);
                    }
                    break;
                case 2:
                    if (value instanceof String) {
                        oneItem.setRegCost((String)value);
                    }
                    break;
                case 3:
                    if (value instanceof String) {
                        oneItem.setDiscountAmount((String)value);
                    }
                    break;
                case 4:
                    if (value instanceof String) {
                        oneItem.setFinalTotal((String)value);
                    }
                    break;
                case 5:
                    if (value instanceof ArrayList) {
                        oneItem.setOrder((ArrayList<String>) value);
                    }
                    break;
            }
            this.fireTableCellUpdated(row,col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        public void clearRows(int firstRow, int endRow){
            data.clear();
            fireTableRowsDeleted(firstRow, endRow);
            fireTableDataChanged();
        }


        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data.get(j).toString());
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }

    }

    /** using this to call setValueAt from other classes*/
    //Sets one cell to new value
    public void setValueAt1(Object value, int row, int col) {
        table.setValueAt(value,row,col);
        ((AbstractTableModel)table.getModel()).fireTableCellUpdated(row,col);
    }

    public void clearRows(int firstRow, int lastRow){
        tableModel.clearRows(firstRow, lastRow);
        tableModel.fireTableDataChanged();
    }

    // this is used to listen to table clicks
    public void showSelection(){
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println("Row selected");
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
    }

}