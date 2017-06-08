import java.io.*;
import java.util.*;


/*
Given two words (start and end), and a dictionary, 
find the length of shortest transformation sequence from start to end, 
such that only one letter can be changed at a time and each intermediate
word must exist in the dictionary. For example, given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",

The idea is to use BFS. We start from the given start word, 
traverse all words that adjacent (differ by one character) to it 
and keep doing so until we find the target word or we have traversed all words.

             hit
ait bit cit ... hat ... hia 

    hot
dot     lot 
dog     log //we would meet this one first, first time we see it, must be shortest 
log

1. class to keep track of each word and its level, BFS will give shortest path when all edges
have same weight or no weight, make sure not to revisit same nodes to prevent loops

2. generate adjacent words, check if it's in the dict, if yes, push onto queue

2. while queue is not empty, pop, repeat step one, if popped word is end word, return
the steps count


*/

class Node {
  String word;
  int steps;

  Node(String s, int c) {
    word = s;
    steps = c;
  }
}

class Ladder {
  String word;
  Ladder pre;
  int steps;

  Ladder(String s, int c, Ladder p) {
    word = s;
    steps = c;
    pre = p;
  }
}


class Solution {

  private static int wordLadder(String start, String end, Set<String> dict) {
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(new Node(start, 1));

    while(!queue.isEmpty()) {
      Node n = queue.poll();
      String str = n.word;
      int stepCount = n.steps;

      //generate adjacent words
      for(int i = 0; i < str.length(); i++) {
        //string builder so we can modify this to generate adjacent words
        StringBuilder builder = new StringBuilder(str); 
        for(char c = 'a'; c<='z'; c++) {
          builder.setCharAt(i,c);
          //turn back to string
          String adjacent = builder.toString();
          if(dict.contains(adjacent)) {
            queue.add(new Node(adjacent, stepCount+1));
            dict.remove(adjacent);
            // System.out.println(adjacent);
          }

          if(adjacent.equals(end)) {
            return stepCount+1;
          }
        }
      }
    }

    return -1;
  }
  /*
  print all possible path of word ladder

1. class to keep track of each word and its level and its previous word (for backtracking),
BFS will give shortest path when all edges
have same weight or no weight, make sure not to revisit same nodes to prevent loops

2. generate adjacent words, check if it's in the dict, if yes, push onto queue

3. while queue is not empty, pop, repeat step one, if popped word is end word, return
the steps count

4. keep track of the visited nodes on each level, once you go to next level, remove everything 
visited from dict. The reason why we do that is because, we want to find all paths, technically 
if the end node occurs on the same level multiple times, it's okay (because of all paths)

      hit
      hot   
    dot  lot
    dog  log
    cog  cog  //it's okay to have this, do not remove until moving onto next level

input
    Set<String> dict = new HashSet<String>(Arrays.asList("hot","dot","dog","lot","log"));  
    wordLadder2("hit", "cog", dict);

Output:
hit hot dot dog cog
hit hot lot log cog


  */
  private static void wordLadder2(String start, String end, Set<String> dict) {
    Queue<Ladder> queue = new LinkedList<Ladder>();
    Set<String> visited = new HashSet<String>();
    ArrayList<LinkedList<String>> results = new ArrayList<LinkedList<String>>();

    queue.add(new Ladder(start, 1, null));
    visited.add(start);
    dict.add(end); //add end word to the dict, in case it doesnt exist, because we want to know pre-word
    int minStep = 0; //shortest level where end word exists
    int previousLevel = 0;

    while(!queue.isEmpty()) {
      Ladder ladder = queue.poll();
      String str = ladder.word;
      int currentLevel = ladder.steps;

      //generate all paths
      if(str.equals(end)) {
        if(minStep == 0) {
          minStep = currentLevel; //first time encounter end word, this is minStep
        }
        if(currentLevel == minStep && minStep != 0) { //only tracks the shorest path
          generatePaths(results, ladder);
          continue; //continue from the same level
        }
      }

      if(previousLevel < currentLevel) {
        dict.removeAll(visited);
        visited = new HashSet<String>();
      } //step 4 algo must do it this way for all paths

      previousLevel = currentLevel;

      //generate adjacent words
      for(int i = 0; i < str.length(); i++) {
        //string builder so we can modify this to generate adjacent words
        StringBuilder builder = new StringBuilder(str); 
        for(char c = 'a'; c<='z'; c++) { //all possible children differ by one element
          builder.setCharAt(i,c);
          String adjacent = builder.toString(); //turn back to string
          if(dict.contains(adjacent)) {
            queue.add(new Ladder(adjacent, currentLevel+1, ladder));
            visited.add(adjacent);
            //dict.remove(adjacent); -> can't do this, because it will only give one path
          }
        }
      }
    }

    for(LinkedList<String> list: results) {
      for(String s: list) {
        System.out.format("%s ", s);
      }
      System.out.println("");
    }
  }

  private static void generatePaths(ArrayList<LinkedList<String>> results, Ladder ladder) {
    LinkedList<String> list = new LinkedList<String>();
    while(ladder != null) {
      list.add(0, ladder.word);            
      ladder = ladder.pre;
    }
    results.add(list);
  }

  public static void main(String[] args) {
    Set<String> dict = new HashSet<String>(Arrays.asList("hot","dot","dog","lot","log"));  
    wordLadder2("hit", "cog", dict);
  }
}





