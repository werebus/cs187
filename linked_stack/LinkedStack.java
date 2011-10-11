public class LinkedStack<T> {
  private int count;
  private LinearNode<T> top;

  public LinkedStack( ) {
    count = 0;
    top = null;
  }

  public void push (T element) {
    LinearNode<T> temp = new LinearNode<T> (element);
    temp.setNext(top);
    top = temp;
    count++;
  }

  public T pop() throws EmptyCollectionException {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    T result = top.getElement();
    top = top.getNext();
    count--;
    return result;
  }

  public T peek() throws EmptyCollectionException {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    return top.getElement();
  }

  public boolean isEmpty() {
    return (top == null);
  }

  public int size() {
    return count;
  }

}

