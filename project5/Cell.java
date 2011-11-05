/* Copied from project 1 solution. 
 */
public class Cell {

  protected boolean open;
  protected int xCoord;
  protected int yCoord;

  public Cell (int x, int y) {
    open = true;
    xCoord = x;
    yCoord = y;
  }

  public Cell (int x, int y, boolean isOpen) {
    open = isOpen;
    xCoord = x;
    yCoord = y;
  }

  public boolean getOpen () {
    return open;
  }

  public void setOpen (boolean isOpen) {
    open = isOpen;
  }

  public int getXCoord () {
    return xCoord;
  }

  public void setXCoord (int x) {
    xCoord = x;
  }

  public int getYCoord () {
    return yCoord;
  }

  public void setYCoord (int y) {
    yCoord = y;
  }

  public String toString () {
    return "("+xCoord+", "+yCoord+") "+(open == true ? "open" : "closed");
  }

  public String toShortString() {
    return (open == true ? "1" : "0");
  }
}
