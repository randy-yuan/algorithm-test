package randy.leetcode;

// https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
// 33. 搜索旋转排序数组
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        //return search1(nums, 0, nums.length - 1, target);
        return binarySearch(nums, target);
    }

    // 针对螺旋数组的查找
    public int search1(int[] nums, int s, int e, int t) {
        if (e < s) return -1;
        int m = s + (e - s) / 2;
        if (nums[m] == t) return m;
        // 如果m在大的一部分，则前半部分必定是升序
        if (nums[m] >= nums[s]) {
            // 在前半部分，必定升序
            if (nums[s] <= t && nums[m] > t)
                return search2(nums, s, m - 1, t);
            // 在后半部分，必定螺旋
            return search1(nums, m + 1, e, t);
        }
        // m在小的一部分，则后半部分必定是升序
        // 在后半部分, 升序
        if (nums[m] < t && nums[e] >= t)
            return search2(nums, m + 1, e, t);
        // 在前半部分，螺旋
        return search1(nums, s, m - 1, t);
    }

    //针对升序数组的查找
    public int search2(int[] nums, int s, int e, int t) {
        int m;
        while (s <= e) {
            m = s + (e - s) / 2;
            if (nums[m] == t) return m;
            else if (nums[m] < t) s = m + 1;
            else e = m - 1;
        }
        return -1;
    }

    // 直接二分查找 ＋ 判断生序部分
    public int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;

            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
