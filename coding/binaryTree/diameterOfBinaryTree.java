import java.io.*;
import java.util.*;

class Node {
  Node right;
  Node left;
  int val;
  Node(int v) { val = v; }
}
/*

 the reason you need a wrapper class is because Java is Pass By Value meaning
  that using an object wrapper around your data is the easiest way to properly
   maintain values through recursion levels.
*/
class Height {
  int h = 0;
}

class Solution {
  // O(n^2)
//   public static int longestPath(Node root) { 
//     if (root == null) return 0;
    
//     int rootDiameter = getHeight(root.left) + getHeight(root.right) + 1;
//     int leftDiameter = longestPath(root.left);
//     int rightDiameter = longestPath(root.right);
    
//     return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
  
//   }
  
//   public static int getHeight(Node root) {
//     if (root == null) return 0;
    
//     int leftHeight = getHeight(root.left);
//     int rightHeight = getHeight(root.right);
    
//     return Math.max(leftHeight, rightHeight) + 1;
//   }
  
  // diameter at that node - height of left tree + height of right + 1;
  // 
  private static int longestPath(Node root, Height height) {
    if (root == null) return 0;
    
    Height leftHeight = new Height();
    Height rightHeight = new Height();
    //post order traversal O(n)
    int leftDiameter = longestPath(root.left, leftHeight);
    int rightDiameter = longestPath(root.right, rightHeight);
    height.h = Math.max(leftHeight.h, rightHeight.h) + 1;
    
    int rootDiameter = leftHeight.h + rightHeight.h + 1;
    
    return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
  }
  
  
  public static int longestPath(Node root) {
    return longestPath(root, new Height());
  }
  

  /*
  diameter at that node - height of left tree + height of right + 1;
  calculate the height of a node and its diameter at the same time
  int[] arr of size two, first ele is max diameter of all time, second height

  1) calcualte height of left and right subtree recursively
  2) once the call comes back, we can calcualte the diameter at the root and the height at the root
      diameter = height of left and right subtree + 1;
      height = max(height of left, right) + 1;

  3) update the max diameter. max(diameter thru root, max(leftDiameter, rightDiameter))

  O(n) touching each node once
  */

  public static int getDiameter(Node root) {
    int [] res = getDiameterHelper(root);
    return res[0];
  }
  //It just calculates height and diameter at the same time. 
  //And since Java does not have pass-by-reference I defined an int[] to return the result.
  public static int[] getDiameterHelper(Node root) {
    int[] result = new int[]{0,0};    //1st element: diameter, 2nd: height    
    if (root == null)  return result;
    int[] leftResult = getDiameterHelper(root.left);
    int[] rightResult = getDiameterHelper(root.right);

    int height = Math.max(leftResult[1], rightResult[1]) + 1; //max(left height + right height) + 1
    int rootDiameter = leftResult[1] + rightResult[1] + 1; //left height + right height + itself, through the root

    int leftDiameter = leftResult[0];
    int rightDiameter = rightResult[0];

    result[0] = Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter)); //whichever one that's longer
    result[1] = height; //height at this root

    return result;
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
    int[] res = getDiameter(root);
    System.out.print(res[0]);
  }
  
}