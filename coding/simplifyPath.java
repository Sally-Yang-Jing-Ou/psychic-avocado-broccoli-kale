import java.io.*;
import java.util.*;



class Solution {

  private static String simplifyPath(String path) {
    Stack<String> stack = new Stack<>();
    Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
    String[] directory = path.split("/");
    
    for (String dir : directory) {
      if (dir.equals("..") && !stack.isEmpty()) stack.pop();
      else if (!skip.contains(dir)) stack.push(dir);
    }
    
    String res = "";
    while(!stack.isEmpty()) res = "/" + stack.pop() + res;
    return res.isEmpty() ? "/" : res;
  }

  public static void main(String[] args) {
    System.out.println(simplifyPath("/c/b/../a/o/./g/r/../s/"));
  }
}
