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
      // to be written by student, this is a stub to allow compilation
      return 0;}
}