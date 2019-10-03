import java.util.ArrayList;

import project1.Dbms;
public class Table{
	ArrayList<ArrayList<String>> table;	
	int size = 0;
	public Table(ArrayList<String> columns) {
		size = columns.size();
		for(int i = 0; i < size; i++) {
			ArrayList<String> entry;
			table.pushback(entry);
		}
	}
	
	public insert(ArrayList<String> values) {
		ArrayList<String> tempEntry;
		for(int i = 0; i < size; i++) {
			
		}
	}
	public void printer(){
        int i = 0;
        for(Hashtable<String,Object> temp : entries){
        //for(int i=0;i<entries.size();i++){
            //System.out.println(columnNames.get(i) + "    ");
            //entry.get(columnNames.get(i))
            System.out.println("Entry"+i+":");
            for(int j = 0; j<columnNames.size();j++){
                String Column_print = columnNames.get(j);

                System.out.print(Column_print+ ":"+temp.get(Column_print)+ " ");
            }
            ++i;
        }
       // for(int i=0; i<entries.size();i++){


    	}
	
}
