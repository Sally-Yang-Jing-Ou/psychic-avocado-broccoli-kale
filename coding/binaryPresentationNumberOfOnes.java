import java.io.*;
import java.util.*;


class Solution {
  
  /*
  Subtraction of 1 from a number toggles all the bits 
  (from right to left) till the rightmost set bit(including the righmost set bit). 

  So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), 
  we unset the righmost set bit. If we do n & (n-1) in a loop and count the 
  no of times loop executes we get the set bit count.
  

  4= 00100
  3= 00011
  */
  private static void num1(int num) {
    int count = 0;
    while (num > 0) {
      num = num & num-1;
      count++;
    }
  
    System.out.print(count);
  }
  
  public static void main(String[] args) {
    num1(6);
  }
}


















