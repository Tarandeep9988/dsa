
import java.util.Arrays;

class MaxXorWithElementFromArray {
    private class Trie {
        private static final int MAX_BITS = 32;
        private class TrieNode {
            TrieNode[] bits = new TrieNode[2];
        }

        private final TrieNode root = new TrieNode();

        public void insert(int num) {
            TrieNode cur = root;
            for (int i = MAX_BITS - 1; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                if (cur.bits[curBit] == null) {
                    cur.bits[curBit] = new TrieNode();
                }
                cur = cur.bits[curBit];
            }
        }

        public int getMaxXor(int num) {
            int maxXor = 0;
            TrieNode cur = root;
            for (int i = MAX_BITS - 1; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                int reqBit = curBit ^ 1;
                maxXor <<= 1;
                if (cur.bits[reqBit] != null) {
                    maxXor |= 1;
                    cur = cur.bits[reqBit];
                }
                else if (cur.bits[curBit] != null) {
                    // go with same bit
                    cur = cur.bits[curBit];
                }
                else {
                    // both are null;
                    return -1;
                }
            }
            return maxXor;
        }
    }
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        
        // sort nums
        Arrays.sort(nums);

        // create new queries
        int[][] newQueries = new int[n][3];
        for (int i = 0; i < n; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Arrays.sort(newQueries, (a, b) -> Integer.compare(a[1], b[1]));

        Trie trie = new Trie();
        int m = nums.length;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int[] query = newQueries[i];
            // add elements till element <= query[1]
            while (idx < m && nums[idx] <= query[1]) {
                trie.insert(nums[idx]);
                idx++;
            }
            ans[query[2]] = trie.getMaxXor(query[0]);
        }
        return ans;
    }
}