/*
Scan the string from beginning to end.
If current character is '(',
push its index to the stack. 

If current character is ')' and the
character at the index of the top of stack is '(', we just find a
matching pair so pop from the stack. Otherwise, we push the index of
')' to the stack.

After the scan is done, the stack will only
contain the indices of characters which cannot be matched.

If the stack is empty, the whole input
string is valid. Otherwise, we can scan the stack to get longest
valid substring as described in step 3.

()(()

(())()()
0123456

*/

public static int longestValidParentheses(String s) {
Stack<int> stack = new Stack<int>();
int result = 0;

for(int i=0; i<=s.length()-1; i++){
	char c = s.charAt(i);
	if(c=='('){
		stack.push(i);
	}else{
		if(stack.empty()|| s.charAt(stack.peek())== ')'){
			stack.push(i);
		}else{
			stack.pop();
			int currentLen=0;
			if(stack.empty()){
				currentLen = i+1;
			}else{
				currentLen = i-stack.peek();
			}
			result = Math.max(result, currentLen);
		}
	}
}

return result;
}



import java.io.*;
import java.util.*;


class Solution { 

  public static int longestValidParentheses(String s) {
    Stack<Integer> stack = new Stack<Integer>();
    int result = 0;
    int start = Integer.MIN_VALUE;
    int f = Integer.MAX_VALUE;
    for(int i=0; i<=s.length()-1; i++){
      char c = s.charAt(i);
      if(c=='('){
        stack.push(i);
      }else{
        if(stack.empty()|| s.charAt(stack.peek())== ')'){
          stack.push(i);
        }else{
          stack.pop();
          int currentLen=0;
          if(stack.empty()){
            currentLen = i+1;
            start = 0;
          }else{
            currentLen = i-stack.peek();
            start = stack.peek()+1;
          }
          if (result < currentLen) {
            result = currentLen;
            f = start;
          }
        }
      }
    }

    System.out.println(s.substring(f, f + result));
    return result;
  }


  public static void main(String[] args) {
    System.out.print(longestValidParentheses("())(())"));
  }
}

