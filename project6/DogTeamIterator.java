import java.util.*;

public class DogTeamIterator implements Iterator<SledDog> {
  private DogTeam team;
  private LinearNode current;

  public DogTeamIterator(DogTeam t) {
    team = t;
    current = team.getLeadNode();
  }

  public boolean hasNext() {
    return !(current == null);
  }

  public SledDog next() throws NoSuchElementException {
    LinearNode ret;

    if (!hasNext())
      throw new NoSuchElementException();

    ret = current;
    current = current.getNext();

    return (SledDog)ret.getElement();
  }

  public void remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
