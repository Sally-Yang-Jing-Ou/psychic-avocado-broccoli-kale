import java.io.*;
import java.util.*;

class Solution {

  /*
    abc
    a 
    ab
    abc
    ac
    b
    bc
    c

    sort it:
    aab
    
    a
    aa
    aab
    ab
    b
  */

  private static void subsetOfString(String str) {
    Set<String> res = new HashSet<String>();
    
    char[] a = str.toCharArray();
    Arrays.sort(a);
   
    subsetOfString(str.toCharArray(), "", 0, res);

    for (String s: res) {
      System.out.println(s);
    }
  }

  private static void subsetOfString(char[] str, String current, int index, Set<String> res)   {

    if (index <= str.length && index!=0) {
      res.add(new String(current));
    }

    for (int i = index; i < str.length; i++ ) {
      current = current + str[i];
      subsetOfString(str, current, i+1, res);
      current = current.substring(0, current.length()-1);
    }

  }

  public static void main(String[] args) {
    subsetOfString("aba");

  }
}


  private static void generateSubsetSizeK(int[] set, int index, int targetSize, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> finalResults) {
    
    if(current.size() == targetSize) {
      finalResults.add(new ArrayList<Integer>(current));
      return;
    }
    
    for (int i = index; i < set.length; i++) {
      current.add(set[i]);
      generateSubsetSizeK(set, i+1, targetSize, current, finalResults);
      current.remove(current.size()-1);
    }
    
    return;
  }


  private static void subsetMinMax(int[] set, int target, int index,  ArrayList<Integer> current) {
    
    if(current.size() >= 1) {
      if (current.get(0) + current.get(current.size()-1) <= target) {
        count++;
      }
    }
    
    for (int i = index; i < set.length; i++) {
      current.add(set[i]);
      subsetMinMax(set, target, i+1, current);
      current.remove(current.size()-1);
    }
  }


import java.io.*;
import java.util.*;
/*
aab

a
aa
aab
ab
b

*/
class Solution { 

  private static void subsetOfString(char[] str, ArrayList<String> current, int index, HashSet<ArrayList<String>> res)   {

    if (index <= str.length && index!=0) {
      res.add(new ArrayList<>(current));
    }

    for (int i = index; i < str.length; i++ ) {
      current.add(Character.toString(str[i]));
      subsetOfString(str, current, i+1, res);
      current.remove(current.size()-1);
    }

  }

  private static void subsetOfString(String str) {
    HashSet<ArrayList<String>> res = new HashSet<ArrayList<String>>();

    char[] a = str.toCharArray();
    Arrays.sort(a);

    subsetOfString(a, new ArrayList<String>(), 0, res);

    for (ArrayList<String> s: res) {
      System.out.println(s.toString());
    }
  }

  public static void main(String[] args) {
    subsetOfString("aba");
  }
}
















