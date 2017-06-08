import java.io.*;
import java.util.*;

/*
Step to generate odd length palindrome:
Fix a centre and expand in both directions for longer palindromes.

Step to generate even length palindrome
Fix two centre ( low and high ) and expand in both directions for longer palindromes.


(n^2) time with O(1)

facebookkoobhello

*/

class Solution {
  
  private static void longestPalidromeSubstring(String s) {
    
    int low = 0, high = 0, maxLength = 0, start = 0;
    
    // One by one consider every character as center point of 
    // even and length palindromes
    for (int i = 1; i < s.length(); i++ ){
      
      // Find the longest even length palindrome with center points
      // as i-1 and i. 
      low = i - 1;
      high = i;
      while ( low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high) ) {
        if (high - low + 1 > maxLength) {
          start = low;
          maxLength = high-low+1;
        }
        low--;
        high++;
      }
      
      
      // Find the longest odd length palindrome with center 
      // point as i
      low = i - 1;
      high = i + 1;
      while ( low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high) ) {
        if (high - low + 1 > maxLength) {
          start = low;
          maxLength = high-low+1;
        }
        low--;
        high++;
      }
    }
    
    System.out.println(s.substring(start, start+maxLength));
    System.out.println(maxLength);
  
  }
  
  public static void main(String[] args) {
   longestPalidromeSubstring("forgeeksskeegfor");
  }
}


















