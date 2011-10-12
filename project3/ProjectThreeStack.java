public class ProjectThreeStack<T> extends DropoutStack<T> {

  //exactly the same constructors as DropoutStack
  public ProjectThreeStack() {
    super();
  }

  public ProjectThreeStack(int c) {
    super(c);
  }

  //before we push, check to see if our size is equal to the capacity
  //of the stack.  If so, resize the stack to twice it's size.
  public void push (T element) {
    if (size() == getCapacity())
      resize(getCapacity() * 2);

    super.push(element);
  }
}
