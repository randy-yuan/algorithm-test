package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// https://leetcode-cn.com/problems/min-cost-to-connect-all-points/
public class MinimumCostConnectionAllPoints {
    public int minCostConnectPoints(int[][] points) {
        if (points.length == 0) return 0;
        int n = points.length;

        // Kruskal算法
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] e = new int[3];
                e[0] = i;
                e[1] = j;
                e[2] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(e);
            }
        }
        edges.sort(Comparator.comparingInt(e -> e[2]));

        int cost = 0;
        int count = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] e: edges) {
            // 每次选择最小的且不会产生环的边，这里通过并查集来判断是否已经连通
            int x = uf.find(e[0]);
            int y = uf.find(e[1]);
            if (x != y) {
                uf.union(x, y);
                cost += e[2];
                if (++count == n - 1) break;
            }
        }
        return cost;
    }

    class UnionFind {
        int[] roots;
        UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) roots[i] = i;
        }
        public int find(int x) {
            while (roots[x] != x) x = roots[x];
            return x;
        }
        public void union(int x, int y) {
            roots[find(x)] = find(y);
        }
    }
}
