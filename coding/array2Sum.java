import java.io.*;
import java.util.*;


class Pair {
  int x, y;
  Pair(int xc, int yc) {
   x=xc;
   y=yc;
  }
}
class Solution {


  private static void array2Sum(int[] nums, int target) {
    List<Pair> results = new ArrayList<Pair>();
    Set<Integer> set = new HashSet<Integer>();
    
    for(int i = 0; i < nums.length; i++) {
      if(set.contains(target-nums[i])) {
        results.add(new Pair(target-nums[i], nums[i]));
      }
      set.add(nums[i]);
    }
    
    for(Pair p : results) {
      System.out.format("%d-%d%n", p.x, p.y);
    }
  }

  private static void array2Sum2(int[] nums, int target) {
        List<Pair> results = new ArrayList<Pair>();

    Arrays.sort(nums);
    int i = 0;
    int j = nums.length - 1;
    
    while(i < j) {
      int sum = nums[i] + nums[j];
      if (sum == target) {
        results.add(new Pair(nums[i], nums[j]));
        i++;
        j--;
      } else if (sum < target) {
        i++;
      } else {
        j--;
      }
    }
    
    for(Pair p : results) {
      System.out.format("%d-%d%n", p.x, p.y);
    }
  }
  
  
  public static void main(String[] args) {
    array2Sum2(new int[]{2, 1, 0, 1, 3, 5, 9 ,10}, 2);
  }
}

