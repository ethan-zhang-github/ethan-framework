package org.ethan.framework.algorithm.solution;

import java.util.Arrays;

/**
 * 调整数组顺序使奇数位于偶数前面
 * @author Ethan Zhang
 */
public class ReOrderArray {

    public int[] invoke(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];
        int l = 0;
        int r = len - 1;
        int rl = 0;
        int rr = len - 1;
        while (l < len) {
            if ((arr[l] & 1) == 1) {
                res[rl++] = arr[l];
            }
            if ((arr[r] & 1) == 0) {
                res[rr--] = arr[r];
            }
            l++;
            r--;
        }
        return res;
    }

    public static void main(String[] args) {
        ReOrderArray solution = new ReOrderArray();
        int[] res = solution.invoke(new int[]{5, 7, 8, 9, 12, 14, 16, 23, 27, 82});
        System.out.println(Arrays.toString(res));
    }

}
