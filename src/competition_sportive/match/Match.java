package competition_sportive.match;

import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;
/**
 * Class for Match of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public abstract class Match {

	protected Competitor c1;
	protected Competitor c2;
	protected Competitor winner;

	/**
	 * Constructor for Match
	 * @param c1 the first competitor who play
	 * @param c2 the second competitor who play
	 */
	public Match(Competitor c1, Competitor c2){
		this.c1 = c1;
		this.c2 = c2;
		this.winner = null;
	}

	/**
	 * Returns the winner of this Match
	 * @return the winner this Match
	 */
	public Competitor getWinner() {
		return this.winner;
	}

	/**
	 * Returns the looser of this Match
	 * @return the looser of this match
	 */
	public Competitor getLooser() {
		if(this.winner.equals(this.c1)) return this.c2;
		else return this.c1;
	}

	/**
	 * Play the match and returns the winner
	 * @return the winner of this Match
	 */
	protected abstract Competitor play();

	/**
	 * Launch the play the match and returns the winner
	 * @return the winner of the match
	 * @throws NoFightClubException if a competitor fight himself
	 * @throws CompetitorNullException if there a null competitor
	 */
	public Competitor playMatch() throws NoFightClubException,CompetitorNullException {
		testCompetitor();
		Competitor win = this.play();
		this.winner = win;
		return win;
	}


	/**
	 * Returns true if this match has been played, false otherwise
	 * @return true if this match has been played, false otherwise
	 */
	public boolean matchPlayed() {
		return this.winner != null;
	}

	/**
	 * Checks if the competitors are valids
	 * @throws NoFightClubException if there two times the same competitor
	 * @throws CompetitorNullException if there one null competitor
	 */
	private void testCompetitor() throws NoFightClubException,CompetitorNullException{
		if( (this.c1 == null) || (this.c2 == null) ) {
			throw new CompetitorNullException();
		}
		if(this.c1.equals(this.c2)) {
			throw new NoFightClubException();
		}
	}

	/**
	 * toString method of the match
	 * @return the String representation of this match
	 */
	public String toString() {
		if(this.matchPlayed()) {
			return this.c1.toString()+" vs "+this.c2.toString()+" --> "+this.winner.toString()+" wins!";
		}
		else {
			return this.c1.toString()+" vs "+this.c2.toString();
		}
	}

}
