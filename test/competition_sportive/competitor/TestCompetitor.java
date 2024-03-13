package competition_sportive.competitor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestCompetitor {

  protected Competitor c1;
  protected Competitor c2;
  protected Competitor c3;

  @Before
	public void init() {
    this.c1 = this.createCompetitor("1");
    this.c2 = this.createCompetitor("2");
    Competitor.clearId();
    this.c3 = this.createCompetitor("1");
    Competitor.clearId();
	}

  protected Competitor createCompetitor(String name) {
    return new Competitor(name);
    
  }

  @Test
  public void testHashCode() {
    assertEquals(1,this.c1.hashCode());
    assertEquals(2,this.c2.hashCode());
    
  }

  @Test
  public void testEquals() {
    assertEquals(this.c1,this.c1);
    assertEquals(this.c1.getId(),this.c3.getId());
    assertFalse(this.c1.equals(this.c2)); //assertNotEquals n'existe pas
    assertTrue(this.c1.equals(this.c3));
    assertFalse(this.c2.equals(2));
  }


  @Test
  public void testGetId() {
    assertEquals(1,this.c1.getId());
    assertEquals(2,this.c2.getId());
    assertEquals(1,this.c3.getId());
  }



  // ---Pour permettre l'execution des tests ----------------------
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(competition_sportive.competitor.TestCompetitor.class);
	}

}
