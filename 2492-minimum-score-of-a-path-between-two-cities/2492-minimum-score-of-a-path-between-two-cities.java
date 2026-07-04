class Solution {
    int[] parent;
    int[] minScore;

    public int minScore(int n, int[][] roads) {
        parent = new int[n + 1];
        minScore = new int[n + 1];
        
        // Initialize DSU arrays
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            minScore[i] = Integer.MAX_VALUE;
        }

        // Process all roads to build connected components
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];
            union(u, v, distance);
        }

        // Return the minimum score of the component containing city 1
        return minScore[find(1)];
    }

    // Find with path compression
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]); 
    }

    // Union by setting one root to another, updating the minimum score
    private void union(int i, int j, int distance) {
        int rootI = find(i);
        int rootJ = find(j);

        // Calculate the minimum score among the two components and the connecting edge
        int min = Math.min(distance, Math.min(minScore[rootI], minScore[rootJ]));

        // Merge components and update the root's minimum score
        parent[rootJ] = rootI;
        minScore[rootI] = min;
    }
}