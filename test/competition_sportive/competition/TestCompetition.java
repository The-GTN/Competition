package competition_sportive.competition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import competition_sportive.match.*;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.*;

public abstract class TestCompetition {

  protected Competition competition;
  protected List<Match> match;
  protected List<Competitor> competitors;

	@Before
	public void init() {
    this.match = this.createMatch();
    this.competitors = this.createCompetitors();
    this.competition = this.createCompetition();
	}

  protected List<Match> createMatch() {
    return new ArrayList<Match>();
  }

  protected List<Competitor> createCompetitors() {
    List<Competitor> competitors = new ArrayList<Competitor>();
    competitors.add(new Competitor("Jojo"));
    competitors.add(new Competitor("Dio"));
    return competitors;
  }

	protected abstract Competition createCompetition();


  @Test
  public void testGetMatch() {
	assertEquals(this.match,this.competition.getMatch());
  }

  @Test
  public void testGetCompetitors() {
    assertEquals(this.competitors,this.competition.getCompetitors());
  }

  @Test
  public void testGetNbCompetitors() {
    assertEquals(2,this.competition.getNbCompetitor());
  }


  @Test(expected=GameAlreadyFinishException.class)
  public void testIfYouPlayThenThePlayWillFinish() throws GameAlreadyFinishException {
    assertFalse(this.competition.isFinish());
    this.competition.play();
    assertTrue(this.competition.isFinish());
    this.competition.play();
  }



}
