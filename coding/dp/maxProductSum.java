import java.io.*;
import java.util.*;

/*

     0 1  2  3 4 index
     
     5 2 -2 -2 2 elements

max  5 10 -2 40 80
          
min  5 2 -20 -2 -8

max[i] = Math.max(num[i], max[i-1]*num[i])
min[i] = Math.min(num[i], min[i-1]*num[i])

if num[i] < 0
max[i] = Math.max(num[i], min[i-1]*num[i])
min[i] = Math.min(num[i], max[i-1]*num[i])

update maxResult everytime max[i] is updated
 */


class Solution { 

  private static void maximumProductSum(int[] num) {
    int[] max = new int[num.length];
    int[] min = new int[num.length];
    
    max[0] = min[0] = num[0];
    int result = num[0];
    for (int i = 1; i < num.length; i++) {
      if (num[i] > 0) {
        max[i] = Math.max(num[i], max[i-1]*num[i]);
        min[i] = Math.min(num[i], min[i-1]*num[i]);
        
      } else {
        max[i] = Math.max(num[i], min[i-1]*num[i]);
        min[i] = Math.min(num[i], max[i-1]*num[i]); 
      }
    
      result = Math.max(max[i], result);
    }
    
    System.out.print(result);
  }
  
  public static void main(String[] args) {
    int[] num = new int[]{1,4,-3,2,2,9};
    maximumProductSum(num);
  }
}


  private static void maximumProductSum(int[] num) {
    int[] max = new int[num.length];
    int[] min = new int[num.length];
    int[] start = new int[num.length];
    start[0] = 0;
    max[0] = min[0] = num[0];
    int result = 0;
    for (int i = 1; i < num.length; i++) {
      if (num[i] > 0) {
        max[i] = Math.max(num[i], max[i-1]*num[i]);
        
        if (num[i] > max[i-1]*num[i]) {
          start[i] = i;
        } else {
          start[i] = start[i-1];
        }
        
        min[i] = Math.min(num[i], min[i-1]*num[i]);
        
      } else {
        max[i] = Math.max(num[i], min[i-1]*num[i]);
        min[i] = Math.min(num[i], max[i-1]*num[i]); 
      }
    
      if (max[i] > max[result]) {
        result = i;
      }
    }
    
    System.out.format("start:%d, end:%d%n", start[result], result);
  }
