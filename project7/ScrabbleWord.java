import java.util.*;

public class ScrabbleWord implements Comparable<ScrabbleWord>{
  private String word;
  private int score;

  public ScrabbleWord(String w, int s){
    word = w;
    score = s;
  }

  public String getWord(){
    return word;
  }

  public void setWord(String w){
    word = w;
  }

  public int getScore(){
    return score;
  }

  public void setScore(int s){
    score = s;
  }

  //Conveniently, Integers already have a compareTo() method.  Box the two
  //scores up into Integers and compare them.  Of course, for Integers, lower
  //is "better"; multiply by -1 to switch it around.
  public int compareTo(ScrabbleWord other){
    return (new Integer(score).compareTo(new Integer(other.score)))*-1;
  }

  public String toString(){
    return (word + ": " + score);
  }
}
