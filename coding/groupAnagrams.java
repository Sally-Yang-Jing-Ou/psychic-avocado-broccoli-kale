import java.io.*;
import java.util.*;


class Solution {
  
  private static void groupAnagrams(String[] strs) {
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    
    for (String s: strs) {
      int[] alphabets = new int[26];
      for (int i = 0; i < s.length(); i++) {
        int index = s.charAt(i) - 'a';
        alphabets[index] += 1;
      }
      
      String key = Arrays.toString(alphabets);
      
      if(!map.containsKey(key)) {
        map.put(key, new ArrayList<String>());
      }
      
      map.get(key).add(s);
      
    }  

    OR

    for (String s : strs) {
      String key = s;
      Arrays.sort(key);

      if(!map.containsKey(key)) {
        map.put(key, new ArrayList<String>());
      }

      map.put(key, s);
    }

    //convert to array
    int index = 0;
    String[] array = new String[strs.length];
    for (ArrayList<String> s: map.values()) {
      for (String st : s ) {
        array[index] = st;
        index++;
      }
    }    
    
    // for(ArrayList<String> s: map.values()) {
    //   for (String s1: s) {
    //     System.out.format("%s ",s1);
    //   }
    //   System.out.println("");
    // }
  }
  
  public static void main(String[] args) {
    String[] strs = new String[]{"gof", "fog", "ddk", "helloface", "faceolleh"};
    groupAnagrams(strs);
  }
}