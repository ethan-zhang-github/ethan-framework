package org.ethan.framework.algorithm.solution;

/**
 * 有序二维数组中查找元素是否存在
 * @author Ethan Zhang
 */
public class FindInTwoDimensionalArray {

    public boolean invoke(int[][] arr, int target) {
        int len = arr.length;
        int i = len - 1;
        int j = 0;
        while (i >= 0 && j < len) {
            if (arr[i][j] == target) {
                return true;
            } else if (arr[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 5, 9},
                {2, 7, 14},
                {4, 8, 17}
        };
        FindInTwoDimensionalArray solution = new FindInTwoDimensionalArray();
        System.out.println(solution.invoke(arr, 15));
    }

}
