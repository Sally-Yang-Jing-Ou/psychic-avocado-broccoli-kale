import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 
 exponential runtime
 
 2 3 4
 
 2
 23
 234
 24
 3
 34
 4

 
 */
class Solution { 
  
  private static void products(int[] array, int index, int prod, List<Integer> list) {
    
    if (index <= array.length && index != 0) {
      list.add(prod);
    }
    
    for (int i = index; i < array.length; i++) {
      //prod = prod * array[i];
      products(prod*array[i], i+1, prod, list);
     // prod = prod / array[i];
    }
  }
  
  
  public static void main(String[] args) {
    int[] array = new int[]{2,3,5,7};
    List<Integer> list = new ArrayList<Integer>();
    
    products(array, 0, 1, list);
    
    for (Integer i : list) {
      System.out.format("%d ", i);
    }
    System.out.println("");
  }
}
    