import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Node {
  Node right;
  Node left;
  int val;
  Node(int v) { 
    val = v; 
    left = right = null; 
  }
}

class Solution {   
  
  
  private static Boolean isMirror(Node root1, Node root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    
    return root1.val == root2.val && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
  }
  
  public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
    Node root2 = new Node(10);
    root2.right = new Node(12);
    root2.left = new Node(15);
    root2.right.right = new Node(25);
    root2.right.left = new Node(30);
    //root2.left.right = new Node(6);
    
    System.out.print(isMirror(root,root2));
  }
}
    