package competition_sportive.master_filters;

import java.util.*;
import competition_sportive.competitor.*;

/**
 * Interface for Filter of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
 public interface Filter {

   /**
    * Returns a list of Competitors selected from a list of ranking of Leagues
    * @param rankings list of ranking
    * @return the list of competitors selected
    */
   public List<Competitor> select(List<Map<Competitor, Integer>> rankings);

   /**
    * Returns if a selection could be done of not for the master
    * @param nbgroups the number of groups in the master we want to make a selection
    * @param nbmembers the number of members per groups in the master we want to make a selection
    * @return if a selection could be done of not
    */
   public boolean maySelect(int nbgroups, int nbmembers);

   /**
    * Returns the n first competitors of each ranking
    * @param rankings the list of rankings
    * @param n the n first competitors of each ranking of the rankings
    * @return the n first competitors of each ranking
    */
    public static List<Competitor> selectTheFirst(List<Map<Competitor, Integer>> rankings,int n) {
      List<Competitor> selected = new ArrayList<Competitor>();
      int index;
      for (Map<Competitor, Integer> ranking : rankings) {
         index = 0;
         for(Map.Entry<Competitor, Integer> entry : ranking.entrySet()) {
           if(index != n) {
             selected.add(entry.getKey());
             index++;
           }
         }
      }
      Collections.shuffle(selected);
      return selected;
    }

    /**
     * Returns if the number of selected competitors will be a power of two or not
     * @param nbgroups the number of groups
     * @param nbmembers the number of member per groups
     * @param selectedByGroups the number of selected competitors per groups
     * @return true if the number of selected competitors will be a power of two, false otherwise
     */
    public static boolean isPowerOfTwo(int nbgroups, int nbmembers, int selectedByGroups) {
      if (nbgroups <= 0 || nbmembers <= 1) return false;
      int nb = nbgroups * selectedByGroups;
      return ((nb & (nb - 1)) == 0);
    }
}
