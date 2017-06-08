import java.io.*;
import java.util.*;

class Solution {   
  
  private static ArrayList<ArrayList<Integer>> generateAllSubsets(int[] set, int index) {
    ArrayList<ArrayList<Integer>> allSubsets;
    if (index == set.length) {
      allSubsets = new ArrayList<ArrayList<Integer>>();
      allSubsets.add(new ArrayList<Integer>());
    
    } else {
      allSubsets = generateAllSubsets(set, index+1);
      int item = set[index];
      ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();;
      for (ArrayList<Integer> subset: allSubsets) {
        ArrayList<Integer> newSubset = new ArrayList<Integer>();
        newSubset.addAll(subset);
        newSubset.add(item);
        moreSubsets.add(newSubset);
      }
      allSubsets.addAll(moreSubsets);
    }
    return allSubsets;
  }
  
  private static void generateSubsetSizeK(int[] set, int index, int targetSize, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> finalResults) {
    
    if(current.size() == targetSize) {
      finalResults.add(new ArrayList<Integer>(current));
      return;
    }
    
    for (int i = index; i < set.length; i++) {
      current.add(set[i]);
      generateSubsetSizeK(set, i+1, targetSize, current, finalResults);
      current.remove(current.size()-1);
    }
    
    return;
  }
  
  
  private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current,List<Set<Integer>> solution) {
    //successful stop clause
    if (current.size() == k) {
        solution.add(new HashSet<>(current));
        return;
    }
    //unseccessful stop clause
    if (idx == superSet.size()) return;
    Integer x = superSet.get(idx);
    current.add(x);
    //"guess" x is in the subset
    getSubsets(superSet, k, idx+1, current, solution);
    current.remove(x);
    //"guess" x is not in the subset
    getSubsets(superSet, k, idx+1, current, solution);
}
  
  public static void main(String[] args) {
    int[] list1 = new int[]{5,1,3,0};
    
   
    List<Integer> list = new ArrayList<Integer>();
    list.add(5);
    list.add(1);
    list.add(3);
    list.add(1);
    
    List<Set<Integer>> results = new ArrayList<Set<Integer>>();
    
  //  getSubsets(list, 3, 0, new HashSet<Integer>(), results);

    ArrayList<ArrayList<Integer>> resultsFinal = new ArrayList<ArrayList<Integer>>();

    generateSubsetSizeK(list1, 0, 3, new ArrayList<Integer>(), resultsFinal);


    for (List<Integer> i : resultsFinal) {
      for (Integer j : i) {
        System.out.format("%d ", j);
      }
      System.out.println("");
    }
  }
}
    