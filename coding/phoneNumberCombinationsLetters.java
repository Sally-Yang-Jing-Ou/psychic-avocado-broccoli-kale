import java.io.*;
import java.util.*;

/*

Runtime O(n^k), n being the number of letters in each set,
k being the number of sets/digits
*/
class Solution {
  public static void letterCombinations(String digits) {
    ArrayList<String> res = new ArrayList<String>();
    //store intermediate results
    ArrayList<String> preres = new ArrayList<String>();
    res.add("");
    //loop through the digits provided in the input
    for(int i=0;i<digits.length();i++){
      if (digits.charAt(i) == '0') continue;
      //loop through previously computed results 
      for(String str: res) {
        //get the new letters
        String letters = map.get(digits.charAt(i));
        //append each new letter to each of previously computed results
        for(int j=0;j<letters.length();j++) {
          preres.add(str+letters.charAt(j));
        }
      }
      res = preres;
      preres = new ArrayList<String>();
    }      
    
    for(String s: res) {
      System.out.println(s);
    }
  }

  static final HashMap<Character,String> map = new HashMap<Character,String>(){{
    put('2',"abc");
    put('3',"def");
    put('4',"ghi");
    put('5',"jkl");
    put('6',"mno");
    put('7',"pqrs");
    put('8',"tuv");
    put('9',"wxyz");
  }};

 //SECOND SOLUTION USING DFS
  private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

  private static List<String> letterCombinations(String digits) {
    List<String> ret = new LinkedList<String>();
    combination("", digits, 0, ret);
    return ret;
  }

  private static void combination(String prefix, String digits, int index, List<String> ret) {
    if (index >= digits.length()) {
      ret.add(prefix);
      return;
    }
    
    String letters = KEYS[(digits.charAt(index) - '0')];
    for (int i = 0; i < letters.length(); i++) {
      combination(prefix + letters.charAt(i), digits, index + 1, ret);
    }
  }


  public static void main(String[] args) {
    List<String> list = letterCombinations("234");
    for(String s: list) {
      System.out.println(s);
    }
  }
}

