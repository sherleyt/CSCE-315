package project1.antlr4;
import project1.*;

import org.antlr.v4.runtime.tree.*;

import java.util.*;


public class MyRulesBaseListener extends RulesBaseListener {

    Dbms myDbms;
    public MyRulesBaseListener() {
        myDbms = new Dbms();
    }

    @Override public void exitCreate_cmd(RulesParser.Create_cmdContext ctx) {

        String tableName = ctx.children.get(1).getText();
        Table table = new Table();
        for (int i = 0; i+1 < ctx.children.get(3).getChildCount(); i = i + 3){
            table.AddColumn(ctx.children.get(3).getChild(i).getText(),
                        ctx.children.get(3).getChild(i+1).getText());
        }
        myDbms.AddTable(tableName,table);

    }


    @Override public void exitShow_cmd(RulesParser.Show_cmdContext ctx) {
        System.out.println("SHOW");
    }

}
