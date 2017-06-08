import java.io.*;
import java.util.*;

class Solution {

  //no need to copy contents of A when elements in B are ran out, because
  //contents of A are already in A
  void merge(int[] a, int[] b, int n) {
    int indexA = n-1;
    int indexB = n-1; //last index element in array b
    int indexMerge = 2*n - 1; //end of merged array
    
    while(indexB >= 0) {
      if(indexA >= 0 && a[indexA] > b[indexB]) {
        a[indexMerge--] = a[indexA--];
      } else {
        a[indexMerge--] = b[indexB--];
      }
    }
  }

  public static void main(String[] args) {
  
  }
}

