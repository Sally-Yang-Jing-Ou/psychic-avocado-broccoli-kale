


import java.io.*;
import java.util.*;


class Solution {
  
  private static void print(List<Integer> path) {
    for(Integer i : path) {
      System.out.format("%d ", i);
    }
    
    System.out.println("");
  }
  
  private static void printMatrixPath(int[][] mat, int i, int j, List<Integer> path) {
    
    if (i >= mat.length || j >= mat[0].length) {
      return;
    }
    
    List<Integer> newPath = new ArrayList<Integer>(path);
    newPath.add(mat[i][j]);
    
    if (i == mat.length-1 && j == mat[0].length-1) {
      print(newPath);
      return;
    }
    
    printMatrixPath(mat, i+1, j, newPath);
    printMatrixPath(mat, i, j+1, newPath);
    //path.remove(path.size()-1);
  }
  private static void printMatrixPath(int[][] mat) {
    
    printMatrixPath(mat, 0, 0, new ArrayList<Integer>());
  }
  
  public static void main(String[] args) {
    int[][] arr = {{1, 2, 3},
                   {4, 6, 6}};
    printMatrixPath(arr);
  }
}


















