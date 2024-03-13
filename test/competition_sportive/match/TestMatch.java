package competition_sportive.match;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import competition_sportive.competitor.*;
import competition_sportive.exceptions.CompetitorNullException;
import competition_sportive.exceptions.NoFightClubException;

public abstract class TestMatch {

  protected Match match;
  protected Competitor c1;
  protected Competitor c2;
  protected static int cmpt = -1;

  @Before
  public void init() {
	  this.c1 = this.createCompetitor();
	  this.c2 = this.createCompetitor();
	  this.match = this.createMatch(this.c1,this.c2);
  }

  protected abstract Match createMatch(Competitor c1, Competitor c2);
  
  protected Competitor createCompetitor() {
	cmpt += 1;
    return new Competitor(Integer.toString(cmpt));
  }
  
  @Test
  public void testPlayMatch() throws NoFightClubException, CompetitorNullException  {
	  this.match.playMatch();
	  assertTrue(match.matchPlayed()==true);
  }
  
  
  // match va changer suivant comment on fait
  @Test(expected=NoFightClubException.class)
  public void testFightAgainstHimself() throws NoFightClubException, CompetitorNullException {
	  Match match = this.createMatch(this.c1, this.c1);
	  match.playMatch();
  }
  
  @Test(expected=CompetitorNullException.class)
  public void testFightAgainsTwoNull() throws NoFightClubException, CompetitorNullException {
	  Match match = this.createMatch(null, null);
	  match.playMatch();
  }
  
  @Test(expected=CompetitorNullException.class)
  public void testFightAgainsFisrtNull() throws NoFightClubException, CompetitorNullException {
	  Match match = this.createMatch(null, this.c2);
	  match.playMatch();
  }
  
  @Test(expected=CompetitorNullException.class)
  public void testFightAgainsSecondNull() throws NoFightClubException, CompetitorNullException {
	  Match match = this.createMatch(this.c1, null);
	  match.playMatch();
  }

}
