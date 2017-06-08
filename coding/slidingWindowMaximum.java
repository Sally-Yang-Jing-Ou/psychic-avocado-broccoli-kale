/*

At each i, we keep "promising" elements, which are potentially max number in window [i-k+1,i] or any subsequent window. This means

If an element in the deque and it is out of i- k + 1, we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

Now only those elements within [i-k+1,i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i],
or any other subsequent window: a[i] would always be a better candidate.

As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]


*/
import java.io.*;
import java.util.*;

class Solution {
  
  public static int[] maxSlidingWindow(int[] a, int k) {    
    if (a == null || k <= 0) {
      return new int[0];
    }
    int n = a.length;
    int[] r = new int[n-k+1];
    int ri = 0;
    // store index
    Deque<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < a.length; i++) {
      // remove numbers out of range k
      while (!q.isEmpty() && q.peek() < i - k + 1) {
        q.poll();
      }
      // remove smaller numbers in k range as they are useless
      while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
        q.pollLast();
      }
      // q contains index... r contains content
      q.offer(i);
      if (i >= k - 1) {
        r[ri++] = a[q.peek()];
      }
    }
    return r;
  }

  public static void main(String[] args) {

  }
}