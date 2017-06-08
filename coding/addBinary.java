import java.io.*;
import java.util.*;

/*

Binary digits are base 2:
so carry over is sum / 2; actual is sum % 2
  1
   1 1 1  7
     1 1  3
 1 0 1 0 

char's are actually of the same type / length as shorts. 
Now when you have a char that represents a ASCII/unicode 
digit (like '1'), and you subtract the smallest possible
ASCII/unicode digit from it (e.g. '0'), then you'll be 
left with the digit's corresponding value (hence, 1)
     1   
     1 1 1
       1 1
    ------
   1 0 1 0
1) loop through the digits from the end (start from least significant digits)
2) add the corresponding digits, append(sum % 2) to the result, and carry over (sum/2), remmeber to add the carry every
time we calculate the current sum 
3) lastly if carry is not zero, append the carry to result 
4) reverse the result

O(m+n) -> touching each digit in both string
or O(max(m,n))
*/
class Solution {
  
  private static String addBinary(String s1, String s2) {
    if (s1 == null && s2 == null || s1.isEmpty() && s2.isEmpty()) {
      return "";
    } else if (s1.isEmpty() || s1 == null) {
      return s2;
    } else if (s2.isEmpty() || s2 == null) {
      return s1;
    }
    
    StringBuilder sb = new StringBuilder();
    int i = s1.length() -1 ;
    int j = s2.length() -1 ;
    int carry = 0;
    int sum;
    
    while (i >= 0 || j >= 0) {
      sum = carry;
      // if (i >= 0) sum += s1.charAt(i--) - '0'; //turn char into digit trick  
      // if (j >= 0) sum += s2.charAt(j--) - '0';
      if (i >= 0) sum += toInt(s1.charAt(i--));
      if (j >= 0) sum += toInt(s2.charAt(j--));
      carry = sum / 2;
      sb.append(sum % 2);
    }
    
    if (carry != 0) {
      sb.append(carry); //the last carry
    }
    
    return sb.reverse().toString();
  }
  
  //alternatively
  private static int toInt (char c) {
    return Character.getNumericValue(c);
  }
  
  public static void main(String[] args) {
    System.out.println(addBinary("111", "11"));
  }
}