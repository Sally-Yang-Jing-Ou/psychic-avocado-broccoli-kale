import java.io.*;
import java.util.*;


class Point {
  int x;
  int y;
  
  Point(int xc, int yc) {
    x = xc;
    y = yc;
  }
}

/*
1. map each x coordinates to a list of y coodinates, sort the order of y coordinates

2. find the common y coordinates in any two lists (x), 
when we have at least 2 common y-coordinates, we have a rectangle


   x       x

   x       x

*/

class Solution {
  
  private static void findRectangle(Point[] points) {
    Map<Integer, TreeSet<Integer>> map = new Map<Integer, TreeSet<Integer>>();
    
    for(Point p : points) {
      if (!map.containsKey(p.x)) {
        map.put(p.x, new TreeSet<Integer>());
      }
      map.get(p.x).add(p.y);
    } //map each x coordinate to a list of y's
    
    
  
  }

  public static void main(String[] args) {

  }
}

