package competition_sportive.competition;

import competition_sportive.match.*;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;
import java.util.*;
/**
 * Class for League of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class Championnat extends Competition {

	/**
	 * Constructor for Chamionnat
	 * @param competitors the competitors of this Championnat
	 */
	public Championnat(List<Competitor> competitors){
		super(competitors);
	}

	/**
	 * Plays the matches of this Competition
	 */
	protected void playMatches() {
		try {
			this.createMatches();
			for(Match match : this.match) {
				match.playMatch();
			}
		}
		catch(NoFightClubException e) {
			System.out.println("Fight Club problem");
		}
		catch(CompetitorNullException e) {
			System.out.println("null problem");
		}
	}

	/**
	 * Creates the matches of this Competitions according the list of Competitors
	 */
	private void createMatches() {
		for(Competitor competitor : this.competitors)
			for(Competitor opponent : this.competitors)
				if(!competitor.equals(opponent)) this.match.add(this.createMatch(competitor,opponent));
	}

	/**
	 * Checks if the number of competitors of this competition is correct or not
	 * @return if the number of competitors of this competition is correct or not
	 */
	protected boolean checkNbCompetitor() {
		return true;
	}

}
