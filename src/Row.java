import java.util.Comparator;

public class Row {

    private Object[] rowData;
    private static int columnSort = 0;
    private boolean visible = true;
//    private String name, location;
//    private int iLvl, alchLvl;

//    public Potion (String name, String location, int iLvl, int alchLvl){
//        this.name = name;
//        this.location = location;
//        this.iLvl = iLvl;
//        this.alchLvl = alchLvl;
//    }

    public Row(Object[] objArray){
        this.rowData = objArray.clone();
    }

    public Object[] getArray(){
        return rowData;
    }

    public int getIntValue(int index){
        return (Integer) rowData[index];
    }

    private String getString(int index){
        return rowData[index].toString();
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }


    private static class byAscending implements Comparator<Row>{
        public int compare(Row p1, Row p2){
            return p1.getIntValue(columnSort) - p2.getIntValue(columnSort);
        }
    }

    private static class byAlphabetical implements Comparator<Row>{
        public int compare(Row p1, Row p2){
            return p1.getString(columnSort).compareToIgnoreCase(p2.getString(columnSort));
        }
    }

    public static Comparator<Row> getComparatorColumnNumSortAsc(int column){
        columnSort = column;
        return new byAscending();
    }

    public static Comparator<Row> getComparatorByAlphabetical(int column){
        columnSort = column;
        return new byAlphabetical();
    }

    @Override
    public String toString(){
        return rowData.toString() + "\n";
    }

}
