package randy.leetcode;

// https://leetcode-cn.com/problems/24-game/
// 679. 24 点游戏
public class GamePoint24 {
    public boolean judgePoint24(int[] nums) {
        double[] d = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            d[i] = nums[i];
        }
        return judge(d);
    }

    private boolean judge(double[] d) {
        int len = d.length;
        if (len == 1) {
            return Math.abs(d[0] - 24.0) < 1e-6;
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j == i) continue;

                int idx = 0;
                double[] nd = new double[len-1];
                if (len > 2) {
                    for (int k = 0; k < len; k++) {
                        if (k != i && k != j) {
                            nd[idx++] = d[k];
                        }
                    }
                }

                if (j > i) {
                    nd[idx] = d[i] + d[j];
                    if (judge(nd)) return true;

                    nd[idx] = d[i] * d[j];
                    if (judge(nd)) return true;
                }

                nd[idx] = d[i] - d[j];
                if (judge(nd)) return true;

                if (d[j] != 0) {
                    nd[idx] = d[i] / d[j];
                    if (judge(nd)) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GamePoint24 gp24 = new GamePoint24();
        int[][] nums = {{4, 1, 8, 7}, {1, 2, 1, 2}, {1, 3, 4, 6}};

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < nums.length; j++) {
                gp24.judgePoint24(nums[j]);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - begin) + " ms");
    }
}
