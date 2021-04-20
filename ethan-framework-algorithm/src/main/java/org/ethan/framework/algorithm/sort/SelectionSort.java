package org.ethan.framework.algorithm.sort;

/**
 * 选择排序
 * @author Ethan Zhang
 */
public class SelectionSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            swap(array, i, k);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = Sort.randomArray(100000, 100000);
        Sort sort = new SelectionSort();
        sort.sortLog(array);
    }

}
