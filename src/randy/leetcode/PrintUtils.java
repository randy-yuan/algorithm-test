package randy.leetcode;

import java.util.List;

public class PrintUtils {
    public static void print(int[] a) {
        System.out.print("result:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }

    public static void print(List<?> list) {
        for (Object obj: list) {
            System.out.println(obj);
        }
    }

    public static void print(Object[] a) {
        for (Object obj: a) {
            System.out.println(obj);
        }
    }
}
