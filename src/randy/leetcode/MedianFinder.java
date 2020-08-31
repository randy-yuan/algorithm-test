package randy.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/continuous-median-lcci/
public class MedianFinder {
    private List<Integer> values;

    /** initialize your data structure here. */
    public MedianFinder() {
        values = new ArrayList<>();
    }

    public void addNum(int num) {
        int idx = Collections.binarySearch(values, num, Integer::compareTo);
        values.add(idx >= 0 ? idx : -idx-1, num);
    }

    public double findMedian() {
        int n = values.size();
        return (n & 0x01) == 1 ? values.get(n >> 1) : ((double)values.get(n >> 1) + values.get((n >> 1)-1)) / 2;
    }

    static class MedianFinder2 {
        private PriorityQueue<Integer> left;
        private PriorityQueue<Integer> right;

        /** initialize your data structure here. */
        public MedianFinder2() {
            left = new PriorityQueue<>((a, b) -> b - a);
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (left.isEmpty() || num <= left.peek()) {
                left.add(num);
            } else {
                right.add(num);
            }
            if (left.size() - right.size() > 1) {
                right.add(left.poll());
            }
            if (right.size() - left.size() > 1) {
                left.add(right.poll());
            }
        }

        public double findMedian() {
            if (left.size() == right.size()) {
                return (left.peek() + right.peek()) * 0.5;
            } else if (left.size() > right.size()){
                return left.peek();
            } else {
                return right.peek();
            }
        }
    }
}
