package competition_sportive;

import java.util.*;
import io.Input;
import competition_sportive.competition.*;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;
import competition_sportive.master_filters.*;
import competition_sportive.observer.*;


/**
 * Main class for the COO Project
 *
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class Main {

  /**
   * Main function
   * @param args the parameters entered
   */
  public static void main(String[] args) {

    if (notCorrectParameters(args)) errorMessage();

    else action(args);

  }

  /**
   * Boolean to check if the parameters are correct or not
   * @param args the parameters to check
   * @return true if they're not correct, false otherwise
   */
  protected static boolean notCorrectParameters(String[] args) {
    try {
      if (NotGoodCompetition(args[0])) return true;
      else if (NotGoodNumberOfCompetitor(args[0],toInt(args[1]))) return true;
      else if (NotCorrectMaster(args)) return true;
      else return false;
    }
    catch(Exception e) {
      return true;
    }
  }

  /**
   * Boolean to check if the competition is a good one
   * @param args the competition name to check
   * @return true if the competition is a good one, false otherwise
   */
  private static boolean NotGoodCompetition(String competition) {
    return !(competition.equals("l") || competition.equals("t") || competition.equals("m") );
  }

  /**
   * Boolean to check if the number of competitors is correct or not
   * @param args the number of competitors to check
   * @return true if the number of competitors is correc, false otherwise
   */
  private static boolean NotGoodNumberOfCompetitor(String competition, int nb) {
    if(competition.equals("t")) return !((nb != 0) && ((nb & (nb - 1)) == 0));
    else return nb <= 1;
  }

  /**
   * Display the good way to use the simulation
   */
  private static void errorMessage() {
    passLine();
    write("run with make competition args=\"c n g m\"");
    write("where:");
    write("c            = the type of Competition you use (here \"t\" for Tournament or \"l\" for League or \"m\" for Master)");
    write("n            = the number of Competitors who participate in the Competition (a power of 2 if you selected the Tournament)");
    write("g [if c = m] = the number of groups (for Master)");
    write("m [if c = m] = the number of members per groups (for Master)");
    write("warning for using Master:");
    write("n = g*m and there at least one valid filter for this numbers n g and m");
    write("try:");
    write("make competition args=\"l 3\"");
    write("or");
    write("make competition args=\"t 4\"");
    write("or");
    write("make competition args=\"m 8 2 4\"");
  }

  /**
   * Launch the main with the parameters verified
   * @param args the parameters verified
   */
  protected static void action(String[] args) {
    int num = toInt(args[1]);
    List<Competitor> competitors = createCompetitors(num);
    passLine();
    Competition compet = createCompetition(args,competitors);
    try {
      CompetitionObserver j = new Journalist();
      CompetitionObserver b = new Bookmaker();
      compet.register(j);
      compet.register(b);
      compet.play();
    }
    catch(Exception e) {
      write(e.getMessage());
      passLine();
    }
  }

  /**
   * Creates the competitors for the Competition of this Main, according to the number expected of competitors
   * @param n the number of competitor
   * @return a list of competitor
   */
  private static List<Competitor> createCompetitors(int n) {
    List<Competitor> competitors = new ArrayList<Competitor>();
    String name;
    passLine();
    for(int i = 0; i!= n; i++) {
      write("What's your name, Competitor "+(i+1)+" ?");
      name = userwrite();
      competitors.add(new Competitor(name));
    }
    Collections.shuffle(competitors);
    return competitors;
  }

  /**
   * Creates the competition, according the type of competition and the competitors
   * @param compet the type of the competition
   * @param competitors the competitors we use to create the competition
   * @return the competition that we'll use in this main
   */
  private static Competition createCompetition(String[] args, List<Competitor> competitors) {
    if (args[0].equals("t")) return new Tournoi(competitors);
    else if (args[0].equals("l")) return new Championnat(competitors);
    else return createMaster(competitors, toInt(args[2]), toInt(args[3]));
  }


  /**
   * Returns if the parameters are correct for a master
   * @param args the parameters
   * @return if the parameters are correct for a master
   */
   private static boolean NotCorrectMaster(String[] args){
     if (!args[0].equals("m")) return false;
     else if (args.length != 4) return true;
     else if (toInt(args[2]) <= 0 || toInt(args[3]) <= 0 ) return true;
     else if (noValidFilter(toInt(args[2]),toInt(args[3]))) return true;
     else return toInt(args[1]) != toInt(args[2]) * toInt(args[3]);
   }

   /**
    * Returns if there no valid filter for a entered number of groups and members per groups
    * @param nbgroups the number of groups
    * @param nbmembers the number of members per groups
    * @return true if there no valid filter, false otherwise
    */
    private static boolean noValidFilter(int nbgroups, int nbmembers) {
      Filter[] filters = {new FirstFilter(), new TwoFirstFilter(), new LastFilter()};
      for(Filter f : filters) if(f.maySelect(nbgroups,nbmembers)) return false;
      return true;
    }

   /**
    * Creates a Master with given parameters
    * @param competitors the list of competitors we use to creates this master
    * @param nbgroups the number of groups
    * @param nbmembers the number of members per groups
    * @return the master with the given parameters
    */
    private static Master createMaster(List<Competitor> competitors, int nbgroups, int nbmembers) {
      write("");
      write("For the Master, we have to know how to select the competitors from the round one to the round two.");
      write("Please, select a way to make this selection :");
      write("");
      write("1 : Select the first of each groups");
      write("");
      write("2 : Select the two first per groups");
      write("");
      write("3 : Select the last per groups");
      write("");
      write("4 : Quit");
      write("");
      write("What do you choose ?");
      Filter filter = chooseFilter();
      if(filter != null) while(filter!=null && (!filter.maySelect(nbgroups,nbmembers))) {
        write("The filter selected doesn't work with given list of competitors, please select an other option.");
        filter = chooseFilter();
      }
      return new Master(competitors,nbgroups,nbmembers,filter);
    }

    /** Returns the filter selected
     * @return the filter selected
     */
    private static Filter chooseFilter() {
      String f = chooseOptionFilter();
      if(f.equals("1")) return new FirstFilter();
      else if(f.equals("2")) return new TwoFirstFilter();
      else if(f.equals("3")) return new LastFilter();
      else return null;
    }

    /** Select a good option
     * @return the option selected
     */
    private static String chooseOptionFilter() {
      String[] options = {"1","2","3","4"};
      String f = Input.readString();
      while(!Arrays.toString(options).contains(f)){
        write("Please select a good option !");
        f = userwrite();
      }
      return f;
    }

    /**
     * Writes a text in the console
     * @param text the text to Write
     */
     protected static void write(String text) {
       System.out.println(text);
     }

     /**
      * Pass a line
      */
      protected static void passLine() {
        write("");
      }

      /**
       * Returns the int value of the entered string
       * @param str the string we want to know the int value
       * @return the int value
     */
       protected static int toInt(String str) {
         return Integer.parseInt(str);
       }

       /**
        * Returns the string written by the user
        * @return the string written by the user
        */
        protected static String userwrite() {
          return Input.readString();
        }


}
