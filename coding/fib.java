import java.io.*;
import java.util.*;

/*
 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

            5
         4     3
       3   2  2  1
      2 1 1 0 
 */


class Solution { 
  
  
  public static int fib(int n) {
    if (n==0 || n==1) return n;
    return fib(n-1) + fib(n-2);
  }

  /*
 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

            5
         4     3x
       3   2x 
      2 1 1x 0x 
     1 0 
 */
  public static int fib(int n) {
    fib(n, new int[n+1]);
  } 

  public static int fib(int n, int[] res) {
    if (n==0 || n==1) return n;

    if (res[n] == 0) {
      res[n] = fib(n-1, res) + fib(n-2, res);

    }
    return res[n];
  }
//bottom up
  public static int fib(int n) {
    if (n==0 || n==1) return n;
    int[] fibo = new int[n+1];
    fibo[0] = 0;
    fibo[1] = 1;
    for (int i = 2; i <= n; i++) {
      fibo[i] = fibo[i-1] + fibo[i-2];
    }
    return fibo[n];
  }


  public static int fib(int n) {
    int[] fibo = new int[n+1];
    int a = 0, b = 1, c = 1;
    if (n == 0) return a;
    for (int i = 2; i <= n; i++) {
      c = a + b;
      a = b;
      b = c;
    }
    return c;
  }
  
  public static void main(String[] args) {
    System.out.print(fib(22));
  }
}


class FibIterator implements Iterator<Integer> {
  int a, b, c;
  int count;
  
  public FibIterator() {
    a = 0;
    b = 1;
    c = 1;
    count = 0;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Integer next() {
    if (count == 0 || count == 1) return count++;
    c = a + b;
    a = b;
    b = c;
    return c;
  }
}

class Solution {

  public static void main(String[] args) {
    FibIterator fib = new FibIterator();
    
    for (int i = 0; i < 8; i++) {
      System.out.println(fib.next());
    }
  }
}
    