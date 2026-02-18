class MaxXorOfTwoElements {
    private static final int NUM_BITS = 32;
    private class Trie {
        private class TrieNode {
            TrieNode[] bit = new TrieNode[2];
        }
        private TrieNode root = new TrieNode();
        public void insert(int num) {
            TrieNode cur = root;
            for (int i = NUM_BITS - 1; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                if (cur.bit[curBit] == null) {
                    cur.bit[curBit] = new TrieNode();
                }
                cur = cur.bit[curBit];
            }
        }
        public int getMaxXor(int num) {
            int result = 0;
            TrieNode cur = root;
            for (int i = NUM_BITS - 1; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                int reqBit = curBit ^ 1;
                result <<= 1;
                if (cur.bit[reqBit] != null) {
                    result |= 1;
                    cur = cur.bit[reqBit];
                }
                else {
                    cur = cur.bit[curBit];
                }
            }
            return result;
        }
    }
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        for (int num : nums) {
            trie.insert(num);
        }
        int maxXor = 0;
        for (int num : nums) {
            maxXor = Math.max(maxXor, trie.getMaxXor(num));
        }
        return maxXor;
    }
}