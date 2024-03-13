package competition_sportive.exceptions;

public class NoFightClubException extends Exception {

  public NoFightClubException() {
    super("You can't match yourself, you're not in Fight Club !");
  }

}
