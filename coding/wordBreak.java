import java.io.*;
import java.util.*;

/*
Recursive implementation:
The idea is simple, we consider each prefix and search it in dictionary. 
If the prefix is present in dictionary, we recur for rest of the string (or suffix). 
If the recursive call for suffix returns true, we return true, otherwise we try next prefix. 
If we have tried all prefixes and none of them resulted in a solution, we return false.

Optimization:
array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
O(n^2) but with s.substring it might be O(n^3)

if t[i] is true, check the rest of substring from i+1 to end of string

we only start where t[i] is true, because each word that gets broken down needs to be valid,
only continue from the valid point

facebook
012345678
^
 ^
TFFFTFFFT  //no matched position 
            //initial state 

mangoicecream

[ice, cream, icecream, man, mango, go]

[
ace,
book,
face
]

1. initialize a boolean array of str.length + 1

2. initial state is true

3. check if the substring at every index starting at i ending at j, exists in the dict or not
array matched[] such that matched[j]==true => i-j can be segmented using dictionary

4. only do step 3 if matched[i] is true, since we want to start at a matching position, if false,
it means the word that ended at i cannot be a valid dict word

*/

class Solution {

  // returns true if string can be segmented into space separated
  // words, otherwise returns false
  private static boolean wordBreak(String str, Set<String> dict){
    int size = str.length();

    // Base case
    if (size == 0)  return true;

    // Try all prefixes of lengths from 1 to size
    for (int i=1; i<=size; i++)
    {
      // The parameter for dictionaryContains is str.substr(0, i)
      // str.substr(0, i) which is prefix (of input string) of
      // length 'i'. We first check whether current prefix is in
      // dictionary. Then we recursively check for remaining string
      // str.substr(i, size-i) which is suffix of length size-i
      if (dict.contains( str.substring(0, i) ) && wordBreak( str.substring(i, size), dict ))
        return true;
    }

    // If we have tried all prefixes and none of them worked
    return false;
  }

  private static boolean wordBreak2(String str, Set<String> dict) {
    boolean[] matched = new boolean[str.length()+1]; //all initialized to false
    matched[0] = true;

    for(int i = 0; i < str.length(); i++) {
      for(int j = i+1; j <= str.length(); j++) { //substring is exclusive for last index
        if(matched[i] == false) continue; //no valid dict words ending at this index

        if(dict.contains(str.substring(i,j))) {
          matched[j] = true;
        }

        if(j == str.length() && matched[j]) {
          return true;
        }
      }
    }
    return matched[str.length()];
  }

  //actual lists of word breaks 
  /*
  we need to track the actual matched words. 
  Then we can use depth first search to get all the possible paths,
  i.e., the list of strings.
 
  m 0  
  a 1
  n 2 
  g 3 man
  o 4 
  i 5 mango, go
  c 6 
  e 7 
  c 8 ice
  r 9
  e 10
  a 11
  m 12
    13 icecream, cream  13-8 = 5

  the valid word at each index
  we can do dfs/backtracking to construct the valid words
  */
  private static void wordBreak3(String str, Set<String> dict) {
    Map<Integer, ArrayList<String>> matched = new HashMap<Integer, ArrayList<String>>();
    // to store valid words at the ending index, index maps to valid words ending there
    matched.put(0, new ArrayList<String>());

    for(int i = 0; i < str.length(); i++) {
      for(int j = i+1; j <= str.length(); j++) { //substring is exclusive for last index
        if(matched.getOrDefault(i, null) == null) continue; //no valid dict words ending at this index
        
        String sub = str.substring(i,j);
        if(dict.contains(sub)) {
          if (matched.getOrDefault(j, null) == null) {
            matched.put(j, new ArrayList<String>());
          }
          matched.get(j).add(sub);
        }
      }
    }
    
    List<String> results = new ArrayList<String>();
    constructWords(str.length(), results, matched, ""); //DFS to construct words
    
    for(String s: results) {
      System.out.println(s);
    }
  }
  
  private static void constructWords(int end, List<String> results, Map<Integer, ArrayList<String>> matched, String current) {
    if(end == 0) {
      results.add(current);
      return;
    }
    
    if (matched.getOrDefault(end, null) != null) {
      for(String s: matched.get(end)) {
        String com = s + " " + current;
        constructWords(end-s.length(), results, matched, com);
      }
    }
  }

  public static void main(String[] args) {
    Set<String> set = new HashSet<String>();
    set.add("mango");
    set.add("icecream");
    set.add("ice");
    set.add("cream");
    set.add("man");
    set.add("go");
    set.add("face");
    set.add("ace");
    set.add("book");
    set.add("boo");
    wordBreak3("facebook", set);

  }
}

