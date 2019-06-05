package randy.leetcode;

// https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/
// 405. 数字转换为十六进制数
public class ConvertNumberToHex {
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] buf = new char[8];
        int i = 7;
        int h;
        while (num != 0) {
            h = num & 0xf;
            num >>>= 4;
            if (h < 10) {
                buf[i--] = (char)(h + '0');
            } else {
                buf[i--] = (char)(h - 10 + 'a');
            }
        }
        return String.valueOf(buf, i + 1, 7 - i);
    }
}
