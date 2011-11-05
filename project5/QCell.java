public class QCell extends SCell {
  protected int distance;
  protected QCell parent;

  public QCell(int x, int y) {
    super(x, y);
    distance = 0;
    parent = null;
  }

  public QCell(int x, int y, boolean isOpen) {
    super(x, y, isOpen);
    distance = 0;
    parent = null;
  }

  public int getDistance () {
    return distance;
  }

  public void setDistance (int d) {
    distance = d;
  }

  public QCell getParent () {
    return parent;
  }

  public void setParent (QCell p) {
    parent = p;
  }

  public void increaseDistance () {
    distance++;
  }

  public void decreaseDistance () {
    distance--;
  }
}
