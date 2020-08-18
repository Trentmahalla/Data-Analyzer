import java.util.*;

public class RowList implements Iterable<Row> {

    private ArrayList<Row> rowList;
    private Object[] columnNames;
    private FileReader fileReader;
    private boolean[] columnType; //for columnType int = false, String = true

    public RowList(){
         rowList = new ArrayList<Row>();
    }

    public RowList(RowList rowListObj){
        this.rowList = rowListObj.rowList;
        this.columnNames = rowListObj.columnNames;
        this.fileReader = rowListObj.fileReader;
        this. columnType = rowListObj.columnType;
    }

    public void addPotion(Row p){
        rowList.add(p);
    }

    public boolean readFile(){
        fileReader = new FileReader();

        if(fileReader.hasFile()) {
            RowList rl = fileReader.readAll();
            columnNames = rl.getColumnNames().clone();
            columnType = rl.getColumnType().clone();

            Iterator<Row> iterator = rl.iterator();
            iterator.forEachRemaining(rowList::add);
            return true;
        }
        else{
            return false;
        }
    }

    public void reReadFile(){
        if(fileReader.hasFile()) {
            rowList.clear();
            RowList rl = fileReader.readAll();
            columnNames = rl.getColumnNames().clone();
            columnType = rl.getColumnType().clone();

            Iterator<Row> iterator = rl.iterator();
            iterator.forEachRemaining(rowList::add);
        }
    }

    public void setColumnNames(Object[] colName){
        this.columnNames = colName;
    }

    public void setColumnType(boolean[] colType){
        this.columnType = colType;
    }

    public Object[] getColumnNames(){
        return columnNames;
    }

    public boolean[] getColumnType(){
        return columnType;
    }

    public void clearColumnNames(){
        Arrays.fill(columnNames, null);
    }

    public void columnSort(int columnChoice, int sortChoice){

        switch(sortChoice){
            case 0: //num ascending
                if(!columnType[columnChoice]) { //for columnType int = false, String = true
                    Collections.sort(rowList, Row.getComparatorColumnNumSortAsc(columnChoice));
                }
                break;
            case 1: //alphabetical
                if(columnType[columnChoice]) { //for columnType int = false, String = true
                    Collections.sort(rowList, Row.getComparatorByAlphabetical(columnChoice));
                }
                break;
            case 2: //range sort with dialog box
                //The list must be sorted first to check a range
                if(!columnType[columnChoice]) { //for columnType int = false, String = true
                    Collections.sort(rowList, Row.getComparatorColumnNumSortAsc(columnChoice));
                }
                RangeSortDialog rsD = new RangeSortDialog(rowList, columnChoice, columnType);
                rsD.setLocationRelativeTo(null);
                rsD.setVisible(true);
                break;
        }

    }

    public void resetVisible(){
        for(Row r: rowList){
            r.setVisible(true);
        }
    }

    public Iterator<Row> getRows(){
        return rowList.iterator();
    }

    public Iterator<Row> iterator(){
        return getRows();
    }


    @Override
    public String toString(){
        StringBuilder tmpString = new StringBuilder();

        for (Row p: rowList){
            tmpString.append(p.toString());
        }
        return tmpString.toString();
    }
}
