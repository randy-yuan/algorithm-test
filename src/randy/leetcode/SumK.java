package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SumK {
    public static void main(String[] args) {

        int k = 4;

        List<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(5);
        a.add(3);
        a.add(2);

        int count = a.size();

        //System.out.println(compute( a, count,k));

        System.out.println(dfs(new int[]{0,5,2,2}, 0, 4));
    }

    public static Boolean  compute(List<Integer> a,int count,int sum)
    {
        System.out.println("count=" + count + ", sum=" + sum);
        if(count == 1)
        {
            return sum  == a.get(0);
        }

        return compute(a,count-1,sum) || compute(a,count-1,sum-a.get(a.size()-1));
    }

    public static boolean dfs(int[] a, int i, int sum) {
        if (a[i] == sum) return true;

        if (i == (a.length - 1)) return false;

        if (dfs(a, i + 1, sum - a[i])) return true;

        return dfs(a, i + 1, sum);
    }

}
