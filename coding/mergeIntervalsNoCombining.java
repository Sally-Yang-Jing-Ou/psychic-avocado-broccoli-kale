import java.io.*;
import java.util.*;


class Interval {
  int start;
  int end;
  Interval(int s, int e) {
    start = s;
    end = e;
  }

  public boolean overlaps(Interval b) {
    if((this.start <= b.end && this.end >= b.start) || (b.start <= this.end && b.end >= this.start)) {
      return true;
    }
    return false;
  }
}

/*
We can combine and sort the two interval lists, which takes O((m+n)log(m+n)), m, n being the number of 
intervals in total. Which i already did before, probably in a java file called mergeIntervals.java or something like that.

But let's say we don't combine and just do it on the fly, we can probably do this in O(m+n)
m, n being the number of intervals in each list respectively, i dont like intervals tbh...
The idea is to use two pointers

*/
class Solution {

  public static List<Interval> mergeIntervalsNoCombining(Interval[] in1, Interval[] in2) {
    List<Interval> result = new ArrayList<Interval>();
    if (in1 == null && in2 == null || in1.length == 0 && in2.length == 0) return result;
    if (in1 == null || in1.length == 0) return new ArrayList<Interval>(Arrays.asList(in2));
    if (in2 == null || in2.length == 0) return new ArrayList<Interval>(Arrays.asList(in1));

    int i = 0;
    int j = 0;
    int len1 = in1.length;
    int len2 = in2.length;
    int resSize;

    while (i < len1 && j < len2) {
      Interval i1 = in1[i];
      Interval i2 = in2[j];

      resSize = result.size();
      //check the current result array, merge the result with whatever (i1 or i2) you can
      if (resSize > 0) {
        Interval last = result.get(resSize - 1);

        if(last.overlaps(i1) || last.overlaps(i2)) {
          Interval newInter;
          if (last.overlaps(i1)) {
            newInter = mergeInterval(last, i1);
            i++;
          } else {
            newInter = mergeInterval(last, i2);
            j++;
          }
          result.remove(resSize - 1);
          result.add(newInter);
          continue;
        }
      }

      if(i1.overlaps(i2)) {
        Interval newIn = mergeInterval(i1, i2);
        result.add(newIn);
        i++;
        j++;
      } else if(i2.start > i1.end) {
        result.add(i1);
        i++;
      } else {
        result.add(i2);
        j++;
      }
    }
    
    if(j < len2) {
      in1 = in2;
      i = j;
    }
    
    while(i < in1.length) {
      Interval last = result.get(result.size() - 1);
      Interval i1 = in1[i];
      if(last.overlaps(i1)) {
        i1 = mergeInterval(last, i1);
        result.remove(result.size()-1);
      }
      result.add(i1);
      i++;
    }

    return result;
  }

  private static Interval mergeInterval(Interval i1, Interval i2) {
    return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
  }
  
  /*
  
  [1,5], [10,14], [16,18]
B: [2,6], [8,10], [11,20]
output [1,6], [8, 20]
  */

  public static void main(String[] args) {
    // Interval[] in1 = new Interval[]{new Interval(3, 11), new Interval(17, 25), new Interval(58, 73)};
    // Interval[] in2 = new Interval[]{new Interval(6,18), new Interval(40, 47)};
    
    Interval[] in1 = new Interval[]{new Interval(1, 5), new Interval(10, 14), new Interval(16, 18)};
    Interval[] in2 = new Interval[]{new Interval(2,6), new Interval(8, 10), new Interval(11,20)};
    List<Interval> list = mergeIntervalsNoCombining(in1, in2);
    
    for(Interval in: list) {
      System.out.format("%d-%d%n", in.start, in.end);
    }
  }
}