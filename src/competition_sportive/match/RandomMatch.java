package competition_sportive.match;

import competition_sportive.competitor.Competitor;
import java.util.Random;
/**
 * Class for RandomMatch of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class RandomMatch extends Match {

	public static final Random ALEA = new Random();

	/**
	 * Constructor for RandomMatch
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 */
	public RandomMatch(Competitor c1, Competitor c2){
		super(c1,c2);
	}

	/**
	 * Return the winner of this match
	 * @return the winner of this match
	 */
	protected Competitor play() {
		int p = ALEA.nextInt(2);
		if(p == 0) {
			return this.c1;
		}
		else {
			return this.c2;
		}
	}

}
