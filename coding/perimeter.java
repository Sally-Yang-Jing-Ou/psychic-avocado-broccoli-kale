import java.io.*;
import java.util.*;

/*
O(mn)
 */


class Solution {   

  
  private static int perimeter(int[][] mat, int x, int y, int width, int height, boolean[][] visited) {
    int per = 0;
    int counter = 0;
    int current = mat[x][y]; 
    visited[x][y] = true;
    
    if (x > 0 && current == mat[x-1][y]) {
      counter++;
      if (!visited[x-1][y]) {
        per += perimeter(mat, x-1, y, width, height, visited);
      }
    }
    
    if (x < width-1 && current == mat[x+1][y]) {
      counter++;
      if (!visited[x+1][y]) {
        per += perimeter(mat, x+1, y, width, height, visited);
      }
    }
    
    if (y > 0 && current == mat[x][y-1]) {
      counter++;
      if (!visited[x][y-1]) {
        per += perimeter(mat, x, y-1, width, height, visited);
      }
    }
     
    if (y < height-1 && current == mat[x][y+1]) {
      counter++;
      if ( !visited[x][y+1] ) {
        per += perimeter(mat, x, y+1, width, height, visited);
      }
    }
    
    return per + 4 - counter;
  }
  
  
  public static void main(String[] args) {
    int[][] mat = {{1, 1, 1, 1},
                   {0, 0, 1, 1},
                   {1, 0, 0, 0},
                   {1, 1, 1, 0}};
    boolean[][] visited = new boolean[4][4];
    System.out.println(visited[0][0]);    
    System.out.println(perimeter(mat, 3, 0, 4, 4, visited));
  }
}
    