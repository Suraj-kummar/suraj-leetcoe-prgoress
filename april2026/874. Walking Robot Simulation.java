
   
    

    import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Store obstacles in a HashSet for O(1) lookups.
        Set<Long> obsSet = new HashSet<>();
        
        // Map 2D coordinates to a unique 1D Long
        for (int[] obs : obstacles) {
            // Offset by 30000 to handle negatives, then shift X by 16 bits and combine with Y
            long hash = (((long) obs[0] + 30000) << 16) | (obs[1] + 30000);
            obsSet.add(hash);
        }

        // Directions ordered clockwise: North(0), East(1), South(2), West(3)
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0; // Starts facing North

        int x = 0, y = 0;
        int maxDistSq = 0;

        for (int cmd : commands) {
            if (cmd == -2) {
                // Turn Left (equivalent to adding 3 in modulo 4 arithmetic to prevent negatives)
                dir = (dir + 3) % 4;
            } else if (cmd == -1) {
                // Turn Right
                dir = (dir + 1) % 4;
            } else {
                // Move forward 'cmd' units, checking for obstacles step-by-step
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    long nextHash = (((long) nx + 30000) << 16) | (ny + 30000);

                    // If the next step hits an obstacle, break the loop early
                    if (obsSet.contains(nextHash)) {
                        break;
                    }

                    // Otherwise, complete the step
                    x = nx;
                    y = ny;

                    // Update maximum distance squared
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }

        return maxDistSq;
    }
}

