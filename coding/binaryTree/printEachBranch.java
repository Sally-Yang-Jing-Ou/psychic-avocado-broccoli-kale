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

class Solution { 
    
  private static void printPaths(Node root, List<Integer> path) {
    if (root == null) return;
    
    path.add(root.val);
    
    if (root.right == null && root.left == null) {
      print(path);
    } else {
      printPaths(root.left, path);
      printPaths(root.right, path);
    }
    
    path.remove(path.size() - 1);
  }
  
  private static void print(List<Integer> path) {
    for (Integer i : path) {
      System.out.format("%d ", i);
    }
    System.out.println("");
  }
  
  public static void main(String[] args) {
    

    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
//     Node root = new Node(1);
//     root.left = new Node(2);
//     root.right = new Node(3);
//     root.left.left = new Node(4);
//     root.left.right = new Node(5);
    printPaths(root, new ArrayList<Integer>());
  }
  
}
