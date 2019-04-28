package randy.leetcode;

// https://leetcode-cn.com/problems/long-pressed-name/
public class IsLongPressedName {
    public static void main(String[] args) {
        System.out.println(isLongPressedName("alex", "aaleex"));
        System.out.println(isLongPressedName("saeed", "ssaaedd"));
        System.out.println(isLongPressedName("leelee", "lleeelee"));
        System.out.println(isLongPressedName("laiden", "laiden"));
    }

    public static boolean isLongPressedName(String name, String typed) {
        int nameLen = name.length();
        int typedLen = typed.length();
        if (nameLen == 0) return typedLen == 0;

        int i = 0;
        int j = 0;
        while (i < nameLen && j < typedLen && name.charAt(i) == typed.charAt(j)) {
            for (; i < nameLen && j < typedLen && name.charAt(i) == typed.charAt(j); i++, j++);
            if (i > 0) {
                for (; j < typedLen && name.charAt(i - 1) == typed.charAt(j); j++) ;
            }
        }
        return i == nameLen && j == typedLen;
    }
}
