package randy.leetcode;

import java.util.Arrays;

public class MaxSortedNumber {
    public static void main(String[] args) {
        int[] a = {25,5,12,97,3,8,79,73,38,88,98,29,84,74,16,2,67,65,41,44,
                88,75,51,87,95,90,45,40,7,53,5,30,77,5,56,58,41,51,62,88,33,
                69,81,78,18,63,82,90,21,6,12,92,67,6,81,83,14,6,76,85,79,96,
                41,44,20,89,59,58,83,58,73,1,41,41,24,55,61,49,10,42,5,1,98,
                30,91,9,34,5,84,43,73,4,22,11,21,14,1,62,77,41};
        System.out.println(largestNumber(a));
    }

    public static String largestNumber(int[] nums) {
        if (nums.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(nums).mapToObj(String::valueOf)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                .forEach(sb::append);

        String s = sb.toString();
        int i = 0;
        for (; i < s.length() && s.charAt(i) == '0'; i++);

        return i == s.length() ? "0" : s.substring(i);
    }
}
