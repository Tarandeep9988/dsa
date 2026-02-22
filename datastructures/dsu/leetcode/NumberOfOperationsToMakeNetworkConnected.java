class NumberOfOperationsToMakeNetworkConnected {
    private class Dsu {
        int[] size;
        int[] parent;
        Dsu(int n){
            size = new int[n];
            Arrays.fill(size, 1);
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }  
        int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            return parent[node] = findParent(parent[node]);
        }
        boolean areConnected(int node1, int node2) {
            return findParent(node1) == findParent(node2);
        }
        void union(int node1, int node2) {
            int parent1 = findParent(node1);
            int parent2 = findParent(node2);
            if (parent1 == parent2) {
                return;
            }
            int size1 = size[parent1];
            int size2 = size[parent2];
            if (size1 < size2) {
                parent[parent1] = parent2;
                size[parent2] += size1;
            }
            else {
                parent[parent2] = parent1;
                size[parent1] += size2;
            }
        }
    }
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            // number of cables must be  >= n - 1, where n is total computers
            return -1;
        }
        Dsu dsu = new Dsu(n);
        int components = n; // suppose initially all are disconnected
        for (int[] conn : connections) {
            if (!dsu.areConnected(conn[0], conn[1])) {
                dsu.union(conn[0], conn[1]);
                components--;
            }
        }   
        return components - 1;
    }
}