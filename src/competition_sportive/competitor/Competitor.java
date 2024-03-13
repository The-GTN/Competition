package competition_sportive.competitor;

/**
 * Class for Competitor of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
public class Competitor {

	private String name;
	private int id;
	private static int cmpt = 1;

	/**
	 * Construtor for Competitor
	 * @param name the name of this Competitor
	 */
	public Competitor(String name){
		this.name = name;
		this.id = cmpt;
		cmpt += 1;
	}

	/**
	 * Returns the id of this Cometitor
	 * @return the id of this Cometitor
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Returns the name of this Competitor
	 * @return the name of this Competitor
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Clear the id of this Competitor
	 */
	public static void clearId() {
		cmpt = 1;
	}

	/**
	 * HashCode method of the Competitor
	 * @return HashCode of the Competitor
	 */
	public int hashCode() {
		return this.id;
	}

	/**
	 * Method equals of the Competitor
	 * @param o the object compared to this Competitor
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Competitor)) {
			return false;
		}
		else {
			Competitor other = (Competitor) o;
			return this.id == other.id && this.name == other.name;
		}
	}

	/**
	 * toString method for Competitor
	 * @return String representation of this Competitor
	 */
	 public String toString() {
		 return this.name;
	 }

}
