package randy.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
// 241. 为运算表达式设计优先级
public class DifferentWaysToCompute {
    public List<Integer> diffWaysToCompute(String input) {
        if (input.length() == 0) return Collections.emptyList();

        List<Integer> numbers = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        parse(input, numbers, ops);

        return compute(numbers, ops, 0, ops.size());
    }

    private void parse(String input, List<Integer> numbers, List<Character> ops) {
        int n = 0;
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (Character.isDigit(c)) {
                n = n * 10 + c - '0';
            } else {
                numbers.add(n);
                ops.add(c);
                n = 0;
            }
        }
        numbers.add(n);
    }

    private List<Integer> compute(List<Integer> numbers, List<Character> ops, int start, int end) {
        List<Integer> result = new ArrayList<>();
        if (start >= end) {
            result.add(numbers.get(end));
        } else if (start == end - 1) {
            result.add(calc(ops.get(start), numbers.get(start), numbers.get(end)));
        } else {
            for (int i = start; i < end; i++) {
                List<Integer> r1 = compute(numbers, ops, start, i);
                List<Integer> r2 = compute(numbers, ops, i + 1, end);
                for (Integer n1 : r1) {
                    for (Integer n2 : r2) {
                        result.add(calc(ops.get(i), n1, n2));
                    }
                }
            }
        }
        return result;
    }

    private Integer calc(char op, Integer n1, Integer n2) {
        switch (op) {
            case '+': return n1 + n2;
            case '-': return n1 - n2;
            case '*': return n1 * n2;
            default: return 0;
        }
    }

    public List<Integer> diffWaysToCompute2(String input) {
        HashMap<String, List<Integer>> cache = new HashMap<>();
        return compute(input, cache);
    }

    private List<Integer> compute(String input, HashMap<String, List<Integer>> cache) {
        List<Integer> result = cache.get(input);
        if (result != null) return result;

        result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> l = compute(input.substring(0, i), cache);
                List<Integer> r = compute(input.substring(i + 1), cache);
                for (int a: l) {
                    for (int b: r) {
                        switch (c) {
                            case '+':
                                result.add(a+b);
                                break;
                            case '-':
                                result.add(a-b);
                                break;
                            case '*':
                                result.add(a*b);
                                break;
                        }
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result.add(Integer.valueOf(input));
        }
        cache.put(input, result);
        return result;
    }

    public static void main(String[] args) {
        DifferentWaysToCompute dwtc = new DifferentWaysToCompute();
        PrintUtils.print(dwtc.diffWaysToCompute("2-1-1"));
        PrintUtils.print(dwtc.diffWaysToCompute("2*3-4*5"));
    }
}
