import java.io.*;
import java.util.*;

/*
Select the open paren­the­ses, 
add it to the result string and reduce its count and make a recur­sive call.

Select the close paren­the­ses, 
add it to the result string and reduce its count and make a recur­sive call.

To print only valid paren­the­ses, make sure at any given point of time, close paren­the­ses count is not less that open paren­the­ses count because it means close paren­the­ses has been printed with its respec­tive open parentheses.

*/
class Solution {

  public static void Validparentheses(int openP, int closeP, String string) {
    if (openP == 0 && closeP == 0) // mean all opening and closing in// string,
              // print it
      System.out.println(string);
    
    if (openP > closeP) // means closing parentheses is more than open ones
      return;
    if (openP > 0)
      Validparentheses(openP - 1, closeP, string + "("); // put ( and
                                // reduce
                                // the count by
                                // 1
    if (closeP > 0)
      Validparentheses(openP, closeP - 1, string + ")"); // put ) and
                                // reduce
                                // the count by
                                // 1
  }

  public static void printParentheses(int n) {
    Validparentheses(n, n, "");
  }

  public static void main(String[] args) {
    int n = 3;
    printParentheses(n);
  }
}

