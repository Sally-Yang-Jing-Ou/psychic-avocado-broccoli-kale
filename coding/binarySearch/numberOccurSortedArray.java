import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Node {
  Node right;
  Node left;
  int val;
  Node(int v) { 
    val = v; 
    left = right = null; 
  }
}




class Solution { 

  
    private static int firstOccurrence(int start, int end, int target, int[] array) {
    if (end>= start) {
      int mid = (start + end)/2;
      if ( target == array[mid] && (mid == 0 || target > array[mid-1]) ) {
        return mid;
      } else if (target > array[mid]) {
        return firstOccurrence(mid + 1, end, target, array);
      } else {
        return firstOccurrence(start, mid-1, target, array);
      }
    }
    return -1;
  }
 
  private static int lastOccurrence(int start, int end, int target, int[] array) {
    if (start <= end) {
      int mid = (start + end)/2;
      if ( target == array[mid] && (mid == array.length-1 || target < array[mid+1]) ) {
        return mid;
      } else if (target < array[mid]) {
        return lastOccurrence(start, mid-1, target, array);
      } else {
        return lastOccurrence(mid + 1, end, target, array);
      }
    }
    return -1;
  }
  
  public static void main(String[] args) {
    int[] array = new int[]{1,1,1,2,2,3,4,5,6,7};
    
    int first = firstOccurrence(0, array.length-1, 8, array);

    if (first == -1) {
      System.out.print("no such number");
      return;
    }
    
    int last =  lastOccurrence(0, array.length-1, 8, array);

    System.out.print(last-first+1);            
  }

  
}
