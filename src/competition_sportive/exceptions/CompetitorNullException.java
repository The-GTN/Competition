package competition_sportive.exceptions;

public class CompetitorNullException extends Exception{
	
	public CompetitorNullException() {
	    super("One or both player are null");
	  }
}
