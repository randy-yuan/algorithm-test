package randy.leetcode;

import java.util.Objects;

// Immutable object
public class Range {
    // inclusive
    private final int start;
    // exclusive
    private final int end;

    public Range(int start, int end) {
        checkRange(start, end);
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public static Range of(int start, int end) {
        return new Range(start, end);
    }

    public boolean isIntersected(int start, int end) {
        checkRange(start, end);
        return start < this.end && end > this.start;
    }

    public boolean isIntersected(Range r) {
        return isIntersected(r.start, r.end);
    }

    public Range merge(int start, int end) {
        if (isIntersected(start, end) || start == this.end || end == this.start) {
            return new Range(Math.min(start, this.start), Math.max(end, this.end));
        }
        throw new IllegalArgumentException("not intersected");
    }

    public Range merge(Range r) {
        return merge(r.start, r.end);
    }

    public Range intersect(int start, int end) {
        if (isIntersected(start, end)) {
            return new Range(Math.max(start, this.start), Math.min(end, this.end));
        }
        throw new IllegalArgumentException("not intersected");
    }

    public Range intersect(Range r) {
        return intersect(r.start, r.end);
    }

    private void checkRange(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("start greater or equal to end");
        }
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start == range.start &&
                end == range.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
