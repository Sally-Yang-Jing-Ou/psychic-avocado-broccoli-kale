import java.io.*;
import java.util.*;

class Solution {   
  
 private static List<Integer> friendsOfFriends(int[] list1, int[] list2) {
  int i = 0, j = 0;
  List<Integer> friends = new ArrayList<Integer>();
  while (i < list1.length && j < list2.length) {
    
    if (list1[i] == list2[j]) {
      friends.add(list1[i]);
      i++;
      j++;
    } else if (list1[i] > list2[j]) {
      j++;
    } else {
      i++;
    }
  }
   
   return friends;
   
 }
  
 private static List<Integer> friendsOfFriends2(int[] list1, int[] list2) {
  Set<Integer> hash = new HashSet<Integer>();
  List<Integer> friends = new ArrayList<Integer>();
  
  for (int i: list1) {
    hash.add(i);
  }
   
  for (int j = 0; j < list2.length; j++) {
    if (hash.contains(list2[j])) {
      friends.add(list2[j]);
    }
  }
   
   return friends;
 }
  
  public static void main(String[] args) {
    int[] list1 = new int[]{5,12,15,1,100,6,10};
    int[] list2 = new int[]{1,2,6,100,3,10,11,13,16,4,5};
    
    List<Integer> print = friendsOfFriends2(list1, list2);
    
    for (Integer i : print) {
      System.out.format("%d ", i);
    }
  }
}
    