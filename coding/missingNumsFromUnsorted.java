import java.io.*;
import java.util.*;

/*
Option1:
sort it O(Nlogn)
space O(1)

Option2:
1. Use a boolean array to keep track of the ones that are present
2. go through boolean array and output the ones that aren't there

O(N) space and time

*/
class Solution {

  private static void missingNumsFromUnsorted(int[] nums, int N) {
    if(nums == null || nums.length == 0) return;
    int expected = 1;
    List<Integer> missingNums = new ArrayList<Integer>();
    Arrays.sort(nums);

    for(int i : nums) {
      while(expected < i) {
        missingNums.add(expected++);
      }
      expected++;
    }

    while(expected <= N) {
      missingNums.add(expected++);
    }

    for(Integer i : missingNums) {
      System.out.format("%d ", i);
    }
    System.out.println("");
  }

  private static void missingNumsFromUnsorted2(int[] nums, int N) {
    int expected = 1;
    boolean[] present = new boolean[N];
    List<Integer> missingNums = new ArrayList<Integer>();

    for(int i : nums){
      present[i-1] = true;
    }

    for(int i = 0; i < N; i++) {
      if(!present[i]) {
        missingNums.add(i+1);
      }
    }

    for(Integer i : missingNums) {
      System.out.format("%d ", i);
    }
    System.out.println("");

  }

  public static void main(String[] args) {
    missingNumsFromUnsorted2(new int[]{1, 2, 3, 4, 7, 8, 9}, 10);
    missingNumsFromUnsorted2(new int[]{10, 4, 9, 8, 7, 5, 6}, 15);

  }
}
