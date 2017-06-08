import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// O(n^2)
class Solution {
  
  private static Boolean matchingPair(char close, char open) {
    if (close == '}' && open == '{') {
      return true;
    } else if (close == ']' && open == '[') {
      return true;
    } else if (close == ')' && open == '(') {
      return true;
    }
    
    return false;
  }
  
  public static Boolean checkIfBalanced(String expression) {
    Stack<Character> st = new Stack<Character>();
    
    for (int i = 0; i < expression.length(); i++) {
      
      if (expression.charAt(i) == '{' || expression.charAt(i) == '[' || expression.charAt(i) == '(') {
        st.push(expression.charAt(i));
        
      }else if (expression.charAt(i) == '}' || expression.charAt(i) == ']' || expression.charAt(i) == ')') {
        if (st.isEmpty() || !matchingPair(expression.charAt(i), st.peek())) return false;
        
        st.pop();
      }
    }
    
    if (st.isEmpty()) return true;
    return false;
  }

  public static void main(String[] args) {
    System.out.println(checkIfBalanced("{[()]}"));
    System.out.println(checkIfBalanced("{[(])}"));
    System.out.println(checkIfBalanced("{[(((()"));
    System.out.println(checkIfBalanced("{))))"));

  }
}
