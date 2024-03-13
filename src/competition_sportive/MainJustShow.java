package competition_sportive;

import java.util.*;
import io.Input;
import competition_sportive.competition.*;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;
import competition_sportive.master_filters.*;
import competition_sportive.observer.*;

/**
 * MainJustShow class for the COO Project
 *
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class MainJustShow extends Main {

  /**
   * Main function
   * @param args the parameters entered
   */
  public static void main(String[] args) {

    action();

  }

  /**
   * Launch the main with the parameters verified
   * @param args the parameters verified
   */
  protected static void action() {
    List<Competitor> competitors = createCompetitors();
    CompetitionObserver j = new Journalist();
    CompetitionObserver b = new Bookmaker();
    Competition compet = new Championnat(competitors);
    compet.register(j);
    compet.register(b);
    Competition compet2 = new Tournoi(competitors);
    compet2.register(j);
    compet2.register(b);
    Competition compet3 = new Master(competitors,2,4,new TwoFirstFilter());
    compet3.register(j);
    compet3.register(b);
    try {
      write("Example of League :");
      write("*******************");
      passLine();
      compet.play();
      passLine();
      write("Example of Tournament :");
      write("***********************");
      passLine();
      compet2.play();
      passLine();
      write("Example of Master :");
      write("*******************");
      passLine();
      compet3.play();
      passLine();
    }
    catch(Exception e) {
      write(e.getMessage());
      passLine();
    }
  }

  /**
   * Creates the competitors for the Competition of this Main, according to the number expected of competitors
   * @return a list of competitor
   */
  private static List<Competitor> createCompetitors() {
    List<Competitor> competitors = new ArrayList<Competitor>();
    competitors.add(new Competitor("Jean-Christophe"));
    competitors.add(new Competitor("Marie Emilie"));
    competitors.add(new Competitor("Jean-Stephane"));
    competitors.add(new Competitor("Leopold"));
    competitors.add(new Competitor("Eric"));
    competitors.add(new Competitor("Benoit"));
    competitors.add(new Competitor("Clement"));
    competitors.add(new Competitor("Damien"));
    return competitors;
  }

}
