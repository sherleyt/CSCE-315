package project1;

import java.util.*;

public class Dbms {
    private Hashtable<String,Table> tables;

    public Dbms(){
        tables = new Hashtable<>();
    }

    public boolean AddTable(String name,Table table){
        tables.put(name, table);

        //Debugging table adding
        System.out.print("Added Table \"" + name + "\": ( ");
        table.getColumnsNames().forEach((n)->System.out.print(n + "(" + table.getDataTypes().get(n) + "), "));
        System.out.println(")");


        return true;
    }

    public void PrintTable(String name){
        tables.get(name).Print();
    }

    public Table getTable(String name){
        return tables.get(name);
    }
}
