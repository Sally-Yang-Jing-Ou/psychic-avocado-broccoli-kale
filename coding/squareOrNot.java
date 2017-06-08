import java.io.*;
import java.util.*;

/*
4 points determine if square 

The idea is to pick any point and calculate
its distance from rest of the points. 
Let the picked point be ‘p’. 
To form a square, distance of two points must be same from ‘p’,
let this distance be d. The distance from one point must be 
different from that d and must be equal to √2 times d. Let this point with different distance be ‘q’.
The above condition is not good enough 
as the the point with different distance can be on the other side. 
We also need to check that q is at same distance from 
2 other points and this distance is same as d.

sqrt(d^2+d^2) = q
2*d^2 = q^2

Example:


  1  -  3
d |     | 
  2  -  4
     d


  2
d |
  1 - 3
    d |
      4

must check if 4-2 == 4-3 && 4-2 == d 

1) The idea is to pick any point and calculate its distance from rest of the points. 

2) If lengths if (p1, p2) and (p1, p3) are same, then
following conditions must met to form a square.
  1. Square of length of (p1, p4) is same as twice
   the square of (p1, p2)  2*d^2 = q^2
  2. p4 is at same distance from p2 and p3 and the distance is the same as (p1, p2)

    
    
 */
class Point {
  int x;
  int y;
  Point(int m, int n) {
    x = m;
    y = n;
  }
}

class Solution {

  //compute square of distance from a to b
  //sqrt(a^2+b^2) = c, a^2 + b^2 = c^2
  private static int squareOfDistances(Point a, Point b) {
    return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
  }
  
  private static boolean squareOrNot(Point p1, Point p2, Point p3, Point p4) {
    int d2 = squareOfDistances(p1, p2);
    int d3 = squareOfDistances(p1, p3);
    int d4 = squareOfDistances(p1, p4);
    
    //2*d^2 = q^2
    if(d2 == d3 && 2*d2 == d4) {
      //check the other two sides from p4
      int d = squareOfDistances(p4, p2);
      return(d == squareOfDistances(p4, p3) && d == d2);
    }
    
    if(d2 == d4 && 2*d2 == d3) {
      //check the other two sides from p4
      int d = squareOfDistances(p3, p2);
      return(d == squareOfDistances(p3, p4) && d == d2);
    }
    
    if(d3 == d4 && 2*d3 == d2) {
      //check the other two sides from p4
      int d = squareOfDistances(p2, p3);
      return(d == squareOfDistances(p2, p3) && d == d3);
    }
    
    return false;
  }
  
  
  public static void main(String[] args) {
    Point p1 = new Point(30, 10);    
    Point p2 = new Point(10, -10);
    Point p3 = new Point(30, -10);
    Point p4 = new Point(10, 10);
    
    System.out.println(squareOrNot(p1,p2,p3,p4));
  }
}









