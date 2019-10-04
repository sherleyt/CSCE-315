package project1.antlr4;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import project1.*;

import org.antlr.v4.runtime.tree.*;

import java.util.*;


public class MyRulesBaseListener extends RulesBaseListener {

    Dbms myDbms;
    enum dataType {String, Integer, Column};

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
        } else {
            return dataType.Column;
        }
    }

    public boolean FilterTable(Table context, ParseTree condition){
        Stack<String> operators = new Stack<>();
        Queue<String> expression = new LinkedList<>();
        Hashtable<String,Integer> values = new Hashtable<>();
        values.put("&&",2);
        values.put("||",2);
        values.put("==",1);
        values.put(">=",1);
        values.put("<=",1);
        values.put(">",1);
        values.put("<",1);
        values.put("!=",1);

        List<String> text = new ArrayList<String>();
        getDeepText(text, condition);
        //System.out.println(text.toString()); //Outputs individual elements
        for (int i = 0; i < text.size(); i++){
            String input = text.get(i);
            if (values.keySet().contains(input)){
                if (operators.size() > 0){
                    if (values.get(operators.peek()) > values.get(input)){
                        System.out.println("Help");
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

        HashSet<Hashtable<String,Object>> entriesToRemove = new HashSet<>();
        for(Hashtable<String,Object> entry: context.getEntries()){
            Queue<String> expressionCopy = new LinkedList<>(expression);
            //System.out.println(expressionCopy.toString()); //Used to debug initial list;
            Stack<Object> attributes = new Stack<>();

            while (!expressionCopy.isEmpty()){
                if (values.keySet().contains(expressionCopy.peek())){

                    boolean evaluation = false;
                    String operator = expressionCopy.poll();
                    if (attributes.get(0) instanceof String && attributes.get(1) instanceof String){
                        //String comparison
                        String operand1 = (String)attributes.pop();
                        String operand2 = (String)attributes.pop();
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
                    else if (attributes.get(0) instanceof Integer && attributes.get(1) instanceof Integer){
                        //String comparison
                        int operand1 = (int)attributes.pop();
                        int operand2 = (int)attributes.pop();

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
                    else if (attributes.get(0) instanceof Boolean && attributes.get(1) instanceof Boolean){
                        //String comparison
                        boolean operand1 = (boolean)attributes.pop();
                        boolean operand2 = (boolean)attributes.pop();

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
                        System.err.println("Trying to compare different or unsupported types: " + attributes.pop().toString() + " " + attributes.pop());
                    }
                    attributes.push(evaluation);
                } else {
                    String input = expressionCopy.poll();
                    switch (ParseType(input)){
                        case String:
                            attributes.push(input.substring(1,input.length()-1));
                            break;
                        case Integer:
                            attributes.push(Integer.parseInt(input));
                            break;
                        case Column:
                            if (entry.get(input) != null){
                                attributes.push(entry.get(input));
                            } else {
                                System.err.println("Entry can't find data for column: " + input);
                            }
                        default:
                            break;
                    }
                }
                //System.out.println(expressionCopy.toString() + attributes.toString()); //Output expressionCopy after modification
            }
            if (attributes.peek() instanceof Boolean){
                if (!(Boolean)attributes.peek()) {
                    entriesToRemove.add(entry);
                }
            } else System.err.println(attributes.toString());
        }
        for(Hashtable<String,Object> entry : entriesToRemove)
            context.getEntries().remove(entry);

        //context.Print(); //Used to debug what entries are left
        return true;
    }
    public Table getTableFromExpr(ParseTree atomic_expr){
        Table table = new Table();
        //I'm sorry this is so hacky
        if (atomic_expr.getChildCount() == 1){
            //Is a relation
            table = myDbms.getTable(atomic_expr.getChild(0).getText());
        } else {
            //Is multipart
        }

        return table;
    }

    @Override public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {

        String tableName = ctx.children.get(1).getText();
        Table table = new Table();
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
        }
    }

    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx) {
        String tableNam = ctx.children.get(1).getChild(0).getText();
        System.out.println("Printing table: " + tableNam);
        myDbms.PrintTable(tableNam);
    }

    @Override public void exitSelection(RulesParser.SelectionContext ctx) {
        myDbms.ClearSelect();

        Table context = getTableFromExpr(ctx.children.get(4));
        //System.out.println(ctx.children.get(2).getChildCount());
        FilterTable(context, ctx.children.get(2));
        //System.out.println(ctx.getRuleContext().getChild(1).getText());
        myDbms.setSelect(context);
    }


}
