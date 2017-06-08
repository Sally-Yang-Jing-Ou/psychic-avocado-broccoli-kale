import java.io.*;
import java.util.*;

/*
We traverse all bars from left to right, maintain a stack of bars. Every bar is pushed to stack once. A bar is popped from stack when a bar of smaller height is seen. When a bar is popped, we calculate the area with the popped bar as smallest bar. How do we get left and right indexes of the popped bar – the current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’. Following is the complete algorithm.

1) Create an empty stack.

2) Start from first bar, and do following for every bar ‘hist[i]’ where ‘i’ varies from 0 to n-1.
……a) If stack is empty or hist[i] is higher than the bar at top of stack, then push ‘i’ to stack.
……b) If this bar is smaller than the top of stack, then keep removing the top of stack while top 
of the stack is greater. Let the removed bar be hist[tp]. Calculate area of rectangle with hist[tp] 
as smallest bar. For hist[tp], the ‘left index’ is previous (previous to tp) item in stack and ‘right index’ is ‘i’ (current index).

3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.


*/

class Solution {
  
  private static void largestRectArea(int[] nums) {
    Stack<Integer> st = new Stack<Integer>();
    int maxArea = 0;
    int area = 0;
    int i = 0;
    while (i < nums.length) {
      // If this bar is higher than the bar on top stack, push it to stack
      if (st.isEmpty() || nums[st.peek()] <= nums[i]) {
        st.push(i);
        i++;
      } else {
         // If this bar is lower than top of stack, then calculate area of rectangle 
        // with stack top as the smallest (or minimum height) bar. 'i' is 
        // 'right index' for the top and element before top in stack is 'left index'
        Integer tp = st.pop();
        int width = st.isEmpty() ? i : i - st.peek() - 1;
        area = nums[tp] * width;
        maxArea = Math.max(area, maxArea);
      }
    }
    
    while (!st.isEmpty()) {
      Integer tp = st.pop();
      int width = st.isEmpty() ? i : i - st.peek() - 1;
      area = nums[tp] * width;
      maxArea = Math.max(area, maxArea);
    }
    
    System.out.println(maxArea);
  }
  
  public static void main(String[] args) {
    largestRectArea(new int[]{6, 2, 5, 4, 5, 1, 6});
  }
}

















