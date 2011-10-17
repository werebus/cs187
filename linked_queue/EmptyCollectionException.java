public class EmptyCollectionException extends RuntimeException
{

  public EmptyCollectionException()
  {
    super("collection is empty");
  }

  public EmptyCollectionException(String message)
  {
    super(message + " collection is empty");
  }

}
