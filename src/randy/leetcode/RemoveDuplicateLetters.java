package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode-cn.com/problems/remove-duplicate-letters/
// https://leetcode-cn.com/problems/remove-duplicate-letters/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-4/

/**
 * 要想使结果的字典序最小，就应该尽可能地将小的元素留在前面
 *
 * 利用单调栈特性，如果栈顶元素比当前元素大，且后续还会出现，则去掉
 *
 */
public class RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {
        char[] arr = s.toCharArray();

        // 字符栈
        char[] stack = new char[arr.length];
        int pos = -1;

        // 最后一次出现位置
        int[] last = new int[26];
        for (int i = 0; i < arr.length; i++) {
            last[arr[i] - 'a'] = i;
        }

        //记录已有字母
        boolean[] seen = new boolean[26];

        // 遍历
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            int idx = c - 'a';
            if (!seen[idx]) {
                while (pos >= 0) {
                    char c2 = stack[pos];
                    int idx2 = c2 - 'a';
                    if (c < c2 && last[idx2] > i) {
                        pos--;
                        seen[idx2] = false;
                    } else {
                        break;
                    }
                }
                seen[idx] = true;
                stack[++pos] = c;
            }
        }

        return new String(stack, 0, pos + 1);
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
}
