import java.io.*;
import java.util.*;

class ListNode {
  ListNode next;
  int val;
  ListNode(int v, ListNode n) {
    val = v;
    next = n;
  }
}

class ListIterator implements Iterator<ListNode> {

  PriorityQueue<ListNode> pq;

  public ListIterator(ListNode[] lists) {
    pq= new PriorityQueue<ListNode>((x,y) -> x.val - y.val);
    for(ListNode n : lists) {
      if (n!=null){
        pq.offer(n);
      }
    }
  }

  @Override
  public boolean hasNext() {
    return !pq.isEmpty();
  }

  @Override
  public ListNode next() {
    if(!pq.isEmpty()) {
      ListNode ln =  pq.poll();

      if (ln.next != null) {
        pq.offer(ln.next);
      }
      
      return ln;
    }
    return null;
  }
}

class Solution {
  
  private static void listIter(ListNode[] lists) {
    ListIterator it = new ListIterator(lists);
    while(it.hasNext()) {
      System.out.format("%d ", it.next().val);
    }
  }


  private static ListNode mergeKSortedList(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;

    ListNode head = new ListNode(0, null);
    ListNode p = head;
    PriorityQueue<ListNode> pq= new PriorityQueue<ListNode>((x,y) -> x.val - y.val);

    for(ListNode n : lists) {
      if (n!=null){
        pq.offer(n);
      }
    }

    while(!pq.isEmpty()) {
      ListNode ln =  pq.poll();
      p.next = ln;
      p = p.next;

      if (ln.next != null) {
        pq.offer(ln.next);
      }
    }

    return head.next;

  }

  public static void main(String[] args) {
    ListNode n1 = new ListNode(2, new ListNode(4, new ListNode(7, new ListNode(7, new ListNode(8, null)))));
    ListNode n2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(6, null))));

    ListNode[] lists = new ListNode[]{n1, n2};

    listIter(lists);

    // while(res!=null) {
    //   System.out.format("%d ", res.val);
    //   res = res.next;
    // }


  }
}
