import java.io.*;
import java.util.*;

/*
If you have only one room, what is the maximum number of meetings
you can scheduled into that room.

2. go through all the meetings in order of finishing time, 
schedule the meeting into the room if the room is not occupied at its start time,
and increase the count by one.
3. no of count will be the max number of meetings you can schedule into the room.

Choose greedily, starting from the one that finishes first

We need to prove that the greedy algorithm is correct 
(choosing the meeting that finishes first can result in a optimal solution)
assume there is another schedule S’ that schedules more meetings (k + 1) then the
solution S (k solutions). Then at some point the S’ must scheduled some meeting that 
tm’ ends before the tm scheduled by S. But as we know that since S scheduled meeting 
that finishes first so the mth meeting must finishes no later than mth scheduled by S’. 
which is a contradiction.

*/
class Interval {
  int start;
  int end;
  Interval(int s, int e) {
    start = s;
    end = e;
  }
}

class Solution {
  
  private static int maxMeetingTofitInOneRoom(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) return 0;
    Arrays.sort(intervals, (x, y)->(x.end - y.end)); //sort by finishing time
    int earliestEnd = intervals[0].end;
    int max = 1;
    for(int i = 1; i < intervals.length; i++) {
      if(intervals[i].start >= earliestEnd){
        max++;
        earliestEnd = intervals[i].end;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    Interval[] in = new Interval[6];
    in[0] = new Interval(1, 2);
    in[1] = new Interval(3, 4);
    in[2] = new Interval(0, 6);
    in[3] = new Interval(8, 9); 
    in[4] = new Interval(5, 7); 
    in[5] = new Interval(5, 9); 

    System.out.println(maxMeetingTofitInOneRoom(in)); //output 4
  }
}
