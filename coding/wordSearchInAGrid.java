import java.io.*;
import java.util.*;


class TrieNode {
  boolean isWord;
  TrieNode[] children;
  TrieNode() {
    children = new TrieNode[26];
    isWord = false;
  }
}

class Trie {
  private TrieNode root;
  
  Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode p = root;
    for(int i = 0; i < word.length(); i++ ) {
      char c = word.charAt(i);
      TrieNode[] children = p.children;
      if (children[c -'a'] == null) {
        children[c -'a'] = new TrieNode();
      }
      p = children[c -'a'];
    }
    p.isWord = true;
  }
  
  public boolean search(String word) {
    TrieNode n = searchNode(word);
    
    if (n!=null && n.isWord == true) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean prefix(String word) {
    if (searchNode(word) == null) {
      return false;
    } else {
      return true;
    }
  }
  
  private TrieNode searchNode(String word) {
    TrieNode n = root;
    for(int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      TrieNode[] children = n.children;
      if (children[c-'a'] == null) {
        return null;
      }
      n = children[c-'a'];
    }
    
    if(n==root) {return null;}
    return n;
  }
}


class Solution {
  
  private static boolean exists(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    boolean result = false;
    boolean[][] visited = new boolean[m][n];
    
    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if (dfs(board, word, 0, i,j, visited)) {
          result = true;
        }
      }
    }
    return result;
  }
  
  /*
  The word can be constructed from letters of sequentially adjacent cell, 
  where "adjacent" cells are those horizontally or vertically neighboring. 
  The same letter cell may not be used more than once.
  
  */
  
  private static boolean dfs(char[][] board, String word, int i, int m, int n, boolean[][] visited) {
    char c = word.charAt(i);
    int row = board.length;
    int col = board[0].length;
 
    if(m<0 || n<0 || m>=row || n>=col){
        return false;
    }
    
    if (!visited[m][n] && c==board[m][n]) {
      visited[m][n] = true;
      if (i == word.length()-1) {
        return true;
      }else if (dfs(board, word, i+1, m+1, n, visited) ||
          dfs(board, word, i+1, m-1, n, visited) ||
          dfs(board, word, i+1, m, n+1, visited) ||
          dfs(board, word, i+1, m, n-1, visited)) {
        return true;
      }
      visited[m][n] = false;
    }
    
    return false;
  }
  
  
  /*
  If the current candidate does not exist in all words' prefix, 
   we can stop backtracking immediately. This can be done by using a trie structure
  
  */
  
  private ArrayList<String> results = new ArrayList<String>();
  private ArrayList<String> existsWithTrie(char[][] board, String[] words) {
    Trie trie = new Trie();
    int m = board.length;
    int n = board[0].length;
    
    for(String w : words) {
      trie.insert(w);
    }
    
    boolean[][] visited = new boolean[m][n];

    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        dfsTrie(board, "", i, j, trie, visited);
      }
    }
    
    return results;
  }
  
  private void dfsTrie(char[][] board, String str, int i, int j, Trie trie, boolean[][] visited) {
    int m = board.length;
    int n = board[0].length;
    
    if (i < 0 || j < 0 || i >= m || j >= n) {
      return;
    }
    
    if (visited[m][n]) {
      return;
    }
    
    str = str + board[m][n];
    if(!trie.prefix(str)) {
      return;
    }
    
    if(trie.search(str)) {
      results.add(str);
    }
    
    visited[m][n] = true;
    dfsTrie(board, str, i+1, j, trie, visited);
    dfsTrie(board, str, i-1, j, trie, visited);
    dfsTrie(board, str, i, j+1, trie, visited);
    dfsTrie(board, str, i, j-1, trie, visited);
    visited[m][n] = false;
  }
  
  
  public static void main(String[] args) {
    
  }
  
}
















