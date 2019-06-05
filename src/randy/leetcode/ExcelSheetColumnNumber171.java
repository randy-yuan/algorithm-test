package randy.leetcode;

// https://leetcode-cn.com/problems/excel-sheet-column-number/
// 171. Excel表列序号
public class ExcelSheetColumnNumber171 {
  public static int titleToNumber(String s) {
    int number = 0;
    for (int i = 0; i < s.length(); i++) {
      number = number * 26 + s.charAt(i) - 'A' + 1;
    }
    return number;
  }

  public static void main(String[] args) {
    System.out.println(titleToNumber("A"));
    System.out.println(titleToNumber("B"));
    System.out.println(titleToNumber("Y"));
    System.out.println(titleToNumber("Z"));
    System.out.println(titleToNumber("AA"));
    System.out.println(titleToNumber("AB"));
    System.out.println(titleToNumber("ZY"));
  }
}
