import java.io.*;
import java.util.*;



/*

O(n*n!) Note that there are n! permutations and it requires O(n) time to print a a permutation.
http://www.geeksforgeeks.org/wp-content/uploads/NewPermutation.gif
*/
class Solution {
  
  private static void permutation(String str, int index, Set<String> set) {
    if (index == str.length() - 1) {
      set.add(new String(str));
      return;
    }
    
    for (int i = index; i < str.length(); i++) {
      str = swap(str, index, i);
      permutation(str, index+1, set);
      str = swap(str, index, i);
    }
  }
  
  private static String swap(String str, int i, int j) {
    char[] a = str.toCharArray();
    char temp = a[i];
    a[i] = a[j];
    a[j] = temp;
    return String.valueOf(a);
  }
  
  public static void main(String[] args) {
    Set<String> set = new TreeSet<String>();
    permutation("aab", 0, set);
    
    for (String s: set) {
      System.out.println(s);
    }
  }
}


import java.io.*;
import java.util.*;

//string permutation 

/*
 abc

 a -> a
 ab -> ab, ba
 abc -> abc, acb, cab, bac, bca, cba
insert char at every possible location of previous permutation 

 //make an empty set
 //insert one element in "", a
 
 //insert another element at every possible location of the string in the previous set
 
 O(n!)
*/
class Solution {

  private static ArrayList<String> permutation(String str) {
    if (str == null) {
      return null;
    }
    
    ArrayList<String> set = new ArrayList<String>();
    if (str.length() == 0) {
      set.add("");
      return set;
    } else {
      char c = str.charAt(0);
      String s = str.substring(1); //remove first char
      ArrayList<String> list = permutation(s);
      
      for (String word: list) {
        for (int i = 0; i <= word.length(); i++) {
          String newS = insertChar(word, c, i);
          set.add(newS);
        }
      }
    }
    
    return set;
  }

  private static String insertChar(String str, char c, int i) {
    String start = str.substring(0,i);
    String end = str.substring(i);
    return start + c + end;
  }

  public static void main(String[] args) {
    ArrayList<String> a = permutation("abc");
    for (String s: a) {
      System.out.println(s);
    }
  }
}


import java.io.*;
import java.util.*;

//string permutation 

/*
aaaaaaa

Generate unique ones in the first place.
Choose prefix, subproblems to solve, find all permutation of remaining characters
append the remaining to already picked prefix.

Use a hashmap to remember the count of repeated characters

O(n!)

a->2 | b->1 | c->3 

P(a->2 | b->1 | c->3 ) =  { a + a->1 | b->1 | c->3 } +
                          { b + a->2 | b->0 | c->3 } +
                          { c + a->2 | b->1 | c->2 }
*/

class Solution {
  
  private static void permutation(String str) {
    Map<Character, Integer> map = charCount(str);
    ArrayList<String> result = new ArrayList<String>();
    permutation(map, "", str.length(), result);
    
    for (String s: result) {
      System.out.println(s);
    }
  }
  
  private static void permutation(Map<Character, Integer> map, String prefix, int len, ArrayList<String> res) {
    
    if(prefix.length() == len) {
      res.add(prefix);
      return;
    }
    
    for (Character c: map.keySet()) {
      int count = map.get(c);
      if (count > 0) {
        map.put(c, count - 1);
        permutation(map, prefix + c, len, res);
        map.put(c, count);
      }
    }
  }
  
  private static Map<Character, Integer> charCount(String str) {
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for (int i = 0; i < str.length(); i++ ) {
      char c = str.charAt(i);
      if (!map.containsKey(c)){
        map.put(c, 0);
      }
      map.put(c, map.get(c) + 1);
    }
    return map;
  }
  
  public static void main(String[] args) {
    permutation("abccc");
   
  }
}





















































