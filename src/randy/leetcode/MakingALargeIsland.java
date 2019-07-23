package randy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/making-a-large-island/
// 827. 最大人工岛
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1)
            return 1;

        int maxArea = 1;
        List<Set<Integer>> islands = new ArrayList<>();
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    continue;

                int size = islands.size();
                for (int k = 0; k < size; k++) {
                    if (isNeighbor(i, j, n, islands.get(k))) {
                        indexs.add(k);
                    }
                }

                if (indexs.isEmpty()) {
                    Set<Integer> newIsland = new HashSet<>();
                    newIsland.add(i * n + j);
                    islands.add(newIsland);
                } else {
                    Set<Integer> merged = islands.get(indexs.get(0));
                    merged.add(i * n + j);
                    for (int k = indexs.size() - 1; k > 0; k--) {
                        merged.addAll(islands.get(indexs.get(k)));
                        islands.remove(indexs.get(k).intValue());
                    }
                    maxArea = Math.max(maxArea, merged.size());
                    indexs.clear();
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    continue;
                int area = 1;
                for (Set<Integer> island: islands) {
                    if (isNeighbor(i, j, n, island)) {
                        area += island.size();
                    }
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    private boolean isNeighbor(int r, int c, int n, Set<Integer> island) {
        int pos = r * n + c;
        return r > 0 && island.contains(pos - n)
                || island.contains(pos + n)
                || c > 0 && island.contains(pos - 1)
                || c < (n - 1) && island.contains(pos + 1);
    }

    private Map<Integer, Integer> areas = new HashMap<>();
    private Map<Integer, List<Integer>> islands = new HashMap<>();
    private int[] tag = new int[2500];

    public int largestIsland2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1)
            return 1;

        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    max = Math.max(max, merge(i, j, n));
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = 1;
                    int pos = i * n + j;
                    int idx;
                    set.clear();
                    if (i > 0 && (idx = tag[pos-n]) != 0) {
                        area += areas.get(idx);
                        set.add(idx);
                    }
                    if (i < (m - 1) && (idx = tag[pos+n]) != 0 && !set.contains(idx)) {
                        area += areas.get(idx);
                        set.add(idx);
                    }
                    if (j > 0 && (idx = tag[pos-1]) != 0 && !set.contains(idx)) {
                        area += areas.get(idx);
                        set.add(idx);
                    }
                    if (j < (n - 1) && (idx = tag[pos+1]) != 0 && !set.contains(idx)) {
                        area += areas.get(idx);
                    }
                    max = Math.max(max, area);
                }
            }
        }

        return max;
    }

    private int merge(int r, int c, int n) {
        int area = 1;
        int pos = r * n + c;
        List<Integer> newIsland = new ArrayList<>();
        newIsland.add(pos);
        tag[pos] = pos + 1;

        if (r > 0 && tag[pos-n] != 0) {
            int top = tag[pos-n];
            for (int p: islands.get(top)) {
                tag[p] = tag[pos];
                newIsland.add(p);
            }
            islands.remove(top);
            area += areas.remove(top);
        }
        if (c > 0 && tag[pos-1] != 0) {
            int left = tag[pos-1];
            if (areas.containsKey(left)) {
                for (int p: islands.get(left)) {
                    tag[p] = tag[pos];
                    newIsland.add(p);
                }
                islands.remove(left);
                area += areas.remove(left);
            }
        }

        islands.put(tag[pos], newIsland);
        areas.put(tag[pos], area);
        return area;
    }

    public static void main(String[] args) {
        MakingALargeIsland largeIsland = new MakingALargeIsland();
        System.out.println(largeIsland.largestIsland2(new int[][] {{1,1},{1,0}}));
    }
}
