public class Driver {
  public static void main(String[] args) {

    header("Creating a default size DropoutStack of Integers");
      DropoutStack<Integer> stack1 = new DropoutStack<Integer>();
      yell(stack1);

    header("Creating a capacity 10 DropoutStack of Strings");
      DropoutStack<String> stack2 = new DropoutStack<String>(10);
      yell(stack2);

    header("Pushing two numbers onto stack1");
      stack1.push(1);
      stack1.push(3);
      say("Should be 1,3");
      yell(stack1);
      say("isEmpty() should be false");
      yell(stack1.isEmpty());


    header("Peek at the top of the stack");
      say("Should be 3");
      yell(stack1.peek());

    header("Pushing two more on should wrap around");
      stack1.push(5);
      stack1.push(7);
      say("Should now be 3,5,7");
      yell(stack1);

    header("Pop off one");
      say("Should be 7");
      yell(stack1.pop());
      say("Should leave 3,5");
      yell(stack1);

    header("Empty the rest of the stack");
      stack1.pop();
      stack1.pop();
      yell(stack1);
      say("isEmpty hould be true now");
      yell(stack1.isEmpty());

    header("Both peek and pop on an empty stack should throw exceptions");
      say("Peeking...");
      try {
        stack1.peek();
      } catch (EmptyCollectionException e) {
        error(e);
      }
      say("Popping...");
      try {
        stack1.pop();
      } catch (EmptyCollectionException e) {
        error(e);
      }

    header("Testing resizing");
      say("Start by shrinking the String stack to 8");
      stack2.resize(8);
      yell(stack2);

      say("Push a few strings on and expand again to 9");
      stack2.push("Alpha");
      stack2.push("Bravo");
      stack2.push("Charlie");
      yell(stack2);
      stack2.resize(9);
      yell(stack2);

      say("Fill it the rest of the way and shrink to 6");
      stack2.push("Delta");
      stack2.push("Echo");
      stack2.push("Foxtrot");
      stack2.push("Golf");
      stack2.push("Hotel");
      stack2.push("India");
      yell(stack2);
      stack2.resize(6);
      yell(stack2);

      say("Wrap around with two more pushes, shrink to 4");
      stack2.push("Juliet");
      stack2.push("Kilo");
      yell(stack2);
      stack2.resize(4);
      yell(stack2);

    header("Testing the ProjectThreeStack");
      say("Make a size-4 ProjectThreeStack");
      ProjectThreeStack<String> stack3 = new ProjectThreeStack<String>(4);
      yell(stack3);
      say("Push 4 items onto it");
      stack3.push("Able");
      stack3.push("Baker");
      stack3.push("Charlie");
      stack3.push("Donnie");
      yell(stack3);
      say("Pushing one more on should resize to 8");
      stack3.push("Ernie");
      yell(stack3);
  }

  //Covenience wrapper around System.out.println
  public static void say(Object statement) {
    System.out.println(statement);
  }

  //Like say, but green (using ANSI codes - not very portable, but good enough
  //for most non-Windows users.  Maybe look into jansi or Jcurses?
  public static void yell(Object statement) {
    System.out.println((char)27 + "[32m" + statement + (char)27 + "[39m");
  }

  //Like say, but red
  public static void error(Object statement) {
    System.out.println((char)27 + "[31m" + statement + (char)27 + "[39m");
  }

  //Outputs a String "underlined" with ='s
  public static void header(Object statement) {
    say("\n" + statement);
    for (int i = 0; i < statement.toString().length(); i++) {
      System.out.print("=");
    }
    System.out.print("\n");
  }

}
