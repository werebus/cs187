public class Cell {
  int x;
  int y;
  boolean open;

  Cell (int xVal, int yVal, boolean isOpen) {
    x = xVal;
    y = yVal;
    open = isOpen;
  }

  //If the user doesn't specify, the Cell is open
  Cell (int xVal, int yVal) {
    this(xVal, yVal, true);
  }

  //Getter / Setter boilerplate (blech)
  public int getX() {
    return x;
  }

  public void setX(int xVal) {
    x = xVal;
  }

  public int getY() {
    return y;
  }

  public void setY(int yVal) {
    y = yVal;
  }

  public boolean getOpen() {
    return open;
  }

  public void setOpen(boolean isOpen) {
    open = isOpen;
  }

  //Returns a string that represents the coordinates of this Cell in the
  //form (x, y)  Public, but currently only called by toString()
  public String coords() {
    return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
  }

  //A Cell's toString looks like '(x, y) open'
  public String toString() {
    if (open) {
      return coords() + " open";
    } else {
      return coords() + " closed";
    }
  }

  //Returns the string '0' or '1', representing whether the cell is open
  //or not.  Used by the Maze class to output the status of each of its cells
  //when calling Maze m.toString()
  public String toShortString() {
    if (open) {
      return "1";
    } else {
      return "0";
    }
  }

}
