public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        
        int[] right = new int[nums.length];
        right[nums.length-1] = 1;
        for (int i = nums.length-2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        
        for(int i = 0; i < nums.length; i++) {
            nums[i] = left[i] * right[i];
        }
        
        return nums;
    }
}

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if (nums == null || nums.length == 0) return result;
        
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i-1] * nums[i-1];
        }
        
        int right = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            result[i] = result[i] * right;
            right = right * nums[i];
        }
        
        return result;
    }
}