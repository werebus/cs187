public class SledDog extends Dog implements Comparable<SledDog> {
   protected String breed = "Husky"; // may be overwritten with another breed
   protected int teamNumber;
   public SledDog (String newName, int newAge, String newBreed, int newTeamNumber) {
      super (newName, newAge);
      breed = newBreed;
      teamNumber = newTeamNumber;}
   public String getBreed( ) {return breed;}
   public void setBreed(String newBreed) {breed = newBreed;}
   public int getTeamNumber( ) {return teamNumber;}
   public void setTeamNumber(int newTeamNumber) {teamNumber = newTeamNumber;}

   public int compareTo (SledDog other) {
     // If they have the same name, compare team numbers, otherwise compare names
     if ( getName().equals( other.getName() ) ) {
       return (new Integer(getTeamNumber()).compareTo( new Integer(other.getTeamNumber()) ) );
     } else {
       return getName().compareTo( other.getName() );
     }
   }

   public String toString() {
     return getName() + ": " + getAge() + "-year-old " + getBreed() + " on team " + getTeamNumber();
   }
}
