package randy.leetcode;

import java.util.TreeMap;

// https://leetcode-cn.com/problems/my-calendar-ii/
// 731. 我的日程安排表 II

// 巧妙利用区间头尾计数抵消，若有交叉时则不会抵消，前提是要排序
class MyCalendarTwo {

    private TreeMap<Integer, Integer> calendar;

    public MyCalendarTwo() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        // 尝试添加
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        // 按时间线统计日程
        int active = 0;
        for (int d : calendar.values()) {
            active += d;

            // 中途日程>=3时，恢复，返回false
            if (active >= 3) {
                calendar.put(start, calendar.get(start) - 1);
                calendar.put(end, calendar.get(end) + 1);

                if (calendar.get(start) == 0) calendar.remove(start);
                if (calendar.get(end) == 0) calendar.remove(end);

                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo calendarTwo = new MyCalendarTwo();
        System.out.println(calendarTwo.book(10, 20));
        System.out.println(calendarTwo.book(50, 60));
        System.out.println(calendarTwo.book(10, 40));
        System.out.println(calendarTwo.book(5, 15));
        System.out.println(calendarTwo.book(5, 10));
        System.out.println(calendarTwo.book(25, 55));
    }
}
