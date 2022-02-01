package baekjoon.day_02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 함수 - 셀프 넘버
 * https://www.acmicpc.net/problem/4673
 */
public class Q4673 {
    public static void main(String[] args) throws IOException {

        boolean[] result = new boolean[10001];
        for (int i = 1; i <= result.length; i++) {
            int isSelfNumber = selfNumber(i);
            if (isSelfNumber < result.length && !result[isSelfNumber]) {
                result[isSelfNumber] = true;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < result.length; i++){
            if (!result[i]) {
                bw.write(i + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    public static int selfNumber(int score) {
        int tmp = score;

        for (int i = 10000; i > 0; i = i / 10) {
            score += tmp / i;
            tmp = tmp % i;
        }

        return score;
    }
}
