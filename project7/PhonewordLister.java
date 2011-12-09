import java.io.*;
import java.util.*;
import java.util.regex.*;

public class PhonewordLister {
  //Constant that holds the default filename for the word lsit
  private static final String DEFAULT_FILENAME = "sgb-words.txt";

  //The actual prefix tree
  private PrefixTree tree;

  public PrefixTree getTree(){
    return tree;
  }

  //zero-parameter constructor calls 1-parameter version with the default
  //file name
  public PhonewordLister() throws IOException {
    this(DEFAULT_FILENAME);
  }

  //Create a new PhoneWordLister.  This is where the text file gets read in
  public PhonewordLister(String fileName) throws IOException {
    tree = new PrefixTree();

    //Reader based on the textfile
    BufferedReader in = new BufferedReader( new FileReader( fileName ) );
    //line hold the most recently read-in line
    String line;

    //While we continue to succeed at reading in a new line, add that line
    //(one of our words) into the prefix tree.
    while ( (line = in.readLine()) != null ) {
      tree.addString(line);
    }

    //Close up the file
    in.close();
  }

  // returns Scrabble score if w is made only of letters, returns 0 otherwise
  public static int scrabbleScore (String w) {
    int [ ] value = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
                     1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    int sum = 0;

    for (int i = 0; i < w.length( ); i++) 
      if ('a' <= w.charAt(i) && w.charAt(i) <= 'z')
        sum += value[w.charAt(i) - 'a'];
      else if ('A' <= w.charAt(i) && w.charAt(i) <= 'Z')
        sum += value[w.charAt(i) - 'A'];
      else
        return 0;

    return sum;
  }

  // returns an array of possible phoneletterchars given a char digit.  THat is,
  // 2 -> [a, b, c], etc.  '*' returns the entire alphabet, anything non-valid
  // returns an empty char array.
  public static char[] phoneLetters( char digit ) {
    char[] result;
    switch (digit) {
      case '2': result = new char[] {'a', 'b', 'c'}; break;
      case '3': result = new char[] {'d', 'e', 'f'}; break;
      case '4': result = new char[] {'g', 'h', 'i'}; break;
      case '5': result = new char[] {'j', 'k', 'l'}; break;
      case '6': result = new char[] {'m', 'n', 'o'}; break;
      case '7': result = new char[] {'p', 'q', 'r', 's'}; break;
      case '8': result = new char[] {'t', 'u', 'v'}; break;
      case '9': result = new char[] {'w', 'x', 'y', 'z'}; break;
      case '*': result = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                                     'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                                     's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; break;
      default: result = new char[0];
    }

    return result;
  }

  //Lists all possible phonewords for a given string of digits in descending
  //order of Scrabble score.
  public String[] list(String digits){
    //This queue holds the initial search; we do a bredth-first search of
    //the prefix tree for all valid words
    Queue<String> search = new ArrayDeque();

    //Priority queue for the ScrabbleWords
    PriorityQueue<ScrabbleWord> sort = new PriorityQueue();

    //Temporary holder
    String word;

    //Set up a regex for invalid characters (not 2-9 or *).
    Pattern invalid = Pattern.compile("[^2-9*]");
    Matcher invalidMatch = invalid.matcher(digits);

    //If this isn't a 5-character string, or it contains any invalid characters,
    //bail early (return an empty array).
    if (digits.length() != 5 || invalidMatch.find() )
      return new String[0];

    //Put each of the possible letters for the first digit in the search queue,
    //presuming they are in the prefix tree (if they're not, there are no words
    //that start with them)
    for ( char first : phoneLetters(digits.charAt(0)) )
      if (tree.contains( Character.toString( first ) )){
        search.add( Character.toString( first ) );
      }

    //While the head of the queue is not a five letter word, we still have
    //combinations to try.  Take the head off the queue, and add the concat of
    //it and each of possible letters for digit[x] (where x is the length of the
    //prefix) to the queue if that combination is itself in the prefix tree.
    while (search.peek().length() != 5) {
      String prefix = search.remove();
      for ( char next : phoneLetters(digits.charAt( prefix.length() ) ) )
        if (tree.contains( prefix + Character.toString( next ) )){
          search.add( prefix + Character.toString( next ) );
        }
    }

    //Now, search should be full of found 5-letter words only.  Take them out
    //of the queue in turn and add them to a priority queue (with it's Scrabble
    //score).
    while (!search.isEmpty()){
      word = search.remove();
      sort.add( new ScrabbleWord(word, scrabbleScore(word)) );
    }

    //Form a result array, and pull everything out of the priority queue in
    //turn and place it in the array.
    String[] result = new String[sort.size()];
    int count = 0;
    while (!sort.isEmpty())
      result[count++] = sort.remove().getWord();

    return result;
  }
}
