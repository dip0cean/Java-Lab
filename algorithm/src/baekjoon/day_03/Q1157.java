package baekjoon.day_03;

import java.io.*;
import java.util.*;

/**
 * 문자열 - 단어 공부
 * https://www.acmicpc.net/problem/2675
 */
public class Q1157 {

    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        goodCode();
    }

    public static void goodCode() throws IOException {
        // 알파벳 개수만큼의 배열
        int[] alphabets = new int[26];

        // 단어 입력
        String sentence = br.readLine().toUpperCase();
        char[] words = sentence.toCharArray();
        for (char word : words) {
            alphabets[word - 65]++;
        }

        char index = 0; // 최대 카운트 알파벳
        int max = alphabets[index]; // 최대 카운트
        int sameCount = 0; // 최대 카운트 값이 같은 알파벳 개수

        for (char i = 1; i < alphabets.length; i++) {
            if (max < alphabets[i]) {
                index = i;
                max = alphabets[i];
                sameCount = 0;
            } else if (max == alphabets[i]) {
                sameCount++;
            }
        }

        String result = (char) (index + 65) + "";
        if (sameCount > 0) {
            result = "?";
        }

        bw.write(result);
        bw.flush();
        bw.close();
    }

    public static void myCode() throws IOException {
        String input = br.readLine().toUpperCase();
        br.close();

        String[] strArray = input.split("");
        Set<String> strSet = new HashSet<>(Arrays.asList(strArray));
        Map<String, Long> countMap = new HashMap<>(strSet.size());

        for (String word : strSet) {
            countMap.put(word, Arrays.stream(strArray).filter(w -> w.equals(word)).count());
        }

        long maxCount = 0;
        long sameCount = 0;
        String result = "?";
        for (Map.Entry<String, Long> entry : countMap.entrySet()) {
            if (maxCount < entry.getValue()) {
                result = entry.getKey();
                maxCount = entry.getValue();
                sameCount = 0;
            } else if (maxCount == entry.getValue()) {
                sameCount++;
            }
        }

        if (sameCount > 0) {
            result = "?";
        }

        bw.write(result);
        bw.flush();
        bw.close();
    }
}
