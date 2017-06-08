/*

Given a list of intervals A and one interval B, find the least
number of intervals from A that can fully cover B.

If cannot find any result, just return 0;

For example:
Given A=[[0,3],[3,4],[4,6],[2,7]] B=[0,6] return 2 since we can use [0,3] [2,7] to cover the B
Given A=[[0,3],[4,7]] B=[0,6] return 0 since we cannot find any interval combination from A to cover the B

Could we sort the array by start time? Each time we select greedily an interval which starts at or before current start point point and is the farthest interval.
for example .
[0,3],[2,7][3,4][4,6]

we select 0,3 because this is the only option
we make a choice between [2,7], [3,4] and takes [2,7] because 7-3 > 4-3
So we choose 2 intervals

Sort the interval list A with starting point and then end point. Traverse the list, and skip all the entries
that have end points less than the start point of the target. The results that cover target B are in retVal
There are three conditions to traverse:

starting with the current element skip all the elements that have end points less than the end point of current
element.
if the end point of next element is greater than current end point and if the start point of next element
is less than or equal to target start point or the end point of last element in retVal list than skip current element. and keep skipping until this condition fails
if the last element in the result array has end point greater than the current end point skip the current element.
*/

class Interval implements Comparable<Interval> {
		int start;		
		int end;	
		Interval(int s,  int e)  {
			start  =  s;			
			end  =  e;
		}
		public String toString()  {
			return String.format("%d %d" ,start, end);
		}
		@Override
		public int compareTo(Interval o) {
			i f(star t!= o.start)
			    return start  -  o.start;
			else
			    return end  -  o.end;
		}		
}

int minNumberOfIntervals(Interval[] list, Interval interval) {
    Arrays.sort(list);
    System.out.println(Arrays.toString(list));
    int i = 0;
    int start = interval.start;
    int max = -1;
    int num = 0;
    while (i < list.length && max < interval.end) { 
      if (list[i].end <= start)
          i++;
      else {
          if (list[i].start > start)
              break;
          while (i < list.length && max < interval.end && list[i].start <= start) {            
              if (list[i].end > max) {
                  max = list[i].end;                 
              }     
              i++;
          }  
          if (start != max) {
              start = max;
              num++;
          }
          
      }    
    }
    if (max < interval.end)
        return 0;   
    return num;
}