package randy.leetcode;

// https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
// 81. 搜索旋转排序数组 II
public class SearchInRotatedSortedArray2 {

    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;

            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < nums[end]) {
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[mid] == nums[start]) {
                start++;
            } else {
                end--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2,5,6,0,0,1,2};
        int[] b = {1, 3, 1, 1, 1};
        int[] c = {3, 1};
        SearchInRotatedSortedArray2 searchArray = new SearchInRotatedSortedArray2();
        System.out.println(searchArray.search(a, 0));
        System.out.println(searchArray.search(a, 3));
        System.out.println(searchArray.search(b, 3));
        System.out.println(searchArray.search(c, 1));
    }
}
