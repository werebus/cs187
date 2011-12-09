import java.io.*;

public class Driver {
  public static void main(String[] args) {
    header("Creating new PhoneWordLister");
      PhonewordLister l = null;
      try{
        l = new PhonewordLister();
      } catch(IOException e) {
        yell(e);
      } catch(Exception e) {
        error("Wrong Exception:" + e);
      }

    header("Creating bad PhoneWordLister");
      say("should be an IOException here");

      PhonewordLister lb = null;
      try{
        lb = new PhonewordLister("foo.txt");
      } catch(IOException e) {
        yell(e);
      } catch(Exception e) {
        error("Wrong Exception:" + e);
      }

    header("Checking tree");
      PrefixTree t = l.getTree();
      say("Size should be 5757");
      yell(t.getSize());
      say("'today' should be true");
      yell("today: " + t.contains("today"));
      say("'happy' should be true");
      yell("happy: " + t.contains("happy"));
      say("'hippo' should be true");
      yell("hippo: " + t.contains("hippo"));
      say("'lazed' should be true");
      yell("lazed: " + t.contains("lazed"));
      say("'ab' should be true");
      yell("ab: " + t.contains("ab"));
      say("'pinot' should be false");
      yell("pinot: " + t.contains("pinot"));

    header("Testing phoneletter map");
      char[] digits = new char[] {'2', '3', '4', '5', '6', '7', '8', '9', '*'};
      for (char d : digits) {
        say(d);
        yell(PhonewordLister.phoneLetters(d).length);
        for (char p : PhonewordLister.phoneLetters(d)) {
          yell("\t" + p);
        }
      }

    header("Testing list method");
      say("A non-5-character string should return an empty array");
      yell(l.list("234").length);
      say("Invalid characters, likewise");
      yell(l.list("3452a").length);
      say("22222 has only one valid word");
      for (String s : l.list("22222"))
        yell(s);
      say("25*** should be a pretty big list");
      for (String s : l.list("25***"))
        yell(s);

  }

  //Covenience wrapper around System.out.println
  public static void say(Object statement) {
    System.out.println(statement);
  }

  //Like say, but green (using ANSI codes - not very portable, but good enough
  //for most non-Windows users.  Maybe look into jansi or Jcurses?
  public static void yell(Object statement) {
    System.out.println((char)27 + "[32m" + statement + (char)27 + "[39m");
  }

  //Like say, but red
  public static void error(Object statement) {
    System.out.println((char)27 + "[31m" + statement + (char)27 + "[39m");
  }

  //Outputs a String "underlined" with ='s
  public static void header(Object statement) {
    say("\n" + statement);
    for (int i = 0; i < statement.toString().length(); i++) {
      System.out.print("=");
    }
    System.out.print("\n");
  }

}
