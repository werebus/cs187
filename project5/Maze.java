import java.util.*;

public class Maze {
  private int width;
  private int height;

  private PQCell[][] cells;

  Maze (int w, int h, String[] init) {
    //TODO: Some error checking here. init should hava a size of h, and all
    //elements of init should have w characters. init should have only 1's
    //and 0's in it.
    boolean open, isT;

    width = w;
    height = h;
    cells = new PQCell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        open = (init[y].charAt(x) != '0');
        isT = (init[y].charAt(x) == '2');
        cells[x][y] = new PQCell(x, y, open, isT);
      }
    }
  }

  //TODO: might be able to DRY up these constructors
  Maze (int w, int h) {

    width = w;
    height = h;
    cells = new PQCell[w][h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        cells[x][y] = new PQCell(x, y, true);
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

  public PQCell getCell(int x, int y) {
    return cells[x][y];
  }

  //set Cells?  Probably not; it would be one heck of a mess.

  //Returns an array of Cells that are adjacent to the coordinates specified
  //and are open.  Note that below I often check if we're ON the boundary of
  //the maze rather than on or beyond it.  This is to allow requests _beyond_
  //the bounds of the maze to still raise an exception.
  public PQCell[] moves(int x, int y) {
    PQCell[] results = new PQCell[0];
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
  public PQCell[] unseenMoves(int x, int y) {
    PQCell[] results = new PQCell[0];
    PQCell[] possible = moves(x, y);

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
  public PQCell[] path(int sourceX, int sourceY, int destX, int destY) {
    Queue<PQCell> search = new ArrayDeque();
    PQCell[] foundPath = new PQCell[0];

    //To start with, we'll add the source cell (if it's open. Otherwise, bail)
    if ( cells[sourceX][sourceY].getOpen() ) {
      search.add(cells[sourceX][sourceY]);
      search.element().setSeen(true);
    }

    //while we still have something in the queue
    while (!search.isEmpty()) {

      //Place all of the unseen moves for the front of the queue in the queue
      for (PQCell move : unseenMoves( search.element().getXCoord(), search.element().getYCoord() ) ) {
        move.setDistance(search.element().getDistance() + 1);
        move.setParent(search.element());
        move.setSeen(true);

        search.add(move);
      }

      //If the front of the queue happens to be the destination, lets get out of here
      if (search.element().getXCoord() == destX && search.element().getYCoord() == destY) break;

      //Remove the next element
      search.remove();
    }

    //Two choices here: the queue is empty, so there's no path, or the front of
    //the queue is our destination.
    if (!search.isEmpty()) {
      foundPath = new PQCell[search.element().getDistance() + 1];
      PQCell result = search.element();

      //Pop off all the cells and lay them in backwards into the array
      for ( int i = result.getDistance(); i >= 0; i-- ) {
        foundPath[i] = result;
        result = (PQCell)result.getParent();
      }
    }

    resetCells();
    return foundPath;

  }

  //Returns the best posible route in terms of most spoons returned.  The result is
  //packed into a Hashtable with one entry.  Said entry has a key of the path array,
  //and a value of the number of spoons that you get to keep.  This method is called
  //by bestPath() and spoons() below.
  public Hashtable<PQCell[],Integer> bestPathHash(int startSpoons, int sourceX, int sourceY, int destX, int destY) {
    Hashtable<PQCell[],Integer> pathHash = new Hashtable(1); //this will end up being the return value
    PriorityQueue<PQCell> search = new PriorityQueue();
    PQCell[] foundPath = new PQCell[0];
    PQCell best = new PQCell(-1, -1, false);
    int spoonTemp = 0;

    //To start with, we'll add the source cell (if it's open. Otherwise, bail)
    if ( cells[sourceX][sourceY].getOpen() && cells[destX][destY].getOpen() ) {
      search.add(cells[sourceX][sourceY]);
      cells[sourceX][sourceY].setSpoons(startSpoons);
    }

    //while we still have something in the queue and "best" isn't our destination
    while (!( search.isEmpty() || (best.getXCoord() == destX && best.getYCoord() == destY) )) {

      //Remove the best cell and set it to "seen"
      best = search.poll();
      best.setSeen(true);

      for (PQCell move : unseenMoves( best.getXCoord(), best.getYCoord() ) ) {

        //How many spoons would this move cost us?
        if (move.getIsTown())
          spoonTemp = (int)(best.getSpoons() * 0.95);
        else
          spoonTemp = best.getSpoons() - 1;

        //Unless this move isn't yet on the queue, or if we can get there with more spoons...
        if (!search.contains(move) || spoonTemp > move.getSpoons()) {
          move.setDistance(best.getDistance() + 1);
          move.setParent(best);
          move.setSpoons(spoonTemp);

          search.add(move);
        }
      }
    }

    //Is best our destination?  did it yield positive spoons?
    if ( best.getXCoord() == destX && best.getYCoord() == destY && best.getSpoons() > 0) {
      foundPath = new PQCell[best.getDistance() + 1];
      PQCell result = best;

      //Pop off all the cells and lay them in backwards into the array
      for ( int i = result.getDistance(); i >= 0; i-- ) {
        foundPath[i] = result;
        result = (PQCell)result.getParent();
      }
    }

    //Pack into the hash
    pathHash.put(foundPath, ( best.getSpoons() > 0 ? best.getSpoons() : 0 ));
    resetCells();
    return pathHash;

  }

  //Return the key of bestPathHash[0], an array of PQCells in order
  public PQCell[] bestPath(int startSpoons, int sourceX, int sourceY, int destX, int destY) {
    return bestPathHash(startSpoons, sourceX, sourceY, destX, destY).keys().nextElement();
  }

  //Return the value of bestPathHash[0] an Integer (unpacked into an int)
  public int spoons(int startSpoons, int sourceX, int sourceY, int destX, int destY) {
    return bestPathHash(startSpoons, sourceX, sourceY, destX, destY).elements().nextElement();
  }

  //Tells whether there is a path between two cells.  Takes the same arguments
  //as path(...) above.
  public boolean isPath(int sourceX, int sourceY, int destX, int destY) {
    return (path(sourceX, sourceY, destX, destY).length > 0);
  }

  //Reset all cells in the maze to "unseen", 0 distance, 0 spoons, no parent
  public void resetCells() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        cells[x][y].setSeen(false);
        cells[x][y].setDistance(0);
        cells[x][y].setSpoons(0);
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
