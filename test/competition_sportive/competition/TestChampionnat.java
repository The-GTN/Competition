package competition_sportive.competition;

public class TestChampionnat extends TestCompetition {

	protected Competition createCompetition() {
		return new Championnat(this.competitors);
	}

  // ---Pour permettre l'execution des tests ----------------------
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(competition_sportive.competition.TestChampionnat.class);
	}

}
