package org.ethan.framework.algorithm.sort;

public class HeapSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            adjustDown(array, i, array.length - 1);
        }
        for (int i = 0; i < array.length; i++) {
            adjustDown(array, 0, array.length - 1 - i);
            swap(array, 0, array.length - 1 - i);
        }
        return array;
    }

    private void adjustDown(int[] array, int start, int end) {
        int temp = array[start];
        int parent = start;
        int child =  2 * parent + 1;
        while (child <= end) {
            if (child + 1 <= end && array[child] < array[child + 1]) {
                child = child + 1;
            }
            if (array[child] > temp) {
                array[parent] = array[child];
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
        array[parent] = temp;
    }

    public static void main(String[] args) {
        int[] array = Sort.randomArray(1000000, 1000000);
        Sort sort = new HeapSort();
        sort.sortLog(array);
    }

}
