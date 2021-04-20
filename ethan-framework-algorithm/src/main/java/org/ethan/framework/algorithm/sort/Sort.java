package org.ethan.framework.algorithm.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public interface Sort {

    int[] sort(int[] array);

    default void sortLog(int[] array) {
        Instant start = Instant.now();
        int[] res = sort(array);
        Instant end = Instant.now();
        if (isSorted(res)) {
            System.out.printf("排序耗时：%s 毫秒%n", Duration.between(start, end).toMillis());
        } else {
            System.out.println("排序失败");
        }
        System.out.println("排序结果：" + Arrays.toString(res));
    }

    default boolean isSorted(int[] array) {
        return IntStream.range(0, array.length - 1).allMatch(i -> array[i] <= array[i + 1]);
    }

    default void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    Random random = new Random();

    static int[] randomArray(int bound, int length) {
        return IntStream.generate(() -> random.nextInt(bound)).limit(length).toArray();
    }

}
