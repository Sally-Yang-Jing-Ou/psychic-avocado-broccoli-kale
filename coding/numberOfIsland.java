import java.io.*;
import java.util.*;

/*


Number of island:

count the ones, DFS

worst time O(m*n), the whole map is one island

 */

class Solution {

  private static int numberOfIsland(int[][] mat) {
    int m = mat.length;
    if(m == 0) return 0; //handle empty mat
    int n = mat[0].length; 
    int count = 0;
    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(mat[i][j] == 1) {
          count++;
          DFS(i, j, mat, m, n); //DFS at the point where there's a one - potential a bigger island, mark those ones as '0' aka visited so we don't come back to the same point
        }
      }
    }
    return count;

  }

  private static void DFS(int i, int j, int[][] mat, int m, int n) {
    if(i >= m || i < 0 || j >= n || j < 0 || mat[i][j] == 0) {
      return;
    }

    mat[i][j] = 0; //mark as visited essentially
    DFS(i-1, j, mat, m, n);
    DFS(i, j-1, mat, m, n);
    DFS(i, j+1, mat, m, n);
    DFS(i+1, j, mat, m, n);
  }

  public static void main(String[] args) {
    int[][] mat = new int[][]{
      { 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 },
      { 0, 1, 1, 0, 0, 1, 0, 0, 0, 0 },
      { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
      { 0, 1, 1, 1, 0, 0, 1, 1, 0, 0 }
    };
    System.out.println(numberOfIsland(mat));

  }
}
