package randy.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode-cn.com/problems/the-skyline-problem/
// 218. 天际线问题
public class SkylineProblem2 {
    class Building {
        int L;
        int R;
        int H;

        public Building(int l, int r, int h) {
            L = l;
            R = r;
            H = h;
        }

        public List<Integer> topLeft() {
            List<Integer> list = new ArrayList<>();
            list.add(L);
            list.add(H);
            return list;
        }

        public List<Integer> bottomRight() {
            List<Integer> list = new ArrayList<>();
            list.add(R);
            list.add(0);
            return list;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0 || buildings[0].length != 3) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Building> merged = merge(buildings, 0, buildings.length);
        Building prev = merged.get(0);
        Building cur;
        int size = merged.size();
        for (int i = 1; i < size; i++) {
            cur = merged.get(i);
            if (cur.L > prev.R || cur.H != prev.H) {
                result.add(prev.topLeft());
                if (cur.L > prev.R) result.add(prev.bottomRight());
                prev = cur;
            } else {
                prev.R = cur.R;
            }
        }

        result.add(prev.topLeft());
        result.add(prev.bottomRight());
        return result;
    }

    private List<Building> merge(int[][] b, int start, int end) {
        List<Building> result = new ArrayList<>();
        if (start >= end) return result;
        if (start == end - 1) {
            result.add(new Building(b[start][0], b[start][1], b[start][2]));
            return result;
        }

        int mid = start + (end - start) / 2;
        List<Building> r1 = merge(b, start, mid);
        List<Building> r2 = merge(b, mid, end);

        int size1 = r1.size();
        int size2 = r2.size();
        int i = 0;
        int j = 0;
        Building b1;
        Building b2;
        while (i < size1 && j < size2) {
            b1 = r1.get(i);
            b2 = r2.get(j);
            if (b1.R <= b2.L) {
                result.add(b1);
                i++;
            } else if (b2.R <= b1.L) {
                result.add(b2);
                j++;
            } else {
                result.addAll(merge(b1, b2));
                if (b1.L >= b1.R) i++;
                if (b2.L >= b2.R) j++;
            }
        }

        if (i < size1) {
            for (; i < size1; i++) result.add(r1.get(i));
        }
        if (j < size2) {
            for (; j < size2; j++) result.add(r2.get(j));
        }

        return result;
    }

    private List<Building> merge(Building b1, Building b2) {
        List<Building> r = new ArrayList<>(2);
        int margin = Math.min(b1.R, b2.R);
        if (b1.L <= b2.L) {
            if (b1.H >= b2.H) {
                r.add(new Building(b1.L, margin, b1.H));
            } else {
                if (b1.L < b2.L) r.add(new Building(b1.L, b2.L, b1.H));
                r.add(new Building(b2.L, margin, b2.H));
            }
        } else {
            if (b2.H >= b1.H) {
                r.add(new Building(b2.L, margin, b2.H));
            } else {
                r.add(new Building(b2.L, b1.L, b2.H));
                r.add(new Building(b1.L, margin, b1.H));
            }
        }
        b1.L = margin;
        b2.L = margin;
        return r;
    }

    public static void main(String[] args) {
        SkylineProblem2 sp = new SkylineProblem2();
        //int[][] b = { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };
        int[][] b = {{2,4,70},{3,8,30},{6,100,41},{7,15,70},{10,30,102},{15,25,76},{60,80,91},{70,90,72},{85,120,59}};
        sp.getSkyline(b);
    }

}
