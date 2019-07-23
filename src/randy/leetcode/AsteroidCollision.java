package randy.leetcode;

// https://leetcode-cn.com/problems/asteroid-collision/
// 735. 行星碰撞
public class AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length < 2)
            return asteroids;

        int[] stk = new int[asteroids.length];
        int top = -1;
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] < 0) {
                boolean exploded = false;
                int v = -asteroids[i];
                while (top != -1 && stk[top] > 0) {
                    if (v < stk[top]) {
                        exploded = true;
                        break;
                    } else if (v == stk[top]) {
                        exploded = true;
                        top--;
                        break;
                    } else {
                        top--;
                    }
                }
                if (!exploded)
                    stk[++top] = asteroids[i];
            } else {
                stk[++top] = asteroids[i];
            }
        }

        int[] r = new int[top + 1];
        System.arraycopy(stk, 0, r, 0, top+1);
        return r;
    }

    public static void main(String[] args) {
        PrintUtils.print(asteroidCollision(new int[] {5, 10, -5}));
        PrintUtils.print(asteroidCollision(new int[] {8, -8}));
        PrintUtils.print(asteroidCollision(new int[] {10, 2, -5}));
        PrintUtils.print(asteroidCollision(new int[] {-2, -1, 1, 2}));
    }
}
