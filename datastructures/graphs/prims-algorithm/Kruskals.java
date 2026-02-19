// User function Template for Java
class Krushkals {
    private static class Dsu {
        private final int size[];
        private final int parent[];
        Dsu(int n) {
            size = new int[n + 1];
            Arrays.fill(size, 1);
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }
        
        private int findParent(int node) {
            if (node == parent[node]) {
                return node;
            }
            
            return parent[node] = findParent(parent[node]);
        }
        
        boolean areConnected(int node1, int node2) {
            return findParent(node1) == findParent(node2);
        }
        void union(int node1, int node2) {
            if (areConnected(node1, node2)) {
                return; // already connected
            }
            int parent1 = findParent(node1);
            int parent2 = findParent(node2);
            int size1 = size[parent1];
            int size2 = size[parent2];
            if (size1 < size2) {
                size[parent2] += size1;
                parent[parent1] = parent2;
            }
            else {
                // size1 >= size2
                size[parent1] += size2;
                parent[parent2] = parent1;
            }
        }
        
    }
    
    static int kruskalsMST(int V, int[][] edges) {
        // Sort edges in increasing order of edge weight
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        
        Dsu dsu = new Dsu(V);
        int mstWt = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (!dsu.areConnected(u, v)) {
                // Add this edge
                mstWt += wt;
                dsu.union(u, v);
            }
        }
        return mstWt;
    }
}
