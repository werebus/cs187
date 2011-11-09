public class LinearNode<T> {
   // pretty much taken from L&C page 81-82
   private LinearNode<T> next;
   private T element;
   public LinearNode ( ) {next = null; element = null;}
   public LinearNode (T elem) {next = null; element = elem;}
   public LinearNode<T> getNext( ) {return next;}
   public void setNext (LinearNode<T> n) {next = n;}
   public T getElement ( ) {return element;}
   public void setElement (T elem) {element = elem;}}

   