import java.io.*;
import java.util.*;


class Solution { 

  private static void subsetSum(int[] array, int target) {
    if (array == null) return;
    
    int sum = 0, start = 0;
    
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
      
      while (sum > target && start <= i) {
        sum -= array[start];
        start++;
      }
      
      if (sum == target) {
        System.out.format("start %d, end at %d", start, i);
        return;
      }
    }
    System.out.format("no results");
  }
  public static void main(String[] args) {
    int[] array = new int[]{15, 2, 4, 8, 9, 5, 10, 23};
    subsetSum(array, 15);
                
  }

  
}
