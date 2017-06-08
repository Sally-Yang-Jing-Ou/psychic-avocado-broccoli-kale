import java.io.*;
import java.util.*;

/*

    123456
    
    456123
      ^ 
    612345
      ^
This problem is a binary search and the key is breaking the array to two parts, 
so that we only need to work on half of the array each time.

If we pick the middle element, we can compare the middle element with the leftmost (or rightmost) element. 


1) if the middle element is greater than the leftmost,
the right half should be selected. because that means the left half is in ascending order

2) If the middle element is less than leftmost, the left half should be selected, since
it means the right side is in ascending order


Using recursion or iteration, 
this problem can be solved in time log(n).

In addition, in any rotated sorted array, the rightmost element should be less than the left-most 
element, otherwise, the sorted array is not rotated and we can simply 
pick the leftmost element as the minimum.

*/

class Solution {
  
  private static int rotatedSortedMin(int[] nums, int left, int right) {
    if (right == left) return nums[right];
    if ((right-left) == 1) return Math.min(nums[left], nums[right]);
    if (nums[left] < nums[right]) return nums[left]; // not rotated
    
    int mid = left + (right-left)/2;
    if (nums[left] < nums[mid]) { //search right, since left to mid is sorted in ascending order
      return rotatedSortedMin(nums, mid, right);
    } else {
      return rotatedSortedMin(nums, left, mid);
    }
  }
  
  private static int rotatedSortedIndexHowManyTimesItHasBeenRotated(int[] nums, int left, int right) {
    if (right == left) return right;
    
    if ((right-left) == 1) {
      if(nums[left] > nums[right]) {
        return right;
      } else {
        return left;
      }
    }
    
    if (nums[left] < nums[right]) return left; // not rotated
    
    int mid = left + (right-left)/2;
    if (nums[left] < nums[mid]) {
      return rotatedSortedIndexHowManyTimesItHasBeenRotated(nums, mid, right);
    } else {
      return rotatedSortedIndexHowManyTimesItHasBeenRotated(nums, left, mid);
    }
  }

  /*
  find min with duplicates
  */
  public int findMin(int[] num) {
    return findMin(num, 0, num.length-1);
  }
 
  public int findMin(int[] num, int left, int right){
    if(right==left){
        return num[left];
    }
    if(right - left == 1){
        return Math.min(num[left], num[right]);
    }
    // 3 3 1 3 3 3
 
    int middle = (right-left)/2 + left;
    // already sorted
    if(num[right] > num[left]){
        return num[left];
    //right shift one
    }else if(num[right] == num[left]){
        return findMin(num, left+1, right); //can't really tell, just shift right one
    //go right    
    }else if(num[middle] >= num[left]){ //since the left side is in ascneding order
        return findMin(num, middle, right);
    //go left    
    }else{
        return findMin(num, left, middle);
    }
  }
  public static void main(String[] args) {
    System.out.println(rotatedSortedIndexHowManyTimesItHasBeenRotated(new int[]{4,5,6,7,1,2,3}, 0, 6));
  }
}