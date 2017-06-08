import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class Solution { 
  private static void sumKLargestElements(int[] array, int target) {
    int k = quickSelect(array, 0, array.length-1, target);
    int sum = 0;
    for (int i = 0; i < k; i++) {
      System.out.println(array[i]);
      sum += array[i];
    }
    System.out.println(array[k]);

    System.out.println(sum);
  }
  
  
  private static int quickSelect(int[] array, int left, int right, int target) {
    if(left == right) return left;
   
    while(true) {
      int pivot = left + (int) Math.floor(Math.random() * (right - left + 1));
      int p = partition(array, left, right, pivot);
      if (p == target) {
        return p;
      } else if (p < target) {
        left = p + 1;
      } else {
        right = p - 1;
      }
    }
  }
  
  private static int partition(int[] array, int left, int right, int pivot) {
    int pivotVal = array[pivot];
    swap(array, pivot, right); // move pivot to end
    int index = left;
    
    for (int i = left; i < right; i++) {
      if(array[i] > pivotVal) {
        swap(array, i, index);
        index++;
      }
    }
    swap(array, right, index); //move pivot to final place
    return index;
  }
  
  private static void swap(int[] a, int left, int right) {
    int temp = a[left];
    a[left] = a[right];
    a[right] = temp;
  }
  
  public static void main(String[] args) {  
    int[] a = new int[]{3,2,1,5,6,4};
    Queue<Integer> pro = new PriorityQueue<Integer>((i, j) -> j-i);
    for(int i : a ) {
      pro.add(i);
    }
    
    int sum = 0;
    for(int i = 0; i < 4; i++ ) {
      sum += pro.poll();
    }
    System.out.println(sum);
    sumKLargestElements(a, 4);
  }

}

















