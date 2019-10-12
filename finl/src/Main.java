//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
package project1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import csce315.project1.MovieDatabaseParser;
import csce315.project1.Movie;
import csce315.project1.Credits;

import project1.antlr4.MyRulesBaseListener;
import project1.antlr4.RulesLexer;
import project1.antlr4.RulesParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {


        MovieDatabaseParser parser1 = new MovieDatabaseParser();
        MyRulesBaseListener listener = new MyRulesBaseListener();

        List<Movie> moviesList = parser1.deserializeMovies("src/project1/movies_single.json");
        List<Credits> creditsList = parser1.deserializeCredits("src/project1/credits_single.json");

        // create a big data base
        // create a map that maps each genre we encounter to a genre name
        List<String> lines = new ArrayList<>();   //"matrix"
        // genre is said to be integer but it is actually a list of ints
        lines.add("CREATE TABLE movies (id INTEGER, popularity INTEGER, has_credits INTEGER) PRIMARY KEY (id);");
        lines.add("CREATE TABLE cast (movie_id INTEGER, name VARCHAR(50), character_name VARCHAR(50), " +
                "id INTEGER, credit_id VARCHAR(50)) PRIMARY KEY (credit_id);");

        // adding info into the movies table
        for(int i = 0; i < moviesList.size(); i++) {
            lines.add("INSERT INTO movies VALUES FROM ("+moviesList.get(i).getId()+", "+
                    Math.round(moviesList.get(i).getPopularity()*1000)+", "+moviesList.get(i).getHasCredits()+");");
            // create a table called g{movie_id}
            lines.add("CREATE TABLE g"+moviesList.get(i).getId()+" (genre_id INTEGER, genre_name VARCHAR(30)) PRIMARY KEY (genre_id);");
            for(int j = 0; j < moviesList.get(i).getGenres().size(); j++) {
                int id = moviesList.get(i).getGenres().get(j).getId();
                String name = moviesList.get(i).getGenres().get(j).getName();
                lines.add("INSERT INTO g"+moviesList.get(i).getId()+" VALUES FROM ("+id+", \"" + name + "\");");
            }
            lines.add("SHOW g"+moviesList.get(i).getId()+";");
        }

        // adding info into the cast table
        // removing white spaces from name and character name
        for(int i = 0; i < creditsList.size(); i++) {
            int movie_id = Integer.parseInt(creditsList.get(i).getId());
            for(int j = 0; j < creditsList.get(i).getCastMember().size(); j++) {
                String name = creditsList.get(i).getCastMember().get(j).getName();
                String tempName = "";
                for(int k = 0; k < name.length(); k++) {
                    if(Character.isLetterOrDigit(name.charAt(k))) {
                        tempName = tempName + name.charAt(k);
                    }
                }
                String character = creditsList.get(i).getCastMember().get(j).getCharacter();
                String tempChar = "";
                for(int k = 0; k < character.length(); k++) {
                    if(Character.isLetterOrDigit(character.charAt(k))) {
                        tempChar = tempChar + character.charAt(k);
                    }
                }
                int id = creditsList.get(i).getCastMember().get(j).getId();
                String credit = creditsList.get(i).getCastMember().get(j).getCredit_id();
                lines.add("INSERT INTO cast VALUES FROM ("+movie_id+", \""+tempName+"\", \""+tempChar+"\", "+id+", \""+credit+"\");");
            }
        }

        // To get the query and generate the SQL statements
        int query = 4; // NEED TO GET THE VALUE FROM THE GUI
        switch(query)
        {
            case 4:
                String character_name = ""; // get actor 1 from GUI
                "SELECT name FROM
                break;
        }

        lines.add("SHOW cast;");
        lines.add("SHOW movies;");
        lines.add("EXIT;");

//        Movie movie = moviesList.get(0);
//        Credits credits = creditsList.get(0);
//
//        System.out.println("Is it an adult film? ");
//        System.out.println(movie.getAdult());
//        System.out.println();
//
//        System.out.println("What language was the movie in?");
//        System.out.println(movie.getSpoken_languages().get(0).getName());

        //Send to antlr/listener
//        MyRulesBaseListener listener = new MyRulesBaseListener();
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
