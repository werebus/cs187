public class Maze {
  int width;
  int height;

  Cell[][] cells;

  public int getWidth() {
    return width;
  }

  public void setWidth(int w) {
    width = w;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int h) {
    height = h;
  }

  //get/set Cells?

  public String toString() {
    String result = new String("");

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
         result += cells[x][y].toShortString();
      }
      if (x < width - 1) {
        result += "\n";
      }
    }

    return result;
  }
}
