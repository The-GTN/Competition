package competition_sportive.competition;

import competition_sportive.match.*;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;
import java.util.*;

/**
 * Class for Tournament of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class Tournoi extends Competition{

	public Tournoi(List<Competitor> competitors){
		super(competitors);
	}

	/**
	 * Plays the matches of this Competition
	 */
	protected void playMatches() {
		try {
			List<Competitor> lc = this.competitors;
			playRounds(lc);
		}
		catch(NoFightClubException e) {
			System.out.println("Fight Club problem");
		}
		catch(CompetitorNullException e) {
			System.out.println("null problem");
		}
	}

	/**
	 * Plays the rounds of this Tournament
	 * @param list the list of the competitors
	 */
	private void playRounds(List<Competitor> list) throws NoFightClubException, CompetitorNullException {
		if(list.size() != 1){
			List<Competitor> winners = new ArrayList<Competitor>();
			List<Match> matches = createMatches(list);
			for(Match match : matches) {
				match.playMatch();
				winners.add(match.getWinner());
			}
			this.match.addAll(matches);
			playRounds(winners);
		}
	}

	/**
	 * Creates the matches of this Competition according the list of Competitors
	 * @param competitors the list of Competitors
	 * @return the matches of this Competition
	 */
	protected List<Match> createMatches(List<Competitor> competitors) {
		List<Match> list = new ArrayList<Match>();

		for(int i = 0; i < competitors.size(); i+=2){
			Competitor c1 = competitors.get(i);
			Competitor c2 = competitors.get(i+1);
			list.add(this.createMatch(c1,c2));
		}
		return list;
	}

	/**
	 * Checks if the number of competitors of this competition is correct or not
	 * @return if the number of competitors of this competition is correct or not
	 */
	protected boolean checkNbCompetitor() {
		int nb = this.getNbCompetitor();
		return (nb != 0) && ((nb & (nb - 1)) == 0); // on regarde si nb est une puissance de 2
	}

}
