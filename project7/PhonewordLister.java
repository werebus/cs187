import java.io.*;
import java.util.*;
import java.util.regex.*;

public class PhonewordLister {
  private static final String DEFAULT_FILENAME = "sgb-words.txt";
  private PrefixTree tree;

  public PrefixTree getTree(){
    return tree;
  }

  public PhonewordLister() throws IOException {
    this(DEFAULT_FILENAME);
  }

  public PhonewordLister(String fileName) throws IOException {
    tree = new PrefixTree();

    BufferedReader in = new BufferedReader( new FileReader( fileName ) );
    String line;

    while ( (line = in.readLine()) != null ) {
      tree.addString(line);
    }

    in.close();
  }

  public static int scrabbleScore (String w) {
  // returns Scrabble score if w is made only of letters, returns 0 otherwise
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

  public String[] list(String digits){
    Queue<String> search = new ArrayDeque();
    Pattern invalid = Pattern.compile("[^2-9*]");
    Matcher invalidMatch = invalid.matcher(digits);

    if (digits.length() != 5 || invalidMatch.find() )
      return new String[0];

    for ( char first : phoneLetters(digits.charAt(0)) )
      if (tree.contains( Character.toString( first ) )){
        search.add( Character.toString( first ) );
      }

    while (search.peek().length() != 5) {
      String prefix = search.remove();
      for ( char next : phoneLetters(digits.charAt( prefix.length() ) ) )
        if (tree.contains( prefix + Character.toString( next ) )){
          search.add( prefix + Character.toString( next ) );
        }
    }

    //TODO: Not yet in order:
    String[] result = new String[search.size()];
    int count = 0;
    while (!search.isEmpty())
      result[count++] = search.remove();

    return result;


  }
    //  This is the key method to be written by the student.
    //  The input "digits" is to be a string of five characters, each a digit in the range 2-9 or a *.
    //  If it is of this form, return an array of all phonewords that can be made from the input,
    //  in order of Scrabble score with highest score first, treating a * as a wild-card.  (Handling *'s is extra credit.)
    //  If the input is invalid, return an array of length 0. 


}
