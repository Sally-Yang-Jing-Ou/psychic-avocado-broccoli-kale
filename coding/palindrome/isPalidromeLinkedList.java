import java.io.*;
import java.util.*;

class Node {
  Node next;
  int val;
  Node(int v, Node n) {
    val = v;
    next = n;
  }
}

class Solution {

  public static boolean linkedListPalidrome(Node node) {
    if (node == null || node.next == null) {
      System.out.println("empty nodes or one node only");
      return false;
    }
    Node slow = node;
    Node fast = slow.next;
    //find the middle point of the linked list
    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    //split the list into two halves
    Node node1 = node;
    Node node2 = slow.next;
    slow.next = null; //the end of first linked list should be pointing to null

    //reverse the second half of linked list
    node2 = reverseList(node2); // reverse the second half of linked list

    while(node2 != null) {
      if(node1.val != node2.val) return false;
      node1 = node1.next;
      node2 = node2.next;
    }
    return true;
  }

  private static Node reverseList(Node node) {
    Node prev = null, curr = node, next;

    while(curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    node = prev;
    return node;
  }  


  public static boolean linkedListPalidrome2(Node node) {
    if (node == null || node.next == null) {
      System.out.println("empty nodes or one node only");
      return false;
    }

    Stack<Node> st = new Stack<Node>();
    Node slow = node;
    Node fast = slow;
    //find the middle point of the linked list
    while(fast != null && fast.next != null) {
      st.push(slow);
      slow = slow.next;
      fast = fast.next.next;
    }

    //odd length, skip one element
    if(fast != null) {
      slow = slow.next;
    }

    while(slow != null) {
      int top = st.pop().val;
      if(top != slow.val) return false;
      slow = slow.next;
    }
    return true;
  }

  private static Node left;
  public static boolean linkedListPalidrome3(Node node) {
    left = node;
    return linkedListPalidrome3Recursive(node);
  }

  private static boolean linkedListPalidrome3Recursive(Node right){

    //stop recursion
    if (right == null) return true; //end of linked list
    
    //if sub-list is not palindrome,return false
    boolean isPalin = linkedListPalidrome3Recursive(right.next);
    if (!isPalin) return false;

    //current left and right
    boolean isEqual = (left.val == right.val);

    //move left to next
    left = left.next;

    return isEqual;
  }

  public static void main(String[] args) {
    Node n = new Node(1, new Node(3, new Node(5, new Node(7, new Node(5, new Node(3, new Node(1, null)))))));
    Node n2 = new Node(1, new Node(3, new Node(6, new Node(5, new Node(5, new Node(6, new Node(3, new Node(1, null))))))));
    Node n3 = new Node(1, new Node(4, new Node(6, new Node(5, new Node(5, new Node(6, new Node(3, new Node(1, null))))))));

    System.out.println("reverse linked");
    // System.out.println(linkedListPalidrome(n));
    // System.out.println(linkedListPalidrome(n2));
    // System.out.println(linkedListPalidrome(n3));

    System.out.println("Stack linked");
    // System.out.println(linkedListPalidrome2(n));
    // System.out.println(linkedListPalidrome2(n2));
    // System.out.println(linkedListPalidrome2(n3));

    System.out.println("recursive linked");
    System.out.println(linkedListPalidrome3(n));
    System.out.println(linkedListPalidrome3(n2));
    System.out.println(linkedListPalidrome3(n3));
  }
}