package randy.leetcode;

// https://leetcode-cn.com/problems/student-attendance-record-i/
// 551. 学生出勤记录 I
public class StudentAttendanceRecord1 {
    public boolean checkRecord(String s) {
        int absent = 0;
        int maxLate = 0;
        int late = 0;
        char[] sa = s.toCharArray();
        for (char c: sa) {
            if (c == 'L') {
                late++;
            } else {
                if (c == 'A') absent++;
                if (late > 0) {
                    if (late > maxLate) maxLate = late;
                    late = 0;
                }
            }
        }
        if (late > maxLate) maxLate = late;
        return absent <= 1 && maxLate <= 2;
    }
}
