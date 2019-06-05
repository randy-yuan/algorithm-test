package randy.leetcode;

// https://leetcode-cn.com/problems/excel-sheet-column-title/
// 168. Excel表列名称
public class ExcelColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        int m;
        while (n > 0) {
            m = n % 26;
            if (m == 0) m = 26;
            sb.append((char)('A' + m - 1));
            n = (n - m) / 26;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        ExcelColumnTitle excelColumnTitle = new ExcelColumnTitle();
        System.out.println(excelColumnTitle.convertToTitle(0));
        System.out.println(excelColumnTitle.convertToTitle(1));
        System.out.println(excelColumnTitle.convertToTitle(25));
        System.out.println(excelColumnTitle.convertToTitle(26));
        System.out.println(excelColumnTitle.convertToTitle(27));
        System.out.println(excelColumnTitle.convertToTitle(28));
        System.out.println(excelColumnTitle.convertToTitle(701));
    }
}
