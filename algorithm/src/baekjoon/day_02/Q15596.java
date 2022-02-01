package baekjoon.day_02;

/**
 * 함수 - 정수 N 개의 합 > 함수 구현 문제
 * https://www.acmicpc.net/problem/15596
 */
public class Q15596 {

    public static void main(String[] args) {
        System.out.println(Q15596.sum(new int[]{0, 1, 2, 3}));
    }

    public static long sum(int[] a) {
        long result = 0;
        for (int score : a) {
            result += score;
        }

        return result;
    }
}
