class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWaviness = 0;
        
        for (int i = num1; i <= num2; ++i) {
            int temp = i;
            
            // Numbers with fewer than 3 digits inherently have 0 waviness
            if (temp < 100) {
                continue;
            }
            
            // Extract the first two right-most digits to set up our sliding window
            int right = temp % 10; 
            temp /= 10;
            int mid = temp % 10; 
            temp /= 10;
            
            // Process the remaining digits moving leftward
            while (temp > 0) {
                int left = temp % 10;
                
                // Check if the middle digit is a Peak or a Valley
                if ((mid > right && mid > left) || (mid < right && mid < left)) {
                    totalWaviness++;
                }
                
                // Shift the window to the left for the next iteration
                right = mid;
                mid = left;
                temp /= 10;
            }
        }
        
        return totalWaviness;
    }
}