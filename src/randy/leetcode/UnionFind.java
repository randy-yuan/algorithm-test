package randy.leetcode;

// 并查集
public class UnionFind {
    int[] root;
    int[] rank;

    UnionFind(int n) {
        root = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    // 路径压缩
    public int find(int x) {
        return x == root[x] ? x : (root[x] = find(root[x]));
    }

    public void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            if (rank[rx] > rank[ry]) {
                root[ry] = rx;
            } else if (rank[rx] == rank[ry]) {
                root[ry] = rx;
                rank[rx]++;
            } else {
                root[rx] = ry;
            }
        }
    }
}