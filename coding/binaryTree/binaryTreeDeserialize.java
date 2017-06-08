import java.io.*;
import java.util.*;

/*
We can save space by storing Preorder traversal and a marker for NULL pointers.

 */

class Int {
  int i;
  public Int(int integer) { i = integer; }
  public Int increment() { i++; return this; }
}

class Node {
  Node right;
  Node left;
  int val;
  public Node(int v, Node l, Node r) { val = v; left = l; right = r; }
}

class Solution {
/*print the tree in pre-order traversal and use "-1" to denote null node
*/
  public static List<Integer> serialize(Node root) {
    List<Integer> list = new ArrayList<Integer>();
    Stack<Node> stack = new Stack<Node>();
    
    stack.push(root);
    Node n;
    
    while (!stack.isEmpty()) {
      n = stack.pop();
      if (n == null) {
        list.add(-1); //stringBuilder.append(node.val).append(spliter);
        continue;
      }
      
      list.add(n.val);
      stack.push(n.right);
      stack.push(n.left);
    }
    
    return list;
  }

    // Decodes your encoded data to tree.
  /* 
For deserializing, we use a Queue to store the pre-order traversal 
and since we have "X" as null node, we know exactly how to where to end building subtress.

Input:
         20
       /    
      8     
     / \
    4  12 
      /  \
     10  14
Output: 20 8 4 -1 -1 12 10 -1 -1 14 -1 -1 -1 

  */
  public TreeNode deserialize(String data) {
      Deque<String> nodes = new LinkedList<>();
      nodes.addAll(Arrays.asList(data.split(spliter)));
      return buildTree(nodes);
  }
  
  private TreeNode buildTree(Deque<String> nodes) {
      String val = nodes.remove();
      if (val.equals(NN)) return null;
      else {
          TreeNode node = new TreeNode(Integer.valueOf(val));
          node.left = buildTree(nodes);
          node.right = buildTree(nodes);
          return node;
      }
  }
  
  public static Node deserialize(List<Integer> list) {
    return deserializeHelper(list, new Int(0)); 
  }
  
  public static Node deserializeHelper(List<Integer> list, Int i) {
    if (list.get(i.i) == -1) {
      return null;
    }
    
    Node root = new Node(list.get(i.i), null, null);
    
    root.left = deserializeHelper(list, i.increment());
    root.right = deserializeHelper(list, i.increment());
    
    return root;
  }
    
    /*
Serialize:
Tree -> string/list
1) pre-order fashion: visit parent before children, put each node in the list or string in pre order fashion

Deserialize:
String/list -> tree
1) base case: if the val is 'X', return null
1) create left and right subtree recurisvely
node.left = recurse(list)
node.right = recurse(list)
    */


public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}


  public static void traversal(Node root) {
    if (root == null) return;
    System.out.println(root.val);
    traversal(root.left);
    traversal(root.right);
  }
  
  public static void main(String[] args) {
      Node n1 = 
      new Node(20, new Node(8, new Node(4, null, null), 
        new Node(12, new Node(10, null, null), 
          new Node(14, null, null))), null);
    
    List<Integer> list = serialize(n1);
    System.out.println(list);
    
    Node root = deserialize(list);
    traversal(root);
  }
}
