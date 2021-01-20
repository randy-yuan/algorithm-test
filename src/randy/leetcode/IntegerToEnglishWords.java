package leetcode;

// https://leetcode-cn.com/problems/integer-to-english-words/
public class IntegerToEnglishWords {
    private String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] sep = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num < 0) return "Negative";
        if (num == 0) return "Zero";

        int[] threes = new int[10];
        int i = 0;
        while (num > 0) {
            threes[i++] = num % 1000;
            num /= 1000;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = i - 1; j >= 0; j--) {
            if (threes[j] > 0) {
                sb.append(translateThree(threes[j]))
                .append(" ").append(sep[j]).append(" ");
            }
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String translateThree(int n) {
        StringBuilder sb = new StringBuilder();
        int h = n / 100;
        int t = n % 100;
        if (h > 0) {
            sb.append(lessThan20[h]).append(" Hundred");
        }
        if (t > 0) {
            if (h > 0) {
                sb.append(" ");
            }
            if (t < 20) {
                sb.append(lessThan20[t]);
            } else {
                sb.append(tens[t / 10]);
                if (t % 10 != 0) {
                    sb.append(" ").append(lessThan20[t % 10]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToEnglishWords englishWords = new IntegerToEnglishWords();
        System.out.println(englishWords.numberToWords(1234567891));
    }
}
