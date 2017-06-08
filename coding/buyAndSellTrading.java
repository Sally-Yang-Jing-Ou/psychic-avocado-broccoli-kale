import java.io.*;
import java.util.*;

class Solution {

  /*

If you were only permitted to complete at most 
one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.


Kinda like the maximum sum subarray question 

keep track of min price and max profit so far
*/

  public static int maxProfit(int[] prices) {
    if(prices==null||prices.length<=1)
      return 0;

    int min=prices[0]; // min so far
    int result=0;

    for(int i=1; i<prices.length; i++){
      result = Math.max(result, prices[i]-min);
      min = Math.min(min, prices[i]);
    }

    return result;
  }

  public static int maxProfit2(int[] prices) {
    int maxCur = 0, maxSoFar = 0;
    for(int i = 1; i < prices.length; i++) {
      maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
      maxSoFar = Math.max(maxCur, maxSoFar);
    }
    return maxSoFar;
  }

  public static void main(String[] args) {
    System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    System.out.println(maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
    System.out.println(maxProfit(new int[]{7,  5, 4, 1}));
    System.out.println(maxProfit2(new int[]{7, 5, 4, 1}));
  }
}

