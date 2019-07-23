package randy.leetcode;

// https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
// 744. 寻找比目标字母大的最小字母
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int begin = 0;
        int end = letters.length - 1;
        int mid = 0;
        while (begin <= end) {
            mid = begin + (end - begin) / 2;
            if (target == letters[mid]) {
                break;
            } else if (target < letters[mid]) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        if (target == letters[mid]) {
            int i = mid + 1;
            for (; i < letters.length && letters[i] == target; i++);
            return i == letters.length ? letters[0] : letters[i];
        } else if (begin == letters.length) {
            return letters[0];
        } else {
            return letters[begin];
        }
    }
}
