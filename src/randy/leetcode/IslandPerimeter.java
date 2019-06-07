package randy.leetcode;

// https://leetcode-cn.com/problems/island-perimeter/
// 463. 岛屿的周长
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int res = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) continue;
                if (i == 0 || grid[i-1][j] == 0) res++;
                if (i == r - 1 || grid[i+1][j] == 0) res++;
                if (j == 0 || grid[i][j-1] == 0) res++;
                if (j == c - 1 || grid[i][j+1] == 0) res++;
            }
        }
        return res;
    }
}
