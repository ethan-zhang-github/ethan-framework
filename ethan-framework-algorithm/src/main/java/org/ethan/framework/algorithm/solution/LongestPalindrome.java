package org.ethan.framework.algorithm.solution;

public class LongestPalindrome {

    public String invoke(String s) {
        String res = "";
        for (int i = 0; i < s.length() - 1; i++) {
            String a = spread(s, i, i);
            String b = spread(s, i, i + 1);
            if (a.length() > res.length()) {
                res = a;
            }
            if (b.length() > res.length()) {
                res = b;
            }
        }
        return res;
    }

    private String spread(String s, int l, int r) {
        while (l >=0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        String s = "bbbab";
        LongestPalindrome solution = new LongestPalindrome();
        System.out.println(solution.invoke(s));
    }

}
