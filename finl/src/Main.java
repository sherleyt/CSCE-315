//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
package project1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project1.antlr4.MyRulesBaseListener;
import project1.antlr4.RulesLexer;
import project1.antlr4.RulesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
		//Open input file(SQL codes in .txt)
        File file = new File("src/project1/input.txt");
		
		//Create scanner to get all SQL codes from input file
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();   //"matrix"
		
        while (scanner.hasNextLine()) {                      //While there are lines, go in while and add
            String line = scanner.nextLine();
            if (line.length() != 0) { lines.add(line); }
        }
		
		
		//Send to antlr/listener
        MyRulesBaseListener listener = new MyRulesBaseListener();  
        for (String line : lines) {
			//Actual strings
            CharStream charStream = CharStreams.fromString(line);
            RulesLexer lexer = new RulesLexer(charStream);
			//Make tokens from lexer
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
			//Parse the tokens to make parse-tree
            RulesParser parser = new RulesParser(commonTokenStream);
            RulesParser.ProgramContext programContext = parser.program(); //Context as needed
			//Give parse-tree to listener
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, programContext);
        }
        
    }
}
