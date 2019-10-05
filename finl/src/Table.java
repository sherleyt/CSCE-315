//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
package project1;

import java.util.*;

public class Table {
    private String name;  //name
    private List<String> columnNames; //Name, in a list of strings
    private Hashtable<String,String> columnDataTypes; // DataType, looks like: <column name, column type> , second is important
    private HashSet<String> primaryKeys; //Which Names are Primary Keys, in a string hashset
    private HashSet<Hashtable<String,Object>> entries; //List of Column-Data, entries look like <type,entry>, inside basically a matrix structure


	//Basic Constructor
    public Table(String name){
        this.name = name;
        columnNames = new ArrayList<>();
        columnDataTypes = new Hashtable<>();
        primaryKeys = new HashSet<>();
        entries = new HashSet<>();
    }
	
	//Basic copy- copies everything into our table from given table
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

	//Just adds column names and types
    public boolean AddColumn(String name, String dataType){
		//Add into column names, after checking if it already exists
        for (String c: columnNames)
            if (c == name)
                return false;

        columnNames.add(name);
		
		//Add to column types (needs column name and its type together)
        columnDataTypes.put(name,dataType);
        return true;
    }
	//Add primarykeys
    public boolean AddPrimaryKey(String name){
		//Check contained in column names if yes, add them
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
                System.err.println("Could not add an entry! (Matching primary keys)");
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
	//Sets name of table
    public void setName(String name){
        this.name = name;
    }
	//returns name of table
    public String getName(){
        return name;
    }
	//Returns the list<string> of column names
    public List<String> getColumnsNames(){
        return columnNames;
    }
	//Returns hashtable of col_data types
    public Hashtable<String,String> getDataTypes(){
        return columnDataTypes;
    }
	//Returns set of the primarykeys
    public Set<String> getPrimaryKeys(){
        return primaryKeys;
    }
	//Has all entries, similar to matrix with objects of <type,actual thing>
    public HashSet<Hashtable<String,Object>> getEntries(){
        return entries;
    }
	//Print the table (just entries)
    public void Print(){
        System.out.println("\nPrinting table: " + getName());
        int i = 0;
		//Go through entries and print them 1 by 1 (entry by entry), separated by new lines
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

