import java.io.*;
import java.util.*;
/*
Loop for all elements in arr[] and maintain two sums incl and excl 
where incl = Max sum including the previous element and excl =
Max sum excluding the previous element.

Max sum excluding the current element will be max(incl, excl) 

Max sum including the current element will be excl + current element 

(Note that only excl is considered because elements cannot be adjacent).

At the end of the loop return max of incl and excl.

incl = last_excl + current[i]
excl = max (excl, incl)

       5,  5, 10, 40,  50, 35

excl   0   5   5   15  45  65

incl   5   5   15  45  65  80

max(incl, excl) =  80 

*/
class Solution { 
  
  private static int kadaneAlternative(int[] nums) {
    int incl = nums[0];
    int new_excl = 0;
    int excl = 0;

    for(int i = 1; i < nums.length; i++) {
      //current max excluding i 
      new_excl = Math.max(excl, incl);
      //current max including i 
      incl = excl + nums[i];
      excl = new_excl;
    }
    
    return Math.max(incl, excl);
  }
  
  public static void main(String[] args) {
    System.out.println(kadaneAlternative(new int[]{5,5,10,40,50,35}));
    System.out.println(kadaneAlternative(new int[]{1,2,3}));
    System.out.println(kadaneAlternative(new int[]{1,20,3}));

  }
}

