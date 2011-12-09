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

  //Add the new word to the tree (and all of it's prefixes) to the tree
  //if they're not there yet.
  public void addString(String w){
    PTNode current = root;

    for (int i = 0; i < w.length(); i++) {
      if (current.getChild(w.charAt(i)) == null){
        current.setChild(w.charAt(i), w.substring(0, i+1));
        if (i == w.length() - 1) size++;
      }
      current = current.getChild(w.charAt(i));
    }
  }

  //Walk through the prefix tree character-by-character.  If we find a null,
  //then the string requested is not in the prefix tree.
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
