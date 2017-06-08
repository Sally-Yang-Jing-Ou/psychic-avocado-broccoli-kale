import java.io.*;
import java.util.*;
/*

1) Calculate the medians m1 and m2 of the input arrays ar1[] 
   and ar2[] respectively.

2) If m1 and m2 both are equal then we are done.
     return m1 (or m2)

3) If m1 is greater than m2, then median is present in one 
   of the below two subarrays.
    a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
    b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
4) If m2 is greater than m1, then median is present in one    
   of the below two subarrays.
   a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
   b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])

5) Repeat the above process until size of both the subarrays 
   becomes 2.
6) If size of the two arrays is 2 then use below formula to get 
  the median.
    Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2

ar1[] = {1, 12, 15, 26, 38}
ar2[] = {2, 13, 17, 30, 45}
For above two arrays m1 = 15 and m2 = 17

For the above ar1[] and ar2[], m1 is smaller than m2. So median is present in one of the following two subarrays.


[15, 26, 38] and [2, 13, 17]

O(logn)

divide and conquer 
*/

class Solution {
  public static int medianOfTwoSortedArray(int[] a1, int[] a2, int s1, int s2, int size) {
    if (size <= 0)
      return -1;
    if (size == 1)
      return (a1[s1] + a2[s2])/2;
    if (size == 2)
      return (Math.max(a1[s1], a2[s2]) + Math.min(a1[s1+1], a2[s2+1])) / 2;

    int m1 = median(a1, s1, size);
    int m2 = median(a2, s2, size);

    if (m1==m2) return m1;

    /* if m1 < m2 then median must exist in ar1[m1....] and ar2[....m2] */
    if (m1 < m2) {
      if (size % 2 == 0) { //if even length 
        return medianOfTwoSortedArray(a1, a2, size/2 - 1, s2, size - size/2 + 1); 
      }
      return medianOfTwoSortedArray(a1, a2, size/2, s2, size - size/2); 
    }

    /* if m1 > m2 then median must exist in ar1[....m1] and ar2[m2...] */
    if (size % 2 == 0) { //if even length 
      return medianOfTwoSortedArray(a1, a2, s1, size/2 - 1, size - size/2 + 1); 
    }
    return medianOfTwoSortedArray(a1, a2, s1, size/2, size - size/2);   
  }

  private static int median(int[] a, int start, int n) {
    if (n % 2 == 0) {
      return (a[start + n/2] + a[start + n/2-1])/2; 
    } else {
      return a[start + n/2];
    }
  }

  private static int medianCombine(int[] a1, int[] a2) {
    int[] sorted = new int[a1.length + a2.length];
    int i = 0;
    int j = 0;
    int index = 0;

    while (i < a1.length && j < a2.length) {
      if (a1[i] < a2[j]) {
        sorted[index]=a1[i];
        i++;
      } else {
        sorted[index]=a2[j];
        j++;
      }
      index++;
    }

    while(i<a1.length) {
      sorted[index++] = a1[i++];
    }


    while(j<a2.length) {
      sorted[index++] = a2[j++];
    }

    int n = sorted.length; 
    if (n % 2 == 0) {
      return (sorted[n/2] + sorted[n/2-1]) /2;
    } else {
      return sorted[n/2];
    }
  }


  public static void main(String[] args) {
    System.out.println(medianOfTwoSortedArray(new int[]{1, 12,14, 15, 17, 26, 38, 50},new int[]{2, 13, 14, 17, 18, 30, 45, 50}, 0 , 0, 8));
    System.out.println(medianCombine(new int[]{1, 12, 14, 15, 17, 26, 38, 50},new int[]{2, 13, 14, 17, 18, 30, 45, 50}));
    System.out.println(medianOfTwoSortedArray(new int[]{1, 50},new int[]{2, 45}, 0 , 0, 2));
    System.out.println(medianCombine(new int[]{1, 50},new int[]{2,45}));
  }
}