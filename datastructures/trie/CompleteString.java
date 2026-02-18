import java.util.* ;
import java.io.*; 

class CompleteString {

  private static class Trie {
    private class TrieNode {
      TrieNode[] children = new TrieNode[26];
      boolean isEnd = false;
    }

    final private TrieNode root = new TrieNode();

    public void insert(String str) {
      int n = str.length();
      TrieNode cur = root;
      for (int i = 0; i < n; i++) {
        char ch = str.charAt(i);
        int idx = getCharIdx(ch);
        if (cur.children[idx] == null) {
          cur.children[idx] = new TrieNode();
        }
        cur = cur.children[idx];
      }
      cur.isEnd = true;
    }

    public boolean isCompleteString(String str) {
      int n = str.length();
      TrieNode cur = root;
      for (int i = 0; i < n; i++) {
        char ch = str.charAt(i);
        int idx = getCharIdx(ch);
        if (cur.children[idx].isEnd == false) {
          return false;
        }
        cur = cur.children[idx];
      }
      return true;
    }

    private int getCharIdx(char ch) {
      return ch - 'a';
    }

    

  }
  public static String completeString(int n, String[] a) {
    // Write your code here.
    Trie trie = new Trie();
    // Add all words to trie
    for (int i = 0; i < n; i++) {
      trie.insert(a[i]);
    }
    String ans = "None";
    for (int i = 0; i < n; i++) {
      if (trie.isCompleteString(a[i])) {
        // add to list;
        if (ans.equals("None")) {
          ans = a[i];
        }
        else if (a[i].length() > ans.length()) {
          ans = a[i];
        }
        else if (a[i].length() == ans.length()) {
          if (a[i].compareTo(ans) < 0) {
            ans = a[i];
          }
        }
      }
    }
    return ans;
  }
}