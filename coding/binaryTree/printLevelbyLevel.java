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


class Solution {
    
    private static void printGivenLevel (Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.val + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
    
    private static void printLevelBylevel (Node root) {
        if(root == null) return;
        // Create an empty queue for level order tarversal
        
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int size;
        
        while(true) {
            // queue size) indicates number of nodes
            // at current lelvel.
            size = q.size();
            if (size == 0) break;
            
            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (size > 0) {
                Node n = q.poll();
                System.out.format("%d ", n.val);
                if(n.left != null) {
                    q.add(n.left);
                }
                if(n.right != null) {
                    q.add(n.right);
                }
                size--;
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
        
        //     Node root = new Node(1);
        //     root.left = new Node(2);
        //     root.right = new Node(3);
        //     root.left.left = new Node(4);
        //     root.left.right = new Node(5);
        
        //printGivenLevel(root, 2);
        printLevelBylevel(root);
    }
    
}
