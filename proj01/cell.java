public class Cell {
  public int x;
  public int y;
  public boolean open;

  Cell (int xVal, int yVal, boolean isOpen) {
    x = xVal;
    y = yVal;
    open = isOpen;
  }

  Cell (int xVal, int yVal) {
    this(xVal, yVal, true);
  }
  
  public String coords() {
    return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")"
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
