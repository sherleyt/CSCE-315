package project1;

import java.util.*;

public class Dbms {
    private Hashtable<String,Table> tables;
    private Table selectTable;

    public Dbms(){
        tables = new Hashtable<>();
        selectTable = new Table();
    }

    public boolean AddTable(String name,Table table){
        tables.put(name, table);

        /* //Debugging table adding
        System.out.print("Added Table \"" + name + "\": ( ");
        table.getColumnsNames().forEach((n)->System.out.print(n + "(" + table.getDataType(n) + "), "));
        System.out.println(")");
         */

        return true;
    }

    public void PrintTable(String name){
        tables.get(name).Printer();
    }

    public Table getTable(String name){
        return tables.get(name);
    }
    public Table getSelect(){
        return selectTable;
    }
    public void setSelect(Table target){
         selectTable = target;
    }
    public boolean ClearSelect(){
        selectTable = new Table();
        return true;
    }
}
