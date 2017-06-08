import java.io.*;
import java.util.*;

class palindromeGenerator { 
  
  private static Set<List<String>> allPairs(Set<String> dict) {
    Set<List<String>> pairs = new HashSet<List<String>>();
    
    if (dict.isEmpty()) { 
      return pairs; 
    }
    
    List<String> list = new ArrayList<String>();
    String left;
    String right;
    String rightReversed;
    String leftReversed;
    
    for (String str: dict) {

      for (int i = 0; i < str.length(); i++) {
        left = str.substring(0, i);
        right = str.substring(i);

        if (isPalidrome(left)) {
          // rightReversed = new StringBuilder(right).reverse().toString();
          rightReversed = reverseString(right);
          if (dict.contains(rightReversed)) {
            list = new ArrayList<String>();
            list.add(rightReversed);
            list.add(str);
            pairs.add(list);
          }
        }
                      
        if (isPalidrome(right)) {
          // leftReversed = new StringBuilder(left).reverse().toString();
          leftReversed = reverseString(left);
          if (dict.contains(leftReversed)) {
            list = new ArrayList<String>();
            list.add(str);
            list.add(leftReversed);
            pairs.add(list);
          }
        }
      }
    }
    
    return pairs;
  }
  
  private static String reverseString(String s) {
    String rev = "";
    
    for (int i = s.length()-1; i >= 0; i--) {
      rev += s.charAt(i);
    }
    
    //System.out.format("original %s, reversed %s" ,s ,rev);
    //System.out.println("");
    return rev;
  }
  
  
  private static boolean isPalidrome(String s) {
    int i = 0;
    int j = s.length() - 1;
    
    while (i < j) {
      if (s.charAt(i++) != s.charAt(j--)) {
        return false;
      }
    }
    
    return true;
  }
  
  public static void main(String[] args) {
    Set<String> dict = new HashSet<String>();
    dict.add("race");
    dict.add("car");
    // dict.add("hi");
    // dict.add("bye");
    // dict.add("eyb");
    // dict.add("h");
    // dict.add("i");
    // dict.add("ecar");
    dict.add("hellol");
    dict.add("leh");
    
    Set<List<String>> pairs = allPairs(dict);
    
    for (List<String> p: pairs) {
      System.out.format("%s%s", p.get(0), p.get(1));
      System.out.println("");
    }
    
    System.out.println("end");

    
  }
}