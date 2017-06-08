import java.io.*;
import java.util.*;


class Solution {

  public static int removeDuplicates(int nums[]) {
    if(nums.length < 2) return nums.length;

    int i = 0;

    for(int j = 1; j < nums.length; j++) {
      if(nums[i] != nums[j]) {
        i++;
        nums[i] = nums[j];
      }
    }

    int[] A = Arrays.copyOfRange(nums, 0, i+1);

    for(int k : A) {
      System.out.format("%d ", k);
    }

    System.out.println("");
    return i+1;
  }

  // Count the number of unique elements
  public static int countUnique(int[] A) {
    int count = 0;
    for (int i = 0; i < A.length - 1; i++) {
      if (A[i] == A[i + 1]) {
        count++;
      }
    }
    return (A.length - count);
  }


  public static void main(String[] args) {
    System.out.println(removeDuplicates(new int[]{1,2,3,3,4,5,5,5}));
  }
}