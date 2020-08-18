import javax.swing.*;
import java.io.*;
import java.util.*;

public class FileReader {

    private File fileName;
    private RowList tmpRowList;

    public FileReader(){

        JFileChooser j = new JFileChooser();

        j.showOpenDialog(null);
        this.fileName = j.getSelectedFile();

    }

    public boolean hasFile(){
        return (fileName != null);
    }


    public RowList readAll(){

        tmpRowList = new RowList();

        Scanner scan = null;
        try {
            if(fileName != null) {
                scan = new Scanner(fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (scan != null) {

            separateColumns(scan.nextLine());

            while (scan.hasNextLine()) {
                separateRows(scan.nextLine());
            }
        }
        return tmpRowList;
    }

    private void separateColumns(String tmpColumns){
        Queue<String> tmpQueue = new LinkedList<>();

        Scanner sc = new Scanner(tmpColumns);
        sc.useDelimiter(",");

        while(sc.hasNext()) {
            tmpQueue.add(sc.next());
        }

        tmpRowList.setColumnNames(tmpQueue.toArray());
    }

    private void separateRows(String tmpRow){
        Queue<Object> tmpQueue = new LinkedList<>();
        boolean[] tmpType = new boolean[tmpRowList.getColumnNames().length]; //for columnType int = false, String = true
        int counter = 0;

        Scanner sc = new Scanner(tmpRow);
        sc.useDelimiter(",");

        while(sc.hasNext()) {
            if(sc.hasNextInt()) {
                tmpQueue.add(sc.nextInt());
                tmpType[counter] = false;
            }
            else if (sc.hasNext()) {
                tmpQueue.add(sc.next());
                tmpType[counter] = true;
            }

            counter++;
        }
        Row tmpPotion = new Row(tmpQueue.toArray());
        tmpRowList.addPotion(tmpPotion);
        tmpRowList.setColumnType(tmpType); //for columnType int = false, String = true
    }
}
