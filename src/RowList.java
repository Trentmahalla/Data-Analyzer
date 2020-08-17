import java.util.*;

public class RowList implements Iterable<Row> {

    private ArrayList<Row> rowList;
    private Object[] columnNames;
    private FileReader fileReader;
    private boolean[] columnType;

    public RowList(){
         rowList = new ArrayList<Row>();
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

    public void setColumnNames(Object[] colName){
        this.columnNames = colName;
    }

    public void setColumnType(boolean[] colType){
        this.columnType = colType;
    }

    public Object[] getColumnNames(){
        return columnNames;
    }

    private boolean[] getColumnType(){
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
                if(columnType[columnChoice]) {
                    Collections.sort(rowList, Row.getComparatorByAlphabetical(columnChoice));
                }
                break;
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
