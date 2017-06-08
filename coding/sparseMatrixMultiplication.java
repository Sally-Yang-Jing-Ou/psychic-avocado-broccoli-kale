import java.io.*;
import java.util.*;

/*


      { 1, 0, 0},
      { -1, 0, 3}


      { 7, 0, 0},
      { 0, 0, 0},
      {0, 0, 1}

7 0 0
-7 0 3

 resulting matrix

 (0,0) -> sum of (row 0 of A x column 0 of B) where the numbers multiply together
 needs a matching row number and column number, column 2 of A x row 2 

 (0,1) -> sum of (row 0 of A x column 1 of B) where the numbers multiply together
 needs a matching row number and column number


Second solution:
The idea is derived from a CMU lecture.

A sparse matrix can be represented as a sequence of rows, 
each of which is a sequence of (column-number, value) 
pairs of the nonzero values in the row.
 CSC or CSR storage
*/

public class Solution {

  private static void matrixMultiplication(int[][] A, int[][] B) {
    int rA = A.length;
    int cA = A[0].length;
    int rB = B.length;
    int cB = B[0].length;
    int[][] results = new int[rA][cB];

    if(cA != rB) {
      System.out.println("Number of columns in A isn't the same as rows in B");
      return;
    }

    for(int i = 0; i < rA; i++) {
      for(int k = 0; k < cA; k++) {
        if(A[i][k] != 0) {
          int valA = A[i][k];
          for(int j = 0; j < cB; j++) {
            if(B[k][j] != 0) results[i][j] += valA * B[k][j];
          }
        }
      }
    }

    for(int i = 0; i < results.length; i++) {
      for(int j = 0; j < results[0].length; j++) {
        System.out.format("%d ", results[i][j]);
      }
      System.out.println("");
    }

  }

  private static void matrixMultiplication2(int[][] A, int[][] B) {
    int rA = A.length;
    int cA = A[0].length;
    int rB = B.length;
    int cB = B[0].length;
    int[][] results = new int[rA][cB];

    if(cA != rB) {
      System.out.println("Number of columns in A isn't the same as rows in B");
      return;
    }

    /*
      represented as a sequence of rows, 
      each of which is a sequence of (column-number, value) 
      pairs of the nonzero values in the row.
    */
    List<Integer>[] indexA = new ArrayList[rA];
    for(int i = 0; i < rA; i++) {
      List<Integer> columnsAndVal = new ArrayList<Integer>();
      for(int k = 0; k < cA; k++) {
        if(A[i][k] != 0) {
          columnsAndVal.add(k);
          columnsAndVal.add(A[i][k]);          
        }
      }
      indexA[i] = columnsAndVal;
    }


    // List<Integer>[] indexB = new ArrayList[cB];
    // for(int i = 0; i < cB; i++) {
    //   for(int k = 0; k < rB; k++) {
    //     List<Integer> rowsAndVal = new ArrayList<Integer>();
    //     if(B[k][i] != 0) {
    //       rowsAndVal.add(k);
    //       rowsAndVal.add(B[k][i]);          
    //     }
    //   }
    //   indexB[i] = rowsAndVal;
    // }


    for(int i = 0; i < rA; i++) {
      List<Integer> columnsVal = indexA[i];
      for(int k = 0; k < columnsVal.size(); k+=2) {
        int col = columnsVal.get(k);
        int val = columnsVal.get(k+1);
        for(int j = 0; j < cB; j++) {
          if(B[col][j] != 0) results[i][j] += val * B[col][j];
        }
      }
    }


    for(int i = 0; i < results.length; i++) {
      for(int j = 0; j < results[0].length; j++) {
        System.out.format("%d ", results[i][j]);
      }
      System.out.println("");
    }

  }

  public static void main(String[] args) {
    int[][] A = new int[][] {
      { 1, 0, 0},
      { -1, 0, 3}
    };

    int[][] B = new int[][] {
      { 7, 0, 0},
      { 0, 0, 0},
      {0, 0, 1}
    };

    matrixMultiplication2(A, B);

  }
}