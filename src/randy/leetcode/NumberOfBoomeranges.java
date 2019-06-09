package randy.leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/number-of-boomerangs/
// 447. 回旋镖的数量
public class NumberOfBoomeranges {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer> dtMap = new HashMap<>();
        int res = 0;
        int dt;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int dx = points[i][0] - points[j][0];
                    int dy = points[i][1] - points[j][1];
                    dt = dx * dx + dy * dy;
                    dtMap.put(dt, dtMap.getOrDefault(dt, 0) + 1);
                }
            }
            for (Integer cnt: dtMap.values()) {
                res += cnt * (cnt - 1);
            }
            dtMap.clear();
        }

        return res;
    }

    public static void main(String[] args) {
        NumberOfBoomeranges boomeranges = new NumberOfBoomeranges();
        int[][] points = {{0,0},{1,0},{2,0}};
        int[][] points2 = {{3,6},{7,5},{3,5},{6,2},{9,1},{2,7},{0,9},{0,6},{2,6}};
        int[][] points3 = {{0,0}, {1,0}, {-1, 0}, {0,1}, {0, -1}};
        System.out.println(boomeranges.numberOfBoomerangs(points));
        System.out.println(boomeranges.numberOfBoomerangs(points2));
        System.out.println(boomeranges.numberOfBoomerangs(points3));
    }
}
