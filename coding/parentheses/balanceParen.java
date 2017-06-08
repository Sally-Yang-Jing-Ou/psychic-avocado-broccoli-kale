import java.io.*;
import java.util.*;


/*
On the first level, there's only one string which is the input string s, 
let's say the length of it is n, to check whether it's valid, we need O(n) time.

 On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings, 
 each of them has n-1 characters, and for each string, we need to check whether it's valid or not, 

 thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, 
 total time complexity is (n-2) x C(n, n-2), so on and so forth...

Finally we have this formula:

T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1)
*/


/*

As we need to generate all possible output we will backtrack among all states by 
removing one opening or closing bracket and check if they are valid if invalid then add the 
removed bracket back and go for next state. We will use BFS for moving through states, use of BFS 
will assure removal of minimal number of brackets because we traverse into states level by level and 
each level corresponds to one extra bracket removal.

The idea is straightforward, with the input string s,
 we generate all possible states by removing one ( or ), check if they are valid, 
 if found valid ones on the current level, put them to the final result list and we are done, 
 otherwise, add them to a queue and carry on to the next level.

The good thing of using BFS is that we can guarantee the number of parentheses that need 
to be removed is minimal, also no recursion call is needed in BFS.
*/

// O(n^2)
class Solution {
  
  
  public List<String> removeInvalidParentheses(String expression) {
    List<String> balancedResults = new ArrayList<String>();
    
    if (expression == null) return balancedResults;
    
    Queue<String> queue = new LinkedList<String>();
    Set<String> visited = new HashSet<String>();

    queue.add(expression);
    visited.add(expression);
    Boolean level = false;
    String str;
    
    while (!queue.isEmpty()) {
      str = queue.poll();
      
      if (isBalanced(str)) {
        balancedResults.add(str);
        level = true;
      }
    
      //only process strings on that level
      if (level) continue;
      
      //          )(()
      // (()   )()   )()   )((
      //
      // generate all possible states
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) != '(' && str.charAt(i) != ')') continue;
        String state = str.substring(0, i) + str.substring(i+1);
        if (!visited.contains(state)) {
          queue.add(state);
          visited.add(state);
        }
      }
    }
    
    return balancedResults;
  }
 
  // ((((())
  // (())))
  private Boolean isBalanced(String str) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '(') {
        count ++;
      } else if (str.charAt(i) == ')' && count-- == 0) {
        return false;
      }
    }
    return count == 0;
  }
}

        
