package org.ethan.framework.algorithm.solution;

/**
 * 字符串转整数
 * @author Ethan Zhang
 */
public class StrToInt {

    public int invoke(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int res = 0;
        int k = 1;
        for (int i = len - 1; i >= 0; i--) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                res += (c - '0') * k;
                k *= 10;
            } else {
                res = 0;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        StrToInt solution = new StrToInt();
        System.out.println(solution.invoke("123"));
    }

}
