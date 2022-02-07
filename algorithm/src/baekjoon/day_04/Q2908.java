package baekjoon.day_04;

import java.io.*;

/**
 * 문자열 - 상수
 * https://www.acmicpc.net/problem/2908
 */
public class Q2908 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        br.close();
        
        int[] reverseNumbers = new int[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            reverseNumbers[i] = Integer.parseInt(new StringBuffer(inputs[i]).reverse().toString());
        }

        bw.write(Math.max(reverseNumbers[0], reverseNumbers[1]) + "");
        bw.flush();
        bw.close();
    }
}
