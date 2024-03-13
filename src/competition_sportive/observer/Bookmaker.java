package competition_sportive.observer;

import competition_sportive.match.*;
import competition_sportive.competitor.*;
import competition_sportive.competition.*;
import java.util.*;

/**
 * Class for Bookmaker of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 20/11/2020
 */
public class Bookmaker implements CompetitionObserver {

    /** the register of the bookmaker */
    protected Map<Competitor,Integer> cotes = new HashMap<Competitor, Integer>();
    /** the name of the bookmaker */
    protected String name;

    /** Construct the bookmaker
     * @param name the name of the bookmaker
     */
    public Bookmaker(String name) {
      this.name = name;
    }

    /**
     * Construct the bookmaker
     */
    public Bookmaker() {
      this("Patrick");
    }

    /**
  	 * toString method of the match
  	 * @return the String representation of this match
  	 */
  	public String toString() {
     return this.name;
   }

    /**
     * Reaction of this Observer in front of a match
     * @param m the match to react
     */
    public void reactToMatch(Match m) {
      Competitor winner = m.getWinner();
      Competitor looser = m.getLooser();
      int coteW = this.getValueOf(winner);
      int coteL = this.getValueOf(looser);
      int newCoteW = coteW - 1;
      int newCoteL = coteL + 1;
      this.putValueInBook(winner,newCoteW);
      this.putValueInBook(looser,newCoteL);
      System.out.println("B : Victoire de "+winner+" (cote "+coteW+") face a "+looser+" (cote "+coteL+"). La cote de "+winner+" passe a "+newCoteW+", celle de "+looser+" a "+newCoteL+".");
    }


    /**
     * Returns the value for a competitor
     * @param competitor the competitor to check the value
     * @return the value of the competitor
     */
    public int getValueOf(Competitor competitor) {
      if (this.cotes.containsKey(competitor)) return this.cotes.get(competitor);
      else return 0;
    }

    /**
     * Put a value to the competitor in the book of this bookmaker
     * @param competitor Competitor to append in the book
     * @param v the value to put for the competitor
     */
     protected void putValueInBook(Competitor competitor,int v) {
       this.cotes.put(competitor,v);
     }

    /**
     * Reaction of this Observer in front of a starting competition
     */
    public void reactToStartCompet() {
      System.out.println("B : Bonjour tout le monde, je suis "+this+", je suis bookmaker.");
    }

    /**
     * Reaction of this Observer in front of a ending competition
     * @param c the competition
     */
    public void reactToFinishCompet(Competition c) {
      System.out.println("B : Voici les cotes :");
      System.out.println("");
      for( Map.Entry<Competitor, Integer> entry : this.cotes.entrySet()) System.out.println(entry.getKey()+" : "+entry.getValue());
      System.out.println("");
    }


}
