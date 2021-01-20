package randy.leetcode;

import java.util.*;

public class NestedInteger {
    private Integer value;
    private List<NestedInteger> nested;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.value = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return value != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return value;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.value = value;
        this.nested = null;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        this.value = null;
        if (nested == null) {
            nested = new ArrayList<>();
        }
        nested.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return nested != null ? nested : Collections.emptyList();
    }

    public static NestedInteger deserialize(String s) {
        if (s == null || s.isEmpty()) return null;

        int len = s.length();
        Deque<NestedInteger> stk = new LinkedList<>();
        NestedInteger ni = null;
        boolean inValue = false;
        boolean isPositive = true;
        int value = 0;

        for (int i = 0; i <= len; i++) {
            char c = i < len ? s.charAt(i) : ' ';
            if (c == '-') {
                if (inValue) {
                    return null;
                }
                inValue = true;
                isPositive = false;
                value = 0;
            } else if (Character.isDigit(c)) {
                if (!inValue) {
                    inValue = true;
                    isPositive = true;
                    value = 0;
                }
                value = value * 10 + c - '0';
            } else if (c == ',') {
                if (inValue) {
                    if (ni == null) return null;
                    ni.add(new NestedInteger(isPositive ? value : -value));
                    inValue = false;
                }
            } else if (c == '[') {
                if (ni != null) {
                    stk.push(ni);
                }
                ni = new NestedInteger();
            } else if (c == ']') {
                if (inValue) {
                    ni.add(new NestedInteger(isPositive ? value : -value));
                    inValue = false;
                }
                if (!stk.isEmpty()) {
                    NestedInteger parent = stk.pop();
                    parent.add(ni);
                    ni = parent;
                }
            } else if (c == ' ') {
                if (inValue) {
                    NestedInteger ni2 = new NestedInteger(isPositive ? value : -value);
                    if (ni != null) {
                        ni.add(ni2);
                    } else {
                        ni = ni2;
                    }
                    inValue = false;
                }
            } else {
                return null;
            }
        }

        if (!stk.isEmpty()) {
            return null;
        }
        return ni;
    }

    public static void main(String[] args) {
        NestedInteger ni1 = NestedInteger.deserialize("324");
        NestedInteger ni2 = NestedInteger.deserialize("[123,[456,[789]]");
        System.out.println("OK");
    }
 }