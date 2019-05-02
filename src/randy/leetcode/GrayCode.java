package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/gray-code/
// 89. 格雷编码
public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> r = new ArrayList<>();
        r.add(0);
        if (n > 0) grayCode(n - 1, r);
        return r;
    }

    public List<Integer> grayCode(int bit, List<Integer> result) {
        if (bit == 0) {
            result.add(result.get(result.size() - 1) ^ 0x1);
        } else {
            grayCode(bit - 1, result);
            result.add(result.get(result.size() - 1) ^ (1 << bit));
            grayCode(bit - 1, result);
        }
        return result;
    }
}
