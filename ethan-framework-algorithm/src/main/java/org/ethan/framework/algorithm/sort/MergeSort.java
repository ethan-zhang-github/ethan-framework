package org.ethan.framework.algorithm.sort;

/**
 * 归并排序
 * @author Ethan Zhang
 */
public class MergeSort implements Sort {

    @Override
    public int[] sort(int[] array) {
        sort(array, 0, array.length - 1, new int[array.length]);
        return array;
    }

    private void sort(int[] array, int l, int r, int[] temp) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(array, l, mid, temp);
        sort(array, mid + 1, r, temp);
        merge(array, l, mid, r, temp);
    }

    private void merge(int[] array, int l, int mid, int r, int[] temp) {
        int i = l;
        int j = mid + 1;
        int k = l;
        while (i <= mid || j <= r) {
            if (i > mid) {
                temp[k++] = array[j++];
            } else if (j > r) {
                temp[k++] = array[i++];
            } else if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        for (int t = l; t <= r; t++) {
            array[t] = temp[t];
        }
    }

    public static void main(String[] args) {
        int[] array = Sort.randomArray(10000, 10000);
        Sort sort = new MergeSort();
        sort.sortLog(array);
    }

}
