package competition_sportive.match;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import competition_sportive.competitor.Competitor;
import competition_sportive.exceptions.CompetitorNullException;
import competition_sportive.exceptions.NoFightClubException;

public class TestRandomMatch extends TestMatch {

  protected Match createMatch(Competitor c1, Competitor c2) {
    return new RandomMatch(c1,c2);
  }

  @Test
  public void testWinner() throws NoFightClubException, CompetitorNullException {
    assertTrue(oneCompetitorWin());
  }

  private boolean oneCompetitorWin() throws NoFightClubException, CompetitorNullException {
    Competitor winner = this.match.playMatch();
    return winner.equals(this.c1) || winner.equals(this.c2);
  }

  // ---Pour permettre l'execution des tests ----------------------
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(competition_sportive.match.TestRandomMatch.class);
	}

}
