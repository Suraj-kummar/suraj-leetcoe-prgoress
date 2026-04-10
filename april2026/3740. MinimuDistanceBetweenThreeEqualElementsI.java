package april2026;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        // Since 1 <= nums[i] <= 100, an array of lists is faster than a HashMap
        List<Integer>[] indicesMap = new ArrayList[101];
        for (int i = 0; i <= 100; i++) {
            indicesMap[i] = new ArrayList<>();
        }

        // Group indices by their corresponding number
        // Because we iterate from 0 to n-1, the indices in each list are naturally
        // sorted
        for (int i = 0; i < n; i++) {
            indicesMap[nums[i]].add(i);
        }

        int minDistance = Integer.MAX_VALUE;

        // Check consecutive triplets for each number
        for (int i = 1; i <= 100; i++) {
            List<Integer> indices = indicesMap[i];

            if (indices.size() >= 3) {
                // Iterate through windows of size 3
                for (int j = 0; j < indices.size() - 2; j++) {
                    // indices.get(j) is the first index, indices.get(j+2) is the third
                    int dist = 2 * (indices.get(j + 2) - indices.get(j));
                    minDistance = Math.min(minDistance, dist);
                }
            }
        }

        // Return the result, or -1 if no valid tuple was found
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}