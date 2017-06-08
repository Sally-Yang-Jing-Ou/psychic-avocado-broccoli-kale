import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  private static void minSizeSubarraySum(int[] array, int target) {
    if (array == null) return;
    
    int sum = 0, start = 0, min = array.length;
    int startIndex = 0, endIndex = 0;
    boolean exists = false;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
      
      while (sum > target && start <= i) {
        exists = true;
        // startIndex = start;
        // endIndex = i;
        min = Math.min(i - start + 1, min);
        sum -= array[start];
        start++;
      }
      
//       while (sum >= target && start <= i) {
//         if (sum == target) {
//           exists = true;
//           startIndex = start;
//           endIndex = i;
//           min = Math.min(i - start + 1, min);        
//         }
        
//         sum -= array[start];
//         start++;
//       }
      
      // if (sum == target) {
      //   System.out.format("start %d, end at %d", start, i);
      //   return;
      // }
    }
    
    if (exists) {
      System.out.format("sum:%d, min:%d, start:%d, end:%d%n", sum, min, startIndex, endIndex);
      
    } else {
      System.out.format("no results");
    }
  }
  
  public static void main(String[] args) {
    int[] num = new int[]{1,4,3,2,1,2,5,9};
    minSizeSubarraySum(num, 12);
  }
}
