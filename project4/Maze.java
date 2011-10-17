import java.util.*;

public class Maze {
  private int width;
  private int height;

  private QCell[][] cells;

  Maze (int w, int h, String[] init) {
    //TODO: Some error checking here. init should hava a size of h, and all
    //elements of init should have w characters. init should have only 1's
    //and 0's in it.
    boolean open;

    width = w;
    height = h;
    cells = new QCell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        open = (init[y].charAt(x) != '0');
        cells[x][y] = new QCell(x, y, open);
      }
    }
  }

  //TODO: might be able to DRY up these constructors
  Maze (int w, int h) {

    width = w;
    height = h;
    cells = new QCell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        cells[x][y] = new QCell(x, y, true);
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

  public QCell getCell(int x, int y) {
    return cells[x][y];
  }

  //set Cells?  Probably not; it would be one heck of a mess.

  //Returns an array of Cells that are adjacent to the coordinates specified
  //and are open.  Note that below I often check if we're ON the boundary of
  //the maze rather than on or beyond it.  This is to allow requests _beyond_
  //the bounds of the maze to still raise an exception.
  public QCell[] moves(int x, int y) {
    QCell[] results = new QCell[0];
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
  public QCell[] unseenMoves(int x, int y) {
    QCell[] results = new QCell[0];
    QCell[] possible = moves(x, y);

    int count = 0;

    //Look through all of the open cells from moves(x,y) and if it's not "seen",
    //tack it onto the results array.
    for (int i = 0; i < possible.length; i++) {
      if (!possible[i].getSeen()) {
        count++;
        results = Arrays.copyOf(results, count);
        results[count-1] = possible[i];
      }
    }

    return results;
  }

  //Returns an array of cells that you could travel through to get from
  //(sourceX, sourceY) to (destX, destY).  It uses a stack of cells to
  //assemble a path, but returns an array (dunno why, just 'cause)
  public QCell[] path(int sourceX, int sourceY, int destX, int destY) {
    Queue<QCell> search = new ArrayDeque();
    QCell[] foundPath = new QCell[0];
    
    //To start with, we'll add the source cell (if it's open. Otherwise, bail)
    if ( cells[sourceX][sourceY].getOpen() ) {
      search.add(cells[sourceX][sourceY]);
      search.element().setSeen(true);
    }

    //while we still have something in the queue
    while (!search.isEmpty()) {

      //Place all of the unseen moves for the front of the queue in the queue
      for (QCell move : unseenMoves( search.element().getX(), search.element().getY() ) ) {
        move.setDistance(search.element().getDistance() + 1);
        move.setParent(search.element());
        move.setSeen(true);

        search.add(move);
      }

      //If the front of the queue happens to be the destination, lets get out of here
      if (search.element().getX() == destX && search.element().getY() == destY) break;

      //Remove the next element
      search.remove();
    }

    //Two choices here: the queue is empty, so there's no path, or the front of
    //the queue is our destination.
    if (!search.isEmpty()) {
      foundPath = new QCell[search.element().getDistance() + 1];
      QCell result = search.element();

      //Pop off all the cells and lay them in backwards into the array
      for ( int i = result.getDistance(); i >= 0; i-- ) {
        foundPath[i] = result;
        result = result.getParent();
      }
    }

    resetCells();
    return foundPath;

  }

  //Tells whether there is a path between two cells.  Takes the same arguments
  //as path(...) above.
  public boolean isPath(int sourceX, int sourceY, int destX, int destY) {
    return (path(sourceX, sourceY, destX, destY).length > 0);
  }

  //Reset all cells in the maze to "unseen"
  public void resetCells() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        cells[x][y].setSeen(false);
        cells[x][y].setDistance(0);
        cells[x][y].setParent(null);
      }
    }
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
