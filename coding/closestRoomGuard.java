import java.io.*;
import java.util.*;


/*
Basically, we need to reverse the question. Don't try to find "for each room" 
- "the shortest distance to a guard", but rather find the shortest path from the set of guards to every room.

To do this, perform a breadth first search where the rooms with guards have distance 0 and expand them.

BFS, add all guards into queue by an O(N^2) search. 
Then since each rooms only been add into queue at most once and the maximum degree of each node is 4, 
the time complexity is O(N^2)

1) identify the location of each guard, put them in a queue
2) do BFS starting from the location of each guard, keep a visited map, since we don't want to visit the same cell
      1. Get the coordinates of the 4 neighbours to this cell.
      These are the coordinates +/- 1 of the x and y position.
      2. check if visited, if not, Mark the neighbour as visited and set its step count as 1 added to the step count 
        of the current cell.
      3. add the neighbor to the queue  
3) repeat BFS until queue is empty
 
*/
class Pos {
  int x;
  int y;
  public Pos(int xi, int yi) { x= xi; y = yi;}
}

class Solution {

  private static int G = -1;
  private static int B = -2;
  private static int O = -3;

  private static void farthestRoomFromGuards(int[][] mat) {
    //find and enqueue guard positions
    Queue<Pos> Q = new LinkedList<Pos>();
        // Initialize a 'visited' grid filled with false.
    boolean[][] visited = new boolean[mat.length][mat[0].length];
    Pos temp;
    int i, j;
    
     // Start the BFS by adding the coordinates of each guard cell.
    // Mark each of these guard cells as visited, and set their step count to 0.
    for (i=0; i < mat.length; i++) {
      for(j=0; j < mat[0].length; j++) {
        if( mat[i][j] == G ) { 
          temp = new Pos(i, j);
          visited[i][j] = true;
          mat[i][j] = 0;
          Q.add(temp); //putting guard position in Q
        }
      }
    }

    //BFS 
    while(!Q.isEmpty()) {

      temp= Q.poll();

      // Get the coordinates of the 4 neighbours to this cell.
      // These are the coordinates +/- 1 of the x and y position.
      Pos neighbours[] = {
        new Pos(temp.x - 1, temp.y), new Pos(temp.x + 1, temp.y),
        new Pos(temp.x, temp.y - 1), new Pos(temp.x, temp.y + 1),
      };

        // Try to visit each of the neighbours.
      for (Pos n : neighbours) {
        if (!inBounds(n.x, n.y, mat.length, mat[0].length) || visited[n.x][n.y] || mat[n.x][n.y] == B) {
          continue;
        } 
         // Mark the neighbour as visited and set its step count
         // as 1 added to the step count of the current cell.
        visited[n.x][n.y] = true;
        mat[n.x][n.y]= mat[temp.x][temp.y] + 1;
        // Add this neighbour to the BFS queue.
        Q.add(n);
      }
    }
      
    for (i=0; i < mat.length; i++) {
      for(j=0; j < mat[0].length; j++) {
        System.out.format("%d ", mat[i][j]);
      }
      System.out.println("");
    }

  }

  private static boolean inBounds(int a , int b, int m, int n) {
    return ((a)>=0 && (a)<m && (b)>=0 && (b)<n);
  }

  public static void main(String[] args) {
    int [][] mat = {
      { 0, 0, 0 },
      {B, G, G},
      {B, 0, 0}
    };
    
    int [][] mat2 = {
       {O, O, O, B, O, O},
        {B, G, B, B, O, B},
        {B, O, O, B, G, G},
        {O, B, O, O, O, B}
    };
    
    farthestRoomFromGuards(mat2);
  }
}
