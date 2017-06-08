import java.io.*;
import java.util.*;

class Solution {

  private static int moveZerosToTheRightEnd(int[] nums) {
    if(nums == null || nums.length == 0) return 0;

    int j = 0;
    for(int i = 0; i < nums.length; i++) {
      if(nums[i] != 0) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
        j++;
      }
    }

    for(int i : nums) {
      System.out.format("%d ", i);
    }
    System.out.println("");
    return j; //size of non zeros, it's j because j only moves by one if the element isn't zero
  }

  private static int moveZerosToTheLeftBeginning(int[] nums) {
    if(nums == null || nums.length == 0) return 0;

    int j = nums.length-1;
    for(int i = nums.length - 1; i>=0; i--) {
      if(nums[i] != 0) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
        j--;
      }
    }

    for(int i : nums) {
      System.out.format("%d ", i);
    }
    System.out.println("");
    return nums.length - j -1; //size of non zeros, it's nums.length - j -1 because j only moves down by one if the element isn't zero, it will be pointing at the index of the last zero element
  }

  public static void main(String[] args) {
    System.out.println(moveZerosToTheRightEnd(new int[]{1,4,0,30,24,0,0,2,0}));
    System.out.println(moveZerosToTheLeftBeginning(new int[]{1,4,0,30,24,0,0,2,0}));
    System.out.println(moveZerosToTheLeftBeginning(new int[]{0,0,0,0,0}));
    System.out.println(moveZerosToTheRightEnd(new int[]{0,0,0,0,0}));

  }
}