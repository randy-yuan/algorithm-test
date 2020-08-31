package randy.leetcode;

public class GeekBang {
    public static void moveZero(int[] a) {
        for (int i = 1, j = 0; i < a.length; i++) {
            if (a[i] != a[j]) {
                a[++j] = a[i];
            }
        }
    }

    public static void print(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0)
                System.out.print(',');
            System.out.print(a[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 1, 2, 2, 2, 3, 4, 4, 5};
        moveZero(a);
        print(a);
    }
}
