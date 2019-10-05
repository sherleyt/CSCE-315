package project1;

import java.util.*;

public class Dbms {
    private Hashtable<String,Table> tables;

    public Dbms(){
        tables = new Hashtable<>();
    }

    public void close_table(String tablenam)throws java.io.IOException{
        Table temp = tables.get(tablenam);
        File fold=new File("C:\\JavaLib\\Project\\src\\project1\\"+tablenam+".csv");
        fold.delete();
        File temp2 = new File("C:\\JavaLib\\Project\\src\\project1\\"+tablenam+".csv");
        temp2.createNewFile();
        PrintWriter writer = new PrintWriter(temp2);
        int i =1;
        StringBuilder sb = new StringBuilder();
        for(String a:temp.getPrimaryKeys()){

            sb.append(a);
            if(i < (temp.getPrimaryKeys().size())) {
                sb.append(",");
            }
            else{
                sb.append("\n");
            }
            ++i;
        }
        writer.write(sb.toString());

        i = 1;
        StringBuilder col = new StringBuilder();
        for(String a:temp.getColumnsNames()){

            col.append(a);
            if(i < (temp.getColumnsNames().size())) {
                col.append(",");
            }
            else{
                col.append("\n");
            }
            ++i;
        }
        writer.write(col.toString());
        i =0;
        String[] col_names = new String[temp.getColumnsNames().size()];
        for(String b:temp.getColumnsNames()){
            col_names[i] = b;
            ++i;
        }
        StringBuilder type = new StringBuilder();
        Hashtable<String,String> types = temp.getDataTypes();
        for(int j = 0; j<temp.getDataTypes().size();j++){

            type.append(types.get(col_names[j]));
            if(j < (temp.getDataTypes().size()-1)) {
                type.append(",");
            }
            else{
                type.append("\n");
            }
        }
        writer.write(type.toString());

        String entry = "";

        for(Hashtable<String,Object> entr:temp.getEntries()){
            for(int k = 0; k<(temp.getColumnsNames().size()-1);k++){
                entry = entry + entr.get(temp.getColumnsNames().get(k)) + ",";

            }
            entry = entry + entr.get(temp.getColumnsNames().get(temp.getColumnsNames().size()-1)) + "\n";
        }
        writer.write(entry.toString());
        tables.remove(tablenam);
    }
    //Usage source:https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
    public void open_table(String tablenam){
        String csvFile = "C:\\JavaLib\\Project\\src\\project1\\"+tablenam+".csv";
        String line = "";
        BufferedReader br = null;
        String cvsSplitBy = ",";
        Table toadd = new Table(tablenam);
        try {

            br = new BufferedReader(new FileReader(csvFile));
            //keys
            line = br.readLine();
            String[] prim_keys = line.split(cvsSplitBy);
            //col_names
            for(int j = 0; j<prim_keys.length;j++){
                toadd.AddPrimaryKey(prim_keys[j]);
            }
            line = br.readLine();
            String[] col_names = line.split(cvsSplitBy);
            //col_types
            line = br.readLine();
            String[] col_types = line.split(cvsSplitBy);

            for(int j=0;j<col_types.length;j++){
                toadd.AddColumn(col_names[j] , col_types[j]);
            }

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] csv_entry = line.split(cvsSplitBy);
                List<Object> entr = new ArrayList<>();
                for(int k = 0;k<csv_entry.length;k++){
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
                    else{
                        String temp = "";
                        temp = "\"" + csv_entry[k] + "\"";
                    entr.add(temp);
                    }
                }
                toadd.AddEntry(entr);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tables.put(tablenam,toadd);

    }

    public void write_table(String tablenam) throws java.io.IOException{
      Table temp = tables.get(tablenam);
        File fold=new File("C:\\JavaLib\\Project\\src\\project1\\"+tablenam+".csv");
        fold.delete();
      File temp2 = new File("C:\\JavaLib\\Project\\src\\project1\\"+tablenam+".csv");
      temp2.createNewFile();
      PrintWriter writer = new PrintWriter(temp2);
      int i =1;
        StringBuilder sb = new StringBuilder();
        for(String a:temp.getPrimaryKeys()){

              sb.append(a);
              if(i < (temp.getPrimaryKeys().size())) {
                  sb.append(",");
              }
              else{
                  sb.append("\n");
              }
              ++i;
          }
        writer.write(sb.toString());

        i = 1;
        StringBuilder col = new StringBuilder();
        for(String a:temp.getColumnsNames()){

            col.append(a);
            if(i < (temp.getColumnsNames().size())) {
                col.append(",");
            }
            else{
                col.append("\n");
            }
            ++i;
        }
        writer.write(col.toString());
       i =0;
        String[] col_names = new String[temp.getColumnsNames().size()];
        for(String b:temp.getColumnsNames()){
            col_names[i] = b;
            ++i;
        }
        StringBuilder type = new StringBuilder();
        Hashtable<String,String> types = temp.getDataTypes();
        for(int j = 0; j<temp.getDataTypes().size();j++){

            type.append(types.get(col_names[j]));
            if(j < (temp.getDataTypes().size()-1)) {
                type.append(",");
            }
            else{
                type.append("\n");
            }
        }
        writer.write(type.toString());

        String entry = "";

            for(Hashtable<String,Object> entr:temp.getEntries()){
                for(int k = 0; k<(temp.getColumnsNames().size()-1);k++){
                    entry = entry + entr.get(temp.getColumnsNames().get(k)) + ",";

                }
                entry = entry + entr.get(temp.getColumnsNames().get(temp.getColumnsNames().size()-1)) + "\n";
            }
            writer.write(entry.toString());
    }
    public boolean AddTable(String name,Table table){
        tables.put(name, table);

        //Debugging table adding
        System.out.print("Added Table: " + name + " ( ");
        table.getColumnsNames().forEach((n)->System.out.print(n + "(" + table.getDataTypes().get(n) + "), "));
        System.out.println(")");


        return true;
    }

    public Table getTable(String name){
        return tables.get(name);
    }
}
