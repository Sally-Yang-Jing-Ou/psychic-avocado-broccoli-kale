import java.io.*;
import java.util.*;

class Solution {
  
  private static boolean inplacePalidromeChecker(String s) {
    int i = 0;
    int j = s.length()-1;
    char ic, jc;

    while (i<=j) {
      ic = s.charAt(i);
      jc = s.charAt(j);
      
      if (!isLetterOrDigit(ic)) {
        i++;
        continue;
      }
      
      if (!isLetterOrDigit(jc)) {
        j--;
        continue;
      }
      
      if (Character.toLowerCase(ic) != Character.toLowerCase(jc)) {
        return false;
      }
      
      i++;
      j--;
    }
    
    return true;
  }
        
  private static boolean isLetterOrDigit(char c) {
    return (c >= 'a' && c <= 'z') ||
           (c >= 'A' && c <= 'Z') ||
           (c >= '0' && c <= '9');
  }
  
  public static void main(String[] args) {
    // System.out.println(isLetterOrDigit('!'));
    System.out.println(inplacePalidromeChecker("A man, a plan, a canal, Panama!!!!!"));
  }
}














