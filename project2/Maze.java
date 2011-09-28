import java.util.*;

public class Maze {
  int width;
  int height;

  public SCell[][] cells;

  Maze (int w, int h, String[] init) {
    //TODO: Some error checking here. init should hava a size of h, and all
    //elements of init should have w characters. init should have only 1's
    //and 0's in it.
    boolean open;

    width = w;
    height = h;
    cells = new SCell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        open = (init[y].charAt(x) != '0');
        cells[x][y] = new SCell(x, y, open);
      }
    }
  }

  //TODO: might be able to DRY up these constructors
  Maze (int w, int h) {

    width = w;
    height = h;
    cells = new SCell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        cells[x][y] = new SCell(x, y, true);
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
  public SCell[] moves(int x, int y) {
    SCell[] results = new SCell[0];
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

  //Returns an array of Cells that are adjacent to the coordinates specified,
  //are open and unseen. 
  public SCell[] unseenMoves(int x, int y) {
    SCell[] results = new SCell[0];
    SCell[] possible = moves(x, y);

    int count = 0;

    for (int i = 0; i < possible.length; i++) {
      if (!possible[i].getSeen()) {
        count++;
        results = Arrays.copyOf(results, count);
        results[count-1] = possible[i];
      }
    }

    return results;
  }

  public SCell[] path(int sourceX, int sourceY, int destX, int destY) {
    Stack<SCell> search = new Stack<SCell>();

    //To start with, we'll have the source cell
    search.push(cells[sourceX][sourceY]);
    search.peek().setSeen(true);

    //As long as there are any cells left on the stack:
    while (!search.empty()) {

      //Is the top of the stack our destination?
      if (search.peek().getX() == destX && search.peek().getY() == destY)
        break;

      //Are there adjacent unseen cells?
      if ( unseenMoves(search.peek().getX(), search.peek().getY()).length > 0 ) {
        //Mark the first one we find as seen and push it onto the stack
        search.push( unseenMoves(search.peek().getX(), search.peek().getY())[0] );
        search.peek().setSeen(true);
      } else {
        search.pop();
      }

    }
    //At this point, the stack is empty, or we found our path.
    resetSeen();
    SCell[] results = new SCell[search.size()];

    for ( int i = search.size() - 1; i >= 0; i-- )
      results[i] = search.pop();

    return results;
  }

  //Reset all cells in the maze to "unseen"
  public void resetSeen() {
    for (int x = 0; x < width; x++)
      for (int y = 0; y < height; y++)
        cells[x][y].setSeen(false);
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
