import java.io.*;
import java.util.*;
/*
 a list of git commits, if a commit is bad, everything after it is bad, 
 but everything before the first bad commit is good
given a function called: bool isBadCommit(commit), that tells you if a commit is bad
make a new function that finds the first bad commit



my interpretation is that this can be a variant of binary search.

O(logn) -> only half of the array each search
*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n==1) return n;
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end-start)/2;
            if(isBadVersion(mid)) {
                end = mid; //search left
            } else {
                start = mid+1; //search right
            }
        }
        return end;
    }
}


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        return binarySearch(1,n);
    }
    
    public int binarySearch(int start, int end) {
        int middle = start + (end - start)/2;
        
        if (isBadVersion(middle)) {
            if (middle == 0 || !isBadVersion(middle-1)) {
                return middle;
            }

            return binarySearch(start,middle); //search left
        } else {
            if (isBadVersion(middle+1)) {
                return middle+1;
            }

            return binarySearch(middle + 1, end); //search right
        }
     }
}




class Solution {

  private static int firstBadCommit(int[] commits) {
    int i = 0;
    int j = commits.length-1;
    int mid;
    while (i <= j ) {
      mid = i+(j-i)/2;
      int c = commits[mid];
      if (isBadCommit(c) && (mid == 0 || !isBadCommit(commits[mid-1]))) {
        return mid;
      } else if (!isBadCommit(c)){
        i = mid + 1;
      } else {
        j = mid - 1;
      }
    }
    
    return -1;
  }

  private static boolean isBadCommit(int i) {
    if (i == 0) {
      return true;
    } else {
      return false;
    }
  }



  public static void main(String[] args) {
    System.out.println(firstBadCommit(new int[]{12,4,5,23,4,5,6,34,6,0,0,0,0,0,0}));
  }
}

