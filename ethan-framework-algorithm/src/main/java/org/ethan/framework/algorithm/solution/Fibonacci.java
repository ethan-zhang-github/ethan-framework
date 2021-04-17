package org.ethan.framework.algorithm.solution;

/**
 * 斐波那契数列
 * @author Ethan Zhang
 */
public class Fibonacci {

    private interface Generator {
        int generate(int k);
    }

    /**
     * 循环法
     */
    private static class FirstGenerator implements Generator {
        @Override
        public int generate(int k) {
            if (k == 1 || k == 2) {
                return 1;
            }
            int first = 1;
            int second = 1;
            int cur = 0;
            for (int i = 3; i <= k; i++) {
                cur = first + second;
                first = second;
                second = cur;
            }
            return cur;
        }
    }

    /**
     * 递归法
     */
    private static class SecondGenerator implements Generator {
        @Override
        public int generate(int k) {
            if (k == 1 || k == 2) {
                return 1;
            }
            return generate(k - 1) + generate(k - 2);
        }
    }

    public static void main(String[] args) {
        Generator generator = new SecondGenerator();
        System.out.println(generator.generate(6));
    }

}
