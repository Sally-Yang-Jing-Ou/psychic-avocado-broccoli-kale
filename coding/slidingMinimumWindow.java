import java.io.*;
import java.util.*;



/*
use a map to record the count of characters in target string

1. move one pointer and record the character if they are in target as well
put it in map, use counter to remember the characters mapped

2. if counter == target length, start moving the begin pointer only if begin pointer is 
not in target or if we have more than we need for target

3. update min and substring



*/

class Solution {
  
   private static void minWindowSubstring(String str, String target) {
    if (str.isEmpty()) {
      System.out.println("empty string");
      return;
    }
    String result = "";
    Map<Character, Integer> targetMap = new HashMap<Character, Integer>();
    
    for (int i = 0; i < target.length(); i++) {
      char c = target.charAt(i);
      targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
    }
    
     Map<Character, Integer> map = new HashMap<Character, Integer>();
    int counter = 0;
    int begin = 0;
    int min = str.length();
    int Bestbegin = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      
      if (targetMap.containsKey(c)) {
        if (map.containsKey(c)) {
          if (map.get(c) < targetMap.get(c)) { //we need more of the same character
            counter++;
          }
          map.put(c, map.get(c) + 1);
        } else {
          map.put(c, 1);
          counter++;
        }
      }
      
      // move start pointer
      if (counter == target.length()) {
        char sc = str.charAt(begin);
        while (!map.containsKey(sc) || map.get(sc) > targetMap.get(sc)) {
          if (map.containsKey(sc) && map.get(sc) > targetMap.get(sc)) {//more than we need 
            map.put(sc, map.get(sc) - 1);
          }
          begin++;
          sc = str.charAt(begin);
        }
        
        if (i - begin + 1 < min) {
            Bestbegin = begin;
            min = i - begin + 1;
        }
      }
    }
    
    result = str.substring(Bestbegin, Bestbegin + min);
    System.out.println(result);
  }
  
  public static void main(String[] args) {
    minWindowSubstring("HELLOOOOOOFACEEBOOAKKK", "AOK");
    
  }
}

















