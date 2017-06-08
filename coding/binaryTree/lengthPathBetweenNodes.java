import java.io.*;
import java.util.*;

/*

       1
    1     2
  2   3  4  5
 2 3 6 7
 
 1) the length path from root to node 1 +  the length path from root to node 2 - the legnth path from root to lca * 2
 
 O(n)
 
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
  
  
  private static int lengthFromRoot(Node root, Node n, int level) {
    if (root == null) return -1;
    if (root == n) return level; 
    
    int l = lengthFromRoot(root.left, n, level+1);
    return l == -1? lengthFromRoot(root.right, n, level+1) : l;
    
  }
  
  private static int pathBetweenNodes(Node root, Node n1, Node n2) {
    if (!covers(root, n1) || !covers(root, n2)) {
      return -1;
    }
    
    Node lca = findLca(root, n1, n2);  
    int n1Length = lengthFromRoot(root, n1, 0);
    int n2Length = lengthFromRoot(root, n2, 0);
    int lcaLength = lengthFromRoot(root, lca, 0);
    
    return n1Length + n2Length - 2*lcaLength;
    
  }
  
  private static Node findLca(Node root, Node n1, Node n2) {
    if (root == null) {
      return null;
    } else if (root == n1) {
      return root;
    } else if (root == n2) {
      return root;
    }
    
    Boolean isN1OnLeft = covers(root.left, n1);
    Boolean isN2OnLeft = covers(root.left, n2);
    if (isN1OnLeft != isN2OnLeft) {
      return root;
    }
    
    Node nextNode = isN1OnLeft? root.left : root.right;
    return findLca(nextNode, n1, n2);
    
  }
  
  private static Boolean covers(Node root, Node n) {
    if (root == null) return false;
    if (root == n) return true;
    return covers(root.left, n) || covers(root.right, n);
  }
  
  public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
    System.out.print(pathBetweenNodes(root, new Node(3),root.left));
  }
}
    