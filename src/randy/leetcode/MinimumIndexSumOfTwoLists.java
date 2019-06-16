package randy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/
// 599. 两个列表的最小索引总和
public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> indexes = new HashMap<>();
        List<String> res = new ArrayList<>();
        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            indexes.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            Integer idx = indexes.get(list2[i]);
            if (idx != null) {
                if (idx + i == minIndexSum) {
                    res.add(list2[i]);
                } else if (idx + i < minIndexSum) {
                    minIndexSum = idx + i;
                    res.clear();
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[0]);
    }
}
