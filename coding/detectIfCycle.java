import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

enum Color {
  WHITE, GREY, BLACK  
}

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

Depth First Traversal can be used to detect cycle in a Graph. 
DFS for a connected graph produces a tree. 
There is a cycle in a graph only if there is a back edge present in the graph. 
A back edge is an edge that is from a node to 
itself (selfloop) or one of its ancestor in the tree produced by DFS

WHITE : Vertex is not processed yet.  Initially
        all vertices are WHITE.

GRAY : Vertex is being processed (DFS for this 
       vertex has started, but not finished which means
       that all descendants (ind DFS tree) of this vertex
       are not processed yet (or this vertex is in function
       call stack)

BLACK : Vertex and all its descendants are 
        processed.

While doing DFS, if we encounter an edge from current 
vertex to a GRAY vertex, then this edge is back edge 
and hence there is a cycle

*/

class Solution {   
  
  private static Boolean isCycle(int[][] graph) {
    int[] color = new int[graph.length];
    Arrays.fill(color, Color.WHITE.ordinal());
    for (int i = 0; i < graph.length; i++ ) {
      
      System.out.println(color[i]);
      if (color[i] == Color.WHITE.ordinal() && isCycle(i, graph, color)) {
        return true;
      }
    }
    return false;
  }
  
  private static Boolean isCycle(int i, int[][] graph, int[] color) {
     color[i] = Color.GREY.ordinal();
     
     for(int j = 0; j < graph[i].length; j++) {
       
       if (graph[i][j] == 1) {
        if (color[j] == Color.GREY.ordinal()) return true;
       
        if (color[j] == Color.WHITE.ordinal() && isCycle(j, graph, color)) {
          return true;
        }
       }
     }
     
    color[i] = Color.BLACK.ordinal();
    return false;
   }

  
  
  public static void main(String[] args) {
    
    int[][] array = new int[][]{{0,1,1,0},{0,0,1,0},{1,0,0,1},{0,0,0,1}};
    int[][] array2 = new int[][]{{0,1,0,0},{0,0,0,0},{1,0,0,1},{1,0,0,0}};

    System.out.print(isCycle(array));
    
  }
}
    