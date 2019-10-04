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
        Queue<String> expression = new PriorityQueue<>();
        Hashtable<String,Integer> values = new Hashtable<>();
        values.put("&&",1);
        values.put("||",1);
        values.put("==",2);
        values.put(">=",2);
        values.put("<=",2);
        values.put(">",2);
        values.put("<",2);
        values.put("!=",2);

        System.out.println(condition.getChildCount());
        for (int i = 0; i < condition.getChildCount(); i++){
            String input = condition.getChild(i).getText();
            if (values.keySet().contains(input)){
                if (values.get(operators.peek()) > values.get(input))
                    expression.add(operators.pop());
                operators.push(input);
            } else if (!input.equals("(") && !input.equals(")")){
                expression.add(input);
            }
        }
        while (!operators.isEmpty()){
            expression.add(operators.pop());
        }

        for(Hashtable<String,Object> entry: context.getEntries()){

            Stack<Object> attributes = new Stack<>();

            while (!expression.isEmpty()){
                if (values.keySet().contains(expression.peek())){

                    boolean evaluation = false;
                    switch(expression.poll()){
                        case "==":
                            if (attributes.peek() instanceof String) {
                                evaluation = (attributes.pop().equals(attributes.pop()));
                            } else {
                                evaluation = (attributes.pop() == attributes.pop());
                            }
                            break;
                        case "!=":
                            if (attributes.peek() instanceof String) {
                                evaluation = !(attributes.pop().equals(attributes.pop()));
                            } else {
                                evaluation = !(attributes.pop() == attributes.pop());
                            }
                            break;
                        case ">=":
                            evaluation = ((Integer)attributes.pop() >= (Integer)attributes.pop());
                            break;
                        case "<=":
                            evaluation = ((Integer)attributes.pop() <= (Integer)attributes.pop());
                            break;
                        case ">":
                            evaluation = ((Integer)attributes.pop() > (Integer)attributes.pop());
                            break;
                        case "<":
                            evaluation = ((Integer)attributes.pop() < (Integer)attributes.pop());
                            break;
                        case "&&":
                            evaluation = ((Boolean)attributes.pop() && (Boolean)attributes.pop());
                            break;
                        case "||":
                            evaluation = ((Boolean)attributes.pop() || (Boolean)attributes.pop());
                            break;
                        default:
                            break;
                    }
                    attributes.push(evaluation);
                } else {
                    String input = expression.poll();
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
                                System.err.println("Entry can find data for column: " + input);
                            }
                        default:
                            break;
                    }
                }
            }
            if (attributes.peek() instanceof Boolean){
                if (!(Boolean)attributes.peek()) {
                    context.getEntries().remove(entry);
                }
            } else System.err.println(attributes.toString());
        }
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
        System.out.println(ctx.children.get(2).getChildCount());
        FilterTable(context, ctx.children.get(2));
        System.out.println(ctx.getRuleContext().getChild(1).getText());
        myDbms.setSelect(context);
    }


}
