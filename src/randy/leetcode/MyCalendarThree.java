package randy.leetcode;

import java.util.TreeMap;

// https://leetcode-cn.com/problems/my-calendar-iii/
// 732. 我的日程安排表 III
class MyCalendarThree {
    private TreeMap<Integer, Integer> calendar;

    public MyCalendarThree() {
        calendar = new TreeMap<>();
    }

    public int book(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        int max = 0;
        int active = 0;
        for (int d : calendar.values()) {
            active += d;
            if (active > max) max = active;
        }
        return max;
    }
}
