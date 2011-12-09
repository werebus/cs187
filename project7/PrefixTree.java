public class PrefixTree {
  private PTNode root;
  private int size;

  public PTNode getRoot(){
    return root;
  }

  public void setRoot(PTNode node){
    root = node;
  }

  public int getSize(){
    return size;
  }

  public PrefixTree(){
    root = new PTNode ("");
  }

  public void addString(String w){
    PTNode current = root;

    for (int i = 0; i < w.length(); i++) {
      if (current.getChild(w.charAt(i)) == null)
        current.setChild(w.charAt(i), w.substring(0, i+1));

      current = current.getChild(w.charAt(i));
    }
    size++;
  }

  public boolean contains(String w){
    PTNode current = root;

    for (int i = 0; i < w.length(); i++) {
      if (current.getChild(w.charAt(i)) == null)
        return false;

      current = current.getChild(w.charAt(i));
    }
    return true;
  }
}
