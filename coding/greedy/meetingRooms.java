import java.io.*;
import java.util.*;

class Interval {
  int start;
  int end;
  Interval(int s, int e) {
    start = s;
    end = e;
  }
}

class comp implements Comparator<Interval> {
  public int compare(Interval a, Interval b) { 
    return a.start - b.start; 
  }
}

class Solution {

  /*

1. order meeting times by starting time
2. if next intervals starting time is less than previous's ending time, there's overlap

note: 
if second doesn't overlap with first, we don't need to worry about the potential of 
third overlapping with first, since it's sorted by starting time
which means, second' starting time is after first's ending time, which means third's starting time has to be greater than second's (which inherently is greater than first's ending)

*/
  private static boolean canAttendAllMeetings(Interval[] intervals) {
    Arrays.sort(intervals, (x, y) -> (x.start - y.start)); //java8 lambda 
    //Arrays.sort(intervals, new comp());
    for(int i = 1; i < intervals.length; i++) {
      if(intervals[i].start < intervals[i-1].end) return false;
    }
    return true;
  }


  /*
  meeting rooms required.
  This is more than just counting the overlaps, we need to do this greedily

  1. We sort the meetings by start time

  2. Then step through all the meetings in order of start time, we schedule a new room if the starting time 
  of next meeting is smaller than the ending time of the meeting that finishes the ealiest (overlap). 
  To check all the previous scheduled meetings, we keep a min heap/priority queue by finishing time 
  of all the scheduled meetings. Assume there are d number of rooms, then checking takes logd time.
  
  3. Count the number of rooms, size of heap

  The run time will be nlogn + nlogd = nlogn time.
  
  So basically, if second doesn't overlap with first, we reuse the room, if it overlaps, we put 
  it in the heap (adding a room)
  we greedily choose the one that finishes the earliest, 
  compares each interval start's time to that
  if the start is less than the earliest finish time, add a room, 
  else reuse it.

  */

  private static int meetingRoomsRequired(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) {return 0;} 
    Arrays.sort(intervals, (x, y) -> (x.start - y.start)); //java8 lambda, ascending time
    PriorityQueue<Interval> pq = new PriorityQueue<Interval>((x,y) -> x.end - y.end); //order by end times, min heap
    
    pq.offer(intervals[0]);
    
    for(int i = 1; i < intervals.length; i++) {
      //if it doesn't overlap, reuse the same room
      //compare with the meeting room that finishes earliest
      if(intervals[i].start >= pq.peek().end) {
        Interval in = pq.poll();
        in.end = intervals[i].end; //merge it, reuse
        pq.offer(in); //put the meeting room back in after updating the end time
      } else {
        pq.offer(intervals[i]); //add an extra room since this meeting starts before the earliest one can end
      }
    }
    return pq.size();
  }

  public static void main(String[] args) {
    Interval[] in = new Interval[4];
    in[0] = new Interval(0, 30);
    in[1] = new Interval(20, 40);
    in[2] = new Interval(40, 50);
    in[3] = new Interval(25, 50); 
    System.out.println(meetingRoomsRequired(in)); //output 3
  }
}
