import java.io.*;
import java.util.*;
/*
A = {a1, a2} 
B = {b1, b2} 
C = {c1} =>

{a1, b1, c}, {a1, b2, c} {a2, b1, c} and {a2, b2, c}

Given bunch of sets in an array,
compute cross products of the sets

in the cross product sets, there are n choices for first element in each set, n choices for second element and so on
Runtime O(n^k), n being the number of elements in each set,
k being the number of sets

""  result 
{a1}, {a2}  result
{a1, b1} {a1, b2} {a2, b1} {a2, b2} result
{a1, b1, c} {a1, b2, c} {a2, b1, c} {a2, b2, c} result 

return result


1) start with an empty set result
2) for every subset in the set result, append a new element to each of these preivously computed result
3) store the newly computed set result to an intermediate results
4) after we loop through every subset in the previous set result, we update the result with the intermediate results 
5) repeat 2-4 until we finish looping all passed in sets


*/


class Solution {
  public static void crossProductsOfSets(ArrayList<HashSet<String>> sets) {
    ArrayList<HashSet<String>> res = new ArrayList<HashSet<String>>();
    //store intermediate results
    ArrayList<HashSet<String>> intermediate = new ArrayList<HashSet<String>>();
    res.add(new HashSet<String>());

    //loop through each sets provided in the input
    for(int i=0; i<sets.size(); i++){
      //loop through previously computed results 
      for(HashSet<String> subset: res) {
        //get the set of elements in the set
        HashSet<String> elements = sets.get(i);
        //append each new element to each of previously computed results
        HashSet<String> newSet = new HashSet<String>();
        for(String l : elements) {
          newSet = new HashSet<String>();
          newSet.addAll(subset);
          newSet.add(l);
          intermediate.add(newSet);
        }
      }
      res = intermediate;
      intermediate = new ArrayList<HashSet<String>>();
    }      

    for(HashSet<String> set: res) {
      for (String l : set) {
        System.out.format("%s ", l);
      }
      System.out.println("");
    }
  }

  public static void main(String[] args) {
    ArrayList<HashSet<String>> sets = new ArrayList<HashSet<String>>();
    HashSet<String> newSet = new HashSet<String>();
    HashSet<String> newSet1 = new HashSet<String>();
    HashSet<String> newSet2 = new HashSet<String>();
    newSet.add("a1");
    newSet.add("a2");
    newSet1.add("b1");
    newSet1.add("b2");
    newSet2.add("c1");
    newSet2.add("c2");
    sets.add(newSet);
    sets.add(newSet1);
    sets.add(newSet2);
    crossProductsOfSets(sets);
  }
}

