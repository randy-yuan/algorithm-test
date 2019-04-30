package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/subsets/
// 78. 子集
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<List<Integer>> prev = new ArrayList<>();
        List<List<Integer>> cur = new ArrayList<>();
        List<Integer> prevIdx = new ArrayList<>();
        List<Integer> curIdx = new ArrayList<>();

        r.add(new ArrayList<>());
        prev.add(new ArrayList<>());
        prevIdx.add(-1);

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < prev.size(); j++) {
                for (int k = prevIdx.get(j) + 1; k < nums.length; k++) {
                    List<Integer> list = new ArrayList<>(prev.get(j));
                    list.add(nums[k]);
                    r.add(list);
                    cur.add(list);
                    curIdx.add(k);
                }
            }

            List<List<Integer>> tmp1 = prev;
            prev = cur;
            cur = tmp1;
            cur.clear();

            List<Integer> tmp2 = prevIdx;
            prevIdx = curIdx;
            curIdx = tmp2;
            curIdx.clear();
        }

        return r;
    }
}
