import java.io.*;
import java.util.*;


/*
use a priority queue (max heap) with frequency as priority.

Given n, each time extract top n elements from the queue and put into scheduler queue (output). 
Now, decrease the frequency of the elements extracted and put it back to priority queue if new frequency is grater than zero. 

If queue is left with less than n elements then put (n-queue_size) numbers of idle jobs in the output.

Overall complexity is O(Nlgm) where N = length of input string. m = total number of unique tasks.

*/

//Overall complexity is O(Nlgm) where N = length of input string. m = total number of unique tasks.

class Task {
  char type;
  int freq;
  Task (char t, int f) {
    type=t;
    freq=f;
  }
}

class Comp implements Comparator<Task> {
  public int compare(Task t1, Task t2) {
    return t2.freq - t1.freq;
  }
}

class Solution {
  
  
  private static void taskScheduleWithCoolDown(String tasks, int n) {
    Map<Character, Integer> frequencies = new HashMap<Character, Integer>();
    PriorityQueue<Task> queue = new PriorityQueue<Task>(new Comp());
    List<Character> res = new ArrayList<Character>();
    
    for (char c: tasks.toCharArray()) {
      frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
    }
    
    for (Character c: frequencies.keySet()) {
      queue.add(new Task(c, frequencies.get(c)));
    }
    
    while(!queue.isEmpty()) {
      int count = n+1; //if n (cool down time) is zero, we still want to pop them out
      List<Task> temp = new ArrayList<Task>();
      while (count != 0) {
        if (!queue.isEmpty()) {
          Task t = queue.poll();
          res.add(t.type);
          temp.add(t); //temp list to keep track of how many were popped this time
        } else {
          break;
        }
        count--;
      }
      //decrease and put them back in the queue
      for(int i = 0; i < temp.size(); i++) {
        Task ta = temp.get(i);
        ta.freq -= 1;
        if (ta.freq > 0) {
          queue.add(ta);
        }
      }
      
      //decide if we have to wait and do nothing
      //temp is the task that will be executed, so just count the remaining we need
      for(int i = 0; i < n-temp.size()+1; i++) {
        if (!queue.isEmpty()) { 
          //only add if queue isn't empty, if queue is empty, no more execution or waiting
          res.add('_');
        }
      }
    }
    
    System.out.println(String.valueOf(res));
  
  }
  
  public static void main(String[] args) {
    taskScheduleWithCoolDown("AAAAAAAABBB",1);
  }
}














