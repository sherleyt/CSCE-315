package project1.antlr4;
import project1.*;

import org.antlr.v4.runtime.tree.*;

import java.util.*;


public class MyRulesBaseListener extends RulesBaseListener {

    Dbms myDbms;
    enum dataType {String, Integer, Column, Boolean};

    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }

    public void getDeepText(List<String> text, ParseTree tree){
        if (tree.getChildCount() > 0){
            for (int i = 0; i < tree.getChildCount(); i++) {
                getDeepText(text, tree.getChild(i));
            }
        } else text.add(tree.getText());
    }

    public dataType ParseType(String input){
        if (input.charAt(0) == '"' && input.charAt(input.length()-1) == '"'){
           return dataType.String;
        } else if (input.matches("[0-9]+")){
            return dataType.Integer;
        } else  if (input.toLowerCase().equals("true") || input.toLowerCase().equals("false")){
            return dataType.Boolean;
        } else {
            return dataType.Column;
        }
    }

    private boolean FilterTableSelection(Table context, ParseTree condition){
        Stack<String> operators = new Stack<>();
        List<String> expression = new ArrayList<>();
        Hashtable<String,Integer> values = new Hashtable<>();
        values.put("&&",2);
        values.put("||",2);
        values.put("==",1);
        values.put(">=",1);
        values.put("<=",1);
        values.put(">",1);
        values.put("<",1);
        values.put("!=",1);

        //Construct expression
        List<String> text = new ArrayList<String>();
        getDeepText(text, condition);
        //System.out.println(text.toString()); //Outputs individual elements
        for (int i = 0; i < text.size(); i++){
            String input = text.get(i);
            if (values.keySet().contains(input)){
                if (operators.size() > 0){
                    if (values.get(operators.peek()) < values.get(input)){
                        expression.add(operators.pop());
                    }
                }
                operators.push(input);
            } else if (input.equals(")") && operators.size()>0) {
                expression.add(operators.pop());
            } else if (!input.equals("(") && !input.equals(")")) {
                expression.add(input);
            }
        }
        while (!operators.isEmpty()){
            expression.add(operators.pop());
        }
        //System.out.println(expression.toString()); //Output expression

        //Parse expression for every entry and remove non-compliant ones
        HashSet<Hashtable<String,Object>> entriesToRemove = new HashSet<>();
        for(Hashtable<String,Object> entry: context.getEntries()){
            Stack<Object> attributes = new Stack<>();

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
                    else if (attributes.get(attributes.size()-1) instanceof Integer && attributes.get(attributes.size()-2) instanceof Integer){
                        //String comparison
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
                                System.err.println("Trying to compare integers: [" +operand1+", "+operand2+"] with: "+operator);
                                break;
                        }

                    }
                    else if (attributes.get(attributes.size()-1) instanceof Boolean && attributes.get(attributes.size()-2) instanceof Boolean){
                        //String comparison
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
                        System.err.println("Trying to compare different or unsupported types: " + attributes.pop().toString() + " " + attributes.pop().toString());
                    }
                    attributes.push(evaluation);
                } else {
                    //If not an operation, check if the value is a string, integer, boolean, or data from the entry
                    String input = expression.get(i);
                    switch (ParseType(input)){
                        case String:
                            attributes.push(input.substring(1,input.length()-1));
                            break;
                        case Integer:
                            attributes.push(Integer.parseInt(input));
                            break;
                        case Boolean:
                            if (input.toLowerCase().equals("true"))
                                attributes.push(true);
                            else if (input.toLowerCase().equals("false"))
                                attributes.push(false);
                            break;
                        case Column:
                            if (entry.get(input) != null){
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
                //System.out.println(expression.get(i) + " " + attributes.toString()); //Output expressionCopy after modification
            }
            if (attributes.peek() instanceof Boolean && attributes.size() == 1){
                if (!(Boolean)attributes.peek()) {
                    entriesToRemove.add(entry);
                }
            } else System.err.println("Equation format unsupported. Missing operators comparators/operators:\n"+attributes.toString());
        }
        for(Hashtable<String,Object> entry : entriesToRemove)
            context.getEntries().remove(entry);

        //context.Print(); //Used to debug what entries are left
        return true;
    }
    private boolean FilterTableProjection(Table context, ParseTree attributeList){
        HashSet<String> attributes = new HashSet<>();
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
    public boolean EditTableRenameColumn(Table context, ParseTree attributeList){
        List<String> attributes = new ArrayList<>();
        for (int i = 0; i < attributeList.getChildCount(); i=i+2){
            attributes.add(attributeList.getChild(i).getText());
        }
        for (int i = 0; i < attributes.size() && i < context.getColumnsNames().size(); i++){
            for (Hashtable<String,Object> entry: context.getEntries()){
                entry.put(attributes.get(i),entry.get(context.getColumnsNames().get(i)));
                entry.remove(context.getColumnsNames().get(i));
            }
            context.getDataTypes().put(attributes.get(i), context.getDataTypes().get(context.getColumnsNames().get(i)));
            context.getDataTypes().remove(context.getColumnsNames().get(i));

            context.getColumnsNames().set(i,attributes.get(i));
        }
        return true;
    }
    public boolean TableMathUnion(Table context, Table table1, Table table2){
        List<String> columnNames = new ArrayList<>();

        //Inherits order from table1
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
                } else {
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
    public boolean TableMathDiff(Table context, Table table1, Table table2){
        return true;
    }
    public boolean TableMathProd(Table context, Table table1, Table table2){
        return true;
    }
    public boolean TableMathNat_Join(Table context, Table table1, Table table2){
        return true;
    }

    public Table getTableFromAtomicExpr(ParseTree atomic_expr){
        Table table;
        //I'm sorry this is so hacky
        if (atomic_expr.getChildCount() == 1){
            table = myDbms.getTable(atomic_expr.getChild(0).getText());
        } else {
            table = getTableFromExpr(atomic_expr.getChild(1));
        }

        return table;
    }
    public Table getTableFromExpr(ParseTree expr){
        Table table;
        expr = expr.getChild(0);
        if (expr instanceof RulesParser.Atomic_exprContext){
            table = getTableFromAtomicExpr(expr);
        }else if (expr instanceof RulesParser.SelectionContext){
            table = new Table(getTableFromAtomicExpr(expr.getChild(4)));
            FilterTableSelection(table, expr.getChild(2));
        } else if (expr instanceof RulesParser.ProjectionContext){
            table = new Table(getTableFromAtomicExpr(expr.getChild(4)));
            FilterTableProjection(table, expr.getChild(2));
        } else if (expr instanceof RulesParser.RenamingContext){
            table = new Table(getTableFromAtomicExpr(expr.getChild(4)));
            EditTableRenameColumn(table, expr.getChild(2));
        } else if (expr instanceof RulesParser.UnionContext){
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "+" + table2.getName());
            TableMathUnion(table, table1, table2);
        } else if (expr instanceof RulesParser.DifferenceContext){
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "-" + table2.getName());
            TableMathDiff(table, table1, table2);
        } else if (expr instanceof RulesParser.ProductContext){
            Table table1 = getTableFromAtomicExpr(expr.getChild(0));
            Table table2 = getTableFromAtomicExpr(expr.getChild(2));
            table = new Table(table1.getName() + "*" + table2.getName());
            TableMathProd(table, table1, table2);
        } else if (expr instanceof RulesParser.Natural_joinContext){
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

    @Override public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {

        String tableName = ctx.children.get(1).getText();
        Table table = new Table(tableName);
        for (int i = 0; i+1 < ctx.children.get(3).getChildCount(); i = i + 3){
            table.AddColumn(ctx.children.get(3).getChild(i).getText(),
                        ctx.children.get(3).getChild(i+1).getText());
        }

        for (int i = 0; i < ctx.children.get(7).getChildCount(); i = i + 2){
            table.AddPrimaryKey(ctx.children.get(7).getChild(i).getText());
        }

        myDbms.AddTable(tableName,table);

    }
    @Override public void exitInsert_cmd(RulesParser.Insert_cmdContext ctx) {
        Table table = myDbms.getTable(ctx.children.get(1).getText());
        if (ctx.children.get(2).getText().equals("VALUES FROM")){
            //Values from
            List<Object> data = new ArrayList<>();
            for (int i = 4; i < ctx.children.size() ; i=i+2){
                String input = ctx.children.get(i).getText();
                switch (ParseType(input)){
                    case String:
                        data.add(input.substring(1,input.length()-1));
                        break;
                    case Integer:
                        data.add(Integer.parseInt(input));
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

        } else if (ctx.children.get(2).getText().equals("VALUES FROM RELATION")) {
            //Values from relation
            table = getTableFromExpr(ctx.getChild(3));
            //FINISH THIS
        }
    }
    @Override public void exitUpdate_cmd(RulesParser.Update_cmdContext ctx) {

    }
    @Override public void exitDelete_cmd(RulesParser.Delete_cmdContext ctx) {

    }
    @Override public void exitOpen_cmd(RulesParser.Open_cmdContext ctx) {

    }
    @Override public void exitClose_cmd(RulesParser.Close_cmdContext ctx) {

    }
    @Override public void exitWrite_cmd(RulesParser.Write_cmdContext ctx) {

    }
    @Override public void exitExit_cmd(RulesParser.Exit_cmdContext ctx) {

    }


    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx) {
        Table table = getTableFromAtomicExpr(ctx.children.get(1));
        table.Print();
    }
    @Override public void exitQuery(RulesParser.QueryContext ctx) {
        String tableName = ctx.getChild(0).getText();
        Table table = getTableFromExpr(ctx.getChild(2));
        table.setName(tableName);
        myDbms.AddTable(tableName,table);
    }


}
