public class MazeDriver {
  public static void main(String[] args) {
    header("Testing Cell constructors");

      Cell cellA = new Cell(0,0);
      say("Should be (0, 0) open");
      yell(cellA);

      Cell cellB = new Cell(2,3,false);
      say("Should be (2, 3) closed");
      yell(cellB);

    header("Testing Cell getters");

      say("cellA x Should be 0");
      yell(cellA.getX());
      say("cellB y Should be 3");
      yell(cellB.getY());
      say("cellB open should be false");
      yell(cellB.getOpen());

    header("Testing Cell setters");

      say("Setting cellA x to 6, y to 10, open to false");
      cellA.setX(6);
      cellA.setY(10);
      cellA.setOpen(false);
      yell(cellA);

    header("Testing Cell misc methods");

      say("Re-opening CellA");
      cellA.setOpen(true);
      say("cellB cordinates should give (2, 3)");
      yell(cellB.coords());
      say("cellB shortString should be '0'");
      yell(cellB.toShortString());
      say("cellA shortString should be '1'");
      yell(cellA.toShortString());

    header("Testing Maze constructors");

      say("Creating a 5-wide, 2-high empty maze");
      Maze mazeA = new Maze(5,2);
      yell(mazeA);
      say("Using construction array: {\"111111\", \"000000\", \"101010\"} (6x3)");
      String s[] = {"111111", "000000", "101010"};
      Maze mazeB = new Maze(6, 3, s);
      yell(mazeB);

    header("Testing Maze getters");

      say("mazeA width should be 5");
      yell(mazeA.getWidth());
      say("mazeA height should be 2");
      yell(mazeA.getHeight());

    header("Testing Maze setters");

      say("setting mazeA width to 4");
      mazeA.setWidth(4);
      yell(mazeA);
      say("setting mazeB height to 2");
      mazeB.setHeight(2);
      yell(mazeB);

    header("Creating a maze for testing 'moves'");

  }

  public static void say(Object statement) {
    System.out.println(statement);
  }

  public static void yell(Object statement) {
    System.out.println((char)27 + "[32m" + statement + (char)27 + "[39m");
  }

  public static void header(Object statement) {
    say("\n" + statement);
    for (int i = 0; i < statement.toString().length(); i++) {
      System.out.print("=");
    }
    System.out.print("\n");
  }

}
