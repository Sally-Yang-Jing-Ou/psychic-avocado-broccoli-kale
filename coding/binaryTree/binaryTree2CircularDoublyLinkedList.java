import java.io.*;
import java.util.*;


class Node {
  Node right;
  Node left;
  int val;
  Node (int v) { val = v; }
}

//O(n)

/*

               5
             1   3
           2  4
*/

// head, prev, use prev to remember the previous node we came from
// connect node.left = prev, prev.right = node;
// in order traversal

/*
   
   <- 2 <-> 1 <-> 4 <-> 5 <-> 3 ->
   3                             2

   1) use a stack for in order binary traversal
   2) keep pushing left children until it reaches null
   3) 1. pop node n, if head is null, set that n to head. then set prev to n, 
      2. pop node n, if head isn't null, node.left = prev, prev.right = node; 
      update prev, n = n.right
   4) head.left = prev; prev. right = head;   

O(n) time and O(n) space
*/

class Solution {
  
  private static void binaryTree2CircularDoublyLinkedList(Node root) {
    Stack<Node> st = new Stack<Node>();
    Node head = null;
    Node prev = null;
    while (!st.isEmpty() || root != null) {

      if (root != null) {
        st.push(root);
        root = root.left;
      } else {  
        root = st.pop();
        if (head == null) {
          head = root;
        } else {
          prev.right = root;      
          root.left = prev;
        }
        prev = root;
        root = root.right;
      }
    }
    
    head.left = prev;
    prev.right = head;
    
    //int count = 0;
    while (head != null) /*&& count++ != 12*/ {
      System.out.format("%d, ", head.val);
      head = head.right;
    }
  }
  
  public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
    binaryTree2CircularDoublyLinkedList(root);
  }
}

















