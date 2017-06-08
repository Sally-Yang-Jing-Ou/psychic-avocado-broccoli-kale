import java.io.*;
import java.util.*;

/*
1) make sure the diff in height of left subtree and right subtree is less than or equal to one

2) carry the height while traversing 

O(n) touching each node once
*/
class Node {
  Node left;
  Node right;
}

class Solution {
  
  public int checkHeight(Node root) {
    if (root == null) return 0;
    
    int left = checkHeight(root.left);
    if (left == -1) {
      return -1;
    }
    
    int right = checkHeight(root.right);
    if (right == -1) {
      return -1;
    }
    
    int heightDiff = left - right;
    if (Math.abs(heightDiff) > 1) {
      return -1;
    } else {
      return Math.max(left, right) + 1;
    }
  }
  
  public static void main(String[] args) {
  }
}
