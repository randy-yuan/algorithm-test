package randy.leetcode;

// https://leetcode-cn.com/problems/24-game/
// 679. 24 点游戏
public class GamePoint24 {
    public boolean judgePoint24(int[] nums) {
        FractionNumber[] fn = new FractionNumber[4];
        boolean[] flag = new boolean[4];
        for (int i = 0; i < 4; i++) {
            flag[i] = true;
            for (int j = 0; j < 4; j++) {
                if (flag[j]) continue;
                flag[j] = true;
                for (int k = 0; k < 4; k++) {
                    if (flag[k]) continue;
                    flag[k] = true;
                    int m = 0;
                    for (; m < 4 && flag[m]; m++);

                    fn[0] = FractionNumber.of(nums[i]);
                    fn[1] = FractionNumber.of(nums[j]);
                    fn[2] = FractionNumber.of(nums[k]);
                    fn[3] = FractionNumber.of(nums[m]);

                    if (judgeOp(fn)) return true;

                    flag[k] = false;
                }
                flag[j] = false;
            }
            flag[i] = false;
        }
        return false;
    }

    private boolean judgeOp(FractionNumber[] fn) {
        char[] SIGN = {'+', '-', '*', '/'};
        char[] op = new char[3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    op[0] = SIGN[i];
                    op[1] = SIGN[j];
                    op[2] = SIGN[k];
                    if (judge(fn, op)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // (a + b) + (c + d)
    // ((a + b) + c) + d
    // a + (b + c) + d
    // a + (b + (c + d))
    private boolean judge(FractionNumber[] fn, char[] op) {
        FractionNumber f01 = calc(fn[0], fn[1], op[0]);
        FractionNumber f23 = calc(fn[2], fn[3], op[2]);
        FractionNumber r = calc(f01, f23, op[1]);
        if (!r.divisionByZero() && r.getRemainder() == 0 && r.getInteger() == 24) return true;

        r = calc(calc(f01, fn[2], op[1]), fn[3], op[2]);
        if (!r.divisionByZero() && r.getRemainder() == 0 && r.getInteger() == 24) return true;

        r = calc(fn[0], calc(fn[1], f23, op[1]), op[0]);
        if (!r.divisionByZero() && r.getRemainder() == 0 && r.getInteger() == 24) return true;

        r = calc(calc(fn[0], calc(fn[1], fn[2], op[1]), op[0]), fn[3], op[2]);
        return (!r.divisionByZero() && r.getRemainder() == 0 && r.getInteger() == 24);
    }

    private FractionNumber calc(FractionNumber fn1, FractionNumber fn2, char op) {
        switch (op) {
            case '+': return fn1.add(fn2);
            case '-': return fn1.subtract(fn2);
            case '*': return fn1.multiply(fn2);
            case '/': return fn1.divide(fn2);
        }
        return fn1;
    }

    private void print(int[] nums, char[] op, FractionNumber fn) {
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append(op[0]).append(nums[1]).append(op[1])
                .append(nums[2]).append(op[2]).append(nums[3]);
        if (fn != null) sb.append(' ').append(fn);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        GamePoint24 jp24 = new GamePoint24();
        int[] nums = {1, 3, 4, 6};
        int[] nums2 = {1, 2, 1, 2};
        int[] nums3 = {1, 2, 1, 2};
        System.out.println(jp24.judgePoint24(nums));
        //System.out.println(jp24.judgePoint24(nums2));
        //System.out.println(jp24.judgePoint24(nums3));
    }
}
