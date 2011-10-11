public class DropoutStack<T> {
  private final int DEFAULT_CAPACITY = 3;

  private int capacity;
  private int top;
  private int bottom;

  private T[] stack;

  public DropoutStack(int c) {
    capacity = c;
    top = 0;
    bottom = 0;

    stack = (T[])(new Object[c]);
  }

  public DropoutStack() {
    capacity = DEFAULT_CAPACITY;
    top = 0;
    bottom = 0;

    stack = (T[])(new Object[DEFAULT_CAPACITY]);
  }

  public void push (T element) {
    if (!isEmpty()) {
      top = (top + 1) % capacity;
      if (top == bottom)
        bottom = (bottom + 1) % capacity;
    }
    stack[top] = element;
  }

  public T pop() throws EmptyCollectionException {
    if (isEmpty())
      throw new EmptyCollectionException("DropoutStack");

    T result = stack[top];

    if (top == bottom) {
      stack[top] = null;
    } else {
      top = (top - 1) % capacity;
    }

    return result;
  }

  public T peek() throws EmptyCollectionException {
    if (isEmpty())
      throw new EmptyCollectionException("DropooutStack");

    T result = stack[top];
    return result;
  }

  public boolean isEmpty() {
    return (stack[top] == null);
  }

  public int size() {
    if (isEmpty()) {
      return 0;
    } else if (bottom > top) {
      return (top - bottom + capacity + 1);
    } else {
      return (top - bottom + 1);
    }
  }

  public String toString() {
    int position = bottom;
    String result = new String;

    result =  "Size:   " + size() + "\n";
    result += "Bottom: " + bottom + "\n";
    result += "Top:    " + top + "\n\n";

    result += stack[position] + "\n";
    while (position != top) {
      position = (position + 1) % capacity;
      result += stack[position] + "\n";
    }

    return result;
}
