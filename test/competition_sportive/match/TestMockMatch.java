package competition_sportive.match;

import org.junit.Test;

import competition_sportive.competitor.Competitor;


public class TestMockMatch extends TestMatch {

  protected Match createMatch(Competitor c1, Competitor c2) {
    return new MockMatch(c1,c2);
  }


  // ---Pour permettre l'execution des tests ----------------------
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(competition_sportive.match.TestMockMatch.class);
	}

}
