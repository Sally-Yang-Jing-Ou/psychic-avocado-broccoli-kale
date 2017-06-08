import java.io.*;
import java.util.*;

class Solution {
  
  private static void intervalSizeKWithLargestSum(int[] nums, int k) {
    int sum = 0;
    for (int i = 0; i < k; i++) {
      sum += nums[i];
    }
    
    int max = sum;
    int start = 0;
    for (int i = k; i < nums.length; i++) {
      sum = sum - nums[i-k] + nums[i];
      if (max < sum) {
        start = i-k+1;
        max = sum;
      }
    }
    
    System.out.println(max);
    System.out.println(start);

  }
  
  public static void main(String[] args) {
    int[] nums = new int[]{1,3,5,2,1,9,0,-3};
    intervalSizeKWithLargestSum(nums, 2);
  }
}
