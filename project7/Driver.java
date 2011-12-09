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
      say("'pinot' should be false");
      yell("pinot: " + t.contains("pinot"));

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
