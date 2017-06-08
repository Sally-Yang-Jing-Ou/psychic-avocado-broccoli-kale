import java.io.*;
import java.util.*;

class Solution {
  
  //O(n*times) 
  private static void rightRotate(int[] array, int times) {
    for (int i = 0; i < times%array.length; i++) {
      int temp = array[array.length-1];
      int j;
      for (j = array.length-1; j > 0; j--) {
        array[j] = array[j-1];
      }
      array[j] = temp;
    }
    
    for(int i : array) {
      System.out.format("%d ", i);
    }
  }

  //The first step moves the first k elements move to the end, and the next two steps put elements in the right order.
  
  private static void rightRotate2(int[] array, int times) {
    times = times % array.length;
    reverseArray(array, 0 , array.length-1);
    reverseArray(array, 0, times - 1);
    reverseArray(array, times, array.length-1);
    
    for(int i : array) {
      System.out.format("%d ", i);
    }
  }
  
  private static void reverseArray(int[] array, int i, int j) {
    while(i < j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }
  }
  
  
  public static void main(String[] args) {
    rightRotate2(new int[]{1,2,3,4,5,6}, 5);
  }
}














