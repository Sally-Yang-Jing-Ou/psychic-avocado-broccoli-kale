import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Solution { 
  
  private static void products(int[] array) {
    int i = 0;
    int j = 0;
    int k = array.length - 1;
  
    while (j <= k) {
      switch(array[j]) {
        case 0:
          swap(array, i++, j++);
          break;
        case 1:
          j++;
          break;
        case 2: 
          swap(array, j, k--);
          break;
      } 
    }
  }
  
  private static void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }
  
  
  public static void main(String[] args) {
    int[] array = new int[]{1,2,2,1,2,2,0,1,2,0,2,1,0,0,0,0,0,0};
    products(array);
    
    for(int i: array) {
      System.out.format("%d ", i);
    }
    
    
  }
}
    