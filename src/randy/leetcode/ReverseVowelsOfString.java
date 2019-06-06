package randy.leetcode;

// https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
// 345. 反转字符串中的元音字母
public class ReverseVowelsOfString {
  public static String reverseVowels(String s) {
    char[] ca = s.toCharArray();
    int i = 0;
    int j = ca.length - 1;
    while (i < j) {
      for (; i < j && !isVowel(ca[i]); i++);
      for (; i < j && !isVowel(ca[j]); j--);
      char c = ca[i];
      ca[i++] = ca[j];
      ca[j--] = c;
    }

    return String.valueOf(ca);
  }

  private static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
        || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
  }

  public static void main(String[] args) {
    System.out.println(reverseVowels("hello"));
  }
}
