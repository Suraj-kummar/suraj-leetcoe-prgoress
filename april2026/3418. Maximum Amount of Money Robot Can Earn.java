package april2026;

import java.util.Arrays;

class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        // dp[j][k] represents the max coins at column j, using k neutralizations for the previous row
        int[][] dp = new int[n][3];
        
        // Initialize with a very small number to represent unreachable states
        // We use -1e9 instead of Integer.MIN_VALUE to prevent underflow when adding negative coins
        int MIN_INF = -1000000000;
        for (int j = 0; j < n; j++) {
            Arrays.fill(dp[j], MIN_INF);
        }
        
        for (int i = 0; i < m; i++) {
            int[][] nextRow = new int[n][3];
            for (int j = 0; j < n; j++) {
                Arrays.fill(nextRow[j], MIN_INF);
            }
            
            for (int j = 0; j < n; j++) {
                // Base Case: Starting point at (0, 0)
                if (i == 0 && j == 0) {
                    nextRow[0][0] = coins[0][0];
                    // If the first cell is a robber, we can immediately neutralize it
                    nextRow[0][1] = coins[0][0] < 0 ? 0 : coins[0][0];
                    nextRow[0][2] = coins[0][0] < 0 ? 0 : coins[0][0];
                    continue;
                }
                
                for (int k = 0; k <= 2; k++) {
                    int prevMax = MIN_INF;
                    
                    // Look Up
                    if (i > 0) prevMax = Math.max(prevMax, dp[j][k]);
                    // Look Left
                    if (j > 0) prevMax = Math.max(prevMax, nextRow[j - 1][k]);
                    
                    // Standard move: take the coin/penalty at current cell
                    if (prevMax != MIN_INF) {
                        nextRow[j][k] = Math.max(nextRow[j][k], prevMax + coins[i][j]);
                    }
                    
                    // Neutralization move: only applicable if we have abilities left and it's a robber
                    if (k > 0 && coins[i][j] < 0) {
                        int prevMaxKMinus1 = MIN_INF;
                        
                        if (i > 0) prevMaxKMinus1 = Math.max(prevMaxKMinus1, dp[j][k - 1]);
                        if (j > 0) prevMaxKMinus1 = Math.max(prevMaxKMinus1, nextRow[j - 1][k - 1]);
                        
                        // We add 0 instead of the negative coin value
                        if (prevMaxKMinus1 != MIN_INF) {
                            nextRow[j][k] = Math.max(nextRow[j][k], prevMaxKMinus1); 
                        }
                    }
                }
            }
            // Move to the next row
            dp = nextRow; 
        }
        
        // The answer is the maximum value at the bottom-right corner across all possible neutralization counts
        return Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
    }
}