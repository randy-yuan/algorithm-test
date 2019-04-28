package randy.leetcode;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] a = {1,1,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(a));
    }

    public static int findMaxConsecutiveOnes(int[] a) {
        if (a.length == 0) return 0;
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) count++;
            else {
                if (count > maxCount) maxCount = count;
                count = 0;
            }
        }
        if (count > maxCount) maxCount = count;
        return maxCount;
    }
}
