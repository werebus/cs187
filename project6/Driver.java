import java.util.*;

public class Driver {
  public static void main(String[] args) {
    header("Creating the dog teams");
      DogTeam [ ] entries = new DogTeam[6];
      for (int i = 0; i < 6; i++)
          entries[i] = new DogTeam( );
      entries[0].addToLead (new SledDog ("Youk", 3, "Husky", 0));
      entries[0].addToLead (new SledDog ("Big Papi", 4, "Husky", 0));
      entries[0].addToLead (new SledDog ("Gonzo", 5, "Husky", 0));
      entries[0].addToLead (new SledDog ("Petey", 2, "Husky", 0));
      entries[0].addToLead (new SledDog ("Ells", 7, "Husky", 0));
      entries[1].addToLead (new SledDog ("Cano", 3, "Husky", 1));
      entries[1].addToLead (new SledDog ("Granderson", 4, "Husky", 1));
      entries[1].addToLead (new SledDog ("Jeter", 6, "Husky", 1));
      entries[1].addToLead (new SledDog ("Swisher", 2, "Husky", 1));
      entries[1].addToLead (new SledDog ("A-Rod", 8, "Husky", 1));
      entries[2].addToLead (new SledDog ("Blitzen", 3, "Husky", 2));
      entries[2].addToLead (new SledDog ("Donner", 3, "Husky", 2));
      entries[2].addToLead (new SledDog ("Cupid", 5, "Husky", 2));
      entries[2].addToLead (new SledDog ("Comet", 3, "Husky", 2));
      entries[2].addToLead (new SledDog ("Vixen", 4, "Husky", 2));
      entries[2].addToLead (new SledDog ("Prancer", 6, "Husky", 2));
      entries[2].addToLead (new SledDog ("Dancer", 3, "Husky", 2));
      entries[2].addToLead (new SledDog ("Dasher", 2, "Husky", 2));
      entries[3].addToLead (new SledDog ("Homer", 3, "Husky", 3));
      entries[3].addToLead (new SledDog ("Lisa", 3, "Husky", 3));
      entries[3].addToLead (new SledDog ("Bart", 3, "Husky", 3));
      entries[3].addToLead (new SledDog ("Marge", 3, "Husky", 3));
      entries[3].addToLead (new SledDog ("Mr. Burns", 3, "Husky", 3));
      entries[3].addToLead (new SledDog ("Smithers", 3, "Husky", 3));
      entries[4].addToLead (new SledDog ("Swinburne", 3, "Husky", 4));
      entries[4].addToLead (new SledDog ("Rosetti", 5, "Husky", 4));
      entries[4].addToLead (new SledDog ("Tennyson", 7, "Husky", 4));
      entries[4].addToLead (new SledDog ("Bronte", 3, "Husky", 4));
      entries[4].addToLead (new SledDog ("Barrett Browning", 6, "Husky", 4));
      entries[4].addToLead (new SledDog ("Fitzgerald", 3, "Husky", 4));
      entries[4].addToLead (new SledDog ("Yeats", 2, "Husky", 4));
      entries[5].addToLead (new SledDog ("Swedish Chef", 3, "Husky", 5));
      entries[5].addToLead (new SledDog ("Gonzo", 3, "Husky", 5));
      entries[5].addToLead (new SledDog ("Animal", 3, "Husky", 5));
      entries[5].addToLead (new SledDog ("Kermit", 3, "Husky", 5));
      entries[5].addToLead (new SledDog ("Miss Piggy", 3, "Husky", 5));
      entries[5].addToLead (new SledDog ("Statler", 11, "Husky", 5));
      entries[5].addToLead (new SledDog ("Beaker", 12, "Husky", 5));
      entries[5].addToLead (new SledDog ("Fozzie", 12, "Husky", 5));
      entries[5].addToLead (new SledDog ("Janice", 12, "Husky", 5));
      entries[5].addToLead (new SledDog ("Sam the Eagle", 12, "Husky", 5));
      entries[5].addToLead (new SledDog ("Waldorf", 12, "Husky", 5));

    header("Testing DogTeamIterator");
      Iterator<SledDog> i1 = entries[0].iterator();

      say("should be Ells");
      yell(i1.next());

      say("hasNext should be True");
      yell(i1.hasNext());

      say("4 dogs down should be Youk");
      i1.next();i1.next();i1.next();
      yell(i1.next());

      say("hasNext should be False");
      yell(i1.hasNext());

      say("next() should throw a NoSuchElementException");
      try {
        i1.next();
      } catch ( NoSuchElementException e ) {
        error(e);
      }

    header("Testing SledDog.compareTo()");
      SledDog dog1 = new SledDog("Ace", 3, "German Shepard", 1);
      SledDog dog2 = new SledDog("Buck", 4, "Bull Mastif", 1);
      SledDog dog3 = new SledDog("Buck", 2, "Bijon Frisse", 3);
      SledDog dog4 = new SledDog("Ace", 4, "Husky", 1);

      say("Ace is less than Buck, should be negative");
      yell(dog1.compareTo(dog2));

      say("Team 3 is greater than team 1, should be positive");
      yell(dog3.compareTo(dog2));

      say("Should be 0");
      yell(dog4.compareTo(dog1));

    header("Testing DogTeam.AddInPlace");
      DogTeam team1 = new DogTeam();

      say("Initial add");
      team1.addInPlace( new SledDog("Alice", 5, "Husky", 1) );
      yell(team1);

      say("Bob should go on the end");
      team1.addInPlace( new SledDog("Bob", 7, "Husky", 2) );
      yell(team1);

      say("Barry goes in the middle");
      team1.addInPlace( new SledDog("Barry", 2, "Chihuahua", 1) );
      yell(team1);

      say("Able goes on the front");
      team1.addInPlace( new SledDog("Able", 3, "Bulldog", 1) );
      yell(team1);

      say("Team 1 Bob should come before team 2 Bob");
      team1.addInPlace( new SledDog("Bob", 6, "Husky", 1) );
      yell(team1);

    header("Testing static Reverse-sort method");
      yell(DogTeam.reverseSort(entries[2]));

    header("Testing merging two Teams");
      yell(DogTeam.merge(entries[2], entries[4]));

    header("Testing merging the Array");
      yell(DogTeam.mergeSortTeams(entries));

    header("Checking to make sure the original array is safe");
      say("size should be 6");
      yell(entries.length);

      say("elements[0] should just be the RedSox, and unsorted");
      yell(entries[0]);

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
