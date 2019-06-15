package randy.leetcode;

// https://leetcode-cn.com/problems/design-hashset/
// 705. 设计哈希集合

/**
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 */
public class DesignHashSet {
    class MyHashSet {
        private long[] bits;

        /** Initialize your data structure here. */
        public MyHashSet() {
            bits = new long[(1000000 >> 6) + 1];
        }

        // long注意整型常量值默认是int，位移会导致溢出
        public void add(int key) {
            bits[key >> 6] |= (1L << (key & 0x3F));
        }

        public void remove(int key) {
            bits[key >> 6] &= ~(1L << (key & 0x3F));
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return (bits[key >> 6] & (1L << (key & 0x3F))) != 0;
        }
    }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
}
