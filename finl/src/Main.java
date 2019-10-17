//Team Not - Sebastian T., Sahil P., Sherley T., Travis D.
//CSCE 315-909
package project1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.io.*;

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

    public static String removeSpace(String word){
        String newWord="";
        for(int k=0;k<word.length();k++){
            if((word.charAt(k)>='A'&& word.charAt(k)<='Z')||(word.charAt(k)>='a'&& word.charAt(k)<='z')||(word.charAt(k)>=48&& word.charAt(k)<=57)) {
                newWord=newWord+word.charAt(k);
            } else {
                newWord = newWord + "_";
            }
        }
        if(newWord == "") {
            return "Unknown";
        }
        return newWord;
    }

    // TODO: add back spaces

    public static void main(String[] args) throws Exception {

        MovieDatabaseParser parser1 = new MovieDatabaseParser();
        MyRulesBaseListener listener = new MyRulesBaseListener();

        List<Movie> moviesList = parser1.deserializeMovies("src/project1/movies_single.json");
        List<Credits> creditsList = parser1.deserializeCredits("src/project1/credits_single.json");

        // create a big data base
        // create a map that maps each genre we encounter to a genre name
        List<String> lines = new ArrayList<>();   //"matrix"
        lines.add("CREATE TABLE movies (id INTEGER, popularity INTEGER, has_credits INTEGER) PRIMARY KEY (id);");
        lines.add("CREATE TABLE cast (m_id INTEGER, name VARCHAR(150), character_name VARCHAR(150), " +
                "id INTEGER, credit_id VARCHAR(150)) PRIMARY KEY (credit_id);");
        lines.add("CREATE TABLE genres (movie_id INTEGER, genre_id INTEGER, genre_name VARCHAR(150), index INTEGER) PRIMARY KEY (index);");

        // adding info into the movies table
        for(int i = 0; i < moviesList.size(); i++) {
            // TODO: change the popularity
            lines.add("INSERT INTO movies VALUES FROM ("+moviesList.get(i).getId()+", "+
                    Math.round(moviesList.get(i).getPopularity()*1000)+", "+moviesList.get(i).getHasCredits()+");");
            for(int j = 0; j < moviesList.get(i).getGenres().size(); j++) {
                int id = moviesList.get(i).getGenres().get(j).getId();
                String name = removeSpace(moviesList.get(i).getGenres().get(j).getName());
                String index = Integer.toString(moviesList.get(i).getId()) + Integer.toString(j) + Integer.toString(i);
                lines.add("INSERT INTO genres"+" VALUES FROM ("+moviesList.get(i).getId()+", "+id+", \"" + name + "\""+", "+index+");");
            }
        }
        lines.add("SHOW genres;");

        // adding info into the cast table
        // removing white spaces from name and character name
        for(int i = 0; i < creditsList.size(); i++) {
            long movie_id = Long.parseLong(creditsList.get(i).getId());
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
                lines.add("INSERT INTO cast VALUES FROM ("+movie_id+", \""+removeSpace(name)+"\", \""+removeSpace(character)+"\", "+id+", \""+credit+"\");");
            }
        }

        lines.add("SHOW movies;");

        // To get the query and generate the SQL statements
        // TODO: get the value from the GUI
        int query = 3;
        boolean checkthree = false;
        boolean checktwo = false;
        int checktwocount = 0;
        switch(query)
        {
            // WORKS ON SINGLE MOVIE AND TWO MOVIES
            case 2:
                checktwo = true;
                // TODO: change the actor_name to get the response frm GUI
                String check_actor = "Tom Hanks";
                // TODO: get char_count frm the GUI
                int char_count = 2;
                checktwocount = char_count;
                lines.add("storetemp <- select (name == \"" + removeSpace(check_actor) +"\") cast;");
                lines.add("temp <- rename (m_id_two, name_two, character_name_two, id_two, credit_id_two) storetemp;");
                lines.add("temp2 <- temp * cast;");
                lines.add("temp3 <- select(name_two != name && m_id_two == m_id) temp2;");

                break;
                // WORKS ON SINGLE AND TWO MOVIES
            case 3:
                // TODO: change the actor_name to get the response frm GUI
                String actor_name = "Tom Hanks";
                lines.add("store <- select (name == \"" + removeSpace(actor_name) +"\") cast;");
                lines.add("store2 <- store * genres;");
                lines.add("store3 <- select (m_id == movie_id) store2;");
                checkthree = true;
                break;
            case 4:
                // TODO: change the character_name to get the response frm GUI
                String character_name = "Woody (voice)";
                lines.add("store <- select (character_name == \"" + removeSpace(character_name) +"\") cast;");
                lines.add("temp <- (project (name) store);");
                lines.add("SHOW temp;");
                break;
        }
        System.out.println("here3");

//        lines.add("SHOW cast;");
//        lines.add("SHOW movies;");
        //lines.add("EXIT;");

        //Send to antlr/listener
        for (String line : lines) {
            //System.out.println("parsing line " + line);
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

        if(checktwo) {
            System.out.println("in check two");
            Table temp = listener.myDbms.getTable("temp3");
            temp.Print();

            Map<String, Integer> count = new TreeMap<String, Integer>();

            // to put in the names
            for (Hashtable<String,Object> entry : temp.getEntries()) {
                String name = entry.get("name").toString();
                if (!count.containsKey(name)) {
                    count.put(name, 1);
                } else {
                    count.put(name, count.get(name) + 1);
                }
            }

            // To display the actors played in given number of movies with choosen actor
            System.out.println("The actors who played " + checktwocount + " movies with choosen actor are:");
            for(String key : count.keySet()) {
                if(count.get(key) == checktwocount) {
                    System.out.println(key);
                }
            }
        }

        if(checkthree) {
            // find the most common genre in store
            // go through each of the entries in store
            // if genre is
            Table temp = listener.myDbms.getTable("store3");

            Map<String, Integer> count = new TreeMap<String, Integer>();
            int max = 0;
            for (Hashtable<String,Object> entry : temp.getEntries()) {
                String genre_name = entry.get("genre_name").toString();
                if (!count.containsKey(genre_name)) {
                    if(1 > max) {
                        max = 1;
                    }
                    count.put(genre_name, 1);
                } else {
                    if((count.get(genre_name) + 1) > max) {
                        max = count.get(genre_name) + 1;
                    }
                    count.put(genre_name, count.get(genre_name) + 1);
                }
            }
            // To display the max genres
            System.out.println("The most common genre/genres played is/are:");

            for(String key : count.keySet()) {
                if(count.get(key) == max) {
                    System.out.println(key);
                }
            }
        }
    }
}
