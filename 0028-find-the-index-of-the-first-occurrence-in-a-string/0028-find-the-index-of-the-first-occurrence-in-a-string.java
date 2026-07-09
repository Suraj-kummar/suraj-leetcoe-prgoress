class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();

        // Loop through the haystack
        // Only need to go up to hLen - nLen
        for (int i = 0; i <= hLen - nLen; i++) {
            // Check if the substring matches the needle
            if (haystack.substring(i, i + nLen).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
} 
