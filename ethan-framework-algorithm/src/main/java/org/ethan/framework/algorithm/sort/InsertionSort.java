package org.ethan.framework.algorithm.sort;

/**
 * 插入排序
 * @author Ethan Zhang
 */
public class InsertionSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j] > temp; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = Sort.randomArray(100000, 100000);
        Sort sort = new InsertionSort();
        sort.sortLog(array);
    }

}
