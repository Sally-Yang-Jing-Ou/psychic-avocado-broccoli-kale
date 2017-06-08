import java.io.*;
import java.util.*;

/*

1. If A[i] is smallest among all end 
   candidates of active lists, we will start 
   new active list of length 1.
2. If A[i] is largest among all end candidates of 
  active lists, we will clone the largest active 
  list, and extend it by A[i].

3. If A[i] is in between, we will find a list with 
  largest end element that is smaller than A[i]. 
  Clone and extend this list by A[i]. We will discard all
  other lists of same length as that of this modified list.
  

  2, 10, 6, 9, 1

result 2    length 1
result 2 10 length 2
result 2 6 length 2
result 2 6 9 length 3
*/


class Solution { 

  private static void longestIncreasingSubsequence(int[] array) {
    if (array.length == 0) {
      System.out.println("empty string");
      return;
    }

    int[] result = new int[array.length]; 
    int len = 1;
    result[0] = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] < result[0]) {
        result[0] = array[i];
      } else if (array[i] > result[len-1]) {
        result[len] = array[i]; 
        len++;
      } else {
        int replace = binarySearch(result, -1, len-1, array[i]);
        result[replace] = array[i];
      }
    }

    System.out.println(len);
  }
  
  private static int binarySearch(int[] array, int left, int right, int key) {
    while (right - left > 1) {
      int mid = left + (right - left)/2;
      if (array[mid] >= key) {
        right = mid;
      } else {
        left = mid;
      } 
    }
    return right;
  }
  
    private static int binarySearch(int[]array, int[] temp, int left, int right, int key) {
    while (right - left > 1) {
      int mid = left + (right - left)/2;
      if (array[temp[mid]] >= key) {
        right = mid;
      } else {
        left = mid;
      } 
    }
    return right;
  }

/*         0   1  2  3  4 index
           2, 10, 6, 9, 1

temp index 0 1 2 3 4
           1 2 3  

result index 0  1 2 3 4
             -1 0 0 2 
*/
  private static void longestIncreasingSubsequenceString(int[] array) {
    //the index of minimum of the last value of an increasing subsequence of the length
    int[] temp = new int[array.length];
    // use for backtracking
    int[] result = new int[array.length];
    Arrays.fill(result, -1);
    temp[0] = 0;
    int len = 1;
    
    for (int i = 1; i < array.length; i++) {
      if (array[i] < array[temp[0]]) {
        temp[0] = i;
      } else if (array[i] > array[temp[len-1]]) {
        temp[len] = i;
        result[i] = temp[len-1];
        len++;
      } else {
        int replace = binarySearch(array, temp, -1, len-1, array[i]);
        temp[replace] = i;
        result[i] = temp[replace-1];
      }
    }
    
    for (int i = temp[len-1]; i != -1; i = result[i]) {
      System.out.println(array[i]);
    } 
  }

/*
Let max[i] represent the length of the longest increasing subsequence so far.
If any element before i is smaller than nums[i], then max[i] = max(max[i], max[j]+1).

max[i] = number of elements that are smaller than i before i

*/
  public int lengthOfLIS(int[] nums) {
    if(nums==null || nums.length==0)
        return 0;
 
    int[] max = new int[nums.length];
    Arrays.fill(max, 1);
 
    int result = 1;
    for(int i=0; i<nums.length; i++){
        for(int j=0; j<i; j++){
            if(nums[i]>nums[j]){
                max[i]= Math.max(max[i], max[j]+1);
 
            }
        }
        result = Math.max(max[i], result);
    }
 
   return result;
}
  
  public static void main(String[] args) {
    int[] array = new int[]{12,22,-1, 1,1,2,-4,5,45,3,4,5,6,0,2,77};
    longestIncreasingSubsequenceString(array);
  }
}
