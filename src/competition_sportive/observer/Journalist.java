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
 public class Journalist implements CompetitionObserver {

   protected String name;

   /**
    * Construct a Journalist named Bob
    */
   public Journalist() {
     this("Bob");
   }

   /**
    * Construct a Journalist named name
    * @param name the name to give
    */
   public Journalist(String name) {
     this.name = name;
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
     System.out.println("J : C'est la victoire de "+m.getWinner()+" contre "+m.getLooser()+" !!");
   }

   /**
    * Reaction of this Observer in front of a starting competition
    */
   public void reactToStartCompet() {
     System.out.println("J : Bonjour tout le monde, je suis "+this+", je suis journaliste.");
   }

   /**
    * Reaction of this Observer in front of a ending competition
    * @param c the competition
    */
   public void reactToFinishCompet(Competition c) {
     this.displayRanking(c);
   }

   /**
	  * Make and Display the ranking of this Competition
    * @param c the competition
	  */
	 public void displayRanking(Competition c){
     Map<Competitor, Integer> rank = c.ranking();
     this.displayMapRank(rank);
   }

   /**
	  * Display the ranking
	  * @param map the map representing the competitors with their points
	  */
	 public void displayMapRank(Map<Competitor, Integer> map) {
    System.out.println("J : Voici le classement :");
		System.out.println("");
		System.out.println("*** Ranking ***");
		for( Map.Entry<Competitor, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey().toString()+" - "+entry.getValue().toString()+" point(s).");
		}
    System.out.println("");
  }

 }
