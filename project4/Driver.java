public class Driver {

  public static String displayPath(QCell[] path) {
    String ret = "";
    if (path == null)
    {
      System.out.println("Incorrect return, Your path method should never return null. Make sure it returns an empty array if no path exists");
      return ret;
    }
    if ( path.length == 0 ) {
      ret = "No path exists.";
      return ret;
    }
    else {
      for ( QCell c : path ) {
        String str = c.toString();
        String [] strarr = str.split("o");
        ret += (strarr[0]+"->");
      }
      return ret;
    }
  }

  public static void main(String[] args) {

    String [] init = {"11011", "10001", "11011", "11000"};
    int h = 4;
    int w = 5;
    int count = 0;
    String ret = "";
    Maze m = new Maze (w, h, init);
    ret = m.toString();
    if (ret.compareTo("11011\n10001\n11011\n11000")==0)
    {
      count++;
      System.out.println("Maze toString return SUCCESSFUL");
    }
    else
      System.out.println("Maze toString return FAILED");

    ret = "";
    for (int row=0; row < h; row++)
      for (int col=0; col < w; col++) {
        QCell [] result = m.moves(col, row);
        int numMoves = result.length;
        ret += (Integer.toString(numMoves)+",");
      }
    if (ret.compareTo("2,1,2,1,2,2,3,0,3,2,3,2,2,1,2,2,2,1,1,1,")==0)
    {
      count++;
      System.out.println("Maze moves return SUCCESSFUL");
    }
    else
      System.out.println("Maze moves return FAILED");

    ret = displayPath(m.path(3, 0, 3, 0));
    if (ret.compareTo("(3, 0) ->") == 0)
    {
      count++;
      System.out.println("Maze path return (when source is destination) SUCCESSFUL");
    }
    else
      System.out.println("Maze path return (when source is destination) FAILED");

    ret = displayPath(m.path(3, 0, 4, 3));
    if (ret.compareTo("No path exists.") == 0)
    {
      count++;
      System.out.println("Maze path return (when no path exists) SUCCESSFUL");
    }
    else
      System.out.println("Maze path return (when no path exists) FAILED");

    ret = displayPath(m.path(0, 0, 3, 2));
    if (ret.compareTo("No path exists.") == 0)
    {
      count++;
      System.out.println("Maze path return (when no path exists) SUCCESSFUL");
    }
    else
      System.out.println("Maze path return (when no path exists) FAILED");

    ret = displayPath(m.path(1, 0, 1, 2));
    if (ret.compareTo("(1, 0) ->(0, 0) ->(0, 1) ->(0, 2) ->(1, 2) ->") == 0)
    {
      count++;
      System.out.println("Maze path return (when a path exists) SUCCESSFUL");
    }
    else
      System.out.println("Maze path return (when a path exists) FAILED");

    ret = displayPath(m.path(4, 0, 3, 2));
    if (ret.compareTo("(4, 0) ->(4, 1) ->(4, 2) ->(3, 2) ->") == 0)
    {
      count++;
      System.out.println("Maze path return (when a path exists) SUCCESSFUL");
    }
    else
      System.out.println("Maze path return (when a path exists) FAILED");

    System.out.println(count+" out of 7 tests PASSED");
  }

}

