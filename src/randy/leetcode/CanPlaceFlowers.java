package randy.leetcode;

// https://leetcode-cn.com/problems/can-place-flowers/
// 605. 种花问题
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0) return true;
        if (flowerbed.length == 1) return flowerbed[0] == 0;
        int end = flowerbed.length - 1;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) continue;
            if ((i == 0 || flowerbed[i-1] == 0)
                    && (i == end || flowerbed[i+1] == 0)) {
                flowerbed[i] = 1;
                if (--n == 0) return true;
            }
        }
        return false;
    }
}
