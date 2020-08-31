package randy.leetcode;

import java.util.Map;
import java.util.TreeMap;

// https://leetcode-cn.com/problems/lemonade-change/
// 860. 柠檬水找零
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        if (bills.length == 0) return true;

        TreeMap<Integer, Integer> money = new TreeMap<>();
        for (int b: bills) {
            if (b > 5) {
                int c = b - 5;
                while (c > 0) {
                    Map.Entry<Integer, Integer> e = money.floorEntry(c);
                    if (e == null) {
                        return false;
                    }
                    if (e.getValue() > 1) {
                        money.put(e.getKey(), e.getValue() - 1);
                    } else {
                        money.remove(e.getKey());
                    }
                    c -= e.getKey();
                }
            }
            money.put(b, money.getOrDefault(b, 0) + 1);
        }

        return true;
    }

    public boolean lemonadeChange2(int[] bills) {
        if (bills.length == 0) return true;

        int[] count = new int[21];
        for (int b: bills) {
            if (b > 5) {
                int c = b - 5;
                while (c > 0) {
                    if (c >= 10 && count[10] > 0) {
                        count[10]--;
                        c -= 10;
                    } else if (count[5] > 0) {
                        count[5]--;
                        c -= 5;
                    } else {
                        return false;
                    }
                }
            }
            count[b]++;
        }

        return true;
    }

    public boolean lemonadeChange3(int[] bills) {
        if (bills.length == 0) return true;

        int five = 0;
        int ten = 0;
        for (int b: bills) {
            if (b == 5) {
                five++;
            } else if (b == 10) {
                if (five > 0) {
                    ten++;
                    five--;
                } else {
                    return false;
                }
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LemonadeChange test = new LemonadeChange();
        System.out.println(test.lemonadeChange(new int[]{5,5,5,10,20}));
    }
}
