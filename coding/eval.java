import java.io.*;
import java.util.*;
/*
1) number and operator stack
2) push numbers to num stack
3) push op to operator stack
    everytime we ready to push, check the top of op stack, if *, calculate it first (pop two from numbers stack, pop top 
    of op stack),
    then push the calculation back to num stack

*/
class Solution {
  
  public static int eval(String expression) {
    Stack<Integer> numbers = new Stack<Integer>();
    Stack<Character> operators = new Stack<Character>();
    
    for (int i = 0; i < expression.length(); i++) {
      
      if (expression.charAt(i) == ' ') {
        continue;
      }
      
      if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
        StringBuffer sb = new StringBuffer();
        //the number can have multiple digits
        while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
          sb.append(expression.charAt(i));
          i++;
        }
        i--;
        numbers.push(Integer.parseInt(sb.toString()));

      } else if (expression.charAt(i) == '*' || expression.charAt(i) == '+') {
        if (!operators.isEmpty() && operators.peek() == '*') {
          numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
        }
        operators.push(expression.charAt(i));
      }      
    }
    
    while (!operators.isEmpty() && !numbers.isEmpty()) {
      //System.out.println(operators.peek());
      numbers.push(calculate(operators.pop(), numbers.pop(), numbers.pop()));
    }
    return numbers.pop(); 
  }
  
  private static int calculate(char op, Integer num1, Integer num2) {
      if (op == '*') {
        return num1 * num2;
      } else if (op == '+') {
        return num1 + num2;
      }
      
      return 0;
    }
  /*
     11 + 12 * 2   +
     11 + 24 +
  */
  public static void main(String[] args) {
    System.out.println(eval("11 + 12 * 9 + 11 * 2"));
    System.out.println(eval("100*4 + 1"));
  }
}
