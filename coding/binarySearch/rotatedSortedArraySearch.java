import java.io.*;
import java.util.*;

/*
 
 456123
 

If we pick the middle element, we can compare the middle element with the leftmost (or rightmost) element. 


1) if the middle element is greater than the leftmost,
the right half should be selected. because that means the left half is in ascending order

2) If the middle element is less than leftmost, the left half should be selected, since
it means the right side is in ascending order

3) after we identify which side is ascending, we can then check if the target number is within that range
nums[left] <= target && target < nums[mid], then we search left, otherwise we search right

Using recursion or iteration, 
this problem can be solved in time log(n).

 */

class Solution {
  
  private static int rotatedSortedSearch(int[] nums, int left, int right, int target) {
    
    if (left > right) return -1; //doesn't exist
    
    int mid = left + (right - left)/2;
    
    if(target == nums[mid]) return mid;
    
    if (nums[left] <= nums[mid]) { //ascending array on the left
      if (nums[left] <= target && target < nums[mid] ) {
        return rotatedSortedSearch(nums, left, mid-1, target); //search left
      } else {
        return rotatedSortedSearch(nums, mid+1, right, target);
      }
    } else { //ascending array on the right
      if (nums[mid] < target && target <= nums[right]) {
        return rotatedSortedSearch(nums, mid+1, right, target); //search right
      } else {
        return rotatedSortedSearch(nums, left, mid-1, target);
      }
    }
    
  }
  
    
  private static int rotatedSortedSearchDuplicate(int[] nums, int left, int right, int target) {
    
    if (left > right) return -1;
    
    int mid = left + (right - left)/2;
    
    if(target == nums[mid]) return mid;
    
    if (nums[left] < nums[mid]) { //ascending array on the left
      if (nums[left] <= target && target < nums[mid] ) {
        return rotatedSortedSearchDuplicate(nums, left, mid-1, target); //search left
      } else {
        return rotatedSortedSearchDuplicate(nums, mid+1, right, target);
      }
    } else if (nums[left] > nums[mid]) {
      if (nums[mid] < target && target <= nums[right]) {
        return rotatedSortedSearchDuplicate(nums, mid+1, right, target);
      } else {
        return rotatedSortedSearchDuplicate(nums, left, mid-1, target);
      }
    } else {
      return rotatedSortedSearchDuplicate(nums, ++left, right, target);
    } 
    
  }
  
  public static void main(String[] args) {
    System.out.println(rotatedSortedSearchDuplicate(new int[]{4,5,6,7,1,1,1,2,3}, 0, 8, 2));
  }
}