class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        long long total = 0, curr = 0;
        unordered_map<long long, int> total_cnt;
        for (auto& arr : grid) {
            for (int v : arr) {
                total_cnt[v]++;
                total += v;
            }
        }


        unordered_map<long long, int> tmp(total_cnt), curr_cnt;
   
        for (int i = 0;i < m - 1;++i) {
            for (int j = 0;j < n;++j) {
                int v = grid[i][j];
                curr += v;
                curr_cnt[v]++;
                //auto it = tmp.find(v);
                //if (--it -> second == 0) tmp.erase(it);
                
            }
            long long o = total - curr;
            if (curr == o) {
                
                return true;
            }

            if (n == 1 || i == 0 || i == m - 2) {


                if (n == 1) {
                    if (curr > o) {
                        if (grid[0][0] == curr - o || grid[i][0] == curr - o) return true;
                    }
                    else {
                        if (grid[i + 1][0] == o - curr || grid[m - 1][0] == o - curr) return true;
                    }
                }
                else {
                    if (curr > o) {
                        if ((i != 0 && curr_cnt.contains(curr - o)) || grid[i][0] == curr - o || grid[i][n - 1] == curr - o) return true;
                    }
                    else {
                        if ((i != m - 2 && tmp.contains(o - curr)) || grid[m - 1][0] == o - curr || grid[m - 1][n - 1] == o - curr) return true;
                    }
                }
            }
            else {
                
                if (tmp.contains(o - curr) || curr_cnt.contains(curr - o)) {
    
                    return true;
                }
                
            }
        }

        tmp = move(total_cnt);
        curr_cnt = unordered_map<long long, int> ();
        curr = 0;
    

        for (int j = 0;j < n - 1;++j) {
            
            for (int i = 0;i < m;++i) {
                int v = grid[i][j];
                curr += v;
                curr_cnt[v]++;
                //auto it = tmp.find(v);
                //if (--it -> second == 0) tmp.erase(it);
                
            }
            long long o = total - curr;
            if (curr == o) return true;
            if (m == 1 || j == 0 || j == n - 2) {
       
                
                if (m == 1) {
                    if (curr > o) {
                        if (grid[0][0] == curr - o || grid[0][j] == curr - o) return true;
                    }
                    else {
                        if (grid[0][j + 1] == o - curr || grid[0][n - 1] == o - curr) return true;
                    }
                }
                else {
                    if (curr > o) {
                        if ((j != 0 && curr_cnt.contains(curr - o)) || grid[0][j] == curr - o || grid[m - 1][j] == curr - o) return true;
                    }
                    else {
                        if ((j != n - 2 && tmp.contains(o - curr)) || grid[0][n - 1] == o - curr || grid[m - 1][n - 1] == o - curr) return true;
                    }
                }
            }
            else {
                
                if (tmp.contains(o - curr) || curr_cnt.contains(curr - o)) return true;
                
            }
        }
        return false;

    }
};