package randy.leetcode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/heaters/
// 475. 供暖器
public class Heaters {
    // 以房子为中心，判断两侧供暖器的最小半径，比二分查找快
    public int findRadius(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0) return 0;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int res = 0;
        int idx = 0;
        int radius;
        for (int i = 0; i < houses.length; i++) {
            for (; idx < heaters.length && heaters[idx] < houses[i]; idx++);
            if (idx < heaters.length) {
                if (idx > 0) {
                    radius = Math.min(houses[i] - heaters[idx-1], heaters[idx] - houses[i]);
                } else {
                    radius = heaters[idx] - houses[i];
                }
            } else {
                radius = houses[i] - heaters[idx-1];
            }
            if (radius > res) res = radius;
        }

        return res;
    }

    public int findRadius2(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0) return 0;

        Arrays.sort(heaters);

        int res = 0;
        int lastIdx = heaters.length - 1;
        int idx;
        int radius = 0;
        for (int i = 0; i < houses.length; i++) {
            idx = find(heaters, 0, lastIdx, houses[i]);
            if (idx > lastIdx) {
                radius = houses[i] - heaters[lastIdx];
            } else if (heaters[idx] != houses[i]) {
                radius = heaters[idx] - houses[i];
                if (idx > 0) radius = Math.min(radius, houses[i] - heaters[idx-1]);
            }
            if (radius > res) res = radius;
        }

        return res;
    }

    private int find(int[] a, int start, int end, int v) {
        int m;
        while (start <= end) {
            m = start + (end - start) / 2;
            if (a[m] == v) return m;
            if (v > a[m]) start = m + 1;
            else end = m - 1;
        }
        return start;
    }

    public static void main(String[] args) {
        Heaters h = new Heaters();
        int[] houses = {1,1,1,1,1,1,999,999,999,999,999};
        int[] heaters = {499,500,501};
        System.out.println(h.findRadius(houses, heaters));

        int[] houses2 = {1,2,3,4};
        int[] heaters2 = {1,4};
        System.out.println(h.findRadius(houses2, heaters2));
    }
}
