package baekjoon.day_02;

import java.io.*;

/**
 * 1998년생인 내가 태국에서는 2541년생?!
 * https://www.acmicpc.net/problem/18108
 */
public class Q18108 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(br.readLine());
        String result = input - 543 + "";
        br.close();
        bw.write(result);
        bw.flush();
        bw.close();
    }
}
