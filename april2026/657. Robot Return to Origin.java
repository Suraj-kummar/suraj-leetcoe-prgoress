package april2026;

class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        
        // Convert string to char array for faster iteration
        for (char move : moves.toCharArray()) {
            if (move == 'U') {
                y++;
            } else if (move == 'D') {
                y--;
            } else if (move == 'R') {
                x++;
            } else if (move == 'L') {
                x--;
            }
        }
        
        // Returns true only if it's back at the origin
        return x == 0 && y == 0;
    }
}