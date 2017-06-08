import java.io.*;
import java.util.*;
/*

1. buy no more than one unit on any given day
2. have to sell all stocks you're currently holding on any given day 
 
*/


class Solution {

  private static void buyAndSellTradingConstraints(int[] prices) {
    // For each day i, bestPriceDay[i] stores the day j after i where prices[j] is maximized.
    // Each bought stock will be sold on their bestPriceDay, if such a day exists.
    // This corresponds to selling all of our stock on each bestPriceDay, as after each bestPriceDay
    // there cannot be previously bought stock waiting to be sold on a future bestPriceDay, since
    // that would imply the future bestPriceDay has a better price than the current bestPriceDay.
    int N = prices.length;
    int[] bestPriceDay = new int[N];
    bestPriceDay[N-1] = N-1; //last day
    
    for(int i = N-2; i >= 0; i--) {     // find the best price day, we know the future!!!!!
      int j = bestPriceDay[i+1];
      
      if (prices[i] > prices[j] ) {
        bestPriceDay[i] = i; //current day is better
      } else {
        bestPriceDay[i] = j; //future day is better
      }
    }
    
    int profit = 0;
    for (int i = 0; i < N; i++) {
      // Get the day in the future where the stock price is the highest.
      int j = bestPriceDay[i];
      // If j == i, then there is no day in the future where the stock price
      // is higher than it currently is. Don't buy the stock in this case.
      if ( i!=j ) {
         // Buy the stock. The future profit we'll get corresponds to the
         // difference in price from the best day.
          profit += prices[j] - prices[i];
      }
    }
    
    System.out.println(profit);
  }

  public static void main(String[] args) {
    buyAndSellTradingConstraints(new int[]{0, 5, 10, 15, 20, 0, 5, 10, 15, 20, 25});
    buyAndSellTradingConstraints(new int[]{0, 5, 10, 15, 20, 25, 0, 5, 10});
    buyAndSellTradingConstraints(new int[]{0, 5, 10, 15, 20, 15, 10, 5, 0});
  }
  
  
}
