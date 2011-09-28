public class SCell extends Cell {
  boolean seen;

  SCell (int xVal, int yVal, boolean isOpen) {
    super(xVal, yVal, isOpen);
    seen = false;
  }

  SCell (int xVal, int yVal) {
    super(xVal, yVal);
    seen = false;
  }

  public boolean getSeen() {
    return seen;
  }

  public void setSeen(boolean beenSeen) {
    seen = beenSeen;
  }
}
