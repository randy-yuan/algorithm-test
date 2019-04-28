package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/my-calendar-i/
// 729. 我的日程安排表 I
public class MyCalendar {
    private List<Range> ranges;

    public MyCalendar() {
        ranges = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        if (start >= end) return false;

        return book2(start, end);
    }

    public boolean book1(int start, int end) {
        for (Range r: ranges) {
            if (r.isIntersected(start, end)) return false;
        }
        ranges.add(Range.of(start, end));
        return true;
    }

    public boolean book2(int start, int end) {
        Range r;
        int i;
        for (i = 0; i < ranges.size(); i++) {
            r = ranges.get(i);
            if (r.isIntersected(start, end)) return false;
            if (end <= r.getStart()) {
                break;
            }
        }

        Range left = null;
        Range right = null;
        if (i > 0) {
            r = ranges.get(i - 1);
            if (start == r.getEnd()) left = r;
        }
        if (i < ranges.size()) {
            r = ranges.get(i);
            if (end == r.getStart()) right = r;
        }

        if (left != null) {
            r = left.merge(start, end);
            if (right != null) {
                r = r.merge(right);
                ranges.remove(i);
            }
            ranges.set(i - 1, r);
        } else if (right != null) {
            r = right.merge(start, end);
            ranges.set(i, r);
        } else {
            ranges.add(i, Range.of(start, end));
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(15, 25));
        System.out.println(calendar.book(20, 30));
    }
}
