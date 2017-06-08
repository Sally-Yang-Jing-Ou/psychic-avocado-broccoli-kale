import java.io.*;
import java.util.*;

class Solution {   
  
  
  private static void goatLatin(String[] str) {
    String[] newStr = new String[str.length];
    for (int i = 0; i < str.length; i++) {
      String s = str[i];
      char first = Character.toLowerCase(s.charAt(0)); 
      if (first !='a' && first!='e' && first!='i' && first!='o' && first!='u') {
        s = s.substring(1) + s.substring(0,1).toLowerCase();
      }
      s = s + "ni" + String.join("", Collections.nCopies(i+1, "a"));
      newStr[i] = s;
    }
    
    for (String s : newStr) {
      System.out.println(s);
    }
  }
  
  public static void main(String[] args) {
    String[] str = new String[]{"This", "is", "goat", "latin"};
    
    goatLatin(str);
    
  }
}
    