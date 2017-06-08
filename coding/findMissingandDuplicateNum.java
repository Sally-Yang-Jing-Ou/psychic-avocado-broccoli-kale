import java.io.*;
import java.util.*;

/*

You have a list of consecutive integers, 
in no particular order. 
There is no guarantee on the range of these integers or the number of elements in the list.

This list is missing one integer and contains a duplicate of another integer.

An example of such a list is {16, 12, 13, 17, 14, 13};
in this case, the missing integer is 15 and the duplicated is 13.

solution 1
sort it, scan it, expect = first element, increment it, if missing, put it as missing vairable, 
if duplicate, put it in duplicate variable

solution 2:
For example, 
measure sum of values and sum of squares and compare them with expected ones. 
If number A is duplicated and number B is missing, you will have:

sum - expected_sum=A-B
sum_of_squares - expected_sum_of_squares=A^2-B^2
Having (A-B) and (A^2-B^2) you can get (A+B)=(A^2-B^2)/(A-B).

Having (A+B) and (A-B) you can get A=(A+B)/2+(A-B)/2 and B=(A+B)/2-(A-B)/2

mathematically - 

sum of a range of number =
(max-min+1)(min+max) / 2

just do some math

1. scan through array, compute sum, find min and max
2. compute expected sum
3. say A is duplicate, B is missing
we have, sum - A + B = expected => sum - expected = A - B
and we have
sum of the square of each number - A^2 + B^2 = sum of the square of each expected nunmber =>
sum_of_squares - expected_sum_of_squares = A^2 - B^2 


sum - expected_sum=A-B = x
sum_of_squares - expected_sum_of_squares=A^2-B^2 = y

 A^2 - B^2 = (A - B)(A + B)
 A^2 - B^2 / A - B = A + B = y / x = z
 
A = (A+B)/2+(A-B)/2 and B=(A+B)/2-(A-B)/2
A = z/2 + x/2 
B = z/2 - x/2
*/
class Solution {
  
  private static int sum(int min, int max) {
    return (max-min+1)*(min+max)/2;
  }
  
  private static void findMissingandDuplicateNum(int[] nums) {
    int dup = 0, missing = 0;
    Arrays.sort(nums);
    int expect = nums[0];
    for(int i = 0; i < nums.length; i++){
      if(i!=0 && nums[i-1] == nums[i]){
        dup = nums[i];
        expect--;
      }
      if(expect != nums[i]){
        missing = expect;
        expect++;
      }
      expect++;
    }
    System.out.format(" %d %d", dup, missing);
  }
  
  private static void findMissingandDuplicateNum2(int[] nums) {
    int min = nums[0];
    int max = nums[0];
    
    int duplication, missing;
    
    int actualSum = nums[0];
    int expectedSum = 0;
    
    int squareSum = nums[0]*nums[0];
    int expectedSquareSum = 0;
    
    for(int i = 1; i < nums.length; i++) {
      actualSum += nums[i];
      squareSum += nums[i]*nums[i];
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]); 
    } //o(N)
    
    expectedSum = sum(min, max);
    for(int i = min; i <= max; i++) {
      expectedSquareSum += i*i;
    } //o(N)
    
    int x = actualSum - expectedSum; //sum - A + B = expected => sum - expected = A - B
    int y = (squareSum - expectedSquareSum); //sum_of_squares - expected_sum_of_squares = A^2 - B^2 
    int z = y/x; //  A^2 - B^2 / A - B = A + B
    duplication = z/2 + x/2;
    missing = z/2 - x/2;
    
    System.out.format(" %d %d %d %d %d", duplication, missing, x, y, z);
    
  }
  
  public static void main(String[] args) {
    findMissingandDuplicateNum2(new int[]{16, 12, 15, 17, 13, 18, 18});
  }
}
