import java.io.*;
import java.util.*;

/*
-20 -1 0 1 3 5 9 10
 
 0   1 2 3 4 5 6 7
         ^
         ---> search right
         if we move from i to i-1, the value at this index will decrease
         by at least one, if we move left, the values will be even smaller
 
*/
class Solution {


  private static int magicIndex (int[] nums) {
    return magicIndex(nums, 0, nums.length-1);
  }


  private static int magicIndex (int[] nums, int left, int right) {
    if (right < left) return -1;

    int mid = left + (right -left)/2;
    if(nums[mid] == mid) return mid;
    else if (nums[mid] > mid) {
      //search left because everythign to the right will just be bigger
      return magicIndex(nums,left, mid-1); 
    } else {
      return magicIndex(nums,mid+1,right);
    }
  }
  public static void main(String[] args) {
    System.out.println(magicIndex(new int[]{-20, -1, 0, 1, 3, 5, 9 ,10}));
  }
}

