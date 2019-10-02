package project1;

import org.antlr.v4.runtime.tree.*;

import java.util.*;

public class Table {
    private List<String> columnNames; //Name
    private Hashtable<String,String> columnDataTypes; // DataType
    private List<String> primaryKeys; //Which Names are Primary Keys
    private HashSet<Hashtable<String,Object>> entries; //List of Column-Data


    public Table(){
        columnNames = new ArrayList<>();
        columnDataTypes = new Hashtable<>();
        primaryKeys = new ArrayList<>();
        entries = new HashSet<>();
    }

    public boolean AddColumn(String name, String dataType){
        for (String c: columnNames)
            if (c == name)
                return false;

        columnNames.add(name);
        columnDataTypes.put(name,dataType);
        return true;
    }

    public boolean AddEntry(Object... values){

        //Check if input can hold primary key indexes;
        for (String key: primaryKeys)
            if (values.length < columnNames.indexOf(key))
                return false;

        //Check if input primary keys clash with existing ones
        for (Hashtable<String,Object> entry: entries){
            boolean matching = true;
            for (String key : primaryKeys){
                if (entry.get(key).equals(values[columnNames.indexOf(key)]))
                    matching = false;
            }
            if (matching)
                return false;
        }

        //Check if input matches column datatypes
        //IMPLEMENT THIS

        //Add entry
        Hashtable<String,Object> newEntry = new Hashtable<>();
        for (int i = 0; i < values.length && i < columnNames.size(); i++){
            newEntry.put(columnNames.get(i),values[i]);
        }
        return true;
    }

    public List<String> getColumnsNames(){
        return columnNames;
    }
    public String getDataType(String columnName){
        return columnDataTypes.get(columnName);
    }
    public HashSet<Hashtable<String,Object>> getEntries(){
        return entries;
    }
    public HashSet<Hashtable<String,Object>> getEntries(List<ParseTree> condition){
        //Check condition
        return entries;
    }
}
