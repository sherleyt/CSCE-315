package project1;

import java.util.*;

public class Table {
    private String name;
    private List<String> columnNames; //Name
    private Hashtable<String,String> columnDataTypes; // DataType
    private HashSet<String> primaryKeys; //Which Names are Primary Keys
    private HashSet<Hashtable<String,Object>> entries; //List of Column-Data


    public Table(String name){
        this.name = name;
        columnNames = new ArrayList<>();
        columnDataTypes = new Hashtable<>();
        primaryKeys = new HashSet<>();
        entries = new HashSet<>();
    }
    public Table(Table copy){
        this.name = copy.getName();
        columnNames = new ArrayList<>(copy.getColumnsNames());
        columnDataTypes = new Hashtable<>(copy.getDataTypes());
        primaryKeys = new HashSet<>(copy.getPrimaryKeys());
        entries = new HashSet<>();
        for (Hashtable<String,Object> entry: copy.getEntries()) {
            entries.add(new Hashtable<>(entry));
        }
    }

    public boolean AddColumn(String name, String dataType){
        for (String c: columnNames)
            if (c == name)
                return false;

        columnNames.add(name);
        columnDataTypes.put(name,dataType);
        return true;
    }
    public boolean AddPrimaryKey(String name){
        if (columnNames.contains(name)){
            primaryKeys.add(name);
            return true;
        } else return false;
    }

    public boolean AddEntry(List<Object> values) {

        //Check if input can hold primary key indexes;
        for (String key : primaryKeys){
            if (values.size() < columnNames.indexOf(key)) {
                return false;
            }
        }

        //Check if input primary keys clash with existing ones
        for (Hashtable<String,Object> entry: entries){
            boolean matching = true;
            for (String key : primaryKeys){
                if (! entry.get(key).equals(values.get(columnNames.indexOf(key))))
                    matching = false;
            }
            if (matching) {
                return false;
            }
        }

        //Check if input matches column datatypes
        //IMPLEMENT THIS

        //Add entry
        System.out.printf("%-15s","Added Entry: ");
        Hashtable<String,Object> newEntry = new Hashtable<>();
        for (int i = 0; i < values.size() && i < columnNames.size(); i++){
            newEntry.put(columnNames.get(i),values.get(i));
            System.out.printf("%-15s",columnNames.get(i)+ "="+values.get(i)+ ",");
        }
        System.out.println(" to Table: " + getName());
        entries.add(newEntry);
        return true;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public List<String> getColumnsNames(){
        return columnNames;
    }
    public Hashtable<String,String> getDataTypes(){
        return columnDataTypes;
    }
    public Set<String> getPrimaryKeys(){
        return primaryKeys;
    }
    public HashSet<Hashtable<String,Object>> getEntries(){
        return entries;
    }
    public void Print(){
        System.out.println("\nPrinting table: " + getName());
        int i = 0;
        for(Hashtable<String,Object> temp : entries){
            System.out.printf("%-15s","Entry ["+i+"]: ");
            for(int j = 0; j<columnNames.size();j++){
                String Column_print = columnNames.get(j);
                System.out.printf("%-15s",Column_print+ "="+temp.get(Column_print)+ ",");
            }
            ++i;
            System.out.println("");
        }
        System.out.println("");
    }
}
