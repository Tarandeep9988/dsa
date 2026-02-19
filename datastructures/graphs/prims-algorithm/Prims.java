class Solution {
    private class Pair {
        int node;
        int wt;
        Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }
    public int spanningTree(int V, int[][] edges) {
        // code here
        // Adj list
        List<List<Pair>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
            adj.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }
        
        boolean[] visited = new boolean[V];
        
        
        // Add any node to priorityQueue
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.wt, b.wt));
        
        pq.add(new Pair(0, 0));
        int totalWt = 0;
        
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (visited[p.node]) {
                continue;
            }
            totalWt += p.wt;
            
            visited[p.node] = true;
            // check all the neighbors
            for (Pair nbrPair : adj.get(p.node)) {
                if (!visited[nbrPair.node]) {
                    pq.add(nbrPair);
                }
            }
        }
        return totalWt;
        
    }
}
