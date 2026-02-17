class Trie {
    private class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        
        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        int n = word.length();
        TrieNode cur = root;
        for (int i = 0; i < n; i++) {
            int idx = charIdx(word.charAt(i));
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
        }
        cur.isEnd = true;
    }
    
    public boolean search(String word) {
        int n = word.length();
        TrieNode cur = root;
        for (int i = 0; i < n; i++) {
            if (cur == null) {
                return false;
            }
            int idx = charIdx(word.charAt(i));
            cur = cur.children[idx];
        }
        return cur != null && cur.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        int n = prefix.length();
        TrieNode cur = root;
        for (int i = 0; i < n; i++) {
            if (cur == null) {
                return false;
            }
            int idx = charIdx(prefix.charAt(i));
            cur = cur.children[idx];
        }
        return cur != null;
    }

    private int charIdx(char ch) {
        return ch - 'a';
    }
}
