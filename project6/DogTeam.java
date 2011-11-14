import java.util.Iterator;

public class DogTeam implements Iterable<SledDog> {
   private LinearNode<SledDog> leadNode;
   private int size;

   public DogTeam(){
     leadNode = null;
     size = 0;
   }

   public LinearNode<SledDog> getLeadNode(){
     return leadNode;
   }

   public void setLeadNode(LinearNode<SledDog> newLead){
     leadNode = newLead;
   }

   public int getSize(){
     return size;
   }

   public void setSize(int s){
     size = s;
   }

   public void addToLead (SledDog newDog) {
      LinearNode<SledDog> newNode = new LinearNode<SledDog> (newDog);
      newNode.setNext (leadNode);
      leadNode = newNode;
      size++;
   }

   public SledDog removeLead ( ) throws EmptyCollectionException {
      if (leadNode == null) throw new EmptyCollectionException ("DogTeam");
      SledDog ret = leadNode.getElement( );
      leadNode = leadNode.getNext( );
      size--;
      return ret;
   }

   //Adds the newDog into it's correct place in a sorted team
   public void addInPlace (SledDog newDog) {
     LinearNode<SledDog> current = getLeadNode();

     //If the team is empty, or the newDog is "less than" the lead,
     //just add the newDog to the lead,
     if (size == 0 || newDog.compareTo(getLeadNode().getElement()) < 0) {
       addToLead( newDog );
       return;
     }

     //While current is not the last node, and the new dog is "greater than"
     //current, advance current down the list.
     while ( current.getNext() != null && newDog.compareTo( current.getNext().getElement() ) > 0 )
       current = current.getNext();

     //Add the newDog in after current
     LinearNode<SledDog> newNode = new LinearNode(newDog);
     newNode.setNext( current.getNext() );
     current.setNext( newNode );
   }

   //Returns a DogTeamIterator object for this DogTeam
   public Iterator<SledDog> iterator( ) {
     return new DogTeamIterator(this);
   }

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

   //The toString method for a dog team uses the iterator, and tacks each
   //dog's toString onto the result with a \n
   public String toString() {
     String result = new String();
     for (SledDog sd : this)
       result += (sd + "\n");

     return result;
   }

   //This does an insertion sort on a DogTeam, then reverses the result
   //before returning it (this is because having the dogs in reverse
   //oreder will work better for when we're trying to merge two teams
   //together.
   public static DogTeam reverseSort(DogTeam team) {
     DogTeam temp = new DogTeam();
     DogTeam result = new DogTeam();

     for (SledDog sd : team)
       temp.addInPlace(sd);

     for (SledDog sd : temp)
       result.addToLead(sd);

     return result;
   }

   public static DogTeam merge (DogTeam teamOne, DogTeam teamTwo) {
     Iterator<SledDog> itOne = reverseSort(teamOne).iterator();
     Iterator<SledDog> itTwo = reverseSort(teamTwo).iterator();
     SledDog tempOne = itOne.next();
     SledDog tempTwo = itTwo.next();

     DogTeam result = new DogTeam();
     boolean done = false;

     while ( !done ) {
       //top block if tempOne is bigger than temp2
       if (tempOne.compareTo(tempTwo) > 0) {

         //Add tempOne to lead
         result.addToLead(tempOne);

         //If there's any more in one, move along
         if (itOne.hasNext()) {
           tempOne = itOne.next();

        //If one is empty, finish up by dumping two into the team
         } else {
           result.addToLead(tempTwo);
           while (itTwo.hasNext())
             result.addToLead(itTwo.next());
           done = true;
         }

      //bottom block if tempTwo is bigger than tempOne
       } else {

         //Add tempTwo to lead
         result.addToLead(tempTwo);

         //If there's any more in two, move along
         if (itTwo.hasNext()) {
           tempTwo = itTwo.next();

        //If two is empty, finish up by dumping one into the team
         } else {
           result.addToLead(tempOne);
           while (itOne.hasNext())
             result.addToLead(itOne.next());
           done = true;
         }
       }
     }

     return result;
   }

   public static DogTeam mergeSortTeams (DogTeam [ ] entries) {
     DogTeam[] workingCopy = entries.clone();
     int size = entries.length;

     while (size > 1) {
       //work through the array, merging the 1st element with the nth,
       //the 2nd with the n-1th, etc, up to the n/2th element rounding
       //down
       for (int s = 0; s < (size/2); s++) {
         workingCopy[s] = merge(workingCopy[s], workingCopy[size - s - 1]);
       }

       //if this was an odd-sized pass, merge the size/2th element in
       if (size % 2 != 0){
         workingCopy[0] = merge(workingCopy[0], workingCopy[size/2]);
       }

       //cut the elements we're working over in half.
       size = size / 2;
     }

     return workingCopy[0];
   }

}
