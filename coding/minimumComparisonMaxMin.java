import java.io.*;
import java.util.*;
/*
Given an unsorted array, 
extract the max and min value
using the least number of comparison


Minimum number of comparisons is 3n/2.
The strategy is to go through the elements in pairs, 
and compare the smaller one to the minimum,
and the bigger one to the maximum. 
This is 3 comparisons, done n/2 times in total, for 3n/2 running time.
*/



class Solution {

  private static void maxMin(int[] nums) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    for(int i = 1; i < nums.length; i+=2) {
      if (nums[i] > nums[i-1]) {
        max = Math.max(max, nums[i]);
        min = Math.min(min, nums[i-1]);
      } else {
        max = Math.max(max, nums[i-1]);
        min = Math.min(min, nums[i]);
      }
    }
    System.out.format("max:%d, min:%d%n", max, min );       
  }

  public static void main(String[] args) {
    maxMin(new int[]{12,4,5,23,4,5,0,6,34,6,1,7,3});
  }
}

