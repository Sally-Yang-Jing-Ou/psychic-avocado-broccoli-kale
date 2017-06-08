import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class CustomString {
  private String _str;
  private int _size;
  
  public CustomString(String s) {
    _str = s;
    _size = s.length();
  }
  
  public int indexOf(String c) {
    return indexOf(c, 0);
  }
  
  public int indexOf(String c, int fromIndex) {
    return indexOf(_str, c, _size, c.length(), fromIndex);
  }
  
  private int indexOf(String source, String pattern, int sourceCount, int patternCount, int fromIndex) {
   
    if (fromIndex >= sourceCount) {
      return (patternCount == 0 ? sourceCount : -1);
    }
    
    if (fromIndex < 0) {
            fromIndex = 0;
    }
    
    if (patternCount == 0) {
        return fromIndex;
    }
    
    char first = pattern.charAt(fromIndex);
    // length to check for in the og string
    int max = sourceCount - patternCount; 

    for (int i = fromIndex; i <= max; i++) {
      // keep going until we find one that matches
      if (source.charAt(i) != first) {
        while (++i <= max && source.charAt(i) != first);
      }
      
      /*
      012345678 
      interview
             ew
           ^end     
      */

      if (i <= max) {
        int j = i + 1;
        int end = j + patternCount - 1; //pattern length index to end at
        for (int k = 1; j < end && source.charAt(j) == pattern.charAt(k); k++, j++);
        
        if (j == end) {
          return i;
        }
      }
    }
    return -1;
  }

}

class Solution {
  
  public static void main(String[] args) {
    CustomString str = new CustomString("interview");
    System.out.println(str.indexOf("ew"));
    
  }
}
