package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/swim-in-rising-water/
public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int t = 0;
        boolean[][] seen = new boolean[N][N];
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(k -> grid[k / N][k % N]));
        queue.offer(0);
        while (!queue.isEmpty()) {
            int k = queue.poll();
            int i = k / N;
            int j = k % N;
            t = Math.max(t, grid[i][j]);

            if (i == N - 1 && j == N - 1) {
                break;
            }

            if (i > 0 && !seen[i-1][j]) {
                queue.add(k - N);
                seen[i-1][j] = true;
            }
            if (i < N - 1 && !seen[i+1][j]) {
                queue.add(k + N);
                seen[i+1][j] = true;
            }
            if (j > 0 && !seen[i][j-1]) {
                queue.add(k - 1);
                seen[i][j-1] = true;
            }
            if (j < N - 1 && !seen[i][j+1]) {
                queue.add(k + 1);
                seen[i][j+1] = true;
            }
        }

        return t;
    }

    public static void main(String[] args) {
        SwimInRisingWater test = new SwimInRisingWater();
        int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        System.out.println(test.swimInWater(grid));
    }
}
