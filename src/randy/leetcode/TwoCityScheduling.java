package randy.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        if (costs.length == 0) return 0;
        int result = 0;

        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] - o1[1]) - (o2[0] - o2[1]);
            }
        });

        int len = costs.length / 2;
        for (int i = 0; i < len; i++) {
            result += costs[i][0] + costs[i+len][1];
        }

        return result;
    }
}
