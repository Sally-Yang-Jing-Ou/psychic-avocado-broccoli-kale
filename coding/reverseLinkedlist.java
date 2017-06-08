import java.io.*;
import java.util.*;

class Node {
  Node next;
  int val;
  Node (int v) { val = v; next = null; }
}

class Solution {

  private static void reverseLinkedList(Node linked) {
    Node current = linked;
    Node prev = null;
    Node next = null;
    
    while (current != null) {
      next = current.next;
      current.next=prev;
      prev = current;
      current = next;
    }
    
    while(prev!=null) {
      System.out.format("%d ", prev.val);
      prev = prev.next;
    }
  }
  
  
  public static void main(String[] args) {
    // Node root = new Node(10);
    // root.left = new Node(12);
    // root.right = new Node(15);
    // root.left.left = new Node(25);
    // root.left.right = new Node(30);
    // root.right.left = new Node(36);
    Node root = new Node(10);
    root.next = new Node(8);
    root.next.next = new Node(22);
    root.next.next.next = new Node(3);
    root.next.next.next.next = new Node(9);
    root.next.next.next.next.next = new Node(15);
    
    reverseLinkedList(root);
  }
}
