package competition_sportive.match;

import competition_sportive.competitor.Competitor;

/**
 * Class for MockMatch of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class MockMatch extends Match {

	/**
	 * Constructor for MockMatch
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 */
	public MockMatch(Competitor c1, Competitor c2){
		super(c1,c2);
	}


	/**
	 * Return the winner of this match
	 * @return the winner of this match
	 */
	protected Competitor play(){
		return this.c1;
	}

}
