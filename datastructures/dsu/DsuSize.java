import java.util.*;

// package datastructures.dsu;

public class DsuSize {
  private final int[] size;
  private final int[] parent;
  
  public DsuSize(int n) {
    size = new int[n + 1];
    Arrays.fill(size, 1);
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
    int size1 = size[parent1];
    int size2 = size[parent2];
    if (size1 < size2) {
      parent[parent1] = parent2;
      size[parent2] += size1;
    }
    else {
      // size1 >= size2
      parent[parent2] = parent1;
      size[parent1] += size2;
    }
  }
}