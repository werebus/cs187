/* SCell --- searchable cell
 * @author blau
 */
public class SCell extends Cell {
  boolean seen; // true if this cell has been encountered in search for path

  /* Creates an open, unseen cell with specified x and y coordinates.
   * @param x: x-coordinate (column) of this cell
   * @param y: y-coordinate (row) of this cell
   */
  public SCell(int x, int y) {
    super(x, y);
    seen = false;
  }

  /* Creates an unseen cell with specified x and y coordinates,
   * open or closed according to parameter isOpen.
   * @param x: x-coordinate (column) of this cell
   * @param y: y-coordinate (row) of this cell
   * @param isOpen: true if the cell is open
   */
  public SCell(int x, int y, boolean isOpen) {
    super(x, y, isOpen);
    seen = false;
  }

  boolean getSeen() {
    return seen;
  }

  /* @param newSeen: new value for seen attribute
   */
  void setSeen(boolean newSeen) {
    seen = newSeen;
  }

  public String toString () {
    return "(" + xCoord + ", " + yCoord + ") " + (open ? "open" : "closed") + (seen ? " seen" : " unseen");
  }

}
