import java.io.*;
import java.util.*;

class ListNode {
  int val;
  ListNode next;
  ListNode(int v) {
    val = v;
    next = null;
  }
}

class Solution {

  private static ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null)
      return head;

    ListNode prev = head;    
    ListNode p = head.next;

    while(p != null){
      if(p.val == prev.val){
        prev.next = p.next;
      }else{
        prev = p;
      }
      p = p.next;
    }

    return head;
  }

  public static void main(String[] args) {
    ListNode n = new ListNode(1);
    n.next = new ListNode(1);
    n.next.next = new ListNode(2);
    n.next.next.next = new ListNode(3);
    n.next.next.next.next = new ListNode(3);
    
    ListNode node = deleteDuplicates(n);
    
    while(node!=null) {
      System.out.format("%d ", node.val);
      node = node.next;
    }

  }
}