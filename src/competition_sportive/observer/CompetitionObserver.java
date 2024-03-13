package competition_sportive.observer;

import competition_sportive.match.Match;
import competition_sportive.competition.Competition;

/**
 * Interface for Observer of Competition of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 20/11/2020
 */

public interface CompetitionObserver {

  /**
   * Reaction of this Observer in front of a match
   * @param m the match to react
   */
  public void reactToMatch(Match m);

  /**
   * Reaction of this Observer in front of a starting competition
   */
  public void reactToStartCompet();

  /**
   * Reaction of this Observer in front of a ending competition
   * @param c the competition
   */
  public void reactToFinishCompet(Competition c);

}
