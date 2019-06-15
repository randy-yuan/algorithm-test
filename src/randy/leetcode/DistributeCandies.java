package randy.leetcode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/distribute-candies/
// 575. 分糖果
public class DistributeCandies {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int c: candies) {
            set.add(c);
        }
        return Math.min(set.size(), candies.length / 2);
    }
}
