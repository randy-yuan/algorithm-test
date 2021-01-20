package randy.leetcode;

public class PlayWithChips {
    public int minCostToMoveChips(int[] chips) {
        if (chips.length == 0) return 0;
        int oddCount = 0;
        int evenCount = 0;
        for (int i = 0; i < chips.length; i++) {
            if ((chips[i] & 0x01) == 1) {
                oddCount++;
            } else {
                evenCount++;
            }
        }
        return Math.min(oddCount, evenCount);
    }
}
