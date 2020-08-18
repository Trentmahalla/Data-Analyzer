import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static jdk.internal.joptsimple.internal.Strings.isNullOrEmpty;

public class RangeSortDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField maxRangeTextField;
    private JTextField minRangeTextField;

    public RangeSortDialog(ArrayList<Row> rowList, int column, boolean[] columnType) {
        init();


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(rowList, column, columnType);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(ArrayList<Row> rowList, int column, boolean[] columnType) {

        for (Row r: rowList){
            if(!columnType[column] && checkTextFields(minRangeTextField.getText(), maxRangeTextField.getText())){
                if(r.getIntValue(column) < Integer.parseInt(minRangeTextField.getText())  || r.getIntValue(column) > Integer.parseInt(maxRangeTextField.getText())){
                    r.setVisible(false);
                }
            }
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void init(){
        setTitle("Range Sort");
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonOK);
    }

    private static boolean checkTextFields(String s1, String s2){
        return !isNullOrEmpty(s1) && !isNullOrEmpty(s2) && (Integer.parseInt(s1) < Integer.parseInt(s2));
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
