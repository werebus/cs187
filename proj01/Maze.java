public class Maze {
  int width;
  int height;

  Cell[][] cells;

  Maze (int w, int h, String[] init) {
    //TODO: Some error checking here. init should hava a size of h, and all
    //elements of init should have w characters. init should have only 1's
    //and 0's in it.
    boolean open;
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        open = (init[y].charAt(x) != '0');
        cells[x][y] = new Cell(x, y, open);
      }
    }
  }

  Maze (int w, int h) {
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        cells[x][y] = new Cell(x, y, true);
      }
    }
  }

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
