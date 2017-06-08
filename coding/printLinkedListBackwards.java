import java.io.*;
import java.util.*;

class Node {
  Node next;
  int val;
  Node (int v) { val = v; next = null; }
}

class Solution {

  private static void printLinkedListBackwards(Node linked) {
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
  
  
  private static void printLinkedListBackwards2(Node linked) {
    if(linked == null) return;
    printLinkedListBackwards(linked.next);
    System.out.format("%d ", linked.val);
  }
  
  private static void printLinkedListBackwards3(Node linked) {
    Stack<Integer> st = new Stack<Integer>();
    
    while(linked != null) {
      st.push(linked.val);
      linked = linked.next;
    }
    
    while(!st.isEmpty()){
      System.out.format("%d ", st.pop());
    }
  }

  public static void main(String[] args) {
    Node root = new Node(10);
    root.next = new Node(8);
    root.next.next = new Node(22);
    root.next.next.next = new Node(3);
    root.next.next.next.next = new Node(9);
    root.next.next.next.next.next = new Node(15);

    printLinkedListBackwards3(root);

  }
}