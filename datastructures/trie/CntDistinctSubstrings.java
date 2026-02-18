class CntDistinctSubstrings {
    private static class Trie {
        private class TrieNode {
            TrieNode[] children = new TrieNode[26];
        }
        
        private final TrieNode root = new TrieNode();
        
        public int insert(int idx, String str) {
            TrieNode cur = root;
            int n = str.length();
            int cnt = 0;
            for (int j = idx; j < n; j++) {
                char ch = str.charAt(j);
                int i = getCharIdx(ch);
                if (cur.children[i] == null) {
                    // add new node
                    cur.children[i] = new TrieNode();
                    cnt++;
                }
                cur = cur.children[i];
            }
            return cnt;
        }
        
        private int getCharIdx(char ch) {
            return ch - 'a';
        }
    }
    public static int countSubs(String s) {
        // code here
        int n = s.length();
        int cnt = 0;
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            cnt += trie.insert(i, s);
        }
        return cnt;
    }
}