import java.io.*;
import java.util.*;

class Inte {
  public int num;
  public int pointer;
  
  public Inte(int n, int p) { num = n; pointer = p;}
}

class Comp implements Comparator<Inte> {
  
  public int compare(Inte x, Inte y) {
    return x.num - y.num;
  }
}


/*
     0 1 2 3 4 5 6 7 8 9 10 11
     1,3,5,2,4,6,10,12,15,9,11
mins:0 3 10        
end: 3 10 11


nlogK
*/

class Solution {   
  
  private static int[] sortList(int[] list) {
    List<Integer> minsPointer = new ArrayList<Integer>();
    List<Integer> endPointer = new ArrayList<Integer>();
    
    
    Comparator<Inte> comparator = new Comp();
    
    Queue<Inte> minHeap = new PriorityQueue<Inte>(comparator);
    int[] sorted = new int[list.length];
    
    
    for (int i = 0; i < list.length-1; i++) {
      if (i == 0 || list[i] < list[i-1]) {
        if (i > 0) {
          endPointer.add(i);
        }
        minsPointer.add(i);
        minHeap.add(new Inte(list[i], minsPointer.size()-1));
      }
    }

    endPointer.add(list.length);
    
    // for (int i = 0; i < minsPointer.size(); i++) {
    //    System.out.format("%d:%d ,", minsPointer.get(i), endPointer.get(i));
    // }
    
    for (int i = 0; i < list.length; i++) {
      Inte min = minHeap.poll();
      System.out.println(min.num);
      sorted[i] = min.num;
      int pointer = min.pointer; //index of the min pointer 
      minsPointer.set(pointer, 1 + minsPointer.get(pointer)); //increment the VALUE at that min pointer
      
      if(minsPointer.get(pointer) < endPointer.get(pointer)) {
        minHeap.add(new Inte(list[minsPointer.get(pointer)], pointer));
      }
    }
    
    return sorted;
  }
  
  public static void main(String[] args) {
    int[] list = new int[]{1,3,5,2,4,6,10,12,15,9,11};
    System.out.print(sortList(list));
  }
}
    