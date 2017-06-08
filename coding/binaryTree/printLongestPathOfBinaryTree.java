import java.io.*;
import java.util.*;

class Node {
  int val;
  Node left;
  Node right;
  Node(int v) {
    val = v;
  }
}

class Solution {

  private static Map<Node, Node> bestDiameterRoot = new HashMap<Node, Node>();
  private static Map<Node, Integer> bestDiameterLength = new HashMap<Node, Integer>();
  private static Map<Node, Node> tallestChild = new HashMap<Node, Node>(); 
  private static Map<Node, Integer> treeHeight = new HashMap<Node, Integer>(); //Height at each node
  
  private static void postDfs(Node root) {
    if (root == null) return;
    postDfs(root.left);
    postDfs(root.right);
 
    // Get the taller of our two children.
    Node tallest;
    if (treeHeight.getOrDefault(root.left, 0) > treeHeight.getOrDefault(root.right, 0)) {
      tallest = root.left;
    } else {
      tallest = root.right;
    }
    // Save the height from this node.
    treeHeight.put(root, treeHeight.getOrDefault(tallest, 0) + 1);
    
    if (tallest == null) return;
    
    // Save our tallest child so we can form the path later.
    tallestChild.put(root, tallest);
    
    // Get the best diameter length within the subtrees of our two children.
    Node childWithBestDiameter;
    if (bestDiameterLength.getOrDefault(root.left, 0) > bestDiameterLength.getOrDefault(root.right, 0)) {
      childWithBestDiameter = root.left;
    } else {
      childWithBestDiameter = root.right;
    }
    
     // We can form a better diameter that goes through this node if the path
     // formed with our two children is greater than the diameter length
    // of our child with the best diameter.
    int childDiameterLength = bestDiameterLength.getOrDefault(childWithBestDiameter, 0);
    int ourDiameter = treeHeight.getOrDefault(root.left, 0) + treeHeight.getOrDefault(root.right, 0) + 1;
    if (ourDiameter >= childDiameterLength) {
      // Save the best diameter as the diameter starting from this node.
      bestDiameterLength.put(root, ourDiameter);
      bestDiameterRoot.put(root, root);
    } else {
      // Save the best diameter as the subtree's previous best diameter.
      bestDiameterLength.put(root, childDiameterLength);
      bestDiameterRoot.put(root, bestDiameterRoot.get(childWithBestDiameter));
    }
  }
  
  private static void printPath(Node root) {
    ArrayList<Node> res = new ArrayList<Node>();
    // Get the best diameter's root node.
    Node n = bestDiameterRoot.get(root);
    // add the left path
    for(Node v = n.left; v!=null; v = tallestChild.get(v)) {
      res.add(v);
    }
    //reverse the current path so we start at the end of the left path
    Collections.reverse(res);
    //add root
    res.add(n);
    //add right path
    for(Node v = n.right; v!=null; v = tallestChild.get(v)) {
      res.add(v);
    }
    
    for (Node no: res) {
      System.out.format("%d ", no.val);
    }
  }
  
  public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
    postDfs(root);
    printPath(root);
  }
  
  
}

