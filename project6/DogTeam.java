import java.util.Iterator;

public class DogTeam implements Iterable<SledDog> {
   private LinearNode<SledDog> leadNode;
   private int size;
   public DogTeam( ) {leadNode = null; size = 0;}
   public LinearNode<SledDog> getLeadNode( ) {return leadNode;}
   public void setLeadNode (LinearNode<SledDog> newLead) {leadNode = newLead;}
   public int getSize( ) {return size;}
   public void setSize (int s) {size = s;}

   public void addToLead (SledDog newDog) {
      LinearNode<SledDog> newNode = new LinearNode<SledDog> (newDog);
      newNode.setNext (leadNode);
      leadNode = newNode;
      size++;}

   public SledDog removeLead ( ) throws EmptyCollectionException {
      if (leadNode == null) throw new EmptyCollectionException ("DogTeam");
      SledDog ret = leadNode.getElement( );
      leadNode = leadNode.getNext( );
      size--;
      return ret;}

   public void addInPlace (SledDog newDog) {
      // adds new dog into correct place in calling team
      // assumes calling team is sorted
      // to be written by student, this is a stub
      }

   public Iterator<SledDog> iterator( ) {
      // return an iterator object for the calling DogTeam
      // to be written by student, this is a stub
      return null;}

   public boolean isSorted ( ) {
      // returns true if calling team is sorted by natural order on SledDogs
      if (size <= 1) return true;
      boolean soFar = true;
      LinearNode<SledDog> current = leadNode.getNext( );
      LinearNode<SledDog> previous = leadNode;
      do {
         if (previous.getElement( ).compareTo (current.getElement( )) > 0)
            soFar = false;
         previous = current;
         current = current.getNext( );}
      while (soFar && current.getNext( ) != null);
      return soFar;} 

   public static DogTeam merge (DogTeam teamOne, DogTeam teamTwo) {
      // returns sorted DogTeam with union of elements of two input teams
      // assumes input teams are sorted
      // to be written by student, this is a stub
      return null;}

   public static DogTeam mergeSortTeams (DogTeam [ ] entries) {
      // returns single sorted team with all dogs from all teams in input array
      // to be written by student, this is a stub
      return null;}




}