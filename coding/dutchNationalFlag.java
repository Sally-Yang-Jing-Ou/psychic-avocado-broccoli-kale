import java.io.*;
import java.util.*;

/*
For example, you might be give the array [5,7,2,9,1,14,12,10,5,3].
For input integers 1 - 3, getCategory(integer) returns "low", 
for 4 - 10 it returns "medium," and for 11 - 15 it returns "high".

You could output an array (or modify the given array) that looks like this: [3,1,2,5,5,9,7,10,14,12]
*/

class Solution {

  private static String getCategory(Integer i) {
    switch(i) {
      case 1:
      case 2:
      case 3:
        return "low";
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
        return "mid";
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
        return "hi";
      default:
        return "";
    }
  }

  private static void dutchNationalFlag(int[] nums) {
    if (nums == null || nums.length ==0) return;

    int low = 0;
    int mid = 0;
    int hi = nums.length - 1;

    while(mid <= hi) {
      switch(getCategory(nums[mid])) {
        case "low":
          swap(nums, low++, mid++);
          break;
        case "mid":
          mid ++;
          break;
        case "hi":
          swap(nums, mid, hi--);
          break;
      }
    }

    for(int i : nums) {
      System.out.format("%d ",i);
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    dutchNationalFlag(new int[]{5,7,2,9,1,14,12,10,5,3}); //2 1 3 9 7 5 5 10 12 14
  }
}