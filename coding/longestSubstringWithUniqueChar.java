import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*
use a map to keep track of each character we've seen thus far

1. keep track of unique character so far - 
use a map, if the value is zero, increment counter

2. while counter > target, decrement the map value at start pointer by one, 
move the start pointer. 
If the map value at start pointer is one
decrement the counter (so now we have TARGET number of unique characters)

3. update max and resulting substring 



*/
class Solution { 
  
  private static void longestSubstringWithNUnique(String str, int n) {
    if (str.isEmpty()) {
      System.out.println("empty string");
      return;
      
    }
    String result = "";
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int counter = 0;
    int begin = 0;
    int max = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (map.getOrDefault(c, 0) == 0) {
        counter++;
      }
      map.put(c, map.getOrDefault(c, 0) + 1);
      
      while (counter > n) {
        char b = str.charAt(begin);
        if(map.get(b) == 1) {
          counter--;
        }
        map.put(b, map.get(b) - 1);
        begin++;
      }
      
      if (i - begin + 1 > max) {
        result = str.substring(begin, i + 1);
        max = i - begin + 1;
      }
    }
    
    System.out.println(result);
  }
  
  public static void main(String[] args) {
    longestSubstringWithNUnique("HELLOOOOOOFACEEBOOKKK", 2);
    
  }
}
    