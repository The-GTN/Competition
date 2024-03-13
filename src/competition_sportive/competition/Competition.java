package competition_sportive.competition;

import competition_sportive.match.*;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;
import competition_sportive.observer.*;
import java.util.*;
import util.MapUtil;
/**
 * Class for Competition of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public abstract class Competition{

	/** list of the competitors of this competition */
	protected List<Competitor> competitors;
	/** List of the matches of this competition */
	protected List<Match> match;
	/** Set of the Observers of the competition */
	protected Collection<CompetitionObserver> observers = new HashSet<CompetitionObserver>();

	/**
	 * Constructor for Competition
	 * @param competitors the list of the competitors
	 */
	public Competition(List<Competitor> competitors){
		this.competitors = competitors;
		this.match = new ArrayList<Match>();
	}

	/**
	 * Returns the map with the competitors ranked by descending value
	 * @return the map with the competitors ranked by descending value
	 */
	public Map<Competitor, Integer> ranking(){
			HashMap<Competitor, Integer> map = new HashMap<Competitor, Integer>();
			List<Match> matches = this.getMatch();
			for(Match match : matches){
				if(match.matchPlayed()){
					Competitor looser = match.getLooser();
					point(looser,map, false);

					Competitor winner = match.getWinner();
					point(winner,map, true);
				}
			}
			return  MapUtil.sortByDescendingValue(map);
	}


	/**
	 * Affects the point to the competitor, according if they're the winner or the looser
	 * @param c the competitor we affect points
	 * @param map the map which represents the competitors with their points
	 * @param iswinner true if the competitor is the winner, false otherwise
	 */
	private void point(Competitor c, HashMap<Competitor, Integer> map, boolean iswinner){
		int pts;
		if(map.containsKey(c)){pts = map.get(c);}
		else {pts = 0;}

		if(iswinner){map.put(c, this.givePointWinner(pts));}
		else{map.put(c, this.givePointLooser(pts));}
	}

	/**
	 * Returns the new points of a competitor who has won
	 * @param pts the points before the win
	 * @return the new points of a competitor who has won
 	 */
	protected int givePointWinner(int pts){
		return pts + 1;
	}


	/**
	 * Returns the new points of a competitor who has lost
	 * @param pts the points before the loose
	 * @return the new points of a competitor who has lost
 	 */
	protected int givePointLooser(int pts){
		return pts;
	}

	/**
	 * function for playing this Competition
	 * @throws competition_sportive.exceptions.GameAlreadyFinishException when the competition is already finished
	 */
	public void play() throws GameAlreadyFinishException {
		if(this.checkNbCompetitor() && !this.isFinish()) {
			this.displayBegin();
			this.playMatches();
			this.displayCompetition();
			this.displayEnd();
		}
		else if(this.isFinish()) throw new GameAlreadyFinishException();
	}

	/**
	 * Returns the number of competitors of this Competition
	 * @return the number of competitors of this Competition
	 */
	public int getNbCompetitor(){
		return this.competitors.size();
	}

	/**
	 * Checks if the number of competitors of this competition is correct or not
	 * @return if the number of competitors of this competition is correct or not
	 */
	protected abstract boolean checkNbCompetitor();

	/**
	 * Returns the list of the competitors of this Competition
	 * @return the list of the competitors of this Competition
	 */
	public List<Competitor> getCompetitors(){
		return this.competitors;
	}

	/**
	 * Returns the list of the match of this competition
	 * @return the list of the match of this competition
	 */
	public List<Match> getMatch(){
		return this.match;
	}

	/**
	 * Returns true if this Competition is finished, returns false otherwise
	 * @return true if this Competition is finished, returns false otherwise
	 */
	public boolean isFinish() {
		List<Match> matches = this.getMatch();
		for(Match match : matches) if(!match.matchPlayed()) return false;
		return matches.size() != 0;
	}

	/**
	 * Displays the Competition
	 */
	public void displayCompetition(){
		this.displayMatches();
	}

	/**
	 * Displays the matches of this Competition
	 */
	public void displayMatches() {
		for(Match match : this.match) this.displayMatch(match);
	}

	/**
	 * Plays the matches of this Competition
	 */
	protected abstract void playMatches();

	/**
	 * Returns a match between the two competitors c1 and c2
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 * @return the Match between them
	 */
	protected Match createMatch(Competitor c1, Competitor c2) {
		return new RandomMatch(c1,c2);
	}

	/**
	 * Add the observer in the set of the observers of this Competition
	 * @param observer the observer to add
	 */
	public void register(CompetitionObserver observer) {
		this.observers.add(observer);
	}

	/**
	 * Remove the observer from the set of the observers of this Competition
	 * @param observer the observer to remove
	 */
	public void unRegister(CompetitionObserver observer) {
		this.observers.remove(observer);
	}

	/**
	 * Display a match
	 * @param m the match
	 */
	public void displayMatch(Match m) {
		for(CompetitionObserver o : this.observers) o.reactToMatch(m);
		System.out.println("");
	}

	/**
	 * Display the begin of the competition
	 */
	public void displayBegin() {
		for(CompetitionObserver o : this.observers) o.reactToStartCompet();
		System.out.println("");
	}

	/**
	 * Display the end of the competition
	 */
	public void displayEnd() {
		for(CompetitionObserver o : this.observers) o.reactToFinishCompet(this);
		System.out.println("");
	}

}
