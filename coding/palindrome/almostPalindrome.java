import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
//O(n)

// start end pointer , check char
// if mismatch, we substring(start, end) and substring(start+1, end + 1);
// if one of them is a palidrome, yes.

class Solution {
  
  public static int almostPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;
    
    for (int i = 0; i <= s.length()/2; i++) {
      if (s.charAt(i) != s.charAt(end - i)) {
        String first = s.substring(i+1, end - i + 1);
        String second = s.substring(i, end - i);
        if (isPalindrome(first)) {
          return i;
        } else if (isPalindrome(second)) {
          return end - i;
        } else {
          return -1;
        }
      }
    }
    
    return -2;
  }
  
  private static Boolean isPalindrome(String s) {
    int end = s.length() - 1;
    for (int i = 0; i <= s.length()/2; i++) {
      if (s.charAt(i) != s.charAt(end - i)) return false;
    }
    
    return  true;
  }
  
  public static void main(String[] args) {
    System.out.println(almostPalindrome("abcba"));
    System.out.println(almostPalindrome("abcbea"));
    System.out.println(almostPalindrome("abecbea"));

  }
}



// import java.io.*;
// import java.util.*;

// /*
//  * To execute Java, please define "static void main" on a class
//  * named Solution.
//  *
//  * If you need more classes, simply define them inline.
//  */

// class Solution {
  
//   public static Boolean almostPalindrome(String s) {
//     int start = 0;
//     int end = s.length() - 1;
    
//     for (int i = 0; i < s.length()/2; i++) {
//       if (s.charAt(i) != s.charAt(end - i)) {
//         String first = s.substring(i+1, end - i + 1);
//         String second = s.substring(i, end - i);
//         System.out.println(first);
//         System.out.println(second);
//         if (!isPalindrome(first) && !isPalindrome(second)) {
//           return false;
//         } else {
//           return true;
//         }
//       }
//     }
    
//     return true;
//   }
  
//   private static Boolean isPalindrome(String s) {
//     int end = s.length() - 1;
//     for (int i = 0; i < s.length()/2; i++) {
//       if (s.charAt(i) != s.charAt(end - i)) return false;
//     }
    
//     return  true;
//   }
  
//   public static void main(String[] args) {
//     System.out.println(almostPalindrome("abcba"));
//     System.out.println(almostPalindrome("abcbea"));
//     System.out.println(almostPalindrome("abecbea"));

//   }
// }
