import java.io.*;
import java.util.*;

class Node {
  Node left;
  Node right;
  int val;
  Node(int v) {
    val = v;
  }
}
/*
  1. insert the middle element of array
  2. recursivly constrcut its left node by recursing on the left subarray elements
  3. recursivly constrcut its right node by recursing on the right subarray elements
*/
class Solution {
  public static Node sortedArrayToBinarySearchTree(int[] num, int start, int end) {
    if (start > end) {
      return null;
    }
    
    int mid = start + (end - start) / 2;
    Node n = new Node(num[mid]);
    n.left = sortedArrayToBinarySearchTree(num, start, mid-1);
    n.right = sortedArrayToBinarySearchTree(num, mid+1, end);
    return n;
  }

  public static void print(Node n) {
    if (n == null) {
      return;
    }
    
    print(n.left);
    System.out.println(n.val);
    print(n.right);
  }
  
  
  public static void main(String[] args) {
    Node n = sortedArrayToBinarySearchTree(new int[] {1,3,4,5,6,7,8,10}, 0, 7);
    print(n);
  }
}

