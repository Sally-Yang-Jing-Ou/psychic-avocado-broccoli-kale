import java.io.*;
import java.util.*;

/*
Case 1: The character is ‘*’
Here two cases arise

We can ignore ‘*’ character and move to next character in the Pattern.
‘*’ character matches with one or more characters in Text. Here we will move to 
next character in the string.

check if * is the last char, if it is, just return true;


Case 2: The character is ‘?’
We can ignore current character in Text and move to next character in the Pattern and Text.

check if ? is last char and if i is NOT text.length-1, if it is, return false


Case 3: The character is not a wildcard character
If current character in Text matches with current character in Pattern, we move to next 
character in the Pattern and Text. If they do not match, wildcard pattern and Text do not match.

*/

class Solution {
  
  private static boolean wildCard(String pattern, String text) {
    return wildCard(text, pattern, 0, 0);  
  }
    
    //O(m*n)
  
  private static boolean wildCard(String text, String pattern, int i, int j) {
    if (i==text.length() && j == pattern.length()) return true;
    if (i >= text.length()) return false;
    
    if (pattern.charAt(j) == '*') {
      if (j == pattern.length()-1) {
        return true;
      }
      return wildCard(text, pattern, i+1, j) || wildCard(text, pattern, i, j+1);
      
    } else if (pattern.charAt(j) == '?' || pattern.charAt(j) == text.charAt(i)) {
      if (j == pattern.length()-1 && i != text.length()-1) {
        return false;
      }
      return wildCard(text, pattern, i+1, j+1);
    } else {
      return false;
    }
  }
  
  public static void main(String[] args) {
    System.out.println(wildCard("*", "geeks"));
    System.out.println(wildCard("*?c*d", "abcd"));
    System.out.println(wildCard("g*k", "gkkefg"));
    System.out.println(wildCard("abc*bcd", "abcdhghgbcd"));
    System.out.println(wildCard("abc*c?d", "abcd"));

  }
}

/*
// both text and pattern are null
T[0][0] = true; 

// pattern is null
T[i][0] = false; 

// text is null
T[0][j] = T[0][j - 1] if pattern[j – 1] is '*'  
DP relation :

 If current characters match, result is same as 
 result for lengths minus one. Characters match
 in two cases:
 a) If pattern character is '?' then it matches  
    with any character of text. 
 b) If current characters in both match
if ( pattern[j – 1] == ‘?’) || 
     (pattern[j – 1] == text[i - 1])
    T[i][j] = T[i-1][j-1]   
 
 If we encounter ‘*’, two choices are possible-
 a) We ignore ‘*’ character and move to next 
    character in the pattern, i.e., ‘*’ 
    indicates an empty sequence.
 b) '*' character matches with ith character in
     input 
else if (pattern[j – 1] == ‘*’)
    T[i][j] = T[i][j-1] || T[i-1][j]  

else // if (pattern[j – 1] != text[i - 1])
    T[i][j]  = fals

*/

/*
    pattern
   0fac*b?
 0 1000000
 f 0100000
 a 0010000
 c 0001100
 e 0000100
 e 0000100
 b 0000110
 o 0000101
 
 */
  public static boolean wildcardMatch(String textStr, String patternStr){
    char[] text = textStr.toCharArray();
    char[] pattern = patternStr.toCharArray();
    boolean[][] dp = new boolean[text.length+1][pattern.length+1];
    //initialize dp table
    dp[0][0] = true;
    
    for(int i=1; i<dp[0].length; i++){
      if(pattern[i-1] == '*') dp[0][i] = dp[0][i-1];// pattern is null
    }
    
    for(int i=1; i<dp.length; i++){
      for(int j=1; j<dp[i].length; j++){
        if(pattern[j-1] == '?' || pattern[j-1] == text[i-1]){
          dp[i][j] = dp[i-1][j-1];
                      // Two cases if we see a '*'
            // a) We ignore ‘*’ character and move
            //    to next  character in the pattern,
            //     i.e., ‘*’ indicates an empty sequence.
            // b) '*' character matches with ith
            //     character in input
        }else if(pattern[j-1] == '*'){
          dp[i][j] = dp[i][j-1] || dp[i-1][j];
        }else {
          dp[i][j] = false;
        }
      }
    }
    
    return dp[text.length][pattern.length];
  }

















