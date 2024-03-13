package competition_sportive.exceptions;

public class GameAlreadyFinishException extends Exception{

	public GameAlreadyFinishException() {
	    super("You can't continue the competition, it's already finish");
	  }
}
