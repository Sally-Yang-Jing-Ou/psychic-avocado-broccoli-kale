import java.io.*;
import java.util.*;

class Solution {

  private static void multiply(String first, String second) {

    if (first.length() > second.length()){
      //ensure first num has equal or less length than second
      String temp = first;
      first = second;
      second = temp;
    }

    // Reverse so that the least-significant digit is at index 0.
    first = new StringBuffer(first).reverse().toString();
    second = new StringBuffer(second).reverse().toString();

    String result = "";

    for (int i = 0; i < first.length(); i++) {
      char c = first.charAt(i);
      int n = toInt(c);

      // skip zeros
      if (n==0) continue;

      // Pad the intermediate result with the necessary number of zeros.
      // This will store the product of first[i] * second.
      String intermediate = String.join("", Collections.nCopies(i, "0"));

      int carry = 0;
      
      // Multiply first[i] with the second number and store it into `intermediate`.
      for (int j = 0; j < second.length(); j++) {
        int current = toInt(second.charAt(j));
        int product = n * current + carry;
        intermediate = intermediate + toChar(product % 10);
        carry = product / 10;
      }

      if (carry != 0) {
        intermediate = intermediate + toChar(carry);
      }

      // Add `intermediate` to `result`. `result` is guaranteed to have equal or less digits than `intermediate`.
      char[] resultArray = result.toCharArray();
      carry = 0;
      for (int j = 0; j < resultArray.length; j++) {
        int sum = toInt(resultArray[j]) + toInt(intermediate.charAt(j)) + carry;
        resultArray[j] = toChar(sum % 10);
        carry = sum / 10;
      }
      result = String.valueOf(resultArray);

      for (int j = result.length(); j < intermediate.length(); j++) {
        int sum = toInt(intermediate.charAt(j)) + carry;
        result = result + toChar(sum % 10);
        carry = sum / 10;
      }

      if(carry != 0) {
        result = result + toChar(carry);
      }
    }

    String reverseResult = new StringBuffer(result).reverse().toString();
    System.out.println(reverseResult);
  }

  private static int toInt (char c) {
    return Character.getNumericValue(c);
  }

  private static char toChar (int i) {
    return Character.forDigit(i, 10);
  }

  private static void addition(String first, String second) {
    if (first.length() > second.length()){
      //ensure first num has equal or less length than second
      String temp = first;
      first = second;
      second = temp;
    }

    // Reverse so that the least-significant digit is at index 0.
    first = new StringBuffer(first).reverse().toString();
    second = new StringBuffer(second).reverse().toString();
    
    int diff = second.length() - first.length();
    if (diff > 0) {
      //pad the end with zeros
      first = first + String.join("", Collections.nCopies(diff, "0"));
    }
    
    String result = "";
    int carry = 0;
    for (int i = 0; i < first.length(); i++) {
      int sum = toInt(first.charAt(i)) + toInt(second.charAt(i)) + carry;
      result = result + toChar(sum % 10);
      carry = sum / 10;
    }
    if (carry != 0) {
      result = result + toChar(carry);
    }
    
    String reverseResult = new StringBuffer(result).reverse().toString();
    System.out.println(reverseResult);
  
  }

  public static void main(String[] args) {
    multiply("11111111111111111", "11111111111111");
    addition("2520", "126800");
  }
}














