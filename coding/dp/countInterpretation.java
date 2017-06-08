import java.io.*;
import java.util.*;

/*
1) If the last digit is non-zero, recur for remaining (n-1) digits and add the result to total count.
2) If the last two digits form a valid character (or smaller than 27), 
recur for remaining (n-2) digits and add the result to total count.

1 2 4 5

*/

int countDecoding(String digits, int n)
{
    // base cases
    if (n == 0 || n == 1)
        return 1;
 
    int count = 0;  // Initialize count
 
    // If the last digit is not 0, then last digit must add to
    // the number of words
    if (digits.charAt(n-1)> '0')
        count =  countDecoding(digits, n-1);
 
    // If the last two digits form a number smaller than or equal to 26,
    // then consider last two digits and recur
    if (digits[n-2] < '2' || (digits[n-2] == '2' && digits[n-1] < '7') )
        count +=  countDecoding(digits, n-2);
 
    return count;
}
/*
one digit one interpretation
zero digit one interpretation 
 
  0 1 2 3  actual index
  1 2 4 5  digits

1 1 2 3 3  num interpretation at current index 

0 1 2 3 4  dp index

1) if char at i is > '0' , we have one interpretation
2) if char at i-2 < '2' || char at i-2 < '2' and char at i-1 < '7', we have another interpretation, so we consume 
both characters and see number of interpretations we have without these two characters (dp[i-2], and we add this number to our
dp[i]



*/
class Solution { 
  
  
  public static int countInterpretation(String str) {
    int[] count = new int[str.length()+1];
    count[0] = 1;
    count[1] = 1;
    
    for (int i = 2; i <= str.length(); i++) { 
      // If it is greater than 0, (one interpretation or same as before) 
      if (str.charAt(i-1) > '0') count[i] = count[i-1];
      
      // If second last digit is smaller than 2 and last digit is
      // smaller than 7, then last two digits form a valid character
      if (str.charAt(i-2) < '2' && str.charAt(i-2) > '0' || str.charAt(i-2) == '2' && str.charAt(i-1) < '7') {
        count[i] += count[i-2];
      }                                       
    }
    return count[str.length()];
  }
  
  public static void main(String[] args) {
    String str = "12341100";
    System.out.print(countInterpretation(str));
  }
}

 public static void print(int [] a, int i, int n, String s) {
    if(i == n)  {
      System.out.println(s);
      return;
    }

    if(i < n-1 && a[i+1] <= 6 && a[i] <= 2) {
      print(a, i+2, n, new String(s+(char)(a[i]*10 + a[i+1] + 'a'-1)));
    }
    print(a, i+1, n, new String(s+(char)(a[i]+'a'-1)));
  }


  public static void main(String[] args) {
    int[] str = new int[]{ 1,2,3};
    print(str, 0, 3, "");
  }




class Solution { 
  
  
  public static int countInterpretation(String str) {
    // int[] count = new int[str.length()+1];
    // count[0] = 1;
    // count[1] = 1;
    int a = 1,b = 1, c = 0;    
    for (int i = 2; i <= str.length(); i++) {      
      if (str.charAt(i-1) > '0') {
        //count[i] = count[i-1];
        c = b;
      } else { c = 0; }
      
      if (str.charAt(i-2) < '2' && str.charAt(i-2) > '0' || str.charAt(i-2) == '2' && str.charAt(i-1) < '7') {
        // count[i] += count[i-2];
        c += a;
      }                                       
      a = b;
      b = c;
    }
    return c;
  }
  
  public static void main(String[] args) {
    String str = "1230";
    System.out.print(countInterpretation(str));
  }
}
    