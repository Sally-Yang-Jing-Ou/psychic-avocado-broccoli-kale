import java.io.*;
import java.util.*;

class Node {
  Node right;
  Node left;
  int val;
  Node(int v) { 
    val = v; 
    left = right = null; 
  }
}

/*
From the pre-order array, we know that first element is the root. We can find the root in in-order array. 
Then we can identify the left and right sub-trees of the root from in-order array.

Using the length of left sub-tree, we can identify left and right sub-trees in pre-order array. 
Recursively, we can build up the tree.

1) Pick an element from Preorder. Increment a Preorder Index Variable (preStart in below code) 
to pick next element in next recursive call.
2) Create a new tree node tNode with the data as picked element.
3) Find the picked element’s index in Inorder. Let the index be inStart.
4) Call buildTree for elements before inStart and make the built tree as left subtree of tNode.
5) Call buildTree for elements after inStart and make the built tree as right subtree of tNode.
6) return tNode.

*/
  
/*
Inorder sequence: D B E (A) F C
Preorder sequence: (A) B D E C F

In a Preorder sequence, leftmost element is the root of the tree. 
So we know ‘A’ is root for given sequences. By searching ‘A’ in Inorder sequence, 
we can find out all elements on left side of ‘A’ are in left subtree and elements on 
right are in right subtree. So we know below structure now.

                 A
               /   \
             /       \
           D B E     F C


         A          
       /   \
     /       \
    B         C
   / \        /
 /     \    /
D       E  F


 O(n^2). Worst case occurs when tree is left skewed. 
 Example Preorder and Inorder traversals for worst case are {A, B, C, D} and {D, C, B, A}
     A
   B
 C 
D

*/

class Solution { 
   
  private static Node constructTree(int[] inOrder, int[] preOrder, int inStart, int inEnd, int preStart, int preEnd) {
    if(inStart>inEnd || preStart>preEnd) return null;
    
    int val = preOrder[preStart];
    Node node = new Node(val);
    
    int k = 0;
    for(int i = 0; i < inOrder.length; i++) {
      if (inOrder[i]==val) {
        k = i;
        break;
      }
    }
    
    //tells you the size of left and right subtree
    node.left = constructTree(inOrder, preOrder, inStart, k-1, preStart + 1, preStart + (k - inStart));
    node.right = constructTree(inOrder, preOrder, k+1, inEnd, preStart+(k-inStart)+1, preEnd);
    
    return node;
    
  }

  private static int preStart = 0;
  private static Node constructTree(int[] inOrder, int[] preOrder, int inStart, int inEnd) {
    if(inStart>inEnd) return null;
    
    int val = preOrder[preStart++];
    Node node = new Node(val);
    
    int k = 0;
    for(int i = 0; i < inOrder.length; i++) {
      if (inOrder[i]==val) {
        k = i;
        break;
      }
    }
    
    //tells you the size of left and right subtree
    node.left = constructTree(inOrder, preOrder, inStart, k-1);
    node.right = constructTree(inOrder, preOrder, k+1, inEnd);
    
    return node;
  }
  
  private static void traverseIn(Node root) {
    if (root == null) return;
    
    traverseIn(root.left);
    System.out.format("%d ", root.val);
    traverseIn(root.right);
  }
  
   private static void traversePre(Node root) {
    if (root == null) return;

    System.out.format("%d ", root.val); 
    traversePre(root.left);
    traversePre(root.right);
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
    
    int[] in = new int[]{4,2,5,1,6, 7, 3, 8};
    int[] pre = new int[]{1, 2, 4, 5,  3, 7, 6, 8};
    
    Node node = constructTree(in,pre,0,in.length-1,0,pre.length-1);
    traverseIn(node);
    System.out.println("");
    traversePre(node);
                      
  }

  
}
