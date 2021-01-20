package randy.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 0) return 0;
        if (stones.length == 1) return stones[0];

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);
        }

        while (queue.size() >= 2) {
            int s1 = queue.poll();
            int s2 = queue.poll();
            if (s1 > s2) {
                queue.offer(s1 - s2);
            }
        }

        return queue.isEmpty() ? 0 : queue.peek();
    }

    public int lastStoneWeight2(int[] stones) {
        if (stones.length == 0) return 0;
        if (stones.length == 1) return stones[0];

        int index = 0;
        int[] data = new int[1001];
        for (int i = 0; i < stones.length; i++) {
            data[stones[i]]++;
            index = Math.max(index, stones[i]);
        }

        while (index > 0) {
            for (; index > 0 && data[index] == 0; index--);
            if (index == 0) {
                return 0;
            }
            int s1 = index;
            data[index]--;

            for (; index > 0 && data[index] == 0; index--);
            if (index == 0) {
                return s1;
            }
            int s2 = index;
            data[index]--;

            if (s1 > s2) {
                data[s1-s2]++;
                index = Math.max(index, s1 - s2);
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        LastStoneWeight test = new LastStoneWeight();
        System.out.println(test.lastStoneWeight2(new int[]{1,3}));
    }
}
