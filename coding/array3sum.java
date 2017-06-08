import java.io.*;
import java.util.*;

/*
 1 1 2 3 4 5 6 7
 ^   ^       ^
 f
 */

// O(n^2)
// sort
// fix one pointer i,
// use one start and end pointer to find two other numbers 
class Solution {
  
  public static List<List<Integer>> array3Sum(int[] array, int target) {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();
    
    if (array == null || array.length < 3) return lists;
    
    Arrays.sort(array); //sort so we can run 2 sum
    for (int i = 0; i < array.length - 2; i++) {
      
      if ( i == 0 || array[i] > array[i-1]) { //make sure the elements aren't the same
        int j = i + 1;
        int end = array.length - 1;
      
        while (j < end) {
          if (array[i] + array[j] + array[end] == target) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(array[i]);
            list.add(array[j]);
            list.add(array[end]);
            lists.add(list);
            
            j++;
            end--;
          } else if (array[i] + array[j] + array[end] < target) {
            j++;
          } else {
            end--;
          } 
        }
      }  
    }
      
    
    return lists;
  }
  
  public static void main(String[] args) {
    System.out.println(array3Sum(new int[]{-1,0,1,2,-1,-4}, 0));
    System.out.println(array3Sum(new int[]{1,2,1,2,3,4,5}, 7));

  }
}
