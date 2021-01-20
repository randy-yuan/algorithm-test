package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode-cn.com/problems/find-median-from-data-stream/
public class MedianFinder295 {
    /** initialize your data structure here. */
    private List<Integer> list;

    public MedianFinder295() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int idx = Collections.binarySearch(list, num, Integer::compareTo);
        if (idx < 0) {
            list.add(-idx - 1, num);
        }
    }

    public double findMedian() {
        int size = list.size();
        if (size == 0) return 0;
        else if (size % 2 == 1) {
            return list.get(size / 2);
        } else {
            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] a = {};
        int[] b = {1};
        System.out.println(Arrays.binarySearch(a, 1));
        System.out.println(Arrays.binarySearch(b, 1));
        System.out.println(Arrays.binarySearch(b, 2));
    }
}
