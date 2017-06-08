import java.io.*;
import java.util.*;

class Solution {
  
  /*
  The subarray sum reminds me the range sum problem. Preprocess the input array such that you get
the range sum in constant time.
sum[i] means the sum from 0 to i inclusively
the sum from i to j is sum[j] - sum[i - 1] except that from 0 to j is sum[j].

j-i is equal to the length of subarray of original array. we want to find the max(j - i)
for any sum[j] we need to find if there is a previous sum[i] such that sum[j] - sum[i] = k

Instead of scanning from 0 to j -1 to find such i, we use hashmap to do the job in constant time.

However, there might be duplicate value of of sum[i] we should avoid overriding its 
index as we want the max j - i, so we want to keep i as left as possible.
  
   index 0 1 2 3 4  5  6
         1 2 3 2 -2 2  1
    
    sum  1 3 6 8 6  8  9

    target: 3 
    running6: 9 - 3 = 6
    

  */
  private static void maximumSizeSubarraySumtoK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(0, -1);
    int start = 0;
    int max = 0;
    for (int i = 1; i < nums.length; i++ ) {
      nums[i] = nums[i-1] + nums[i];
    }
    
    for(int i = 1; i < nums.length; i++) {
      if(map.containsKey(nums[i] - k)) {
        if (max < i - map.get(nums[i] - k)) {
          start = map.get(nums[i] - k) + 1;
          max = i - map.get(nums[i] - k);
        }
      }
      if(!map.containsKey(nums[i])) {
        map.put(nums[i], i);
      }
    }
    
    System.out.println(start);
    System.out.println(start + max -1);
    
  }
  
  public static void main(String[] args) {
    int[] nums = new int[]{1,3,5,2,1,9,0,-3,1,1,12,1,99};
    maximumSizeSubarraySumtoK(nums, 12);
  }
}














