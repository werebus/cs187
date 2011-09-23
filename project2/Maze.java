//I hope this is OK.  It was the easiest way to resize the moves result.
import java.util.Arrays;

public class Maze {
  int width;
  int height;

  Cell[][] cells;

  Maze (int w, int h, String[] init) {
    //TODO: Some error checking here. init should hava a size of h, and all
    //elements of init should have w characters. init should have only 1's
    //and 0's in it.
    boolean open;

    width = w;
    height = h;
    cells = new Cell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        open = (init[y].charAt(x) != '0');
        cells[x][y] = new Cell(x, y, open);
      }
    }
  }

  //TODO: might be able to DRY up these constructors
  Maze (int w, int h) {

    width = w;
    height = h;
    cells = new Cell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        cells[x][y] = new Cell(x, y, true);
      }
    }
  }

  //Getter / Setter boilerplate.  Of course, re-setting the width or height of
  //a Maze doesn't guarantee that cells array will be correctly populated.
  //Shrinking a Maze works OK, the extra Cells just become unreachable, but
  //growing a Maze will likely cause some null pointer errors.
  public int getWidth() {
    return width;
  }

  public void setWidth(int w) {
    width = w;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int h) {
    height = h;
  }

  //get/set Cells?  Probably not; it would be one heck of a mess.

  //Returns an array of Cells that are adjacent to the coordinates specified
  //and are open.  Note that below I often check if we're ON the boundary of
  //the maze rather than on or beyond it.  This is to allow requests _beyond_
  //the bounds of the maze to still raise an exception.
  public Cell[] moves(int x, int y) {
    Cell[] results = new Cell[0];
    int count = 0;

    //Check up
    if (y != 0 && cells[x][y-1].getOpen()) {
      count++;
      results = Arrays.copyOf(results, count);
      results[count-1] = cells[x][y-1];
    }

    //Check down
    if (y != (height - 1) && cells[x][y+1].getOpen()) {
      count++;
      results = Arrays.copyOf(results, count);
      results[count-1] = cells[x][y+1];
    }

    //Check left
    if (x != 0 && cells[x-1][y].getOpen()) {
      count++;
      results = Arrays.copyOf(results, count);
      results[count-1] = cells[x-1][y];
    }

    //Check right
    if (x != (width - 1) && cells[x+1][y].getOpen()) {
      count++;
      results = Arrays.copyOf(results, count);
      results[count-1] = cells[x+1][y];
    }

    return results;
  }

  //A Maze's toString is a box made up of 1's and 0's representing the open
  //state of the Cell in that position.
  //
  //  1110011
  //  0011101
  //  1110000
  public String toString() {
    String result = new String("");

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        result += cells[x][y].toShortString();
      }
      if (y < height - 1) {
        result += "\n";
      }
    }

    return result;
  }
}
