package competition_sportive.competition;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import competition_sportive.master_filters.*;
import java.util.*;
import competition_sportive.competitor.*;

public class TestMaster extends TestCompetition {

	protected Competition createCompetition() {
		return new Master(this.competitors,2,2,new FirstFilter());
	}

	protected List<Competitor> createCompetitors() {
    List<Competitor> competitors = new ArrayList<Competitor>();
    competitors.add(new Competitor("Jojo"));
    competitors.add(new Competitor("Dio"));
		competitors.add(new Competitor("Alex"));
		competitors.add(new Competitor("Terieur"));
    return competitors;
  }

	@Test
  public void testGetNbCompetitors() {
    assertEquals(4,this.competition.getNbCompetitor());
  }

  // ---Pour permettre l'execution des tests ----------------------
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(competition_sportive.competition.TestMaster.class);
	}

}
