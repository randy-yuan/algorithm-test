package randy.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode-cn.com/problems/the-skyline-problem/
// 218. 天际线问题
public class SkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0 || buildings[0].length != 3) {
            return Collections.emptyList();
        }

        List<int[]> merged = new ArrayList<>();
        merged.add(buildings[0]);
        for (int i = 1; i < buildings.length; i++) {
            int[] cur = buildings[i];
            int idx = Math.max(0, search(merged, cur[0]) - 1);
            int len = merged.size() - idx;
            int[][] overlapped = new int[len][];

            for (int j = merged.size() - 1; j >= idx; j--) {
                overlapped[j-idx] = merged.get(j);
                merged.remove(j);
            }

            for (int j = 0; j < len; j++) {
                merged.addAll(merge(overlapped[j], buildings[i]));
            }

            if (cur[1] > overlapped[len-1][1]) {
                int[] tmp = new int[3];
                tmp[0] = Math.max(cur[0], overlapped[len-1][1]);
                tmp[1] = cur[1];
                tmp[2] = cur[2];
                merged.add(tmp);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        int[] prev = merged.get(0);
        int[] cur;
        for (int i = 1; i < merged.size(); i++) {
            cur = merged.get(i);
            if (cur[0] > prev[1] || cur[2] != prev[2]) {
                List<Integer> list = new ArrayList<>();
                list.add(prev[0]);
                list.add(prev[2]);
                result.add(list);
                if (cur[0] > prev[1]) {
                    List<Integer> list2 = new ArrayList<>();
                    list2.add(prev[1]);
                    list2.add(0);
                    result.add(list2);
                }
                prev = cur;
            } else {
                prev[1] = cur[1];
            }
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(prev[0]);
        list1.add(prev[2]);
        list2.add(prev[1]);
        list2.add(0);
        result.add(list1);
        result.add(list2);

        return result;
    }

    private int search(List<int[]> b, int x) {
        int start = 0;
        int end = b.size() - 1;
        int mid = 0;
        int bx;
        while (start <= end) {
            mid = start + (end - start) / 2;
            bx = b.get(mid)[0];
            if (x == bx) return mid;
            else if (x > bx) start = mid + 1;
            else end = mid - 1;
        }
        return start;
    }

    // b1[0] <= b2[0]
    private List<int[]> merge(int[] b1, int[] b2) {
        List<int[]> r = new ArrayList<>();
        if (b1[1] <= b2[0] || b1[0] >= b2[1] || b1[2] >= b2[2]) {
            r.add(b1);
            return r;
        }

        if (b1[0] < b2[0]) {
            int[] bl = new int[3];
            bl[0] = b1[0];
            bl[1] = b2[0];
            bl[2] = b1[2];
            r.add(bl);
        }

        int[] bm = new int[3];
        bm[0] = Math.max(b1[0], b2[0]);
        bm[1] = Math.min(b1[1], b2[1]);
        bm[2] = b2[2];
        r.add(bm);

        if (b1[1] > b2[1]) {
            int[] br = new int[3];
            br[0] = b2[1];
            br[1] = b1[1];
            br[2] = b1[2];
            r.add(br);
        }
        return r;
    }

    public static void main(String[] args) {
        SkylineProblem sp = new SkylineProblem();
        int[][] b = { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };
        sp.getSkyline(b);
    }

}
