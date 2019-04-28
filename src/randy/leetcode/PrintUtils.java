package randy.leetcode;

public class PrintUtils {
    public static void print(int[] a) {
        System.out.print("result:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }
}
