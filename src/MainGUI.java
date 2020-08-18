import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JButton openButton;
    private JTable table;
    private DefaultTableModel model = (DefaultTableModel) table.getModel();
    private JButton testSortButton;
    private JButton clearButton;
    private JComboBox<String> sortTypeBox;
    private JComboBox<Integer> columnNumBox;
    private JButton resetButton;
    private RowList rowList;
    private String[] sortTypes = {"Number Ascending", "Alphabetical", "Range Sort"};


    public MainGUI(String title){
        super(title);
        for(int i = 0; i < sortTypes.length; i++) {
            sortTypeBox.addItem(sortTypes[i]);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonAction();
            }
        });

        testSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testSortAction();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearButtonAction();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonAction();
            }
        });
    }

    private void openButtonAction(){
        rowList = new RowList();
        if(rowList.readFile()) {
            model.setRowCount(0);
            model.setColumnCount(0);
            for (int i = 0; i < rowList.getColumnNames().length; i++) {
                model.addColumn(rowList.getColumnNames()[i]);
                columnNumBox.addItem(i + 1);
            }

            for (Row r : rowList) {
                if(r.isVisible()){
                    model.addRow(r.getArray());
                }
            }
        }
    }

    private void testSortAction(){
        if (columnNumBox.getSelectedIndex() != -1) {
            rowList.columnSort(columnNumBox.getSelectedIndex(), sortTypeBox.getSelectedIndex());
            model.setRowCount(0);

            for (Row r : rowList) {
                if(r.isVisible()){
                    model.addRow(r.getArray());
                }
            }
        }
    }

    private void clearButtonAction(){
        if(model.getColumnCount() != 0) {
            model.setColumnCount(0);
            model.setRowCount(0);
            rowList.clearColumnNames();

            columnNumBox.removeAllItems();
        }
    }

    private void resetButtonAction(){
        if(model.getColumnCount() != 0) {
            model.setColumnCount(0);
            model.setRowCount(0);

            rowList.reReadFile();

            for (int i = 0; i < rowList.getColumnNames().length; i++) {
                model.addColumn(rowList.getColumnNames()[i]);
                columnNumBox.addItem(i + 1);
            }

            for (Row r : rowList) {
                if (r.isVisible()) {
                    model.addRow(r.getArray());
                }
            }
        }
    }

}
