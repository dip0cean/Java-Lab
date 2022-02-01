package baekjoon.day_02;

import java.io.*;

/**
 * 1차원 배열 - 평균
 * https://www.acmicpc.net/problem/1546
 */
public class Q1546 {

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 시험친 과목 개수 N
        int subjects = Integer.parseInt(br.readLine());
        // 각 과목 별 점수
        String[] scores = br.readLine().split(" ");

        int sum = 0; // 과목 별 점수 합
        int max = 0; // 최고점

        for (String score : scores) {
            int intScore = Integer.parseInt(score);
            sum += intScore;
            max = Math.max(intScore, max);
        }

        String avg = ((((double) sum / max) * 100) / subjects) + "";

        bw.write(avg);
        bw.flush();
        br.close();
        bw.close();
    }
}
