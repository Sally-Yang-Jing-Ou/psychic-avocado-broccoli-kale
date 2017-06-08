/*
To do so, in this implementation, we make use of a stack
We traverse over the nums array starting from the beginning. 
As we go on facing elements in ascending order(a rising slope), we keep on pushing the elements' 
indices over the stackstack. This is done because such elements are in the correct sorted order(as it seems till now). 
As soon as we encounter a falling slope, i.e. an element nums[j] which is smaller than the element
 on the top of the stackstack, we know that nums[j] isn't at its correct position.

In order to determine the correct position of nums[j], we keep on popping the elemnents 
from the top of the stackstack until we reach the stage where the element(corresponding to 
the index) on the top of the stack is lesser than nums[j]. Let's say the popping 
stops when the index on stackstack's top is k. Now, nums[j] has found its correct position. 
It needs to lie at an index k+1.

We follow the same process while traversing over the whole array, and determine the value of 
minimum such k. This marks the left boundary of the unsorted subarray.

Similarly, to find the right boundary of the unsorted subarray, we traverse over the numsnums
array backwards. This time we keep on pushing the elements if we see a falling slope. 
As soon as we find a rising slope, we trace forwards now and determine the larger element's correct position. 
We do so for the complete array and thus, determine the right boundary.

1 2 6 7 3 9 5 8 10



*/

import java.io.*;
import java.util.*;

class Solution {   

  public static int findUnsortedSubarray(int[] nums) {
    Stack < Integer > stack = new Stack < Integer > ();
    int l = nums.length, r = 0;

    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) //decendng slpe
        l = Math.min(l, stack.pop()); //keep going find min
      stack.push(i);
    }
    
    stack.clear();
    for (int i = nums.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) //rising slope
        r = Math.max(r, stack.pop());
      stack.push(i);
    }
    
    System.out.println(l);
    System.out.println(r);
    return r - l > 0 ? r - l + 1 : 0;
  }

  public static void main(String[] args) {
    System.out.print(findUnsortedSubarray(new int[]{1, 2, 6, 7, 3, 9, 5 ,8 ,10}));
  }
}
