import java.io.*;
import java.util.*;

/*
number of non empty subsets where min + max <= K


[2,4,5,7]

*/
class Solution {
  
  static int count = 0;
  private static void subsetMinMax(int[] set, int target) {
    subsetMinMax(set, target, 0, new ArrayList<Integer>());
  }
  
  private static void subsetMinMax(int[] set, int target, int index,  ArrayList<Integer> current) {
    
    if(current.size() >= 1) {
      if (current.get(0) + current.get(current.size()-1) <= target) {
        count++;
      }
    }
    
    for (int i = index; i < set.length; i++) {
      current.add(set[i]);
      subsetMinMax(set, target, i+1, current);
      current.remove(current.size()-1);
    }
  }
  /*
  2   3  5  9
  fix
   8
  2
   23
   235
   25
   3
   35
   
   
  sort the set
  start and end
  diff is the power 
  number of subsets of all size that can be generated
  
  */
  private static void subsetMinMaxMath(int[] set, int target) {
    Arrays.sort(set);
    int i = 0;
    int j = set.length - 1;
    int count = 0;
    while (i <= j) {
      if (set[i] + set[j] <= target) {
        int power = j - i;
        //power
        count += 1 << power;
        i++;
      } else {
        j--;
      }
    }
    System.out.println(count);
  }

  public static void main(String[] args) {
    subsetMinMax(new int[]{2,4,5,6,9}, 11);
    System.out.println(count);
    subsetMinMaxMath(new int[]{2,4,5,6,9}, 11);
  }
}

















