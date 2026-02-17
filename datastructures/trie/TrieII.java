import java.util.* ;
import java.io.*; 
public class TrieII {

    private class TrieNode {
        TrieNode[] children;
        int endFreq;
        int freq;
        
        TrieNode() {
            children = new TrieNode[26];
            freq = 0;
            endFreq = 0;
        }
    }

    final private TrieNode root;

    public TrieII() {
        // Write your code here.
        root = new TrieNode();
    }

    public void insert(String word) {
        // Write your code here.
        TrieNode cur = root;
        cur.freq++;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = getCharIdx(word.charAt(i));
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
            cur.freq++;
        }
        cur.endFreq++;
    }

    public int countWordsEqualTo(String word) {
        // Write your code here.
        TrieNode cur = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = getCharIdx(word.charAt(i));
            if (cur.children[idx] == null) {
                return 0;
            }
            cur = cur.children[idx];
        }
        return cur == null ? 0 : cur.endFreq;
    }

    public int countWordsStartingWith(String word) {
        // Write your code here.
        TrieNode cur = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = getCharIdx(word.charAt(i));
            if (cur.children[idx] == null) {
                return 0;
            }
            cur = cur.children[idx];
        }
        return cur.freq;
    }

    public void erase(String word) {
        // Write your code here.
        TrieNode cur = root;
        cur.freq--;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = getCharIdx(word.charAt(i));
            cur = cur.children[idx];
            cur.freq--;
        }
        cur.endFreq--;
    }

    private int getCharIdx(char ch) {
        return ch - 'a';
    }

}
