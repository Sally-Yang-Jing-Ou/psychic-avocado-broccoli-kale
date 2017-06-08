import java.io.*;
import java.util.*;

/*
1. To maintain a hash for the branch (horizontal distance) to each node.
 
2. Traverse the tree in level order fashion. (level by leve)
 
3. In level order traversal, maintain a queue
   which holds (node and its vertical branch) -> probably a class to store both info
    * pop from queue.
    * add this node's data corresponding to its horizontal distance in the hash.
    * if this node has left child, insert in the
      queue, left with branch - 1.
    * if this node has right child, insert in the
      queue, right with branch + 1.
*/
class Node {
  Node right;
  Node left;
  int val;
  Node(int v) { val = v; }
}

class Tuple{ 
  Node node;
  int hd; //horizontal distance
  Tuple(Node x, int y) { 
    node = x;
    hd = y;
  } 
} 

class Solution { 
  private static void printTreeByColumns(Node root) {
    Queue<Tuple> q = new LinkedList<Tuple>();
    Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
    q.add(new Tuple(root, 0));
    
    while(!q.isEmpty()) {
      Tuple t = q.poll();
      int hd = t.hd;
      Node node = t.node;
      
      if (!map.containsKey(hd)) {
        map.put(hd, new ArrayList<Integer>());
      } 
      //add the value to the map
      map.get(hd).add(node.val);
      
      if (node.left != null) {
        q.add(new Tuple(node.left, hd-1));
      }
          
      if (node.right != null) {
        q.add(new Tuple(node.right, hd+1));
      }
    }
    
    for(ArrayList<Integer> array: map.values()) {
      for (Integer i : array) {
        System.out.format("%d ", i);
      }
      System.out.println("");
    }
  }
  
  
  public static void main(String[] args) {
    Node root = new Node(10);
    root.left = new Node(12);
    root.right = new Node(15);
    root.left.left = new Node(25);
    root.left.right = new Node(30);
    root.right.left = new Node(36);
    
    
    printTreeByColumns(root);
  }
}

















