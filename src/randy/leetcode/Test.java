package leetcode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        System.out.println(-4%2);
        /*long bits = optimalNumOfBits(2000_0000, 0.01);
        System.out.println("bits=" + bits + ", m=" + bits / 8 / 1024 / 1024);
        System.out.println("2<<20=" + (2<<26));*/

        //testTime();
    }

    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    static void testTime() {
        LocalDateTime dateTime1 = LocalDateTime.now();
        long mills1 = dateTime1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        LocalDateTime dateTime2 = dateTime1.minusDays(400);
        long mills2 = dateTime2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long mills3 = mills1 - mills2;
        long hourMills = 24 * 3600000;

        System.out.println("mills3=" + mills3 + ", days=" + (mills3 / hourMills) + ", remain=" + (mills3 % hourMills));


        String s1 = "a";
        String s2 = "b";
        System.out.println((s1 + s2).compareTo(s2 + s1));
    }
}
