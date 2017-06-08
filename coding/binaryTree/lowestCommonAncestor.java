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
  Node parent;
  int val;
  Node(int v) { 
    val = v; 
    left = right = parent = null; 
  }
}

class Solution {
  
  //if both nodes are smaller or bigger than root, keep looking
  //if one is smaller the other is bigger, we have a branch out
  public static Node commonAncestorBST(Node root, Node n1, Node n2) {
    while ((root.val - n1.val) * (root.val - n2.val) > 0) {
      root = root.val < n1.val ? root.right : root.left;
    }
    return root;
  }
  

  /*
  the lca 
  1) either be n1 or n2 (in this case, same level as n1 or n2)
  2) level above both n1 and n2, specically has to be higher than whichever one that's higher in the level

         2
      1    4
     6  7  9 5
    8    9 
  3) doesn't exist

  1) get the depth of n1 and n2
  2) move whichever one that is deeper onto the same level
  3) traverse up at the same time until they meet, it will be the lca
  4) if they dont meet and reach null, return null, no lca

  O(depth of (n1 + n2))
  */

  private static boolean isInTree2(Node root, Node n) {
    while(n!= null) {
      if (n == root) return true;
      n = n.parent;
    }
    return false;
  }
  
  public static Node commonAncestorParent(Node root, Node n1, Node n2) {
    if (root == null || n1 == null || n2 == null) return null;
    if (!isInTree2(root, n1) || !isInTree(root, n2)) return null;
    if (n1 == n2) return n1;
    int n1Depth = getDepth(n1);
    int n2Depth = getDepth(n2);
    int diff = n1Depth - n2Depth;
    
    //we want n1 to be deeper in the tree, for calculations purposes
    if (diff < 0) {
      Node temp = n1;
      n1 = n2;
      n2 = temp;
      diff = -diff;
    }
    
    //we only want to move n1
    while (diff-- > 0) {
      //move to n2 level
      n1 = n1.parent;
    }
    
    while (n1 != null && n2 != null) {
      if (n1 == n2) return n1;
      n1 = n1.parent;
      n2 = n2.parent;
    }
    
    return null;
    
  }
  
  private static int getDepth(Node n) {
    int depth = -1;
    
    while (n != null) {
      depth++;
      n = n.parent;
    }
    
    return depth;
  }

  /*
  1) check if n1 and n2 are in the tree (2n)
  once it branches left, discarded right half
  so we have

  2n + 2n /2 + 2n /4 < 2n 

  O(n)

  1) if n1 and n2 are on the same side right, branch out to right to find it 
  2) if n1 and n2 are on the left side, branch out to the left to find it

  
  */
  public static Node commonAncestorNoParent(Node root, Node n1, Node n2) {
    if (root == null || n1 == null || n2 == null) return null;
    
    if (!isInTree(root, n1) || !isInTree(root, n2)) return null;
    
    return commonAncestorNoParentHelper(root, n1, n2);
    
  }
  
  public static Node commonAncestorNoParentHelper(Node root, Node n1, Node n2) {
    if (root == null) {
      return null;
    } else if (root == n1) {
      return n1;
    } else if (root == n2) {
      return n2;
    }
    
    boolean isNode1InLeft = isInTree(root.left, n1);
    boolean isNode2InLeft = isInTree(root.left, n2);
    
    if (isNode1InLeft != isNode2InLeft) return root;
    
    root = isNode1InLeft ? root.left : root.right;
    return commonAncestorNoParentHelper(root, n1, n2);
    
  }
  // o(n), 2n nodes in first call, branches left or right at which covers will be caleld on 2n/2, then 2n/4
  private static boolean isInTree(Node root, Node n) {
    if (root == null) return false;
    if (root == n) return true;
    return (isInTree(root.left, n) || isInTree(root.right, n));
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
    
//     Node root = new Node(1);
//     root.left = new Node(2);
//     root.right = new Node(3);
//     root.left.left = new Node(4);
//     root.left.right = new Node(5);
    Node ans = commonAncestorNoParent(root,root.left.left, root.right.left);
    System.out.println(ans.val);
  }
  
}
