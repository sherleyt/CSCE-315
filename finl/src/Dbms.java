package project1;

import java.util.*;

public class Dbms {
    private Hashtable<String,Table> tables;

    public Dbms(){
        tables = new Hashtable<>();

    }

    public boolean AddTable(String name,Table table){
        tables.put(name, table);

        System.out.print("Added Table \"" + name + "\": ( ");
        table.getColumnsNames().forEach((n)->System.out.print(n + "(" + table.getDataType(n) + "), "));
        System.out.println(")");

        return true;
    }
}
