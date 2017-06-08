import java.io.*;
import java.util.*;



class Solution {

  private static void getDirectory(String src, String destination){

    Stack<String> directoryStack = new Stack<String>();

    String[] listHost = src.split("/");
    String[] listDestination = destination.split("/");

    if(!destination.startsWith("/")){
      for(String str : listHost){
        if(!(str.equals(".."))  && !str.equals("")){
          directoryStack.push(str);
        }
        else if(str.equals("..") && !directoryStack.isEmpty()){
          directoryStack.pop();
        }
      }
    }

    for(String str : listDestination){
      if(!(str.equals("..")) && !str.equals("")){
        directoryStack.push(str);
      }
      else if(str.equals("..") && !directoryStack.isEmpty()){
        directoryStack.pop();
      }
    }

    String str = "";
    while (!directoryStack.isEmpty())
    {
      str = "/" + directoryStack.pop() +  str ;
    }
    System.out.println(str);
  
  }

  public static void main(String[] args) {
    getDirectory("/c/b/../a","/test/hi/../../g");
  }
}
