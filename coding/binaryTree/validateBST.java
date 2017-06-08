import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Node {
  Node left;
  Node right;
  int val;
  Node(int v) { val = v; }
}

class WrapInt {
  int val;
  WrapInt(int v) { val = v; }
}

class Solution {
  private void makeBT(Node root, ArrayList<Integer> array) {
    if (root == null) return;
    makeBT(root.left, array);
    array.add(root.val);
    makeBT(root.right, array);
  }
  
  public Boolean isBST(Node root) {
    ArrayList<Integer> array = new ArrayList<Integer>();
    if (root == null) return true;
    makeBT(root, array);
    for(int i = 1; i < array.size(); i++) {
      if (array.get(i) <= array.get(i-1)) return false;
    }
    
    return true;
  }
  
  private static boolean isBSTRecursive(Node root, WrapInt last) {
    if (root == null) return true;
    
    if(!isBSTRecursive(root.left, last)) return false;
    
    if(last.val >= root.val) return false;
    
    last.val = root.val;
    
    if(!isBSTRecursive(root.right, last)) return false;
    
    return true;
  }
  
  public static void main(String[] args) {
    // Node root = new Node(10);
    // root.left = new Node(12);
    // root.right = new Node(15);
    // root.left.left = new Node(25);
    // root.left.right = new Node(30);
    // root.right.left = new Node(36);
    Node root = new Node(10);
    root.left = new Node(8);
    root.right = new Node(22);
    root.left.left = new Node(3);
    root.left.right = new Node(9);
    root.right.left = new Node(15);
    
    WrapInt i = new WrapInt(Integer.MIN_VALUE);
    System.out.println(isBSTRecursive(root, i));
  }
}
