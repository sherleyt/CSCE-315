package project1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.lang.NoSuchMethodException;


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


public class Controller{

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
    public static String addSpace(String word){
        String newWord="";
        for(int k=0;k<word.length();k++){
            if((word.charAt(k)>='A'&& word.charAt(k)<='Z')||(word.charAt(k)>='a'&& word.charAt(k)<='z')||(word.charAt(k)>=48&& word.charAt(k)<=57)) {
                newWord=newWord+word.charAt(k);
            } else {
                newWord = newWord + " ";
            }
        }
        if(newWord == "") {
            return "Unknown";
        }
        return newWord;
    }
    private static MovieDatabaseParser parser1 = new MovieDatabaseParser();
    private static MyRulesBaseListener listener = new MyRulesBaseListener();
    private static List<String> lines = new ArrayList<String>();

    static void run_parse(List<String> querry){
        for (String line : querry) {
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
    }
public static void main(String[] args) {
    parser1 = new MovieDatabaseParser(); //The movie parser from P.Taele
    listener = new MyRulesBaseListener(); //Rules listener for SQL code


    List<Movie> moviesList = null;   //Movie.json parser
    List<Credits> creditsList = null;  //Credits.json parser
    //Try opening json files
    try {
        moviesList = parser1.deserializeMovies("src/project1/movies.json");
        creditsList = parser1.deserializeCredits("src/project1/credits.json");
    } catch (Exception e) {
        System.err.println("No file found");
    }
    // create a big data base
    // create a map that maps each genre we encounter to a genre name
    //"matrix"

    //Create basic tables from the movie/credit parser we made
    lines.add("CREATE TABLE movies (name VARCHAR(100), id INTEGER, popularity INTEGER, has_credits INTEGER) PRIMARY KEY (id);");
    lines.add("CREATE TABLE cast (m_id INTEGER, name VARCHAR(150), character_name VARCHAR(150), " +
            "id INTEGER, credit_id VARCHAR(150)) PRIMARY KEY (credit_id);");
    //Genre table(not basic) for easier use in q4, and directors table for easier use in q5
    lines.add("CREATE TABLE genres (movie_id INTEGER, genre_id INTEGER, genre_name VARCHAR(150), index INTEGER) PRIMARY KEY (index);");
    lines.add("CREATE TABLE directors (m_id INTEGER, name VARCHAR(100), job VARCHAR(30)) PRIMARY KEY (m_id);");


    // adding info into the movies table
    for (int i = 0; i < moviesList.size(); i++) {
        //Make a movie entry using the movie's name, id, its rating, and credit in that order
        lines.add("INSERT INTO movies VALUES FROM (\""+removeSpace(moviesList.get(i).getTitle()) +"\", "+ moviesList.get(i).getId() + ", " +
                Math.round(moviesList.get(i).getVote_average()*10) + ", " + moviesList.get(i).getHasCredits() + ");");
        //Add the genre information from the same parser into the genre table(will only contain movie's id and index
        for (int j = 0; j < moviesList.get(i).getGenres().size(); j++) {
            int id = moviesList.get(i).getGenres().get(j).getId();
            String name = removeSpace(moviesList.get(i).getGenres().get(j).getName());
            String index = Integer.toString(moviesList.get(i).getId()) + Integer.toString(j) + Integer.toString(i);
            //The entry will be movie id+genre id + genre name + movie id(j,i) - which serializes entry for easier calling later
            lines.add("INSERT INTO genres" + " VALUES FROM (" + moviesList.get(i).getId() + ", " + id + ", \"" + name + "\"" + ", " + index + ");");
        }
    }


    // adding info into the cast table
    // removing white spaces from name and character name
    for (int i = 0; i < creditsList.size(); i++) {
        //Get the credit information from credit parser
        long movie_id = Long.parseLong(creditsList.get(i).getId()); //movie id
        for (int j = 0; j < creditsList.get(i).getCastMember().size(); j++) {
            String name = creditsList.get(i).getCastMember().get(j).getName();   //cast's name
            String tempName = "";
            //For the cast's name, only use letters for easier use
            for (int k = 0; k < name.length(); k++) {
                if (Character.isLetterOrDigit(name.charAt(k))) {
                    tempName = tempName + name.charAt(k);
                }
            }
            //Get the character played by the cast
            String character = creditsList.get(i).getCastMember().get(j).getCharacter();
            String tempChar = "";
            //Again, only use letters for easier parsing
            for (int k = 0; k < character.length(); k++) {
                if (Character.isLetterOrDigit(character.charAt(k))) {
                    tempChar = tempChar + character.charAt(k);
                }
            }
            //Get the cast member's id
            int id = creditsList.get(i).getCastMember().get(j).getId();
            String credit = creditsList.get(i).getCastMember().get(j).getCredit_id();
            //Remove spacing/weird letters for the cast member's name
            String newWord = "";
            for (int k = 0; k < name.length(); k++) {
                //Check if letter, or number
                if ((name.charAt(k) >= 'A' && name.charAt(k) <= 'Z') || (name.charAt(k) >= 'a' && name.charAt(k) <= 'z') || (name.charAt(k) >= 48 && name.charAt(k) <= 57)) {
                    newWord = newWord + name.charAt(k);
                } else {//Else put _ for rest
                    newWord = newWord + "_";
                }
            }
            //All characters are space/weird letter, use "unknown"
            if (newWord == "") {
                newWord = "Unknown";
            }
            //Remove spacing/weird letters for the cast member's character
            String newWord2 = "";
            for (int k = 0; k < character.length(); k++) {
                //Check if letter, or number
                if ((character.charAt(k) >= 'A' && character.charAt(k) <= 'Z') || (character.charAt(k) >= 'a' && character.charAt(k) <= 'z') || (character.charAt(k) >= 48 && character.charAt(k) <= 57)) {
                    newWord2 = newWord2 + character.charAt(k);
                } else {//Else put _ for rest
                    newWord2 = newWord2 + "_";
                }
            }
            //All characters are space/weird letter, use "unknown"
            if (newWord2 == "") {
                newWord2 = "Unknown";
            }
            //Make cast table with movie id, cast name, character, id and what they are
            lines.add("INSERT INTO cast VALUES FROM (" + movie_id + ", \"" + newWord + "\", \"" + newWord2 + "\", " + id + ", \"" + credit + "\");");
        }
        //Get the director's info (id, director name and their job info
        for(int j = 0; j < creditsList.get(i).getCrewMember().size(); j++) {
            String director_name = removeSpace(creditsList.get(i).getCrewMember().get(j).getName());  //director's name
            String job = removeSpace(creditsList.get(i).getCrewMember().get(j).getJob());   //Director's job
            if(job.equals(removeSpace("Director"))) { //add to table
                lines.add("INSERT INTO directors VALUES FROM ("+movie_id+", \"" + removeSpace(director_name) +"\", \"" +removeSpace(job)+"\");");
            }
        }
    }
    //Run the sql code from the list<string> lines
    run_parse(lines);
    //Check
   // System.out.println("Made tables in DBMS");
}
//a = "a"ctor
String process2(String a,String counter){
    //a = actor, counter= number, set the checktwocount to b's integer value
        int checktwocount = Integer.parseInt(counter);
    List<String> p2 = new ArrayList<String>();
    p2.add("storetemp <- select (name == \"" + removeSpace(a) +"\") cast;"); //Get cast info of the actor
    p2.add("actedmovies <- (project (m_id) storetemp);");  //Find the movie id from that table
    //Run the above SQL commands
    run_parse(p2);

    //Get the table from DBMS
    Table temp0 = listener.myDbms.getTable("actedmovies");
    //Make a set of the movie ids with the actor in it
    Set<Object> movie_ids = new HashSet<Object>();
    for (Hashtable<String,Object> entry : temp0.getEntries()) {
        movie_ids.add(entry.get("m_id"));
    }
    //Get the table of casts
    Table cast2 = listener.myDbms.getTable("cast");
    //Make of costars and their count
    Map<String, Integer> count = new TreeMap<String, Integer>();
    for (Hashtable<String,Object> entry : cast2.getEntries()) {
        if(movie_ids.contains(entry.get("m_id"))) { //Find the cast information for the movies from actor
            String name = entry.get("name").toString();
            if (!count.containsKey(name)) {
                count.put(name, 1);
            } else {
                count.put(name, count.get(name) + 1);
            }
        }
    }
    // To display the actors played in given number of movies with choosen actor
    String to_return = "";
    for(String key : count.keySet()) {
        if(count.get(key) == checktwocount) {
            to_return = to_return + addSpace(key) + "     ";

        }
    }
    return to_return;
}
String process3(String a){//a = actor name
    List<String> p3 = new ArrayList<String>();
    p3.add("store <- select (name == \"" + removeSpace(a) +"\") cast;"); //Get the cast info of our actor
    p3.add("store3 <- select (m_id == movie_id) (store * genres);");   //Find the movies with the id, and get their genre
    run_parse(p3);
    //Table with genre info
    Table temp = listener.myDbms.getTable("store3");
    //Map that contains actor's genres and thier counts
    Map<String, Integer> count = new TreeMap<String, Integer>();
    int max = 0;
    //Get all the genres that cast with our actor and find the max
    for (Hashtable<String,Object> entry : temp.getEntries()) {
        String genre_name = entry.get("genre_name").toString();
        if (!count.containsKey(genre_name)) { //New genre found, add to our list
            if(1 > max) { //Base case(first add)
                max = 1;
            }
            count.put(genre_name, 1);
        } else {
            if((count.get(genre_name) + 1) > max) {  //Found new max, set it as the new max
                max = count.get(genre_name) + 1;
            }
            count.put(genre_name, count.get(genre_name) + 1);
        }
    }
    // To display the max genres, get them from the table we just made and put into returning string
    String to_ret = "";
    for(String key : count.keySet()) {
        if(count.get(key) == max) {  //Which genre has the count of MAX
            to_ret = to_ret + addSpace(key) + "     ";
        }
    }
    return to_ret;
}
String process4(String a){ //a = actor name
        //List of strings of more SQL code
    List<String> p4 = new ArrayList<String>();
    p4.add("store <- select (character_name == \"" + removeSpace(a) +"\") cast;"); //Get the character's cast info
    p4.add("temp <- (project (name) store);");      //Get the name column from the created table
    //Runs the SQL code above
    run_parse(p4);

    String to_ret4 = "";
    //Get all the actors that played the character and add them to the output
    Table temp = listener.myDbms.getTable("temp");
    for (Hashtable<String,Object> entry : temp.getEntries()) {
        to_ret4 = to_ret4 + (addSpace(entry.get("name").toString())) + "\n    ";
    }
    return to_ret4;
}
String process5(String a){ //a = actor name
    List<String> p5 = new ArrayList<String>();

    //Make temp tables that ahve the actor's movies
    p5.add("storetemp <- select (name == \"" + removeSpace(a) +"\") cast;");
    p5.add("actedmovies <- (project (m_id) storetemp);");
    //Run those 2 lines
    run_parse(p5);

    //Get table from database
    Table temp = listener.myDbms.getTable("actedmovies");
    //Make set of the actor's movies
    Set<Object> movie_ids = new HashSet<Object>();
    for (Hashtable<String,Object> entry : temp.getEntries()) {
        movie_ids.add(entry.get("m_id"));
    }

    //Get the ratings information for those movies to find the best rated film
    Table movies = listener.myDbms.getTable("movies");

    long max = Long.parseLong("0");
    long max_id = Long.parseLong("0");

    for (Hashtable<String,Object> entry : movies.getEntries()) {
        if(movie_ids.contains(entry.get("id"))) {//Movie is part of actor's discography
            if(Long.parseLong(entry.get("popularity").toString()) > max){  //Found a new max, add as the max_id
                //System.out.println("here");
                max = Long.parseLong(entry.get("popularity").toString());
                max_id = Long.parseLong(entry.get("id").toString());
            }
        }
    }

    // Now find the director with matching movie name
    Table director = listener.myDbms.getTable("directors");
    String storeDir = "";
    //Find the director of the best movie from the director table
    for (Hashtable<String,Object> entry : director.getEntries()) {
        if(Long.parseLong(entry.get("m_id").toString()) == max_id) {//found it
            //System.out.println(entry.get("name").toString());
            storeDir = entry.get("name").toString();
        }
    }

    // list of all movies with the director
    Set<Object> directorMovies = new HashSet<Object>();
    for (Hashtable<String,Object> entry : director.getEntries()) { //Get movie id of that director
        if(entry.get("name").toString().equals(storeDir)) {
            directorMovies.add(entry.get("m_id"));
        }
    }


    // go through all those movies and get the minimum
    String worstMovie = "Worst Movie not found correctly"; //Send error message if director could not be found
    long min = Long.parseLong("101");
    //Find in the worst movie from the director from the movie table
    for (Hashtable<String,Object> entry : movies.getEntries()) {
        if(directorMovies.contains(entry.get("id"))) {  //Found a movie
            if(Long.parseLong(entry.get("popularity").toString()) < min) {  //Found new min, set the worst movie value to it
                min = Long.parseLong(entry.get("popularity").toString());
                worstMovie = addSpace(entry.get("name").toString());
            }
        }
    }
        //return the string
        return worstMovie;
}

@FXML
private Button q2;
@FXML
private Button q3;
@FXML
private Button q4;
@FXML
private Button q5;
@FXML
private TextField i1;
@FXML
private TextField i2;
@FXML
private TextField out;
@FXML
void eventq2(ActionEvent event){
            String input11 = i1.getText(); //Get actor Name
            String input21 = i2.getText(); //Get # appreances

            try{
                int num = Integer.parseInt(input21);
            } catch (NumberFormatException e) {
                out.setText("You entered an improper number");
                System.exit(0);
            }
            if(input11.equals("") || input21.equals("")){    //Check empty string
                out.setText("You entered an empty string");
            }
            else{
                String out_t = process2(input11,input21); //Get the output
                 out.setText(out_t); //Print the output in the text field
            }
}
@FXML
void eventq3(ActionEvent event){
            String input31 = i1.getText(); //Get Actor Name
    if(input31.equals("")){    //Check empty string
        out.setText("You entered an empty string");
    }
    else{
            String out_t2 = process3(input31); //Get the output using function
            out.setText(out_t2); //Print the output in the text field
         }
            }
@FXML
void eventq4(ActionEvent event){
            String input41 = i1.getText(); //Get Actor Name
    if(input41.equals("")){    //Check empty string
        out.setText("You entered an empty string");
    }
    else {
        String out4 = process4(input41); //Get the output using functions
        out.setText(out4); //Print the output in the text field
    }
}
@FXML
void eventq5(ActionEvent event){
     String input51 = i1.getText(); //Get Actor Name
    if(input51.equals("")){    //Check empty string
        out.setText("You entered an empty string");
    }
    else{
     String out5 = process5(input51); //Get the output using functions
     out.setText(out5); //Print the output in the text field
    }
}

}



