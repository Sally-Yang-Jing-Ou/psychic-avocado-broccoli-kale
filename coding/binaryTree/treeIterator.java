import java.io.*;
import java.util.*;

/* 
The average time complexity of next() function is O(1) indeed in your case. 
As the next function can be called n times at most, and the number of right nodes in self.pushAll(tmpNode.right) 
function is maximal n in a tree which has n nodes, so the amortized time complexity is O(1).
*/

// not really BST, too lazy to change the name, it's just binary tree, not binary search okay bye.
class BSTNode {
  BSTNode right;
  BSTNode left;
  int val;
  BSTNode (int v) { val = v; }
}
/*
In order traversal

Find the left-most node of the root and store previous left children in a stack;
Pop up the top node from the stack;

If it has a right child, find the left-most
node of the right child and store left children in the stack.
*/
class BSTIteratorInOrder implements Iterator<Integer> {
  Stack<BSTNode> stack;

  public BSTIteratorInOrder(BSTNode root) {
    stack = new Stack<BSTNode>();
    pushLeftChildren(root);
  }

  private void pushLeftChildren(BSTNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public Integer next() {
    if(!hasNext()) return null;
    BSTNode node = stack.pop();
    int result = node.val;
    pushLeftChildren(node.right);
    return result;

  }
}

/*
Post order traversal


Alternatively, we can do it on the fly somehow.
Initially, we find the first leaf that is going to be visited first and store all intermediate nodes in a stack;
Each time we pop out a node from the stack, 
we check whether it is the left child of the current top of the stack.
If so, repeat the step above on the right sub-tree of the current top.

We only need to check whether current is left of top since if it is right,
we know top will be the next-to-be-popped node and thus no need to do anything.
*/
class BSTIteratorPostOrder implements Iterator<Integer> {
  Stack<BSTNode> stack;

  public BSTIteratorPostOrder(BSTNode root) {
    stack = new Stack<BSTNode>();
    pushAllChildren(root);
  }

  private void pushAllChildren(BSTNode root) {
    while (root != null) {
      stack.push(root);
      if(root.left != null) {
        root = root.left;
      } else {
        root = root.right;
      }
    }
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public Integer next() {
    if (!hasNext()) {  
      throw new NoSuchElementException("All nodes have been visited!");  
    }  
    
    BSTNode node = stack.pop();
    int result = node.val;

    if(hasNext()) {
      BSTNode top = stack.peek();
      if(node == top.left) { //if the current popped one is a left child
        pushAllChildren(top.right);
      }
    }
    return result;
  }
}

/*
Pre order traversal
*/
class BSTIteratorPreOrder implements Iterator<Integer> {
  Stack<BSTNode> stack;

  public BSTIteratorPreOrder(BSTNode root) {
    stack = new Stack<BSTNode>();
    if(root!=null) stack.push(root);
  }

  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  @Override
  public Integer next() {
    if (!hasNext()) {  
      throw new NoSuchElementException("All nodes have been visited!");  
    }

    BSTNode node = stack.pop();
    int result = node.val;
    if(node.right != null) stack.push(node.right);
    if(node.left != null) stack.push(node.left);
    return result;
  }
}

class Solution {

  public static void main(String[] args) {
    BSTNode root = new BSTNode(10);
    root.left = new BSTNode(12);
    root.right = new BSTNode(15);
    root.left.left = new BSTNode(25);
    root.left.right = new BSTNode(30);
    root.right.left = new BSTNode(36);

    BSTIteratorInOrder iterIn = new BSTIteratorInOrder(root);
    while(iterIn.hasNext()) {
      System.out.format("%d ",iterIn.next());
    }
    System.out.println("");

    BSTIteratorPostOrder iterPost = new BSTIteratorPostOrder(root);
    while(iterPost.hasNext()) {
      System.out.format("%d ",iterPost.next());
    }
    System.out.println("");

    BSTIteratorPreOrder iterPre = new BSTIteratorPreOrder(root);
    while(iterPre.hasNext()) {
      System.out.format("%d ",iterPre.next());
    }
    System.out.println("");

  }
}