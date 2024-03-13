package competition_sportive.competition;

import java.util.*;
import util.MapUtil;
import competition_sportive.master_filters.*;
import competition_sportive.match.*;
import competition_sportive.competitor.*;
import competition_sportive.observer.*;
import competition_sportive.exceptions.*;


/**
 * Class for Master of the COO Project v2
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class Master extends Competition {

  protected int nbgroups;
  protected int membersByGroups;
  protected Filter filter;
  protected List<Competition> roundOne;
  protected Competition roundTwo;


  public Master(List<Competitor> competitors,int nbgroups,int membersByGroups, Filter filter){
		super(competitors);
    this.nbgroups = nbgroups;
    this.membersByGroups = membersByGroups;
    this.filter = filter;
    this.roundOne = new ArrayList<Competition>();
    this.roundTwo = null;
	}

  /**
   * Get the number of groups of this master
   * @return the number of groups of this master
   */
  public int getNbGroups() {
    return this.nbgroups;
  }

  /**
   * Get the number of member per groups of this master
   * @return the number of member per groups of this master
   */
  public int getNbMembersByGroups() {
    return this.membersByGroups;
  }

   /**
    * Get the filter of the master
    * @return the filter of the master
    */
    public Filter getFilter() {
      return this.filter;
    }

    /**
     * Set the filter of the master
     * @param filter the filter to set
     */
     public void setFilter(Filter filter) {
       this.filter = filter;
     }

  /**
	 * Checks if the number of competitors of this competition is correct or not
	 * @return if the number of competitors of this competition is correct or not
	 */
	protected boolean checkNbCompetitor() {
		return (this.getNbCompetitor() == this.nbgroups * this.membersByGroups) && this.filter.maySelect(this.nbgroups,this.membersByGroups);
	}

    /**
  	 * Plays the matches of this Master
  	 */
  	protected void playMatches() { this.roundTwo( this.roundOne() ); }

    /**
     * Plays the first round
     * @return the list of the competitors selected at the end of the first round
     */
    protected List<Competitor> roundOne() {
      this.createChampionnats();
      displayRoundOne();
      List<Map<Competitor, Integer>> rankings = this.getChampionnatsRankings();
      List<Competitor> selection = this.filter.select(rankings);
      return selection;

    }


    /**
     * Create the Leagues with the list of Competitors (it creates the matches of the first round)
     */
     protected void createChampionnats() {
       for(int i = 0; i != this.nbgroups*this.membersByGroups; i+= this.membersByGroups){
         this.roundOne.add(this.createChampionnat( this.competitors.subList( i , i+this.membersByGroups ) ) );
        }
     }

     /**
      * Create a League with a list of Competitors (it creates the matches group by group )
      * @param competitors the list of competitors
      * @return a league for the master
      */
      protected Competition createChampionnat(List<Competitor> competitors) {
        Competition c = new Championnat(competitors);
        for(CompetitionObserver o : this.observers) c.register(o);
        return c;
      }

    /**
     * Displays the round one
     */
    protected void displayRoundOne(){
      displayTitle("Round one");
      int i = 1;
      for(Competition c : this.roundOne) {
        System.out.println("");
        displaySubTitle("League "+i);
        try {
          c.play();
        } catch (Exception GameAlreadyFinishException) {
          System.out.println("erreur");
        }
        i++;
      }
    }

    /**
      * Get the list of the rankings of the leagues of the first round
      * @return the list of the rankings of the leagues of the first round
      */
     protected List<Map<Competitor, Integer>> getChampionnatsRankings() {
       List<Map<Competitor, Integer>> rankings = new ArrayList<Map<Competitor, Integer>>();
       for(Competition c : this.roundOne) rankings.add(c.ranking());
       return rankings;
     }

    /**
     * Play the second round with the competitors selected
     * @param list the list of the selected competitors
     */
    protected void roundTwo(List<Competitor> list) {

      displaySelection(list);
      createRoundTwo(list);
      displayRoundTwo();

    }

    /**
     * Create the Competition for the round two
     * @param list the list of the competitors selected for the round two
     */
    protected void createRoundTwo(List<Competitor> list) {
      Competition t = new Tournoi(list);
      for(CompetitionObserver o : this.observers) t.register(o);
      this.roundTwo = t;
    }


    /**
     * Displays a string in a title format
     * @param title the string to sisplay in a title format
     */
    public static void displayTitle(String title) {
      System.out.println("");
      System.out.println(repeat("*",title.length()+4));
      System.out.println("* ".concat(title).concat(" *"));
      System.out.println(repeat("*",title.length()+4) );
      System.out.println("");
    }

    /**
     * Displays a string in a subtitle format
     * @param title the string to sisplay in a subtitle format
     */
    public static void displaySubTitle(String title) {
      System.out.println(title);
      System.out.println(repeat("*",title.length()) );
      System.out.println("");
    }

    /**
     * Returns n times the string s
     * @param s the string to repeat
     * @param n the number of repeat
     * @return the string repeated n times (so it returns a string)
     */
    public static String repeat(String s, int n) {
      return String.join("", Collections.nCopies(n , s) );
    }

    /**
     * Display the selection
     * @param selection the selection
     */
    public void displaySelection(List<Competitor> selection) {
      displayTitle("Selection");
      displaySubTitle("Here is the list of the selected competitors :");
      for(Competitor c : selection) System.out.println(c);
    }

    /**
     * Display the round two
     */
    protected void displayRoundTwo() {
      displayTitle("Round two");
      try {
        this.roundTwo.play();
      }
      catch (Exception GameAlreadyFinishException) {
        System.out.println("erreur round2");
      }
    }

  /**
	 * Returns true if this Competition is finished, returns false otherwise
	 * @return true if this Competition is finished, returns false otherwise
	 */
	public boolean isFinish() {
    if( this.roundTwo != null){
      List<Competition> comp = this.roundOne;
      comp.add(this.roundTwo);
		  for(Competition c : comp ) if(!c.isFinish() ) return false;
		  return true;
    }
    else return false;
	}

  /**
	 * Returns the map with the competitors ranked by descending value
	 * @return the map with the competitors ranked by descending value
	 */
	public Map<Competitor, Integer> ranking(){
			return this.roundTwo.ranking();
	}

  /**
	 * function for playing this Competition
	 * @throws competition_sportive.exceptions.GameAlreadyFinishException when the competition is already finished
	 */
	public void play() throws GameAlreadyFinishException {
		if(this.checkNbCompetitor() && !this.isFinish()) {
			this.playMatches();
			this.displayCompetition();
		}
		else if(this.isFinish()) throw new GameAlreadyFinishException();
	}

}
