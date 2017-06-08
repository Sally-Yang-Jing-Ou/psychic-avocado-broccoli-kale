import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

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
  //Complexity would be O(m + n).
  
  public static int[] search(int[][] matrix, int target) {
    int[] coordinates = new int[2];
    Arrays.fill(coordinates, -1);
    if (matrix == null) return coordinates;
    int n = matrix.length;
    int m = matrix[0].length;
    
    int x = 0;
    int y = m - 1;
    //search from top right
    while (x < n && y >= 0) {
      if (matrix[x][y] == target) {
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
      } else if (matrix[x][y] > target) {
        y--;
      } else {
        x++;
      }
    }
    
    return coordinates;
    
  }
  
  public static void main(String[] args) {
    
    int[][] mat = {{10, 20, 30, 40},
                   {15, 25, 35, 45},
                   {27, 29, 37, 48},
                   {32, 33, 39, 50}};
    
    int [] print = search(mat, 33);
    
    if (print[0] == -1) {
          System.out.print("does not exist");

    } else {
    System.out.print(print[0]);
    System.out.print(print[1]);
    }
  }
}


import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

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
  // matrix[x][y] = array[x * numberColumns + y];
  
  
  public static int[] search(int[][] matrix, int target) {
    int[] coordinates = new int[2];
    Arrays.fill(coordinates, -1);
    if (matrix == null) return coordinates;
    int n = matrix.length;
    int m = matrix[0].length;
    
    int x = 0;
    int y = n * m - 1;
    
    while (x <= y) {
      
      int mid = (x+y)/2;
      int midX = mid / m;
      int midY = mid % m;
      
      if (matrix[midX][midY] == target) {
        coordinates[0] = midX;
        coordinates[1] = midY;
        return coordinates;
      } else if (matrix[midX][midY] > target) {
        y--;
      } else {
        x++;
      }
    }
    
    return coordinates;
    
  }

  //log(mn)
  
  public static void main(String[] args) {
    
    int[][] mat = { {1, 3,  5,  7},
                   {10, 11, 16, 20},
                   {23, 30, 34, 50}};
    
    int [] print = search(mat, 7);
    
    if (print[0] == -1) {
          System.out.print("does not exist");

    } else {
    System.out.print(print[0]);
    System.out.print(print[1]);
    }
  }
}

    