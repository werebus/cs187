public class PQCell extends QCell implements Comparable<PQCell> {
  protected boolean isTown;
  protected int spoons;

  public PQCell(int x, int y) {
    super(x, y);
    isTown = false;
    spoons = 0;
  }

  public PQCell(int x, int y, boolean isOpen) {
      super(x, y, isOpen);
    isTown = false;
    spoons = 0;
  }

  public PQCell(int x, int y, boolean isO, boolean isT) {
    this (x, y, isO);
    isTown = isT;
  }

  public boolean getIsTown () {
    return isTown;
  }

  public void setIsTown (boolean b) {
    isTown = b;
  }

  public int getSpoons () {
    return spoons;
  }

  public void setSpoons (int s) {
    spoons = s;
  }

  public int compareTo (PQCell other) {
    if (this.spoons > other.spoons) return -1;
    if (this.spoons == other.spoons) return 0;
    return 1;
  }

  public String toString () {
    return "(" + xCoord + ", " + yCoord + ") " + (open ? "open" : "closed") + (seen ? " seen" : " unseen") + (isTown ? " town" : " village");
  }

  public String toShortString() {
    if (open == false) return "0";
    return (isTown == true ? "2" : "1");
  }

}
