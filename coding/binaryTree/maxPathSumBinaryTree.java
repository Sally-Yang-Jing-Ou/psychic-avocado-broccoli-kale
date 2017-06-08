import java.io.*;
import java.util.*;

class Node {
  Node right;
  Node left;
  int val;
  Node(int v) { 
    val = v; 
    left = right = null; 
  }
}

/*

compute the maximum sum in a post order manner

for each node, max sum can either reside in
1) left subtree + node itself
2) right subtree + node itself
3) node.val itself
4) left + right + node.val itself


1 recursively compute the sum from left subtree and right subtree
2 when the call comes back, compute the sum through root, 1) 2) 3)leftsum + right sum + node.val
3 update maximum max (1,2,3,4 option)

O(n) 
*/

class Solution {

  private static int max;

  public static int maxPathSum(Node root) {
    if (root == null) return 0;
    max = Integer.MIN_VALUE;
    maxPathSumHelper(root);
    return max;
  }

  private static int maxPathSumHelper(Node root) {
    if (root == null) return 0;
    int leftSum = maxPathSumHelper(root.left);
    int rightSum = maxPathSumHelper(root.right);
    int arch = leftSum + rightSum + root.val; //through root
    int singlePath = Math.max(root.val, Math.max(leftSum, rightSum) + root.val);
    max = Math.max(max, Math.max(arch, singlePath)); 
    //through the node or not, may or may not include parent node, post order traversal

    return singlePath;
  }
  
  /*
  avoid global variable, use one element array
  */

  private static int maxPathSum2(Node root) {
    int[] max = new int[1];
    max[0] = Integer.MIN_VALUE;
    maxPathSum(root, max);
    return max[0];
  }

  private static int maxPathSum(Node root, int[] max) {
    if (root == null) {
      return 0;
    }

    int left = Math.max(maxPathSum(root.left, max), 0);
    int right = Math.max(maxPathSum(root.right, max), 0);
    int arch = left + root.val + right;
    max[0] = Math.max(max[0], arch);

    return Math.max(left, right) + root.val;
  }

  public static void main(String[] args) {
    Node root = new Node(-10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(-30);
    root.right.left = new Node(-36);

    System.out.println(maxPathSum(root));
    System.out.println(maxPathSum2(root));
  }

}
