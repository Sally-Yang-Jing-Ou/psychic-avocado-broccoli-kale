import java.io.*;
import java.util.*;

//Modified BFS

class Node {
  Node right;
  Node left;
}

class Solution {
  public ArrayList<LinkedList<Node>> listofDepths (Node root) {
    if (root == null) return null;
    
    ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
    LinkedList<Node> list = new LinkedList<Node>();
    list.add(root);
    
    while (!list.isEmpty()) {
      lists.add(list);
      LinkedList<Node> parents = list;
      list = new LinkedList<Node>();
      
      for(Node parent: parents) {
        if (parent.left != null) {
          list.add(parent.left);  
        }
        
        if (parent.right != null) {
          list.add(parent.right);  
        }
      }
    }
    
    return lists;
  }
  
    
    public static void main(String[] args) {
  }
}
