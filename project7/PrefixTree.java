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

  public void addString(String w){ // stub, to be written by student
  // add new nodes to calling tree, for w and any of its prefixes that are new
  }
}
