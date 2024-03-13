package competition_sportive.master_filters;

import java.util.*;
import competition_sportive.competitor.*;

/**
 * Class for Filter of the COO Project
 * @author Nollet Antoine Minaud Mathilde
 * @version 05/10/2020
 */
 public class FirstFilter implements Filter {

   /**
    * Creates the filter
    */
   public FirstFilter() {
     super();
   }

   /**
    * Returns a list of Competitors selected from a list of ranking of Leagues
    * @param rankings list of ranking
    * @return the list of competitors selected
    */
   public List<Competitor> select(List<Map<Competitor, Integer>> rankings) {
     return Filter.selectTheFirst(rankings,1);
   }

   /**
    * Returns if a selection could be done of not for the master with nbgroups groups with nbmembers members per groups
    * @param nbgroups the number of groups
    * @param nbmembers the number of members per groups
    * @return if a selection could be done of not
    */
   public boolean maySelect(int nbgroups, int nbmembers) {
     return Filter.isPowerOfTwo(nbgroups,nbmembers,1);
   }

 }
