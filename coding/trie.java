
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