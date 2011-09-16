public class Maze {
  public int width;
  public int height;

  Cell[][] cells;

  public String toString() {
    String result = new String("");

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
         result += cells[x][y].toShortString();
      }
      if (x < width - 1) {
        result += "\r";
      }
    }

    return result;
  }
}
