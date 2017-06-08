import java.io.*;
import java.util.*;

class Solution {

  private static boolean oneEditAway(String s1, String s2) {
    if(s1.length() == s2.length()) {
      return isReplace(s1, s2);
    } else if (s1.length() + 1 == s2.length()) { //shorter one goes first
      return isInsert(s1, s2);
    } else if (s1.length() - 1 == s2.length()) {
      return isInsert(s2, s1);
    }

    return false;
  }

  private static boolean isReplace(String s1, String s2) {
    boolean found = false;

    for(int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (found) return false;
        found = true;
      }
    }

    return true;
  }

  private static boolean isInsert(String s1, String s2) {
  
    int i = 0;
    int j = 0;
    
    while( i < s1.length() && j < s2.length()) {
      if (s1.charAt(i) != s2.charAt(j)) {
        if(i != j) return false;
        j++;
      } else {
        i++;
        j++;
      }
    }
    return true;
  }



  public static void main(String[] args) {
    System.out.println(oneEditAway("abcd","abc"));
    System.out.println(oneEditAway("facebook","facebkok"));
    System.out.println(oneEditAway("hi","ih"));

  
  }
}

