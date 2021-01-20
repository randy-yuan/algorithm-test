package randy.leetcode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

public class MinimumOperations {
    public int minOperations(int[] nums, int x) {
        Map<Integer, Integer> lsum = new HashMap<>();
        Map<Integer, Integer> rsum = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > x) break;
            lsum.put(sum, i + 1);
        }
        sum = 0;
        for (int j = nums.length-1; j > -1; j--) {
            sum += nums[j];
            if (sum > x) break;
            rsum.put(sum, nums.length - j);
        }
        int res = Math.min(lsum.getOrDefault(x, Integer.MAX_VALUE), rsum.getOrDefault(x, Integer.MAX_VALUE));
        for (Map.Entry<Integer, Integer> e: lsum.entrySet()) {
            Integer r = rsum.get(x - e.getKey());
            if (r != null) {
                res = Math.min(res, e.getValue() + r);
            }
        }
        return res >= nums.length ? -1 : res;
    }

    public static void main(String[] args) {
        MinimumOperations op = new MinimumOperations();
        int[] nums = {8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309};
        System.out.println(op.minOperations(nums, 134365));
    }
}
