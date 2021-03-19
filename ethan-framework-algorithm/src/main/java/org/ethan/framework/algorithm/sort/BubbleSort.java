package org.ethan.framework.algorithm.sort;

public class BubbleSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        int lastExchangeIndex = 0;
        int sortBorder = array.length - 1;
        for (int i = 1; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = Sort.randomArray(10000, 10000);
        Sort sort = new BubbleSort();
        sort.sortLog(array);
    }

}
