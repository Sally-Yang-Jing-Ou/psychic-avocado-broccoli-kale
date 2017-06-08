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
  Path sum
  Given a binary tree and a sum, find all root-to-leaf paths where each path’s sum equals the given sum.

 1) do a dfs down the tree, keep track of list of all the nodes we've seen so far
 2) if the sum added up is the target sum and this node's left and right are null (we're at leaf),we add this list to somewhere
 
 O(N) since each node is touched once
*/

class Solution {
  private static List<List<Integer>> pathSum(Node root, int sum) {
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    dfs(root, sum, new LinkedList<Integer>(), ret);
    return ret;
  }

  private static void dfs(Node root, int sum, List<Integer> current, List<List<Integer>> ret) {
    if(root == null) return;

    current.add(root.val);
    if(sum == root.val && root.left == null && root.right == null) {
      ret.add(new LinkedList<Integer>(current));
    } else {
      dfs(root.left, sum - root.val, current, ret);
      dfs(root.right, sum - root.val, current, ret);
    }

    current.remove(current.size()-1);
  }


  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    if (root.val == sum && root.left == null && root.right == null) {
      return true;
    }
    boolean bl = hasPathSum(root.left, sum - root.val);
    boolean br = hasPathSum(root.right, sum - root.val);
    return bl || br;
  }


  /*
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
  */
  public static void main(String[] args) {
    Node root = new Node(5);
    root.left = new Node(4);
    root.right = new Node(8);
    root.left.left = new Node(11);
    root.right.left = new Node(13);
    root.right.right = new Node(4);
    root.right.right.left = new Node(5);
    root.right.right.right = new Node(1);
    root.left.left.left = new Node(7);
    root.left.left.right = new Node(2);

    List<List<Integer>> list = pathSum(root, 22);

    for(List<Integer> li : list) {
      for(int i : li) {
        System.out.format("%d ", i);
      }
      System.out.println("");
    }
  }

}