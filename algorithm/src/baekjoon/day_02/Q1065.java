package baekjoon.day_02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 함수 - 한수
 * https://www.acmicpc.net/problem/1065
 */
public class Q1065 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (cal(i)) {
                count++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    public static boolean cal(int n) {
        List<Integer> values = new ArrayList<>();
        int tmp = n;
        for (int i = 1000; i > 0; i = i / 10) {
            int value = tmp / i;
            if (values.size() == 0 && value == 0) {
                continue;
            }
            values.add(value);
            tmp = tmp % i;
        }

        if (values.size() > 2) {
            int gap = values.get(values.size() - 1) - values.get(values.size() - 2);
            for (int i = values.size() - 2; i > -1; i--) {
                int pi = i - 1;
                if (pi > -1) {
                    int thisGap = values.get(i) - values.get(pi);
                    if (gap != thisGap) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
