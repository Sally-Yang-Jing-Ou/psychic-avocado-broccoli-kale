import java.io.*;
import java.util.*;


class Entry {
  int index;
  int[] array;

  Entry(int i, int[] arr) {
    index = i;
    array = arr;
  }

  public int value() {
    return array[index];
  }
}
/*
n number of elements in each array
k number of total arrays
O(n*kLogk)
*/
class Solution {

  private static int[] mergeKSortedArrays(int[][] array) {
    if (array == null || array.length == 0) return null; 

    PriorityQueue<Entry> pq = new PriorityQueue<Entry>((x,y) -> x.value() - y.value());
    int total = 0;

    //add arrays to heap
    for (int i = 0; i < array.length; i++) {
      pq.add(new Entry(0, array[i]));
      total = total + array[i].length;
    }

    int[] results = new int[total];
    int i = 0;
    while(!pq.isEmpty()) {
      Entry e = pq.poll();
      results[i++] = e.value();

      if(e.index < e.array.length-1){
        e.index++;
        pq.offer(e);
      }
    }

    return results; 
  }

  public static void main(String[] args) {
    int[] arr1 = { 1, 3, 5, 7, 9, 10, 15 };
    int[] arr2 = { 2, 4, 6, 8 };
    int[] arr3 = { 0, 9, 10, 11 };
    
    int[] result = mergeKSortedArrays(new int[][] { arr1, arr2, arr3 });
    System.out.println(Arrays.toString(result));
  }
}

