public class QCell extends SCell {
  private int distance;
  private QCell parent;

  QCell (int xVal, int yVal, boolean isOpen) {
    super(xVal, yVal, isOpen);
    distance = 0;
  }

  QCell(int xVal, int yVal) {
    super(xVal, yVal);
    distance = 0;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int newDist) {
    distance = newDist;
  }

  public QCell getParent() {
    return parent;
  }

  public void setParent(QCell newParent){
    parent = newParent;
  }

}
