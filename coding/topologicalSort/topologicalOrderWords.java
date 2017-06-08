import java.io.*;
import java.util.*;
/*
To build the relationship,
we look at adjacent words and ignore all the characters from 
left to right that are same. Note: The order of left-to-right
is an implicit assumption that we make. Once we hit a 
character that is different, then we can add the relation
that character c2 of the current word “follows” or “depends on”
character c1 of the previous word.

BFS: 
easier to understand actually
1. build a dependency map - graph 
character -> list of characters (these are the characters that come after)

2. build a degree graph (all characters should be here, initialize initial dependency
number to 0)
character -> integer (represents the number of dependencies, incoming edges) 

3. put all the character with no dependency in a queue

4. pop them (node) one buy one and put it in result. 
go through the list that this "node" maps to, 
decrement each of its character's degree count by one. If one of its character
degree count is 0, put it into queue (meaning this doesn't depend on anything else 
anymore - no incoming edges)

5. compare results size with degree map size, if not equal, we have a cycle somewhere
 the last check is to ensure that all letters have been used. 
 The degrees contains all the characters in all the words. 
 If the result size is different then a character has been left out and the letters can't be arranged lexicographically.




*/

class Solution {

  private static String BFSarrangeTopologicalOrder(String[] words) {
    if(words==null || words.length==0) return "words are null or empty";

    Map<Character, HashSet<Character>> map = new HashMap<Character, HashSet<Character>>();
    Map<Character, Integer> indegree = new HashMap<Character, Integer>();
    //initialize all indegree to 0 first
    for (String s: words) {
      for(char c: s.toCharArray()) {
        indegree.put(c, 0);
      }
    }

    makeGraphAndComputeInDegrees(map, indegree, words);

    //only the characters with no incoming edges are in the queue
    Queue<Character> noDependencyQ = new LinkedList<Character>(); 
    for(char c: indegree.keySet()) {
      if(indegree.get(c) == 0) {
        noDependencyQ.add(c);
      }
    }
    //ask interview if "wrtkj","wrt" is valid or not
    //if no dependencies, return ""
    if(noDependencyQ.size() == indegree.size()) return "Not possible No dependency";

    String results = "";
    while(!noDependencyQ.isEmpty()) {
      char c = noDependencyQ.poll();
      results += c;
      if(map.containsKey(c)) {
        for(char dependent: map.get(c)) {
          indegree.put(dependent, indegree.get(dependent) - 1);
          if(indegree.get(dependent) == 0) {
            noDependencyQ.add(dependent); //no imcoming edges, put to queue
          }
        }
      }
    }

    if(results.length() != indegree.size()) return "Not possible";
    return results; 
  }

  private static void makeGraphAndComputeInDegrees(Map<Character, HashSet<Character>> map, Map<Character, Integer> indegree, String[] words) {
    //compare two adjacent words in the list 
    //if mismatch, build the dependency list
    for(int i = 0; i < words.length - 1; i++) {
      String w1 = words[i];
      String w2 = words[i+1];
      int l = Math.min(w1.length(), w2.length());

      for(int j = 0; j < l; j++) {
        char c1 = w1.charAt(j);
        char c2 = w2.charAt(j);
        if(c1 != c2) {
          if(!map.containsKey(c1)) {
            map.put(c1, new HashSet<Character>());
          }
          if(!map.get(c1).contains(c2)) { //if c2 wasn't in the dependency list
            indegree.put(c2, indegree.getOrDefault(c2,0) + 1);
            map.get(c1).add(c2);
          }
          break; //already found one mismatch, move on to next two adjacent words
        }
      }
    }
  }

  /*
   DFS Topological Sort

   a) Let the current pair of words be word1 and word2. 
   One by one compare characters of both words and find the first mismatching characters.
   b) Create an edge in g from mismatching character 
   of word1 to that of word2.
   c) 

  */
  private static String DFSarrangeTopologicalOrder(String[] words) {
    if(words==null || words.length==0) return "words are null or empty";

    Map<Character, HashSet<Character>> map = new HashMap<Character, HashSet<Character>>();
    //build dependency graph
    buildGraph(words, map);
    return topologicalSort(map);
  }

  private static String topologicalSort(Map<Character, HashSet<Character>> map) {
    boolean[] visited = new boolean[256];
    boolean[] visiting = new boolean[256]; //need to check for cycle
    StringBuilder sb = new StringBuilder();
    for(char c: map.keySet()) {
      if(!visited[c] && map.getOrDefault(c, null) != null) {
        if(!isAcyclic(visited, visiting, sb, map, c)) {
          return "invalid";
        }
      }
    }

    return sb.reverse().toString();
  }

  //DFS
  private static boolean isAcyclic(boolean[] visited, boolean[] visiting, StringBuilder sb,Map<Character, HashSet<Character>> map, char c) {

    if (visiting[c]) return false; //cycle
    if (visited[c]) return true; //dont wanna repeat same node
    visiting[c] = true;
    for(char dependent: map.get(c)) {
      if (!visited[c]) {
        if(!isAcyclic(visited, visiting, sb, map, dependent)) {
          return false;
        }
      }
    }
    visited[c] = true;
    visiting[c] = false; //finish the node
    sb.append(c);
    return true;
  }


  private static void buildGraph(String[] words, Map<Character, HashSet<Character>> map) {
    //compare two adjacent words in the list 
    //if mismatch, build the dependency list
    for(int i = 0; i < words.length - 1; i++) {
      String w1 = words[i];
      String w2 = words[i+1];
      int l1 = w1.length();
      int l2 = w2.length();
      int l = Math.max(l1, l2);
      boolean mismatch = false;
      for(int j = 0; j < l; j++) {
        if(j < l1 && !map.containsKey(w1.charAt(j))) {
          map.put(w1.charAt(j), new HashSet<Character>());
        }
        if(j < l2 && !map.containsKey(w2.charAt(j))) {
          map.put(w2.charAt(j), new HashSet<Character>());
        }
        if(j < l1 && j < l2 && w1.charAt(j) != w2.charAt(j) && !mismatch) {
          map.get(w1.charAt(j)).add(w2.charAt(j));
          mismatch = true; //already found one mismatch
        }
      }
    }
    
    // for(char c: map.keySet()) {
    //   System.out.format("key:%c maps to ", c);
    //   for(char c2: map.get(c)) {
    //     System.out.format("%c%n ", c2);
    //   }
    // }

  }  

  public static void main(String[] args) {
    System.out.println(DFSarrangeTopologicalOrder(new String[]{"wrtkj","wrt"}));
    System.out.println(DFSarrangeTopologicalOrder(new String[]{"wrt",
                                               "wrf",
                                               "er",
                                               "ett",
                                               "rftt"}));
  }
}