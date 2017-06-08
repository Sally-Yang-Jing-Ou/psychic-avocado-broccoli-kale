import java.io.*;
import java.util.*;

/*

1) If all digits sorted in descending order, then output is always “Not Possible”. For example, 4321.
2) If all digits are sorted in ascending order, then we need to swap last two digits. For example, 1234.
3) For other cases, we need to process the number from rightmost side (why? because we need to find the smallest of all greater numbers)

You can now try developing an algorithm yourself.

Following is the algorithm for finding the next greater number.
I) Traverse the given number from rightmost digit, keep traversing 
till you find a digit which is smaller than the previously traversed digit. 
For example, if the input number is “534976”, we stop at 4 because 4 is 
smaller than next digit 9. If we do not find such a digit, then output is “Not Possible”. find a decending slope

II) Now search the right side of above found digit ‘d’ for the smallest 
digit greater than ‘d’. For “534976″, the right side of 4 contains “976”. 
The smallest digit greater than 4 is 6.

III) Swap the above found two digits, 
we get 536974 in above example.

IV) Now sort all digits from position next to ‘d’ to the end of number. 
The number that we get after sorting is the output. For above example, 
we sort digits in bold 536974. We get “536479” which is the next greater number for input 534976.



 1234 //no more smallest 
 4321 // swap last two

 846357 //next smallest, find a rising slope. found 6 digit 'd'
 84     // find the biggest digit smaller than 6, 5
 845367 //swap them
 845763 //sort the remaining next to digit d in decending order
*/


class reverseComp implements Comparator<Character> {

  @Override
  public int compare(Character one, Character two) {
    return two.compareTo(one);
  }
}

class Solution { 

  private static void nextBiggestNumber(int num) {
    char[] nums = String.valueOf(num).toCharArray();
    int i = nums.length;
    for (i = nums.length - 1; i > 0; i--) {
      if (nums[i] > nums[i-1]) { //find a decending slope
        break;
      }
    }

    if (i == 0) {
      System.out.println("already biggest");
      return;
    }

    //find the smallest on the right side that is bigger than nums[i-1]
    int smallest = i, x = nums[i-1];
    for (int begin = i; begin < nums.length; begin ++ ) {
      if (x < nums[begin] && nums[begin] < nums[smallest]) {
        smallest = begin;
      }
    }

    char temp = nums[smallest];
    nums[smallest] = nums[i-1];
    nums[i-1] = temp;

    //sort in ascending order
    Arrays.sort(nums, i, nums.length);

    for(char j : nums) {
      System.out.print(j);
    }
  }

  private static void nextSmallestNumber(int num) {
    // char[] numsChar = String.valueOf(num).toCharArray();
    Character[] nums = 
      String.valueOf(num).chars().mapToObj(c -> (char)c).toArray(Character[]::new); 

    int i = nums.length;
    for (i = nums.length - 1; i > 0; i--) {
      if (nums[i-1] > nums[i]) { //find a rising slope
        break;
      }
    }

    if (i == 0) {
      System.out.println("already smallest"); //sorted in acending order
      return;
    }

    //find the biggest on the right side that is smaller than nums[i-1]
    int biggest = i, x = nums[i-1];
    for (int begin = i; begin < nums.length; begin ++ ) {
      if (x > nums[begin] && nums[begin] > nums[biggest]) {
        biggest = begin;
      }
    }
    //swap
    char temp = nums[biggest];
    nums[biggest] = nums[i-1];
    nums[i-1] = temp;

    //sort in descending order, actually do not sort them
    //just reverse the digits!!! they're originally in ascending order
    //how did you miss that trick sally... just reverse... don't sort!!!!!
    reverse(i, nums.length);
   // Arrays.sort(nums, i, nums.length, new reverseComp());

    for(char j : nums) {
      System.out.print(j);
    }
  }

  public static void main(String[] args) {
    nextBiggestNumber(386886);
    System.out.println("");
    nextSmallestNumber(594789);
  }
}
