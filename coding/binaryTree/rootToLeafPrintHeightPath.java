import java.io.*;
import java.util.*;

/*
Print longest root to leaf path
basically height, but print it
*/

class Node {
  Node left;
  Node right;
  int val;
  Node (int v) {
    val = v;
  }
}

class Solution {

  private static int height(Node root) {
    if (root == null) return 0;

    int leftHeight = height(root.left);
    int rightHeight = height(root.right);

    return Math.max(leftHeight, rightHeight) + 1;
  }

  private static void rootToLeafLongest(Node root, int[] path, int h, int index) {
    if (root == null) return; 

    path[index++] = root.val;

    if(index == h && root.right == null && root.left == null) {
      printPath(path);
      return;
    }

    rootToLeafLongest(root.left, path, h, index);
    rootToLeafLongest(root.right, path, h, index);
  }
  
  private static void printPath(int[] path) {
    for(int i : path) {
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
    root.right.left.right = new Node(77);

    List<Node> list = new ArrayList<Node>();
    int h = height(root);
    int[] path = new int[h];
    rootToLeafLongest(root, path, h, 0);
  }

  /*
    Second Method
  */
  private static int rootToLeafLongestHelper(Node root, Map<Node, Node> map) {
    if (root == null) return 0;

    int leftHeight = rootToLeafLongestHelper(root.left, map);
    int rightHeight = rootToLeafLongestHelper(root.right, map);

    if(leftHeight > rightHeight) {
      map.put(root, root.left);
      return leftHeight + 1;
    } else {
      map.put(root, root.right);
      return rightHeight + 1;
    }

  }

  private static void rootToLeafLongest(Node root) {
    Map<Node, Node> map = new HashMap<Node, Node>();
    int height = rootToLeafLongestHelper(root, map);
    Node node = root;
    while (node != null) {
      System.out.format("%d ",node.val);
      node = map.get(node);
    }
    
    System.out.format("%n%d ", height);
  }

  public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    root.right.left.right = new Node(77);
    rootToLeafLongest(root);
  }
}

