package baekjoon.day_03;

import java.io.*;

/**
 * 문자열 - 숫자의 합
 * https://www.acmicpc.net/problem/11720
 */
public class Q11720 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split("");

        int result = 0;
        for (String n : numbers) {
            result += Integer.parseInt(n);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
