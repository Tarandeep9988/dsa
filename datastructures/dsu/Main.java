public static void main(String[] args) {
    Dsu dsu = new Dsu(10);

    // Test 1: basic union and connection
    dsu.union(1, 2);
    dsu.union(2, 3);
    System.out.println(dsu.areConnected(1, 3)); // true - transitive connection

    // Test 2: unconnected nodes
    System.out.println(dsu.areConnected(1, 4)); // false

    // Test 3: node connected to itself
    System.out.println(dsu.areConnected(1, 1)); // true

    // Test 4: two separate components then merge
    dsu.union(5, 6);
    dsu.union(7, 8);
    System.out.println(dsu.areConnected(5, 8)); // false - different components
    dsu.union(6, 7);
    System.out.println(dsu.areConnected(5, 8)); // true - now connected

    // Test 5: redundant union (already connected)
    dsu.union(1, 2);
    System.out.println(dsu.areConnected(1, 2)); // true - should still work fine

    // Test 6: large chain
    dsu.union(1, 4);
    dsu.union(4, 9);
    dsu.union(9, 10);
    System.out.println(dsu.areConnected(1, 10)); // true
    System.out.println(dsu.areConnected(2, 10)); // true - since 1 and 2 were joined earlier
}