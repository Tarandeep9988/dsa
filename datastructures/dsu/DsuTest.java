public class DsuTest {

    public static void main(String[] args) {
        testDsuRank();
        testDsuSize();
        System.out.println("All tests passed");
    }

    private static void testDsuRank() {
        DsuRank dsu = new DsuRank(10);

        // Test 1: basic union and connection
        dsu.union(1, 2);
        dsu.union(2, 3);
        assert dsu.areConnected(1, 3) : "Rank: 1 and 3 should be connected";

        // Test 2: unconnected nodes
        assert !dsu.areConnected(1, 4) : "Rank: 1 and 4 should NOT be connected";

        // Test 3: node connected to itself
        assert dsu.areConnected(1, 1) : "Rank: Node should connect to itself";

        // Test 4: merge separate components
        dsu.union(5, 6);
        dsu.union(7, 8);
        assert !dsu.areConnected(5, 8) : "Rank: 5 and 8 should NOT be connected";

        dsu.union(6, 7);
        assert dsu.areConnected(5, 8) : "Rank: 5 and 8 should be connected";

        // Test 5: redundant union
        dsu.union(1, 2);
        assert dsu.areConnected(1, 2) : "Rank: 1 and 2 should remain connected";

        // Test 6: large chain
        dsu.union(1, 4);
        dsu.union(4, 9);
        dsu.union(9, 10);
        assert dsu.areConnected(1, 10) : "Rank: 1 and 10 should be connected";
        assert dsu.areConnected(2, 10) : "Rank: 2 and 10 should be connected";
    }

    private static void testDsuSize() {
        DsuSize dsu = new DsuSize(10);

        // Test 1
        dsu.union(1, 2);
        dsu.union(2, 3);
        assert dsu.areConnected(1, 3) : "Size: 1 and 3 should be connected";

        // Test 2
        assert !dsu.areConnected(1, 4) : "Size: 1 and 4 should NOT be connected";

        // Test 3
        assert dsu.areConnected(1, 1) : "Size: Node should connect to itself";

        // Test 4
        dsu.union(5, 6);
        dsu.union(7, 8);
        assert !dsu.areConnected(5, 8) : "Size: 5 and 8 should NOT be connected";

        dsu.union(6, 7);
        assert dsu.areConnected(5, 8) : "Size: 5 and 8 should be connected";

        // Test 5
        dsu.union(1, 2);
        assert dsu.areConnected(1, 2) : "Size: 1 and 2 should remain connected";

        // Test 6
        dsu.union(1, 4);
        dsu.union(4, 9);
        dsu.union(9, 10);
        assert dsu.areConnected(1, 10) : "Size: 1 and 10 should be connected";
        assert dsu.areConnected(2, 10) : "Size: 2 and 10 should be connected";
    }
}
