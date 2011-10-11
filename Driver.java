public class Driver {
  public static void main(String[] args) {

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
