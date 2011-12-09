import java.io.*;
import java.util.*;

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

  public String[] list(String digits){
    return new String[0];
  }
    //  This is the key method to be written by the student.
    //  The input "digits" is to be a string of five characters, each a digit in the range 2-9 or a *.
    //  If it is of this form, return an array of all phonewords that can be made from the input,
    //  in order of Scrabble score with highest score first, treating a * as a wild-card.  (Handling *'s is extra credit.)
    //  If the input is invalid, return an array of length 0. 


}
