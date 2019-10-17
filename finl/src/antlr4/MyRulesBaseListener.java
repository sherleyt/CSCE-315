//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
package project1.antlr4;
import project1.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;


public class MyRulesBaseListener extends RulesBaseListener {

    public Dbms myDbms;
    enum dataType {String, Long, Column, Boolean};    //enum to help parse objects, and check the type of it

    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }

    //Go down the tree and get the important object, and add it to List<string> text, that was inputted
    public void getDeepText(List<String> text, ParseTree tree){
        if (tree.getChildCount() > 0){
            for (int i = 0; i < tree.getChildCount(); i++) {                //Keep going down    
                getDeepText(text, tree.getChild(i));
            }
        } else text.add(tree.getText());
    }

    //Check our enum, and find which data type the string is
    public dataType ParseType(String input){
        if (input.charAt(0) == '"' && input.charAt(input.length()-1) == '"'){ //if surrounded by quotes, is string
            return dataType.String;
        } else if (input.matches("[-]?[0-9]+")){                      //If digits, is a digit
            return dataType.Long;
        } else  if (input.toLowerCase().equals("true") || input.toLowerCase().equals("false")){     //Booleans
            return dataType.Boolean;
        } else {                           //If none of above, has to be column name
            return dataType.Column;
        }
    }

    //filter select's filtering part using Dijkstra shunting-yard algorithm
    private boolean FilterTableSelection(Table context, ParseTree condition){
        //Stack and list for dijstra
        Stack<String> operators = new Stack<>();
        List<String> expression = new ArrayList<>();
        //All possible operations
        Hashtable<String,Integer> values = new Hashtable<>();
        values.put("&&",2);           //&& and || carry more(different) weight than basic operations
        values.put("||",2);
        values.put("==",1);
        values.put(">=",1);
        values.put("<=",1);
        values.put(">",1);
        values.put("<",1);
        values.put("!=",1);

        //Get all the parts of tree
        List<String> text = new ArrayList<String>();
        getDeepText(text, condition); //defined above
        //System.out.println(text.toString()); //Outputs individual elements

        //Dijkstra algorithm-- takes all the parts of inputs and puts them in stack accordingly
        for (int i = 0; i < text.size(); i++){
            String input = text.get(i);
            if (values.keySet().contains(input)){ //If it is one of the expressions, add to list for later check
                if (operators.size() > 0){
                    if (values.get(operators.peek()) < values.get(input)){            //Check the weights, and pop if greater
                        expression.add(operators.pop());
                    }
                }
                operators.push(input); //push to stack
            } else if (input.equals(")") && operators.size()>0) { //found ending parenthesis, pop operator from stack
                expression.add(operators.pop());
            } else if (!input.equals("(") && !input.equals(")")) { //variable
                expression.add(input);
            }
        }
        while (!operators.isEmpty()){ //Ending, pop everything from stack
            expression.add(operators.pop());
        }

        //Parse expression for every entry and remove non-compliant ones
        HashSet<Hashtable<String,Object>> entriesToRemove = new HashSet<>();
        for(Hashtable<String,Object> entry: context.getEntries()){
            Stack<Object> attributes = new Stack<>();//Attributes stack, will help recurse/iterate

            for (int i = 0; i < expression.size(); i++){
                if (values.keySet().contains(expression.get(i))){

                    //If an operation, pull 2 values from the stack and evaluate/compare them
                    boolean evaluation = false;
                    String operator = expression.get(i);
                    if (attributes.get(attributes.size()-1) instanceof String && attributes.get(attributes.size()-2) instanceof String){
                        //String comparison
                        String operand2 = (String)attributes.pop();
                        String operand1 = (String)attributes.pop();
                        switch(operator){
                            case "==":
                                evaluation = operand1.equals(operand2);
                                break;
                            case "!=":
                                evaluation = !operand1.equals(operand2);
                                break;
                            default:
                                System.err.println("Trying to compare strings: [" +operand1+", " + operand2 + "] with: " + operator);
                                break;
                        }

                    }
                    //Check if they are operation comparisons
                    else if (attributes.get(attributes.size()-1) instanceof Long && attributes.get(attributes.size()-2) instanceof Long){
                        //String comparison of 2 atomic comparisons
                        int operand2 = (int)attributes.pop();
                        int operand1 = (int)attributes.pop();

                        switch(operator){
                            case "==":
                                evaluation = operand1 == operand2;
                                break;
                            case "!=":
                                evaluation = operand1 != operand2;
                                break;
                            case ">=":
                                evaluation = operand1 >= operand2;
                                break;
                            case "<=":
                                evaluation = operand1 <= operand2;
                                break;
                            case ">":
                                evaluation = operand1 > operand2;
                                break;
                            case "<":
                                evaluation = operand1 < operand2;
                                break;
                            default:
                                System.err.println("Trying to compare longs: [" +operand1+", "+operand2+"] with: "+operator);
                                break;
                        }

                    }
                    //Check if they are boolean result comparison types, ending
                    else if (attributes.get(attributes.size()-1) instanceof Boolean && attributes.get(attributes.size()-2) instanceof Boolean){
                        //String comparison, find which operator is it
                        boolean operand2 = (boolean)attributes.pop();
                        boolean operand1 = (boolean)attributes.pop();

                        switch(operator){
                            case "&&":
                                evaluation = operand1 && operand2;
                                break;
                            case "||":
                                evaluation = operand1 || operand2;
                                break;
                            case "==":
                                evaluation = operand1 == operand2;
                                break;
                            case "!=":
                                evaluation = operand1 != operand2;
                                break;
                            default:
                                System.err.println("Trying to compare booleans: [" +operand1+", "+operand2+"] with: "+operator);
                                break;
                        }

                    }
                    else {
                        //Error check
                        System.err.println("Trying to compare different or unsupported types: [" + attributes.pop().toString() + "] and [" + attributes.pop().toString() + "]");
                    }
                    attributes.push(evaluation);
                } else {
                    //If not an operation, check if the value is a string, integer, boolean, or data from the entry
                    String input = expression.get(i);
                    switch (ParseType(input)){
                        case String:
                            attributes.push(input.substring(1,input.length()-1));
                            break;
                        case Long:
                            attributes.push(Long.parseLong(input));
                            break;
                        case Boolean:
                            if (input.toLowerCase().equals("true"))
                                attributes.push(true);
                            else if (input.toLowerCase().equals("false"))
                                attributes.push(false);
                            break;
                        case Column:
                            if (entry.get(input) != null){             //Find the column, using check and push to attributes
                                attributes.push(entry.get(input));
                            } else {
                                System.err.println("Warning: An Entry couldn't find data for column: " + input);
                                System.err.println("Substituting with empty string");
                                attributes.push("");
                            }
                        default:
                            //Should never evaluate to this as Column is catch-all
                            break;
                    }
                }
            }
            if (attributes.peek() instanceof Boolean && attributes.size() == 1){       //Check if remove
                if (!(Boolean)attributes.peek()) {
                    entriesToRemove.add(entry);
                }
                //error input
            } else System.err.println("Equation format unsupported. Missing operators comparators/operators:\n"+attributes.toString());
        }
        //Go through which entries to remove from table and remove
        for(Hashtable<String,Object> entry : entriesToRemove)
            context.getEntries().remove(entry);

        //context.Print(); //Used to debug what entries are left
        return true;
    }

    //helps filter tables for Projection command
    private boolean FilterTableProjection(Table context, ParseTree attributeList){
        HashSet<String> attributes = new HashSet<>();                    //Has all attributes as strings
        for (int i = 0; i < attributeList.getChildCount(); i=i+2){
            attributes.add(attributeList.getChild(i).getText());
        }
        List<String> columnsToRemove = new ArrayList<>();
        for (String columnName : context.getColumnsNames()){
            if (!attributes.contains(columnName))  columnsToRemove.add(columnName);
        }

        for (String columnName: columnsToRemove){
            HashSet<Hashtable<String,Object>> entriesToRemove = new HashSet<>();
            //Remove data from entries, remove entries if they are empty.
            for(Hashtable<String,Object> entry: context.getEntries()){
                entry.remove(columnName);
                if (entry.keySet().isEmpty()){
                    entriesToRemove.add(entry);
                    System.err.println("Warning: Projection removed entry (No Data)!");
                }
            }
            for (Hashtable<String,Object> entry:entriesToRemove){
                context.getEntries().remove(entry);
            }
            //Finally, remove column from keys, columns, and datatype information
            context.getPrimaryKeys().remove(columnName);
            if (context.getPrimaryKeys().size() <= 0) System.err.println("Warning: Projection removed all primary keys! (Can't add entries without primary keys)");
            context.getColumnsNames().remove(columnName);
            context.getDataTypes().remove(columnName);
        }
        return true;
    }
    //Renames/edits column according to tree/put into context table
    public boolean EditTableRenameColumn(Table context, ParseTree attributeList){
        List<String> attributes = new ArrayList<>(); //Entries to pass over
        for (int i = 0; i < attributeList.getChildCount(); i=i+2){            //Go down,
            attributes.add(attributeList.getChild(i).getText());
        }
        for (int i = 0; i < attributes.size() && i < context.getColumnsNames().size(); i++){
            for (Hashtable<String,Object> entry: context.getEntries()){
                entry.put(attributes.get(i),entry.get(context.getColumnsNames().get(i)));
                entry.remove(context.getColumnsNames().get(i));
            }
            context.getDataTypes().put(attributes.get(i), context.getDataTypes().get(context.getColumnsNames().get(i)));
            context.getDataTypes().remove(context.getColumnsNames().get(i));

            if (context.getPrimaryKeys().contains(context.getColumnsNames().get(i))){
                context.getPrimaryKeys().remove(context.getColumnsNames().get(i));
                context.getPrimaryKeys().add(attributes.get(i));
            }
            context.getColumnsNames().set(i,attributes.get(i));

        }
        return true;
    }
    //Not finished, will help + command
    public boolean TableMathUnion(Table context, Table table1, Table table2){
        List<String> columnNames = new ArrayList<>();

        //Inherits column order from table1
        //Adds column to context if Name and Datatype are the same.
        //I know this is gross... don't @ me
        for (int i = 0; i< table1.getColumnsNames().size(); i++){
            String columnName = table1.getColumnsNames().get(i);
            if (table2.getColumnsNames().contains(columnName)){
                if (table1.getDataTypes().get(columnName).equals(table2.getDataTypes().get(columnName))){
                    columnNames.add(columnName);
                    context.AddColumn(columnName,table1.getDataTypes().get(columnName));
                    //Add column as a primary key is it is one in either table1 or table2 (More General)
                    if (table1.getPrimaryKeys().contains(columnName) || table2.getPrimaryKeys().contains(columnName)){
                        context.getPrimaryKeys().add(columnName);
                    }
                } else if (table1.getDataTypes().get(columnName).toLowerCase().contains("varchar") && table2.getDataTypes().get(columnName).toLowerCase().contains("varchar")){ //Checks if columns are different sized strings
                    int size1 = Integer.parseInt(table1.getDataTypes().get(columnName).substring(8,table1.getDataTypes().get(columnName).length()-1));
                    int size2 = Integer.parseInt(table2.getDataTypes().get(columnName).substring(8,table2.getDataTypes().get(columnName).length()-1));
                    System.err.println("Warning: Union between [" + table1.getName() + "," + table2.getName()+"] of Column: ["+columnName+"] has different sized strings. (Merging to largest)");

                    columnNames.add(columnName);
                    context.AddColumn(columnName,(size1 >= size2 ? table1.getDataTypes().get(columnName):table2.getDataTypes().get(columnName)));
                    if (table1.getPrimaryKeys().contains(columnName) || table2.getPrimaryKeys().contains(columnName)){
                        context.getPrimaryKeys().add(columnName);
                    }

                } else {
                    //Matching error
                    System.err.println("Warning: Union between [" + table1.getName() + "," + table2.getName()+"] of Column: ["+columnName+"] failed. (Different datatypes)");
                }
            }
        }

        //Only Entries if they actually have data in at least one columns
        for (Hashtable<String,Object> entry: table1.getEntries()){
            List<Object> entryValues = new ArrayList<>();
            for (int i = 0;i < columnNames.size(); i++){
                entryValues.add(entry.get(columnNames.get(i)));
            }
            if (entryValues.size() > 0) context.AddEntry(entryValues);
        }
        for (Hashtable<String,Object> entry: table2.getEntries()){
            List<Object> entryValues = new ArrayList<>();
            for (int i = 0;i < columnNames.size(); i++){
                entryValues.add(entry.get(columnNames.get(i)));
            }
            if (entryValues.size() > 0) context.AddEntry(entryValues);
        }

        return true;
    }
    //Function to help - command, according to syntax
    public boolean TableMathDiff(Table context, Table table1, Table table2){
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i< table1.getColumnsNames().size(); i++){
            String columnName = table1.getColumnsNames().get(i);
            if (table2.getColumnsNames().contains(columnName)){
                if (table1.getDataTypes().get(columnName).equals(table2.getDataTypes().get(columnName))){
                    columnNames.add(columnName);
                    context.AddColumn(columnName,table1.getDataTypes().get(columnName));
                    //Add column as a primary key is it is one in either table1 or table2 (More General)
                    if (table1.getPrimaryKeys().contains(columnName) || table2.getPrimaryKeys().contains(columnName)){
                        context.getPrimaryKeys().add(columnName);
                    }
                } else {
                    System.err.println("Warning: Difference between [" + table1.getName() + "," + table2.getName()+"] of Column: ["+columnName+"] failed. (Different datatypes)");
                }
            }
        }

        // System.out.println(context.getColumnsNames().toString());//Erorr check
        //Go through columns and check similar columns
        for (Hashtable<String,Object> entry1: table1.getEntries()){
            boolean matching = false;
            //indented for loops for each table
            for (Hashtable<String,Object> entry2: table2.getEntries()) {
                matching = true;
                for (String columnName:columnNames){
                    if (entry1.get(columnName) != entry2.get(columnName)){
                        matching = false;
                    }
                }
                if (matching == true) break;
            }
            if (!matching){
                List<Object> entryValues = new ArrayList<>();
                for (int i = 0;i < columnNames.size(); i++){
                    entryValues.add(entry1.get(columnNames.get(i)));
                }
                if (entryValues.size() > 0) context.AddEntry(entryValues);
            }
        }
        //Go through entries and check similar entries
        for (Hashtable<String,Object> entry2: table2.getEntries()){
            boolean matching = false;
            //indented for loops for each entry
            for (Hashtable<String,Object> entry1: table1.getEntries()) {
                matching = true;
                for (String columnName:columnNames){
                    if (entry1.get(columnName) != entry2.get(columnName)){
                        matching = false;
                    }
                }
                if (matching == true) break;
            }
            if (!matching){
                List<Object> entryValues = new ArrayList<>();
                for (int i = 0;i < columnNames.size(); i++){
                    entryValues.add(entry2.get(columnNames.get(i)));
                }
                if (entryValues.size() > 0) context.AddEntry(entryValues);
            }
        }
        return true;
    }
    //Not finished, helps * command and creates temp(context) table accordingly
    public boolean TableMathProd(Table context, Table table1, Table table2){
        //Add Column names/types
        List<String> col_type1 = table1.getColumnsNames();
        for(String a: col_type1){
            context.AddColumn(a,table1.getDataTypes().get(a));
        }
        //Add columns from table2
        List<String> col_type2 = table2.getColumnsNames();
        for(String a: col_type2){
            context.AddColumn(a,table2.getDataTypes().get(a));
        }

        //Add primary keys
        Set<String> prim_tab1 = table1.getPrimaryKeys();
        Set<String> prim_tab2 = table2.getPrimaryKeys();
        for(String a:prim_tab1) {
            if(context.AddPrimaryKey(a) == false){
                System.err.println("Matching Primary keys in " + a);
                return false;
            }
        }
        for(String b:prim_tab2) {
            if(context.AddPrimaryKey(b)==false) {
                System.err.println("Matching Primary keys in *" + b);
                return false;
            }
        }
        //Add all entries
        HashSet<Hashtable<String,Object>> entries1 = table1.getEntries();
        HashSet<Hashtable<String,Object>> entries2 = table2.getEntries();
        List<Object> final_entry;

        //Go through table1's entries and add them to a list of objects
        for(Hashtable<String,Object> obj1:entries1){
            final_entry = new ArrayList<Object>();
            for(int k = 0; k<(table1.getColumnsNames().size());k++){
                final_entry.add(obj1.get(table1.getColumnsNames().get(k)));
            }

            //Add values from table2, appended to previous table
            for(Hashtable<String,Object> obj2:entries2){
                List<Object> actual_entry = new ArrayList<Object>(final_entry);

                for(int j = 0; j<(table2.getColumnsNames().size());j++){
                    actual_entry.add(obj2.get(table2.getColumnsNames().get(j)));
                }
                //Add entry with all values in table1 and table2
                context.AddEntry(actual_entry);
            }
        }
        return true;
    }

    //DONT HAVE TO-- ONLY TEAMS of 5
    //Not finished, helps / command and creates temp(context) table accordingly
    public boolean TableMathNat_Join(Table context, Table table1, Table table2){
        return true;
    }

    //Find table name/information from the parse tree, as per syntax
    public Table getTableFromAtomicExpr(ParseTree atomic_expr){
        Table table;
        //I'm sorry this is so hacky
        if (atomic_expr.getChildCount() == 1){                             //Just child

            table = myDbms.getTable(atomic_expr.getChild(0).getText());
            if(table == null){
                System.err.println("Couldnt find table in DBMS, " + atomic_expr.getChild(0).getText());
                System.exit(1);
            }
        } else {
            table = getTableFromExpr(atomic_expr.getChild(1));             //second child
        }


        return table;
    }
    //Function that going down a parse tree to satisfy a given command
    public Table getTableFromExpr(ParseTree expr){
        Table table;
        expr = expr.getChild(0);
        if (expr instanceof RulesParser.Atomic_exprContext){                 //Ending case
            table = getTableFromAtomicExpr(expr);
        }else if (expr instanceof RulesParser.SelectionContext){               //Syntax for select command
            table = new Table(getTableFromAtomicExpr(expr.getChild(4)));
            FilterTableSelection(table, expr.getChild(2));
        } else if (expr instanceof RulesParser.ProjectionContext){               //Syntax for projection command
            table = new Table(getTableFromAtomicExpr(expr.getChild(4)));
            FilterTableProjection(table, expr.getChild(2));
        } else if (expr instanceof RulesParser.RenamingContext){               //Syntax for rename command
            table = new Table(getTableFromAtomicExpr(expr.getChild(4)));
            EditTableRenameColumn(table, expr.getChild(2));
        } else if (expr instanceof RulesParser.UnionContext){               //Syntax for + command
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "+" + table2.getName());
            TableMathUnion(table, table1, table2);
        } else if (expr instanceof RulesParser.DifferenceContext){               //Syntax for - command
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "-" + table2.getName());
            TableMathDiff(table, table1, table2);
        } else if (expr instanceof RulesParser.ProductContext){               //Syntax for * command
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "*" + table2.getName());
            TableMathProd(table, table1, table2);
        } else if (expr instanceof RulesParser.Natural_joinContext){               //Syntax for natural join command
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "&" + table2.getName());
            TableMathNat_Join(table, table1, table2);
        } else {
            System.err.println("Could not parse expression! Returning empty table!");
            table = new Table("EMPTY");
        }
        return table;
    }
    //Create table command, will create according to syntax provided in .g4 file
    @Override public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {
        //Get all information in the same syntax everytime, simply keep getting children
        String tableName = ctx.children.get(1).getText();
        Table table = new Table(tableName);
        for (int i = 0; i+1 < ctx.children.get(3).getChildCount(); i = i + 3){          //Column names/types here
            table.AddColumn(ctx.children.get(3).getChild(i).getText(),
                    ctx.children.get(3).getChild(i+1).getText());
        }

        for (int i = 0; i < ctx.children.get(7).getChildCount(); i = i + 2){          //Primary names here
            table.AddPrimaryKey(ctx.children.get(7).getChild(i).getText());
        }

        myDbms.AddTable(tableName,table); //Add table to DBMS

    }
    //Not finished, Insert into table, using information after either "VALUES FROM" or "... RELATION"
    @Override public void exitInsert_cmd(RulesParser.Insert_cmdContext ctx) {
        Table table = myDbms.getTable(ctx.children.get(1).getText());
        if (ctx.children.get(2).getText().equals("VALUES FROM")){      //Simple, just get all entries and put into a row
            //Values from
            List<Object> data = new ArrayList<>();
            for (int i = 4; i < ctx.children.size() ; i=i+2){      //4 is where actual information start
                String input = ctx.children.get(i).getText();
                switch (ParseType(input)){
                    case String:
                        data.add(input.substring(1,input.length()-1));
                        break;
                    case Long:
                        data.add(Long.parseLong(input));
                        break;
                    default:
                        System.err.println("Unrecognized data format " + input);
                        data.add(ctx.children.get(i).getText());
                        break;
                }

            }
            if (!table.AddEntry(data)){
                System.err.println(data.toString() + " couldn't be added!");
            }

        } else if (ctx.children.get(2).getText().equals("VALUES FROM RELATION")) {            //requires evaluation first, then add to table
            //Values from relation, makes a new table according to provided expr
            Table table2 = getTableFromExpr(ctx.getChild(3));


            //Make new table with table previously made that contains values to be added
            Table table_final = new Table(ctx.children.get(1).getText());

            //Add All values taht need to be added from both tables
            TableMathUnion(table_final,table2,table);

            //Remove un-updated table and insert new updated one
            myDbms.delete_table(ctx.children.get(1).getText());
            myDbms.AddTable(ctx.children.get(1).getText(),table_final);
        }
    }
    //Update command, changes values in table to those specified based on check provided
    @Override public void exitUpdate_cmd(RulesParser.Update_cmdContext ctx) {
        String table_name_update = ctx.getChild(1).getText();
        int num_changes = (ctx.getChildCount()-3-2+1)/4;

        //Hashtable of all the changes we need, will be consistent
        Hashtable<String,Object> updates = new Hashtable<>();
        for(int i = 0; i < num_changes;i++){
            String col = ctx.getChild(i*4 + 3).getText();
            String obj = ctx.getChild(i*4+5).getText();
            switch (ParseType(obj)){
                case String:
                    updates.put(col,obj.substring(1,obj.length()-1));
                    break;
                case Long:
                    updates.put(col,(Long.parseLong(obj)));
                    break;
                default:
                    System.err.println("Unrecognized data format " + obj);
                    break;
            }
        }


        //Tables of rows taht have to be changed
        Table filtered_table = new Table(myDbms.getTable(table_name_update));
        FilterTableSelection(filtered_table,ctx.getChild(ctx.getChildCount()-1));

        //Table of rows that we have to keep
        Table to_keep = new Table(table_name_update);
        TableMathDiff(to_keep,myDbms.getTable(table_name_update),filtered_table);

        //Change the entries accordinging to previously made hashtable
        HashSet<Hashtable<String,Object>> change_entries = filtered_table.getEntries();
        //Each row
        for(Hashtable<String,Object> entry1:change_entries){
            //Each object is changed
            for(Map.Entry<String,Object> y : updates.entrySet()){
                if((filtered_table.getColumnsNames().contains(y.getKey()))){ //Change only if key of the column exists
                    entry1.put(y.getKey(),y.getValue());
                }

            }
            //Make a list of the created augmented entry to then add using AddEntry
            List<Object> changed_entry = new ArrayList<>();
            for(int o = 0;o < filtered_table.getColumnsNames().size();o++) {
                changed_entry.add(entry1.get(filtered_table.getColumnsNames().get(o)));

            }
            to_keep.AddEntry(changed_entry);
        }

        //Delete the un-updated table from DBMS and then add the new updated one
        myDbms.delete_table(table_name_update);
        myDbms.AddTable(table_name_update,to_keep);

    }
    //Not finished, but is just a delete command for table
    @Override public void exitDelete_cmd(RulesParser.Delete_cmdContext ctx) {
        String table_to_delete_from = ctx.getChild(1).getText();
        Table to_sub = new Table(myDbms.getTable(table_to_delete_from));
        //Filter the entries according to specified changes, will be table with values to remove
        FilterTableSelection(to_sub,ctx.getChild(3));

        //Make a table of final values that have old_table - to_sub
        Table answer = new Table(table_to_delete_from);
        TableMathDiff(answer,myDbms.getTable(table_to_delete_from), to_sub);

        //remove old table and add the new updated one
        if (myDbms.delete_table(table_to_delete_from) == false){
            System.out.println("Error deleting table");
            System.exit(1);
        }
        myDbms.AddTable(table_to_delete_from,answer);
    }
    //Create a table from file, acutal code found in table.java
    @Override public void exitOpen_cmd(RulesParser.Open_cmdContext ctx) {
        String table = ctx.getChild(1).getText();
        myDbms.open_table(table);
    }
    //Write to file and remove from dmbs, acutal code found in table.java
    @Override public void exitClose_cmd(RulesParser.Close_cmdContext ctx) {
        String table = ctx.getChild(1).getText();
        try{
            myDbms.close_table(table);}
        catch(Exception e){
            System.out.println("error closing");
        }
    }
    //Write into file, found in table class
    @Override public void exitWrite_cmd(RulesParser.Write_cmdContext ctx) {
        String table = ctx.getChild(1).getText();
        try{
            myDbms.write_table(table);}
        catch (Exception e){
            System.out.println("error writing");
        }
    }
    //Not finished yet, but will just exit and not take SQL commands
    @Override public void exitExit_cmd(RulesParser.Exit_cmdContext ctx) {
        System.out.println("Exit command called, no more SQL commands.");
        System.exit(0);
    }

    //Show command, calls table from dbms using getTableFromAtomicExpr, which will just return from the DBMS
    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx) {
        Table table = getTableFromAtomicExpr(ctx.children.get(1));
        table.Print();
    }
    //Querry command, ads the table in question
    @Override public void exitQuery(RulesParser.QueryContext ctx) {
        String tableName = ctx.getChild(0).getText();
        Table table = new Table(getTableFromExpr(ctx.getChild(2)));
        table.setName(tableName);
        myDbms.AddTable(tableName,table);
    }


}
