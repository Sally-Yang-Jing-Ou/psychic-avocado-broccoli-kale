import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class Solution {   
  
  public static int coinDenomination(int[] coins, int target) {
    if (coins.length == 0) return 0;
    int[] combo = new int[target+1];
    combo[0] = 1;
    
    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= target; j++) {
        combo[j] += combo[j - coins[i]];
      }
    }
    
    return combo[target];
  }
  
  public static void main(String[] args) {
    int[] array = new int[]{1,2,3};
    System.out.print(coinDenomination(array, 4));
    
  }
}

/*
Base Cases:

if amount=0 then just return empty set to make the change, so 1 way to make the change.
if no coins given, 0 ways to change the amount.
Rest of the cases:

For every coin we have an option to include it in solu­tion or exclude it.
check if the coin value is less than or equal to the amount needed, if yes then we will 
find ways by includ­ing that coin and exclud­ing that coin.

Include the coin: reduce the amount by coin value and use the sub prob­lem solu­tion (amount-v[i]).
Exclude the coin: solu­tion for the same amount with­out con­sid­er­ing that coin.
If coin value is greater than the amount then we can’t con­sider that coin, so solu­tion will be with­out con­sid­er­ing that coin.

           Amount
         0 1 2 3 4 5
       0 1 0 0 0 0 0
0      1 1 1 1 1 1 1
1      2 1 1 2 2 3 3
2      3 1 1 2 3 4 5

1) dp[i][0] = 1
2) dp[0][i] = 0
3) if coin value <= amount: dp[i][j] = dp[i][j- coin[i-1]] + dp[i-1][j]
   else: dp[i][j] = dp[i-1][j] //exclude that coin

*/


public class WaysToCoinChange {
  public static int dynamic(int[] v, int amount) {
    int[][] solution = new int[v.length + 1][amount + 1];

    // if amount=0 then just return empty set to make the change
    for (int i = 0; i <= v.length; i++) {
      solution[i][0] = 1;
    }

    // if no coins given, 0 ways to change the amount
    for (int i = 1; i <= amount; i++) {
      solution[0][i] = 0;
    }

    // now fill rest of the matrix.

    for (int i = 1; i <= v.length; i++) {
      for (int j = 1; j <= amount; j++) {
        // check if the coin value is less than the amount needed
        if (v[i - 1] <= j) {
          // reduce the amount by coin value and
          // use the subproblem solution (amount-v[i]) and
          // add the solution from the top to it
          // with the coin and without the coin
          solution[i][j] = solution[i - 1][j]
              + solution[i][j - v[i - 1]];
        } else {
          // just copy the value from the top/ making money without the current coin
          solution[i][j] = solution[i - 1][j];
        }
      }
    }
    return solution[v.length][amount];
  }

/*

For every coin we have 2 options, 
either we include it or exclude it so if we think in terms of binary, 
its 0(exclude) or 1(include). so for exam­ple if we have 2 coins, 
options will be 00, 01, 10, 11. so its 2^2. for n coins , it will be 2^n.
 In all these options we will be check­ing whether that selec­tion has made the change which is required.

*/

public static int total(int n, int[] v, int i) {
    if (n < 0) {
      return 0;
    }
    if (n == 0) {
      return 1;
    }
    // means coins over and n>0 so no solution
    if (i == v.length && n > 0) {
      return 0;
    }
    return total(n - v[i], v, i) + total(n, v, i + 1);
}
    