package org.ethan.framework.algorithm.sort;

/**
 * 快排
 * @author Ethan Zhang
 */
public class QuickSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int part = partition(array, start, end);
        quickSort(array, start, part - 1);
        quickSort(array, part + 1, end);
    }

    private int partition(int[] array, int start, int end) {
        int p = start;
        int temp = array[start];
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < temp) {
                p++;
                swap(array, p, i);
            }
        }
        swap(array, start, p);
        return p;
    }

    public static void main(String[] args) {
        int[] array = Sort.randomArray(1000000, 1000000);
        Sort sort = new QuickSort();
        sort.sortLog(array);
    }

}
