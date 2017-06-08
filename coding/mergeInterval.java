import java.io.*;
import java.util.*;

/*
The idea is, in sorted array of intervals, if interval[i] doesn’t overlap with interval[i-1], 
then interval[i+1] cannot overlap with interval[i-1] because starting time of interval[i+1] 
must be greater than or equal to interval[i].


1. Sort the intervals based on increasing order of 
    starting time.
2. Push the first interval on to a stack.
3. For each interval do the following
   a. If the current interval does not overlap with the stack 
       top, push it.
   b. If the current interval overlaps with stack top and ending
       time of current interval is more than that of stack top, 
       update stack top with the ending  time of current interval.
4. At the end stack contains the merged intervals.
*/

class Interval {
  int start;
  int end;
  Interval(int s, int e) { start = s; end = e;} 

}

class Comp implements Comparator<Interval> {
  public int compare(Interval i1, Interval i2) {
    return i1.start - i2.start;
  }
}

class Solution {
  
  private static void mergeIntervals(List<Integer[]> a1, List<Integer[]> a2) {
    ArrayList<Interval> intervals = new ArrayList<Interval>();
    Stack<Interval> st = new Stack<Interval>();
    for (Integer[] i: a1) {
      intervals.add(new Interval(i[0], i[1]));
    }
    
    for (Integer[] i: a2) {
      intervals.add(new Interval(i[0], i[1]));
    }
    
    Collections.sort(intervals, new Comp());
    
    st.push(intervals.get(0));
    
    for (int i = 1; i < intervals.size(); i++) {
      Interval in = intervals.get(i);
      Interval top = st.peek();  
      if (top.end < in.start) {
        st.push(in);
      } else {
        top.end = Math.max(in.end, top.end);
        st.pop();
        st.push(top);
      }
    }
    
    OR PUT IT IN AN ARRAYLIST AND REVERSE IT
    while (!st.isEmpty()) {
      Interval top = st.pop();
      System.out.format("%d-%d%n", top.start, top.end);
    }
    
//     for (Interval i: intervals) {
//       System.out.format("%d-%d%n", i.start, i.end);
//     }
    
  
  }

/*

                    [3, 10]

[1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] 

in as [1,2],[3,10],[12,16]
new interval [4,9] overlaps with [3,5],[6,7],[8,10].

*/
  private static void mergeOneInterval(List<Integer[]> a1, Interval newIn) {
    ArrayList<Interval> intervals = new ArrayList<Interval>();
    ArrayList<Interval> res = new ArrayList<Interval>();
    for (Integer[] i: a1) {
      intervals.add(new Interval(i[0], i[1]));
    }
    
    for (Interval in: intervals) {
      if (in.end < newIn.start) { //not overlapping
        res.add(in);
      } else if (in.start <= newIn.end) { //if overlap -> newInstart <= in.end && in.start <= newEnd
        newIn = new Interval(Math.min(newIn.start, in.start), Math.max(newIn.end, in.end));
      } else { //if in.start > newInterval.end
        res.add(newIn);
        newIn = in;
      }
    }
    
    res.add(newIn);
    
    for (Interval i: res) {
      System.out.format("%d-%d%n", i.start, i.end);
    }
    
  }

public class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    ArrayList<Interval> res = new ArrayList<Interval>();

    for (Interval in: intervals) {
      if (in.end < newInterval.start) { //not overlapping
        res.add(in);
      } else if (in.start <= newInterval.end) { //if overlap -> newInstart <= in.end && in.start <= newEnd
        newInterval = new Interval(Math.min(newInterval.start, in.start), Math.max(newInterval.end, in.end));
      } else { //if in.start > newInterval.end
        res.add(newInterval);
        newInterval = in;
      }
    }

    res.add(newInterval);

    return res;
  }
}
  
  public static void main(String[] args) {
    List<Integer[]> a1 = new ArrayList<Integer[]>();
    List<Integer[]> a2 = new ArrayList<Integer[]>();
    a1.add(new Integer[]{3,11});
    a1.add(new Integer[]{17,25});
    a1.add(new Integer[]{58,73});
    a2.add(new Integer[]{6,18});
    a2.add(new Integer[]{40,47});
    mergeIntervals(a1, a2);
  }
}

import java.io.*;
import java.util.*;

class Interval {
  int start;
  int end;
  Interval(int s, int e) { start = s; end = e;} 

}

class Comp implements Comparator<Interval> {
  public int compare(Interval i1, Interval i2) {
    return i1.start - i2.start;
  }
}

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
import java.io.*;
import java.util.*;

public class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1)
      return intervals;

    List<Interval> result = new LinkedList<Interval>();
    Collections.sort(intervals, (i1, i2) -> (i1.start - i2.start));

    int start = intervals.get(0).start;
    int end = intervals.get(0).end;
    for (Interval interval : intervals) {
      if (interval.start <= end) // Overlapping intervals, move the end if needed
        end = Math.max(end, interval.end);
      else {                     // Disjoint intervals, add the previous one and reset bounds
        result.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }

    // Add the last interval
    result.add(new Interval(start, end));
    return result;
  }
}

class Solution {

  private static void mergeIntervals(List<Integer[]> a1, List<Integer[]> a2) {
    ArrayList<Interval> intervals = new ArrayList<Interval>();
    Stack<Interval> st = new Stack<Interval>();
    for (Integer[] i: a1) {
      intervals.add(new Interval(i[0], i[1]));
    }

    for (Integer[] i: a2) {
      intervals.add(new Interval(i[0], i[1]));
    }

    Collections.sort(intervals, new Comp());

    List<Interval> result = new LinkedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;

    for (Interval interval : intervals) {
      if (interval.start <= end) // Overlapping intervals, move the end if needed
        end = Math.max(end, interval.end);
      else {                     // Disjoint intervals, add the previous one and reset bounds
        result.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }

    // Add the last interval
    result.add(new Interval(start, end));


    for (Interval i: result) {
      System.out.format("%d-%d%n", i.start, i.end);
    }
  }
  
  
  public static void main(String[] args) {
    List<Integer[]> a1 = new ArrayList<Integer[]>();
    List<Integer[]> a2 = new ArrayList<Integer[]>();
    a1.add(new Integer[]{3,11});
    a1.add(new Integer[]{17,25});
    a1.add(new Integer[]{58,73});
    a2.add(new Integer[]{6,18});
    a2.add(new Integer[]{40,47});
    mergeIntervals(a1, a2);
  }
}






/*
Given two arrays/Lists (choose whatever you want to) with sorted and non intersecting intervals. Merge them to get a new sorted non intersecting array/list. 
Eg: 
Given: 
Arr1 = [3-11, 17-25, 58-73]; 
Arr2 = [6-18, 40-47]; 

3-11, 6-18, 17-25, 40-46, 58-73

Wanted: 
Arr3 = [3-25, 40-47, 58-73];

*/
