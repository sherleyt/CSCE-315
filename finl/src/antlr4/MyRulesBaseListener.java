package project1.antlr4;
import project1.*;

import org.antlr.v4.runtime.tree.*;

import java.util.*;


public class MyRulesBaseListener extends RulesBaseListener {

    Dbms myDbms;
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }

    public boolean evaluateConditional(List<ParseTree> condition){
        return false;
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
        Table table = myDbms.GetTable(ctx.children.get(1).getText());
        if (ctx.children.get(2).getText().equals("VALUES FROM")){
            //Values from
            List<Object> data = new ArrayList<>();
            for (int i = 4; i < ctx.children.size() ; i=i+2){
                data.add(ctx.children.get(i).getText()); // Maybe evaluate data type
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
        myDbms.printer(tableNam);
    }

}
