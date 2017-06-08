import java.io.*;
import java.util.*;

class Solution {

  private static void reverseString(String str) {
    char[] charA = str.toCharArray();
    reverse(charA, 0, charA.length-1);
    
    int i = 0;
    int j = 0;
    while(j <= charA.length) {
      if(j == charA.length || charA[j] == ' ') {
        reverse(charA, i, j-1);
        i=j+1;
      }
      j++;
    }
    
    System.out.println(Arrays.toString(charA));
    
  }
  
  private static void reverse(char[] charA, int i, int j) {
    while(i <= j) {
      char temp = charA[i];
      charA[i++] = charA[j];
      charA[j--] = temp;
    }
  }

  public static void main(String[] args) {
    reverseString("perfect makes practice");
  }
}