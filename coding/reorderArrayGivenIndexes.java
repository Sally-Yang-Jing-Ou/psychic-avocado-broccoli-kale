import java.io.*;
import java.util.*;

/*
1) Do following for every element arr[i]
   a) While index[i] is not equal to i
       (i)  Store array and index values of the target (or 
            correct) position where arr[i] should be placed.
            The correct position for arr[i] is index[i]
       (ii) Place arr[i] at its correct position. Also
            update index value of correct position.
       (iii) Copy old values of correct position (Stored in
            step (i)) to arr[i] and index[i] as the while 
            loop continues for i.


Input:  arr[]   = [50, 40, 70, 60, 90]
        index[] = [3,  0,  4,  1,  2]
 
Output: arr[]   = [40, 60, 90, 50, 70]
        index[] = [0,  1,  2,  3,   4] 

50, 40, 70, 60, 90
3,  0,  4,  1,  2

essentially we want to do:

60, 40, 70, 50, 90
1,  0,  4,  3,   2


0,  1,  2,  3,   4

*/
class Solution {

  
  private static void reorderArrayGivenIndexes(int[] array, int[] index) {
    if (array.length != index.length){
      return;
    }
    
    for (int i = 0; i < array.length; i++) {
      
      if (index[i] != i) {
      
        int oldIndex = index[index[i]];
        int oldValue = array[index[i]];

        index[index[i]] = index[i];
        array[index[i]] = array[i];
        
        index[i] = oldIndex;
        array[i] = oldValue;
      }
    }
    
    for (int i = 0; i < array.length; i++) {  
      System.out.format("%d ", array[i]);
    }
    
    System.out.println("");
    
    for (int i = 0; i < array.length; i++) {  
      System.out.format("%d ", index[i]);
    }
  }
  
  public static void main(String[] args) {
    
    reorderArrayGivenIndexes(new int[]{50, 40, 70, 60, 90}, new int[]{3,  0,  4,  1,  2}); 
  }
}
