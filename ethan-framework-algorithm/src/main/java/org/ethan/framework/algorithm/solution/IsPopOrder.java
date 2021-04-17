package org.ethan.framework.algorithm.solution;

import java.util.Stack;

/**
 * 栈的压入,弹出序列
 * @author Ethan Zhang
 */
public class IsPopOrder {

    public boolean invoke(int[] push, int[] pop) {
        int i = 0;
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < push.length) {
            stack.push(push[i]);
            i++;
            while (!stack.isEmpty() && stack.peek() == pop[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        IsPopOrder solution = new IsPopOrder();
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 5, 3, 2, 1};
        System.out.println(solution.invoke(push, pop));
    }

}
