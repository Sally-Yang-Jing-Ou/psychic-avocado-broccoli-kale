import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*
This is a classic problem of Dynamic Programming.
 We define the state dp[i][j] to be the minimum number of operations to convert word1[0..i - 1] to word2[0..j - 1].
  The state equations have two cases: the boundary case and the general case. Note that in the above notations,
   both i and j take values starting from 1.

For the boundary case, that is, to convert a string to an empty string, 
it is easy to see that the mininum number of operations to convert word1[0..i - 1] to "" 
requires at least i operations (deletions). In fact, the boundary case is simply:

dp[i][0] = i;
dp[0][j] = j.
Now let's move on to the general case, that is, convert a non-empty word1[0..i - 1] to another non-empty word2[0..j - 1]. 
Well, let's try to break this problem down into smaller problems (sub-problems). Suppose we have already known how to 
convert word1[0..i - 2] to word2[0..j - 2], which is dp[i - 1][j - 1]. Now let's consider word[i - 1] and word2[j - 1]. 
If they are euqal, then no more operation is needed and dp[i][j] = dp[i - 1][j - 1]. Well, what if they are not equal?

If they are not equal, we need to consider three cases:

Replace word1[i - 1] by word2[j - 1] (dp[i][j] = dp[i - 1][j - 1] + 1 (for replacement));
Delete word1[i - 1] and word1[0..i - 2] = word2[0..j - 1] (dp[i][j] = dp[i - 1][j] + 1 (for deletion));
Insert word2[j - 1] to word1[0..i - 1] and word1[0..i - 1] + word2[j - 1] = word2[0..j - 1] (dp[i][j] = dp[i][j - 1] + 1 (for insertion)).
Make sure you understand the subtle differences between the equations for deletion and insertion. For deletion, we are actually converting word1[0..i - 2] to word2[0..j - 1], which costs dp[i - 1][j], and then deleting the word1[i - 1], which costs 1. The case is similar for insertion.

Putting these together, we now have:

dp[i][0] = i;
dp[0][j] = j;
dp[i][j] = dp[i - 1][j - 1], if word1[i - 1] = word2[j - 1];
dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1), otherwise.

    0 1 2 3 4 index
    0 a b c d string 1
0 0 0 1 2 3 4 dp result
1 a 1 0 1
2 c 2 
3 c 3 
4 b 4
*/
class Solution { 

  private static void editDistance(String s1, String s2) {
    int[][] dp = new int[s1.length()+1][s2.length()+1];
    dp[0][0] = 0;
    for (int i = 1; i <= s1.length(); i++) {
      dp[i][0] = i;
    }
    
    for (int j = 1; j <= s2.length(); j++) {
      dp[0][j] = j;
    }
    
    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        if (s1.charAt(i-1) == s2.charAt(i-1)) {
          dp[i][j] = dp[i-1][j-1];
        } else {
          int replace = dp[i-1][j-1] + 1;
          int remove = dp[i-1][j] + 1;
          int insert = dp[i][j-1] + 1;

          dp[i][j] = Math.min(replace, Math.min(remove, insert));
        }
        
      }
    }
    
    System.out.println(dp[s1.length()][s2.length()]);
    
  }
  
  public static void main(String[] args) {
    editDistance("hi", "aifc");
  }
}
