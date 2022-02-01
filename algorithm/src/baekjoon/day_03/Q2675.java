package baekjoon.day_03;

import java.io.*;

/**
 * 문자열 - 문자열 반복
 * https://www.acmicpc.net/problem/2675
 */
public class Q2675 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(br.readLine());
        for (int i = 0; i < rows; i++) {
            String[] arr = br.readLine().split(" ");
            for (String str : arr[1].split("")) {
                for (int j = 0; j < Integer.parseInt(arr[0]); j++) {
                    bw.write(str);
                }
            }
            if (i != (rows - 1)) {
                bw.write("\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
