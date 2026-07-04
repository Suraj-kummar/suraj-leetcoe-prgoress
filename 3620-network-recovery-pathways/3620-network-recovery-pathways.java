import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[n];
        Set<Integer> uniqueCostsSet = new HashSet<>();
        
        // 1. Build an adjacency list strictly ignoring edges involving offline nodes
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int c = edge[2];
            if (online[u] && online[v]) {
                adj.get(u).add(new int[]{v, c});
                inDegree[v]++;
                uniqueCostsSet.add(c);
            }
        }
        
        // 2. Perform Kahn's algorithm to obtain the topological ordering of the DAG
        int[] topo = new int[n];
        int topoIdx = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[topoIdx++] = u;
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        
        // Collect and sort unique costs for binary search
        int[] uniqueCosts = new int[uniqueCostsSet.size()];
        int idx = 0;
        for (int c : uniqueCostsSet) {
            uniqueCosts[idx++] = c;
        }
        Arrays.sort(uniqueCosts);
        
        // 3. Binary search across the unique edge costs
        int low = 0;
        int high = uniqueCosts.length - 1;
        int ans = -1;
        
        long[] dist = new long[n];
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int minC = uniqueCosts[mid];
            
            // Re-initialize distance array for DP
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;
            
            // DP over the topological order to find the shortest path
            for (int i = 0; i < topoIdx; i++) {
                int u = topo[i];
                if (dist[u] == Long.MAX_VALUE) continue;
                
                for (int[] edge : adj.get(u)) {
                    int v = edge[0];
                    int c = edge[1];
                    // Only traverse edges that meet the current score threshold
                    if (c >= minC) {
                        if (dist[u] + c < dist[v]) {
                            dist[v] = dist[u] + c;
                        }
                    }
                }
            }
            
            // Check if n-1 is reachable within the maximum allowed cost k
            if (dist[n - 1] <= k) {
                ans = minC;
                low = mid + 1; // Try to look for a higher minimum edge cost (score)
            } else {
                high = mid - 1; // Target score was too aggressive, shrink the threshold
            }
        }
        
        return ans;
    }
}