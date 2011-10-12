public class DropoutStack<T> {
  private final int DEFAULT_CAPACITY = 3;

  private int capacity;
  private int top;
  private int bottom;

  private T[] stack;

  // This constructor takes an integer capacity.  Top and bottom are set to 0
  public DropoutStack(int c) {
    capacity = c;
    top = 0;
    bottom = 0;

    stack = (T[])(new Object[c]);
  }

  // No capacity given, go with the default capacity.  Seems like you should be
  // able to use this(DEFAULT_CAPACITY), but that gave me some trouble.
  public DropoutStack() {
    capacity = DEFAULT_CAPACITY;
    top = 0;
    bottom = 0;

    stack = (T[])(new Object[DEFAULT_CAPACITY]);
  }

  //Push an element onto the "top" of the stack.
  public void push (T element) {
    //if we're not empty, move some stuff around, otherwise top is where we 
    //want to be.
    if (!isEmpty()) {
      //increment the top, wrapping around if we've gone over the capacity
      top = (top + 1) % capacity;

      //If the top bumped up against the bottom, move the bottom up to
      //accomodate.
      if (top == bottom)
        bottom = (bottom + 1) % capacity;
    }
    stack[top] = element;
  }

  //Pop an element off the top of the stack, returning it
  public T pop() throws EmptyCollectionException {
    //Throw an EmptyCollectionException if we can't
    if (isEmpty())
      throw new EmptyCollectionException("DropoutStack");

    T result = stack[top];
    stack[top] = null;

    //Are the top and the bottom the same?  Then there's only one element
    //in the stack.  Set the top to null, effectively "emptying" it.
    if (top != bottom) {
      //top = (top - 1) % capacity;  --Grrr..

      //decrement the top, wrapping back around to the top if we've crossed
      //into negative teritory.  In some languages, the above line would work;
      //ie in ruby -1 % 3 == 2, but in java -1 % 3 == -1
      top--;
      if (top < 0)
        top += capacity;
    }

    return result;
  }

  //Simply return the element in the top spot of the stack.  Easy.
  public T peek() throws EmptyCollectionException {
    if (isEmpty())
      throw new EmptyCollectionException("DropoutStack");

    T result = stack[top];
    return result;
  }

  //stack[] starts full of null.  It's also the state after everything has been
  //popped off.
  public boolean isEmpty() {
    return (stack[top] == null);
  }

  //returns an integer size of the stack
  public int size() {
    if (isEmpty()) {
      //empty stacks have size of 0
      return 0;
    } else if (bottom > top) {
      //if bottom > top, then this is a "wrapped" array.  Add the capacity.
      return (top - bottom + capacity + 1);
    } else {
      //easy
      return (top - bottom + 1);
    }
  }

  //Convenience method lets us see the size, capacity, top, bottom, and contents
  //of the DropoutStack.
  public String toString() {
    int position = bottom;
    String result;

    result =  "Size:     " + size() + "\n";
    result += "Capacity: " + capacity + "\n";
    result += "Bottom:   " + bottom + "\n";
    result += "Top:      " + top + "\n\n";

    if (!isEmpty())
      result += stack[position] + "\n";
    while (position != top) {
      position = (position + 1) % capacity;
      result += stack[position] + "\n";
    }

    return result;
  }

  public void resize(int newCapacity) {
    int currentPosition;
    int counter = 0;
    T[] oldStack = stack;
    
    stack = (T[])(new Object[newCapacity]);

    if(newCapacity > size()) {
      currentPosition = bottom;
    } else {
      currentPosition = (top - newCapacity + 1);
      if (currentPosition < 0)
        currentPosition += capacity;
    }

    do {
      stack[counter] = oldStack[currentPosition];
      counter++;
      currentPosition = (currentPosition + 1) % capacity;
    } while (currentPosition != top);

    top = currentPosition;
    bottom = 0;

    while (counter < newCapacity) {
      stack[counter] = null;
      counter++;
    }

    capacity = newCapacity;

  }
}
