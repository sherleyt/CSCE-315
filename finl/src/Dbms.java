//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
package project1;

import java.util.*;
import java.io.*;

public class Dbms {
	//All the tables in the DBMS stored in hashtable
    private Hashtable<String,Table> tables;

    public Dbms(){
		//make empty hashtable to start
        tables = new Hashtable<>();
    }

    //Usage source:https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	//Open file, get the table information and make a new table with that information/name
	//.csv File looks like:
	//
	//-------------------------
	//Primary_key1 , Primary_key2, ...
	//Column_name1 , Column_name2, ...
	//Column_type1(type of column1), Column_type2, ...
	//Entry1_1(of type of Column1), Entry1_2, Entry1_3, ...
	//Entry2_1(of type of Column1), Entry2_2, Entry2_3, ...
	//...
	//-------------------------
    public void open_table(String tablenam){
		
        String csvFile = "C:\\Users\\sjpat\\Desktop\\315\\Project"+tablenam+".csv";
        String line = "";
        BufferedReader entry_from_csv = null;
        String split_char = ",";
        Table toadd = new Table(tablenam);
        try {
			
			//Get entry as lines
            entry_from_csv = new BufferedReader(new FileReader(csvFile));
            //keys at first line-will add later
            line = entry_from_csv.readLine();
            String[] prim_keys = line.split(split_char);
            
			
            //col_names at second line
            line = entry_from_csv.readLine();
            String[] col_names = line.split(split_char);
            //col_types to add later and for hashtable
            line = entry_from_csv.readLine();
            String[] col_types = line.split(split_char);
            for(int j=0;j<col_types.length;j++){
                toadd.AddColumn(col_names[j] , col_types[j]);      //Will add the column_types as needed
            }
			
			//Now add the primary keys(as we match it to columnnames)
			for(int j = 0; j<prim_keys.length;j++){
                toadd.AddPrimaryKey(prim_keys[j]);
            }

			//Entries left
            while ((line = entry_from_csv.readLine()) != null) {

                // use comma as separator
                String[] csv_entry = line.split(split_char);
                List<Object> entr = new ArrayList<>();
                for(int k = 0;k<csv_entry.length;k++){
					//Check if its type is INTEGER
                    if(col_types[k].equals("INTEGER")){
                        int tem;
                        try{
                            tem = Integer.parseInt(csv_entry[k]);}
                        catch (NumberFormatException e)
                        {
                            tem = 0;
                        }
                        entr.add(tem);
                    }
					//Else it is just a string
                    else{
                        String temp = "";
                        temp = "\"" + csv_entry[k] + "\"";  //Added quotes as its syntax of addentry
                        entr.add(temp);
                    }
                }
                toadd.AddEntry(entr);
            }
			//Take caer of exceptions
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tables.put(tablenam,toadd);//Put into the tables

    }

	//Write/overwrite a file with given table, and KEEP the table in DBMS
	//.csv File looks like:
	//
	//-------------------------
	//Primary_key1 , Primary_key2, ...
	//Column_name1 , Column_name2, ...
	//Column_type1(type of column1), Column_type2, ...
	//Entry1_1(of type of Column1), Entry1_2, Entry1_3, ...
	//Entry2_1(of type of Column1), Entry2_2, Entry2_3, ...
	//...
	//-------------------------
    public void write_table(String tablenam) throws java.io.IOException{
		//make temp table to use to write
        Table temp = tables.get(tablenam);
		//Delete existing file and make new one exactly same way
        File fold=new File("C:\\Users\\sjpat\\Desktop\\315\\Project\\src\\project1\\"+tablenam+".csv");
        fold.delete();
        File temp2 = new File("C:\\Users\\sjpat\\Desktop\\315\\Project\\src\\project1\\"+tablenam+".csv");
        temp2.createNewFile();
		
		//-------------------------
		//GO LINE BY LINE AND ADD
		//-------------------------
		//Make string(using StringBuilder) of primarykeys
        FileWriter writer = new FileWriter(temp2);
        int i =1; 
        StringBuilder sb = new StringBuilder();
        for(String a:temp.getPrimaryKeys()){ //for-each with primary keys

            sb.append(a);
			//Check if we need ","(middle) or "\n"(ending)
            if(i < (temp.getPrimaryKeys().size())) {
				//middle entry
                sb.append(",");
            }
            else{
				//ending entry
                sb.append("\n");
            }
            ++i;
        }
        writer.append(sb.toString());
		System.out.print(sb.toString());
		
		//Make string(using StringBuilder) of column_names
        i = 1;
        StringBuilder col = new StringBuilder();
        for(String a:temp.getColumnsNames()){ //for-each with column names

            col.append(a);
			//Check if we need "," or "\n"
            if(i < (temp.getColumnsNames().size())) {
				//middle entry
                col.append(",");
            }
            else{
				//ending entry
                col.append("\n");
            }
            ++i;
        }
        writer.append(col.toString());
		System.out.print(col.toString());
		//Make string_array of column names(used later to find their types)
        i =0;
        String[] col_names = new String[temp.getColumnsNames().size()]; //should be same size as ColumnNames.size(), because list has an easy size function
		
        for(String b:temp.getColumnsNames()){
            col_names[i] = b;
            ++i;
        }
		
		// Make Equalivalent hashtable to then search, and insert types (Hastable <col_name> <coltype>), use col_name as key to get coltype
        StringBuilder type = new StringBuilder();
        Hashtable<String,String> types = temp.getDataTypes();
        for(int j = 0; j<temp.getDataTypes().size();j++){

            type.append(types.get(col_names[j]));
			//Check if we need "," or "\n"
            if(j < (temp.getDataTypes().size()-1)) {
				//middle entry
                type.append(",");
            }
            else{
				//ending entry
                type.append("\n");
            }
        }
        writer.append(type.toString());
        System.out.print(type.toString());

        String entry = ""; //Each entry in 1 string, has /n's to get new line at the end
        for(Hashtable<String,Object> entr:temp.getEntries()){
            for(int k = 0; k<(temp.getColumnsNames().size()-1);k++){
                entry = entry + entr.get(temp.getColumnsNames().get(k)) + ",";

            }
            entry = entry + entr.get(temp.getColumnsNames().get(temp.getColumnsNames().size()-1)) + "\n";
        }
        writer.append(entry.toString());
        System.out.print(entry.toString());
        writer.flush();
        writer.close();
    }
	
	//Create/rewrite .csv file of table with current iteration of the table, delete the table from DBMS
	//.csv File looks like:
	//
	//-------------------------
	//Primary_key1 , Primary_key2, ...
	//Column_name1 , Column_name2, ...
	//Column_type1(type of column1), Column_type2, ...
	//Entry1_1(of type of Column1), Entry1_2, Entry1_3, ...
	//Entry2_1(of type of Column1), Entry2_2, Entry2_3, ...
	//...
	//-------------------------
    public void close_table(String tablenam)throws java.io.IOException{
        Table temp = tables.get(tablenam); //Table in question
		//Find the .csv file and delete it
        File fold=new File("C:\\Users\\sjpat\\Desktop\\315\\Project"+tablenam+".csv");
        fold.delete();
		//Make new empty file to print current iteration of table
        File temp2 = new File("C:\\Users\\sjpat\\Desktop\\315\\Project"+tablenam+".csv");
        temp2.createNewFile();
		
		//Start writing using PrintWriter
        PrintWriter writer = new PrintWriter(temp2);
		
		//-------------------------
		//GO LINE BY LINE AND ADD
		//-------------------------
		//Make string(using StringBuilder) of primarykeys
        int i =1; 
        StringBuilder sb = new StringBuilder();
        for(String a:temp.getPrimaryKeys()){ //for-each with primary keys

            sb.append(a);
			//Check if we need ","(middle) or "\n"(ending)
            if(i < (temp.getPrimaryKeys().size())) {
				//middle entry
                sb.append(",");
            }
            else{
				//ending entry
                sb.append("\n");
            }
            ++i;
        }
        writer.print(sb.toString());
		
		
		//Make string(using StringBuilder) of column_names
        i = 1;
        StringBuilder col = new StringBuilder();
        for(String a:temp.getColumnsNames()){ //for-each with column names

            col.append(a);
			//Check if we need "," or "\n"
            if(i < (temp.getColumnsNames().size())) {
				//middle entry
                col.append(",");
            }
            else{
				//ending entry
                col.append("\n");
            }
            ++i;
        }
        writer.print(col.toString());
		
		//Make string_array of column names(used later to find their types)
        i =0;
        String[] col_names = new String[temp.getColumnsNames().size()]; //should be same size as ColumnNames.size(), because list has an easy size function
		
        for(String b:temp.getColumnsNames()){
            col_names[i] = b;
            ++i;
        }
		
		// Make Equalivalent hashtable to then search, and insert types (Hastable <col_name> <coltype>), use col_name as key to get coltype
        StringBuilder type = new StringBuilder();
        Hashtable<String,String> types = temp.getDataTypes();
        for(int j = 0; j<temp.getDataTypes().size();j++){

            type.append(types.get(col_names[j]));
			//Check if we need "," or "\n"
            if(j < (temp.getDataTypes().size()-1)) {
				//middle entry
                type.append(",");
            }
            else{
				//ending entry
                type.append("\n");
            }
        }
        writer.print(type.toString());


        String entry = ""; //Each entry in 1 string, has /n's to get new line at the end
        for(Hashtable<String,Object> entr:temp.getEntries()){
            for(int k = 0; k<(temp.getColumnsNames().size()-1);k++){
                entry = entry + entr.get(temp.getColumnsNames().get(k)) + ",";

            }
            entry = entry + entr.get(temp.getColumnsNames().get(temp.getColumnsNames().size()-1)) + "\n";
        }
        writer.print(entry.toString());
		//Remove from DBMS
        tables.remove(tablenam);
    }


	//Adds table to DBMS
    public boolean AddTable(String name,Table table){
		//Does all the work
        tables.put(name, table);
		
        //Debugging(for now)
        System.out.print("Added Table: " + name + " ( ");
        table.getColumnsNames().forEach((n)->System.out.print(n + "(" + table.getDataTypes().get(n) + "), "));
        System.out.println(")");


        return true;
    }
	//Returns the table object
    public Table getTable(String name){
        return tables.get(name);
    }
}


