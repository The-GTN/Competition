package competition_sportive.competition;

public class TestTournoi extends TestCompetition {

	protected Competition createCompetition() {
		return new Tournoi(this.competitors);
	}


  // ---Pour permettre l'execution des tests ----------------------
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(competition_sportive.competition.TestTournoi.class);
	}

}
