package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/fizz-buzz/
// 412. Fizz Buzz
public class FizzBuzz {
  public List<String> fizzBuzz(int n) {
    List<String> res = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0) {
        res.add(i % 5 == 0 ? "FizzBuzz" : "Fizz");
      } else if (i % 5 == 0) {
        res.add("Buzz");
      } else {
        res.add(String.valueOf(i));
      }
    }
    return res;
  }
}
