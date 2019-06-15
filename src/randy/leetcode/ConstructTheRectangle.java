package randy.leetcode;

// https://leetcode-cn.com/problems/construct-the-rectangle/
// 492. 构造矩形
public class ConstructTheRectangle {
  public int[] constructRectangle(int area) {
    int i = (int)Math.sqrt(area);
    for (; i >= 1 && (area % i) != 0; i--);
    return new int[]{area / i, i};
  }
}
