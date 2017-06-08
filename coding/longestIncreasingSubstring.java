import java.io.*;
import java.util.*;
/*

*/


class Solution {
  public static void longestIncreasingSubstring(int[] num) {
    int count = 1;
    int decreCount = 1;
    int bestEnd = 0;
    int max = 0;
    for (int i = 0; i < num.length-1; i++) {
      if (num[i] < num[i+1]) {
        count++;
        decreCount=1;
      } else (num[i] > num[i+1]){
        decreCount++;
        count = 1;
      } else {
        count = 1;
        decreCount = 1;
      }
      
      if (count > max) {
        max = count;
        bestEnd = i+1;
      }
      if (decreCount > max) {
        max = decreCount;
        bestEnd = i+1;
      }
    }
    System.out.println(num.range(bestEnd - max, bestEnd));
  }

  public static void main(String[] args) {
    longestIncreasingSubstring(new int[]{1,2,3,4,2,1,0,1,4,5,6,7,8,9,3,4});
  }
}

