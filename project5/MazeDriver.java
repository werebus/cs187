public class MazeDriver {
  public static void main(String[] args) {
    header("Creating a maze for testing 'moves'");

      String r[] = {"1111", "1001", "1111"};
      Maze mc = new Maze(4, 3, r);

    header("Testing unseen moves");

      say("unseenMoves at 0,0 should be (1,0), and (0,1)");
      for (QCell c : mc.unseenMoves(0, 0)) {
        yell(c);
      }
      say("Marking (1,0) as 'seen'");
      mc.getCell(1,0).setSeen(true);
      say("unseenMoves at 0,0 should now be only (0,1)");
     for (QCell c : mc.unseenMoves(0, 0))
       yell(c);

    header("Testing resetCells()");
      mc.resetCells();
      say("(1,0) should now be unseen (false)");
      yell(mc.getCell(1,0).getSeen());

    header("Testing path");
      String p[] = {"1111100111", "1000000101", "1011111110", "1111000101", "0001110000"};
      Maze md = new Maze(10, 5, p);
      yell(md);

      say("From a cell to itself is a 1-move path");
      yell(md.path(0,0,0,0).length + " moves");
      for(QCell c : md.path(0,0,0,0))
        yell(c);

      say("No way from (0,0) to (5,0)");
      yell(md.path(0,0,5,0).length + " moves");
      for(QCell c : md.path(0,0,5,0))
        yell(c);

      say("Nice long path from (3,0) to (5,4)");
      yell(md.path(3,0,5,4).length + " moves:");
      for(QCell c : md.path(3,0,5,4))
        yell("\t" + c);

      say("We can't start in a closed cell (0,4); should be 0 moves");
      yell(md.path(0,4,0,0).length + " moves");

    header("Testing isPath");

      say("Should be false for (0,0) to (9,3)");
      yell(md.isPath(0,0,9,3));

      say("Should be true for (2,3) to (8,2)");
      yell(md.isPath(2,3,8,2));

    header("Is it the shortest?");
      String s[] = {"1101", "1111", "1101"};
      Maze ms = new Maze(4, 3, s);
      yell(ms);

      say("from (1,2) to (3,2) should be (1,2),(1,1),(2,1),(3,1),(3,2)");
      for(QCell c : ms.path(1,2,3,2))
        yell("\t" + c);

    header("Testing bestPath");
      String s1[] = {"1111", "1221", "1001"};
      Maze mbp = new Maze(4, 3, s1);
      yell(mbp);

      say("Starting with 10 spoons, best path from (0,2) to (3,2) should be");
      say("(0,2),(0,1),(1,1),(2,1),(3,1),(3,2)");
      for(PQCell c : mbp.bestPath(10, 0, 2, 3, 2))
        yell("\t" + c);
      yell(mbp.spoons(10,0,2,3,2) + " spoons");

      say("Starting with 100 spoons, best path from (0,2) to (3,2) should be");
      say("(0,2),(0,1),(0,0),(1,0),(2,0),(3,0),(3,1),(3,2)");
      for(PQCell c : mbp.bestPath(100, 0, 2, 3, 2))
        yell("\t" + c);
      yell(mbp.spoons(100,0,2,3,2) + " spoons");

      say("Starting with 2 spoons, you can't get there without running out");
      yell(mbp.bestPath(2, 0, 2, 3, 2).length + " moves");
      yell(mbp.spoons(2,0,2,3,2) + " spoons");

      say("Starting with 2 spoons, you run out exactly trying to get from");
      say("(0,2) to (0,0)");
      yell(mbp.bestPath(2, 0, 2, 0, 0).length + " moves");

      say("Going from 1 Town to another with 200 spoons should cost 10 (leaving 190)");
      for(PQCell c : mbp.bestPath(200, 1,1,2,1))
        yell("\t" + c);
      yell(mbp.spoons(200,1,1,2,1) + " spoons");
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
