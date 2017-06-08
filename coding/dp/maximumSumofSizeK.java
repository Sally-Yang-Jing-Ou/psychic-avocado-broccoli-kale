import java.io.*;
import java.util.*;

class Solution {
  
    //Maximum with continuous elements
  public int MaxCont(List<Integer> list) {
    int currMax = list.get(0);
    int maxsoFar = list.get(0);

    for (int i = 1; i < list.size(); i++) {
      currMax = Math.max(list.get(i), currMax + list.get(i));
      maxsoFar = Math.max(currMax, maxsoFar);
    }

    return maxsoFar;
  }

  //Maximum with K-min continuous elements
  public static int MaxContwithK(int[] list, int k) {

    int curK = 0;

    for (int i = 0; i < k; i++) {
      curK = curK + list[i];
    }
    
    int currMax = curK;
    int maxsoFar = curK;

    for (int i = k; i < list.length; i++) {

      curK = curK - list[i - k] + list[i]; //sliding window of size k
      currMax = Math.max(curK, currMax + list[i]);
      maxsoFar = Math.max(currMax, maxsoFar);
    }
    return maxsoFar;
  }
  
  public static void main(String[] args) {
    System.out.print(MaxContwithK(new int[]{-1,2,3,-4}, 2));
  }
}














