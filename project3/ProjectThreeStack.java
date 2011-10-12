public class ProjectThreeStack<T> extends DropoutStack<T> {

  public ProjectThreeStack() {
    super();
  }

  public ProjectThreeStack(int c) {
    super(c);
  }

  public void push (T element) {
    if ((getTop() + 1) == getCapacity())
      resize(getCapacity() * 2);

    super.push(element);
  }
}
