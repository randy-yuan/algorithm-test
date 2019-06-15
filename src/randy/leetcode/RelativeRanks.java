package randy.leetcode;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode-cn.com/problems/relative-ranks/
// 506. 相对名次
public class RelativeRanks {
    class Rank {
        int idx;
        int score;
        int rank;

        public Rank(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
        public int getIdx() {
            return idx;
        }
        public int getScore() {
            return score;
        }
    }

    public String[] findRelativeRanks(int[] nums) {
        Rank[] ranks = new Rank[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ranks[i] = new Rank(i, nums[i]);
        }

        Arrays.sort(ranks, Comparator.comparingInt(Rank::getScore).reversed());
        for (int i = 0; i < ranks.length; i++) {
            ranks[i].rank = i + 1;
        }

        Arrays.sort(ranks, Comparator.comparingInt(Rank::getIdx));
        String[] res = new String[ranks.length];
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].rank < 4) {
                if (ranks[i].rank == 1) {
                    res[i] = "Gold Medal";
                } else if (ranks[i].rank == 2) {
                    res[i] = "Silver Medal";
                } else if (ranks[i].rank == 3) {
                    res[i] = "Bronze Medal";
                }
            } else res[i] = String.valueOf(ranks[i].rank);
        }
        return res;
    }

    public static void main(String[] args) {
        RelativeRanks rr = new RelativeRanks();
        int[] nums = {10,3,8,9,4};
        rr.findRelativeRanks(nums);
    }
}
