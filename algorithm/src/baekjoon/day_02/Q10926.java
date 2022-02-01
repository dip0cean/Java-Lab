package baekjoon.day_02;

import java.io.*;

/**
 * ??! 문제
 * https://www.acmicpc.net/problem/10926
 */
public class Q10926 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        br.close();
        bw.write(str + "??!");
        bw.flush();
        bw.close();
    }
}
