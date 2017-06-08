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

/*
mid / lower mid if even length
 1->3->5->7->6->4->2->
1 2 3 4 5 6 7
second half
 6->4->2->

reverse
 1->3->5->7
 2->4->6->
 
1 2 3 4 5 6 7

*/
class Solution {

  public static void linkedListNodesFromBothSides(Node node) {
    if (node == null || node.next == null) return;
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

    //merge alternative nodes
    Node n = new Node(0, null); //dummy
    // curr is the pointer to this dummy Node, which will
    // be used to form the new list, curr keeps being updated
    Node curr = n;

    while (node1 != null || node2 != null) {
      if(node1 != null) {
        curr.next = node1;
        curr = curr.next;
        node1 = node1.next;
      }

      if(node2 != null) {
        curr.next = node2;
        curr = curr.next;
        node2 = node2.next;
      }
    }
    
    n = n.next;
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

  public static void main(String[] args) {
    Node n = new Node(1, new Node(3, new Node(5, new Node(7, new Node(6, new Node(4, new Node(2, null)))))));

    linkedListNodesFromBothSides(n);

    while(n!=null){
      System.out.format("%d ", n.val);
      n = n.next;
    }
  }
}