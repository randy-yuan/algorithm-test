package randy.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/all-oone-data-structure/
 *
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
public class AllOne432 {
    static class Node {
        Node prev;
        Node next;
        int freq;
        Set<String> keys;

        public Node(int freq) {
            this.prev = null;
            this.next = null;
            this.freq = freq;
            this.keys = new HashSet<>();
        }
    }

    private Map<String, Integer> keyMap;
    private Map<Integer, Node> freqMap;
    private Node min;
    private Node max;

    /** Initialize your data structure here. */
    public AllOne432() {
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        min = null;
        max = null;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int freq = keyMap.getOrDefault(key, 0) + 1;
        keyMap.put(key, freq);

        Node n = freqMap.computeIfAbsent(freq, Node::new);
        n.keys.add(key);

        if (freq == 1) {
            if (min == null) {
                min = n;
                max = n;
            } else if (min != n) {
                n.next = min;
                min.prev = n;
                min = n;
            }
        } else {
            Node old = freqMap.get(freq - 1);
            old.keys.remove(key);

            if (old.keys.isEmpty()) {
                n.prev = old.prev;
                n.next = old.next;
                if (old.prev != null) old.prev.next = n;
                if (old.next != null) old.next.prev = n;
                if (min == old) min = n;
            } else {
                n.prev = old;
                n.next = old.next;
                old.next = n;
                if (n.next != null) n.next.prev = n;
            }

            if (max == old) max = n;
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        int freq = keyMap.getOrDefault(key, 0) - 1;
        if (freq < 0) return;

        Node old = freqMap.get(freq + 1);
        old.keys.remove(key);
        if (freq == 0) {
            keyMap.remove(key);
            if (old.keys.isEmpty()) {
                if (old.prev != null) old.prev.next = old.next;
                if (old.next != null) old.next.prev = old.prev;
                if (min == old) min = min.next;
                if (max == old) max = null;
            }
        } else {
            keyMap.put(key, freq);
            Node n = freqMap.computeIfAbsent(freq, Node::new);
            n.keys.add(key);

            if (old.keys.isEmpty()) {
                n.prev = old.prev;
                n.next = old.next;
                if (old.prev != null) old.prev.next = n;
                if (old.next != null) old.next.prev = n;
                if (max == old) max = n;
            } else {
                n.prev = old.prev;
                n.next = old;
                old.prev = n;
                if (n.prev != null) n.prev.next = n;
            }

            if (min == old) min = n;
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (max == null) return "";
        return max.keys.isEmpty() ? "" : max.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (min == null) return "";
        return min.keys.isEmpty() ? "" : min.keys.iterator().next();
    }

    public static void main(String[] args) {
        exec(new String[]{"inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"},
            new String[] {"a","b","b","c","c","c","b","b","","a","",""});

    }

    private static void exec(String[] ops, String[] keys) {
        AllOne432 allOne = new AllOne432();
        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "inc": allOne.inc(keys[i]); break;
                case "dec": allOne.dec(keys[i]); break;
                case "getMaxKey": System.out.println(allOne.getMaxKey()); break;
                case "getMinKey": System.out.println(allOne.getMinKey()); break;
                default: break;
            }
        }
    }
}
