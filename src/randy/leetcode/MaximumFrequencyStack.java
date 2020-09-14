package randy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 895 https://leetcode-cn.com/problems/maximum-frequency-stack/
public class MaximumFrequencyStack {
    private Map<Integer, Integer> valueMap;
    private Map<Integer, List<Integer>> freqMap;
    private int maxFreq;

    public MaximumFrequencyStack() {
        valueMap = new HashMap<>();
        freqMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int freq = valueMap.getOrDefault(x, 0) + 1;
        valueMap.put(x, freq);
        freqMap.computeIfAbsent(freq, k -> new ArrayList<>()).add(x);
        if (freq > maxFreq) maxFreq = freq;
    }

    public int pop() {
        List<Integer> values = freqMap.get(maxFreq);
        if (values == null || values.isEmpty()) return 0;

        int x = values.remove(values.size() - 1);
        valueMap.put(x, valueMap.get(x) - 1);

        if (values.isEmpty()) maxFreq--;

        return x;
    }
}
