package baekjoon.day_03;

import java.io.*;

/**
 * 문자열 - 알파벳 찾기
 * https://www.acmicpc.net/problem/10809
 */
public class Q10809 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        br.close();

        for (char c = 'a'; c <= 'z'; c++) {
            String index = word.indexOf(c) + " ";
            if (c == 'z') {
                index = index.split(" ")[0];
            }
            bw.write(index);
        }

        bw.flush();
        bw.close();
    }
}
