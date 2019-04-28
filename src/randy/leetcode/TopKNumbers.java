package randy.leetcode;

public class TopKNumbers {

    public static void main(String[] args) {
        TopKNumbers topk = new TopKNumbers();
        int[] a = {2,5,7,10,3,1,19,4,13,50};

        int[] r = topk.heapK(a, 3);
        topk.print(r);

        r = topk.partitionK(a, 3);
        topk.print(r);
    }

    public void print(int[] a) {
        System.out.print("result:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }

    public int[] heapK(int[] a, int k) {
        if (a == null || k <= 0) {
            return null;
        }
        if (a.length < k) {
            return a;
        }

        int[] h = makeHeap(a, 0, k);
        for (int i = k; i < a.length; i++) {
            if (a[i] > h[0]) {
                adjustHeap(h, a[i]);
            }
        }

        return h;
    }

    public int[] makeHeap(int[] a, int from, int to) {
        if (from < 0 || to > a.length || from >= to) {
            throw new IndexOutOfBoundsException();
        }

        int[] h = new int[to - from];
        for (int i = 0, j = from; j < to; i++, j++) {
            h[i] = a[j];
            int k = i;
            int p = (k - 1) / 2;
            while (k > 0 && h[k] < h[p]) {
                int tmp = h[p];
                h[p] = h[k];
                h[k] = tmp;
                k = p;
                p = (k - 1) / 2;
            }
        }

        return h;
    }

    public void adjustHeap(int[] h, int n) {
        h[0] = n;
        int i = 0;
        int left = 1;
        int right;
        int idx;
        while (left < h.length) {
            right = left + 1;
            idx = right < h.length && h[right] < h[left] ? right : left;
            if (h[idx] < h[i]) {
                int tmp = h[i];
                h[i] = h[idx];
                h[idx] = tmp;
                i = idx;
                left = i * 2 + 1;
            } else {
                break;
            }
        }
    }

    public int[] partitionK(int[] a, int k) {
        if (a == null || k <= 0) {
            return null;
        }
        if (a.length < k) {
            return a;
        }

        partition(a, 0, a.length, k);
        int[] r = new int[k];
        for (int i = 0; i < k; i++) {
           r[i] = a[i];
        }

        return r;
    }

    public int partition(int[] a, int begin, int end, int k) {
        int n = a[begin];
        int i = begin;
        int j = end - 1;
        for (; i < j; ) {
            for (; i < j && a[j] <= n; j--);
            a[i] = a[j];

            for (; i < j && a[i] >= n; i++);
            a[j] = a[i];
        }
        a[i] = n;

        int count = i - begin + 1;
        if (count > k) {
            return partition(a, begin, i, k);
        } else if (count < k) {
            return partition(a, i + 1, end, k - count);
        }
        return i;
    }
}

