package baekjoon.day_03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 문자열 - 단어의 개수
 * https://www.acmicpc.net/problem/2675
 */
public class Q1152 {

    public final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        goodCode();
    }

    public static void goodCode() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        bw.write(st.countTokens() + "");
        bw.flush();
        bw.close();
    }

    public static void myCode() throws IOException {
        String sentence = br.readLine();
        br.close();

        String[] words = sentence.split(" ");

        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            if (!word.equals("")) {
                int count = 1;
                if (countMap.get(word) != null) {
                    count = countMap.get(word) + 1;
                }
                countMap.put(word, count);
            }
        }

        bw.write(countMap.values().stream().mapToInt(i -> i).sum() + "");
        bw.flush();
        bw.close();
    }
}
