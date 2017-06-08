import java.io.*;
import java.util.*;

/*

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/

class Pos {
  int x;
  int y;
  Pos(int xc, int yc) {
    x = xc;
    y = yc;
  }
}  

class Solution {

  private static int INF = Integer.MAX_VALUE; 

  private static void wallsAndGates(int[][] mat) {
    if(mat == null || mat.length == 0) return;
    int m = mat.length;
    int n = mat[0].length;
    boolean[][] visited = new boolean[m][n];

    Queue<Pos> q = new LinkedList<Pos>();

    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(mat[i][j] == 0) {
          visited[i][j] = true;
          q.add(new Pos(i, j));
        }
      }
    }

    while(!q.isEmpty()) {
      Pos curr = q.poll();
      int cx = curr.x;
      int cy = curr.y;

      // Get the coordinates of the 4 neighbours to this cell.
      // These are the coordinates +/- 1 of the x and y position.
      Pos[] neighbours = {
        new Pos(cx+1, cy), new Pos(cx-1, cy),
        new Pos(cx, cy+1), new Pos(cx, cy-1)
      };

      for(Pos p : neighbours) {
        if (!inBounds(p, m, n) || visited[p.x][p.y] || mat[p.x][p.y] == -1) continue;

        visited[p.x][p.y] = true;
        mat[p.x][p.y] = mat[cx][cy] + 1;
        q.add(p);
      }
    }
  }

  private static boolean inBounds (Pos p, int m, int n) {
    return p.x < m && p.x >= 0 && p.y < n && p.y >=0;
  }

  public static void main(String[] args) {
    int [][] mat = {
      { INF, -1, 0, INF },
      { INF, INF, INF, -1 },
      { INF, -1, INF, -1 },
      { 0, -1, INF, INF}
    };

    wallsAndGates(mat);

    for(int i = 0; i < mat.length; i++) {
      for(int j = 0; j < mat[0].length; j++) {
        System.out.format("%d ", mat[i][j]);
      }
      System.out.println("");
    }

  }
}