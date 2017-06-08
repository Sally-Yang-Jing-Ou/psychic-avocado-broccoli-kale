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
  
  public static boolean identical(Node tree1, Node tree2) {
    if (tree1 == null && tree2 == null) return true;
    
    if (tree1 != null && tree2 != null) {
      return tree1.val == tree2.val && identical(tree1.left, tree2.left) && identical(tree1.right, tree2.right);
    }
    
    return false;
  }
  
  public static void main(String[] args) {
    
    // Node root = new Node(7);
    // root.left = new Node(5);
    // root.left.parent = root;
    // root.right = new Node(12); 
    // root.right.parent = root;
    // root.left.left = new Node(1);
    // root.left.left.parent = root.left;
    // root.left.right = new Node(6);
    // root.left.right.parent = root.left;
    // root.right.left = new Node(10);
    // root.right.left.parent = root.right;
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
    
    Node root2 = new Node(10);
    root2.left = new Node(12);
    root2.right = new Node(15);
    root2.left.left = new Node(25);
    root2.left.right = new Node(30);
    root2.right.left = new Node(6);
    
//     Node root = new Node(1);
//     root.left = new Node(2);
//     root.right = new Node(3);
//     root.left.left = new Node(4);
//     root.left.right = new Node(5);
    System.out.println(identical(root, root2));
  }
  
}
