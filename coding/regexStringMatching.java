import java.io.*;
import java.util.*;

/*

        0 1 2 3 4  index
        c * b * a  pattern
      0 1 2 3 4 5  dp index
    0 T F T F T F 
0 b 1 F F F T T F 
1 b 2 F F F F T F
2 a 3 F F F F F T

1) DP[0][0] = true //initial state
2) DP[i][0] = false // i >= 1, since no nonempty character can match empty string
3) DP[0][j] = true //if p[0..j - 2, j - 1] matches empty iff p[j-1] is '*' and p[0..j - 2] (dp[0][j-2] matches empty

4) pattern[j] == text[i] || pattern[j] == '.' then DP[i][j] = DP[i-1][j-1] 

5) if pattern[j] == '*'
  we have if  1.  if p[j-1] != t[i] : dp[i][j] = dp[i][j-2]  //in this case, c* only counts as empty
              2.  if p[j-1] == t[i] or p[j-1] == '.':
                   1. matches empty (0 of the preceding element) DP[i][j] = DP[i][j-2] OR
                   2. matches one extra of the preceding element DP[i][j] = DP[i][j-1] OR
                   3. matches multiple of preceding element DP[i][j] = DP[i-1][j]
6) else no matching, DP[i][j] = false
*/

class Solution {

  private static boolean regexStringMatching(String text, String pattern) {
    int m = text.length();
    int n = pattern.length();
    boolean[][] dp = new boolean[m+1][n+1];
    
    dp[0][0] = true;
    
    for(int i = 1; i <= m; i++) {
      dp[i][0] = false;
    } //implicit 
    
    for(int j = 1; j <= n; j++) {
      if (pattern.charAt(j-1) == '*') {
        if (j > 2 && dp[0][j-2]) {
          dp[0][j] = true; //pattern matches empty text 
        }
      }
    }
    
    for(int i = 1; i <= m; i++) {
      for(int j = 1; j <= n; j++) {
       
        if(pattern.charAt(j-1) == text.charAt(i-1) || pattern.charAt(j-1) == '.') {
          dp[i][j] = dp[i-1][j-1];
          
        } else if (pattern.charAt(j-1) == '*') { //* can't be the first char in the pattern, check just in case anyway
            
          if (pattern.charAt(j-2) != text.charAt(i-1) && pattern.charAt(j-2) != '.') {
              dp[i][j] = dp[i][j-2]; //ignore the c* in the pattern, as if matching empty
            
            } else {
              dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i-1][j];
            }
        }
      }
    }
    
    return dp[m][n];
    
  }

  public static void main(String[] args) {
    System.out.println(regexStringMatching("facebook", "f.*ebo*k")); //true
    System.out.println(regexStringMatching("facebook", "f.*eb**k")); //false
    System.out.println(regexStringMatching("facebook", "fa*ebook*")); //false
    System.out.println(regexStringMatching("facebook", ".a*ce.o*k*")); //true
    System.out.println(regexStringMatching("ab", "b*")); //false
    System.out.println(regexStringMatching("aaaa", ".*")); //true

  }
}