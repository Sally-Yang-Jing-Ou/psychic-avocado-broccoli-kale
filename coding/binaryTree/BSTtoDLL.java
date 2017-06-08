import java.io.*;
import java.util.*;

class BSTNode {
  BSTNode right;
  BSTNode left;
  int val;
  public BSTNode(int v) {
    val = v;
    right = null;
    left = null;
  }
}


class DLLNode {
  DLLNode prev;
  DLLNode next;
  int val;
  public DLLNode(int v) {
    val = v;
    prev = null;
    next = null;
  }
}

class Solution {
  
  public static DLLNode BSTtoDLL(BSTNode root) {
    if (root == null) return null;
    
    Stack<BSTNode> st = new Stack<BSTNode>();
    DLLNode head = null;
    DLLNode prev = null;

    while (true) {
      while (root != null) {
        st.push(root);
        root = root.left;
      }
      
      if (!st.isEmpty()) {
        root = st.pop();
        DLLNode dllNode = new DLLNode(root.val);
        
        if (head == null) {
          head = dllNode;
        } else {
          dllNode.prev = prev;
          prev.next = dllNode;
        }
        prev = dllNode;
        
        root = root.right;
      } else {
        break;
      }
    }
    
    return head;
    
  }
  
  public static void printDLL(DLLNode head) {
    while (head != null) {
      System.out.format("%d ->", head.val);
      head = head.next;
    }
  }
  
  public static void main(String[] args) {
    BSTNode root = new BSTNode(10);
    root.left = new BSTNode(12);
    root.right = new BSTNode(15);
    root.left.left = new BSTNode(25);
    root.left.right = new BSTNode(30);
    root.right.left = new BSTNode(36);
    
    printDLL(BSTtoDLL(root));
  }
}
