package org.ethan.framework.algorithm.solution;

/**
 * 数值的整数次方
 * @author Ethan Zhang
 */
public class DoublePower {

    public double invoke(double base, int exponent) {
        if (exponent >= 0) {
            return getPower(base, exponent);
        } else {
            return 1 / getPower(base, -exponent);
        }
    }

    private double getPower(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double res = getPower(base, exponent >> 1);
        if ((exponent & 1) == 0) {
            return res * res;
        } else {
            return res * res * base;
        }
    }

    public static void main(String[] args) {
        DoublePower solution = new DoublePower();
        System.out.println(solution.invoke(1.2d, -2));
    }

}
