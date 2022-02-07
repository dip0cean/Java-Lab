package baekjoon.day_04;

import java.io.*;
import java.util.Arrays;

/**
 * 문자열 - 크로아티아 알파벳
 * https://www.acmicpc.net/problem/2941
 */
public class Q2941 {
    public static void main(String[] args) throws IOException {
        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder  sb = new StringBuilder(br.readLine());
        br.close();

        int count = 0;
        for (String alphabet : croatia) {
            while (sb.indexOf(alphabet) > -1) {
                int start = sb.indexOf(alphabet);
                int end   = start + alphabet.length();
                sb.replace(start, end, " ");
                count++;
            }
        }

        count += Arrays.stream(sb.toString().trim().split("")).filter(result -> !result.equals(" ") && !result.equals("")).count();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(count + "");
        bw.flush();
        bw.close();
    }
}
