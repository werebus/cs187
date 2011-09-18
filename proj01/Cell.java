public class Cell {
  int x;
  int y;
  boolean open;

  Cell (int xVal, int yVal, boolean isOpen) {
    x = xVal;
    y = yVal;
    open = isOpen;
  }

  Cell (int xVal, int yVal) {
    this(xVal, yVal, true);
  }

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

  public String coords() {
    return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
  }

  public String toString() {
    if (open) {
      return coords() + " open";
    } else {
      return coords() + " closed";
    }
  }

  public String toShortString() {
    if (open) {
      return "1";
    } else {
      return "0";
    }
  }
  
}
