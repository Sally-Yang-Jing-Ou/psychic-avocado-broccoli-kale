import java.io.*;
import java.util.*;

class Solution {

    public static double findDistance(Point p1, Point p2) {
        int squareOfX = (p2.getX() - p1.getX()) * (p2.getX() - p1.getX());
        int squareOfY = (p2.getY() - p1.getY()) * (p2.getY() - p1.getY());
        return Math.sqrt(squareOfX + squareOfY);
    }
    
    public static Point[] findClosest(Point[] points, Point point, int k) {
        int len = points.length;
        for (int i = 0; i < len; i++) {
            Point currPoint = points[i];
            currPoint.setDistance(findDistance(point, currPoint));
        }
        //Arrays.sort(points);
        //return Arrays.copyOf(points, k);
        return points;
      
    }
  
    public static void main(String[] args) {
        Point[] points = {new Point(-2, -4), new Point(0, 0), new Point(10, 15),
                new Point(5, 6), new Point(7, 8), new Point(-10, -30)};
        Point[] output = findClosest(points, new Point(5, 5), 2);
        
      int k = quickSelect(output, 0, output.length-1, 2);
        for (int i=0; i<k; i++){
            System.out.println(output[i].toString());
        }
    }
  
   
  private static int quickSelect(Point[] array, int left, int right, int target) {
    if(left == right) return left;
   
    while(true) {
      int pivot = left + (int) Math.floor(Math.random() * (right - left + 1));
      int p = partition(array, left, right, pivot);
      if (p == target) {
        return p;
      } else if (p < target) {
        left = p + 1;
      } else {
        right = p - 1;
      }
    }
  }
  
  private static int partition(Point[] array, int left, int right, int pivot) {
    double pivotVal = array[pivot].getDistance();
    swap(array, pivot, right); // move pivot to end
    int index = left;
    
    for (int i = left; i < right; i++) {
      if(array[i].getDistance() < pivotVal) {
        swap(array, i, index);
        index++;
      }
    }
    swap(array, right, index); //move pivot to final place
    return index;
  }
  
  private static void swap(Point[] a, int left, int right) {
    Point temp = a[left];
    a[left] = a[right];
    a[right] = temp;
  }
  
  
  
}

class Point implements Comparable<Point> {

    /**
     * The x position of the point
     */
     int mX;
    /**
     * The y position of the point
     */
     int mY;
    /**
     * distance form the (5,5)
     */
     double mDistance;
  
    public Point(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    public int getX() {
        return mX;
    }

    public void setX(int mX) {
        this.mX = mX;
    }

    public int getY() {
        return mY;
    }

    public void setY(int mY) {
        this.mY = mY;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double mDistance) {
        this.mDistance = mDistance;
    }

    @Override
    public int compareTo(Point other) {
        return (int) (mDistance - other.mDistance);
    }

    @Override
    public String toString() {
        return "(" + mX + ", " + mY +")";
    }
}














