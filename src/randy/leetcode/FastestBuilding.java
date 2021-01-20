package randy.leetcode;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/furthest-building-you-can-reach/
public class FastestBuilding {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int l = 1;
        int r = heights.length - 1;
        int m;
        while (l < r) {
            m = l + (r - l) / 2;
            long sum = helper(heights, bricks, ladders, m);
            if (sum > bricks) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        return l;
    }

    private long helper(int[] heights, int bricks, int ladders, int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        long sum = 0;
        int i = 1;
        for (; i <= n; i++) {
            int jump = heights[i] - heights[i-1];
            if (jump > 0) {
                queue.offer(jump);
                if (queue.size() == ladders) {
                    i++;
                    break;
                }
            }
        }
        for (; i <= n; i++) {
            int jump = heights[i] - heights[i-1];
            if (jump > 0) {
                if (jump > queue.peek()) {
                    sum += queue.poll();
                    queue.offer(jump);
                } else {
                    sum += jump;
                }
                if (sum > bricks) break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        FastestBuilding test = new FastestBuilding();
        int[] heights = {4,12,2,7,3,18,20,3,19};
        System.out.println(test.furthestBuilding(heights, 10, 2));
    }
}
