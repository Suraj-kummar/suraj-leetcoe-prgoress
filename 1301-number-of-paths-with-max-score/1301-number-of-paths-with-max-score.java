import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;
        
        // dp[r][c][0] = max_sum, dp[r][c][1] = ways
        int[][][] dp = new int[n][n][2];
        
        // Initialize with -1 to indicate unreachable paths
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = 0;
            }
        }
        
        // Base case: Starting point 'S'
        dp[n - 1][n - 1][0] = 0;
        dp[n - 1][n - 1][1] = 1;
        
        // Valid directions we can arrive from: Right, Down, Down-Right
        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};
        
        // Bottom-up approach starting from the end 'S'
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // Skip the starting cell since it is already initialized
                if (r == n - 1 && c == n - 1) {
                    continue;
                }
                
                char ch = board.get(r).charAt(c);
                
                // Cannot traverse through an obstacle
                if (ch == 'X') {
                    continue;
                }
                
                int maxSum = -1;
                int ways = 0;
                
                // Check the valid predecessors
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr < n && nc < n && dp[nr][nc][0] != -1) {
                        if (dp[nr][nc][0] > maxSum) {
                            maxSum = dp[nr][nc][0];
                            ways = dp[nr][nc][1];
                        } else if (dp[nr][nc][0] == maxSum) {
                            ways = (ways + dp[nr][nc][1]) % MOD;
                        }
                    }
                }
                
                // If there's at least one valid path reaching this cell
                if (maxSum != -1) {
                    int val = 0;
                    if (ch != 'E' && ch != 'S') {
                        val = ch - '0';
                    }
                    dp[r][c][0] = maxSum + val;
                    dp[r][c][1] = ways;
                }
            }
        }
        
        int[] ans = dp[0][0];
        
        // If the max sum remains -1, it means the target 'E' is unreachable
        if (ans[0] != -1) {
            return new int[]{ans[0], ans[1]};
        } else {
            return new int[]{0, 0};
        }
    }
}