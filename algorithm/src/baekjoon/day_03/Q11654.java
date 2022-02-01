package baekjoon.day_03;

import java.io.*;

/**
 * 문자열 - 아스키 코드
 * https://www.acmicpc.net/problem/11654
 */
public class Q11654 {
    public static void main(String[] args) throws IOException {
        // 모범 답안
        goodAnswer();

        // 나의 코드
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        br.close();

        for (byte b : input.getBytes()) {
            bw.write(b + "");
        }

        bw.flush();
        bw.close();
    }

    public static void goodAnswer() throws IOException {
        System.out.println(System.in.read());
    }
}
