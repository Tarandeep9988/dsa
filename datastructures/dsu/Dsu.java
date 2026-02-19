public class Dsu {
  private final int[] rank;
  private final int[] parent;
  
  public Dsu(int n) {
    rank = new int[n + 1];
    parent = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
    }
  }

  public int findParent(int node) {
    if (parent[node] == node) {
      return node;
    }
    return parent[node] = findParent(parent[node]);
  }

  public boolean areConnected(int node1, int node2) {
    return findParent(node1) == findParent(node2);
  }

  public void union(int node1, int node2) {
    if (areConnected(node1, node2)) {
      // Already united
      return;
    }

    int parent1 = findParent(node1);
    int parent2 = findParent(node2);
    int rank1 = rank[parent1];
    int rank2 = rank[parent2];
    if (rank1 < rank2) {
      parent[parent1] = parent2;
    }
    else if (rank1 > rank2) {
      parent[parent2] = parent1;
    }
    else {
      // rank1 == rank2
      parent[parent2] = parent1;
      rank[parent1]++;
    }
  }
}