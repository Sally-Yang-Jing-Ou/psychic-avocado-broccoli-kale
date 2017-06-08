import java.io.*;
import java.util.*;

/*

           12     341      234
Billion  Million  Thousand Hundred

How many times this number can be mod by 1000, 

english = null; 

if number % 1000 ! = 0
   english = recurse on the last 3 digits (mod by 1000) + "BIG NUMBER (thousand, million, bill) " + english
   after done with last three, get the remaining so, num = num / 1000;
   
   
recurse on this
234
1.if it is zero, return ""
2.if less than 20, take it from lessthan20 map
3.if less than 100 (98), divide by 10, find the english word from tens map, recurse on the rest (mod by 10) (which is 8)
4.else, divide by 100 (234) + "hundred" keyword + recurse on the rest (mod by 100) which is 34
*/
class Solution {

  private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
  private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

  private static String intToEnglish(int num) {
    if (num == 0) {
      return "Zero";
    }
    
    String english = "";
    int i = 0;
    
    while (num > 0) {
      if (num % 1000 != 0) {
        english = intToEnglishhelper(num % 1000) + THOUSANDS[i] + " " + english;
      }
      num = num / 1000;
      i++;
    }
    
    return english.trim();
  }
  
  //three digits
  private static String intToEnglishhelper(int num) {
    if (num == 0) {
      return "";
    } else if (num < 20) {
      return LESS_THAN_20[num] + " ";
    } else if (num < 100) {
      return TENS[num / 10] + " " + intToEnglishhelper(num%10);
    } else {
      return LESS_THAN_20[num / 100] + " Hundred " + intToEnglishhelper(num%100);
    }
  }
  

  public static void main(String[] args) {
    System.out.println(intToEnglish(10000000));
  }
}