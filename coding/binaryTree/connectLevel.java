import java.io.*;
import java.util.*;

class Node {
  Node right;
  Node left;
  Node next;
  int val;
  Node(int v) { 
    val = v; 
    left = right = next = null; 
  }
}

/*
           10
       12      15
     11  13   14  

1) need to know the nodes at each level
2) list, parent, 
   while we are not at the level we're interested in, 
   parent = list;
   for (node in parent), if the child isn't null, we would add to the list
   i--;

Run time O(n), maximum visiting each node once
*/

class Solution { 
    
  public static List<Node> connectLevel(Node root, int i) {
    List<Node> list = new LinkedList<Node>();
    if (root == null) return list;    
    list.add(root);
    
    while (i != 0 && !list.isEmpty()) {
      List<Node> parent = list;
      list = new LinkedList<Node>();
      
      for(Node node: parent) {
        if(node.left!=null) list.add(node.left);
        if(node.right!=null) list.add(node.right);
      }

      i--;
    }
    
    for (int k = 1; k < list.size(); k++) {
      list.get(k-1).next = list.get(k);
    }
    
    return list;
  }
  
  public static void main(String[] args) {
    

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
    
    List<Node> list = connectLevel(root, 2);
    
    Node print = list.get(0);
    while (print != null) {
      System.out.println(print.val);
      print = print.next;
    }
    
    for(Node i: list) {
      System.out.println(i.val);
    }
  }
  
}
