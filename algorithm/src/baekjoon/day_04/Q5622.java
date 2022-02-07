package baekjoon.day_04;

import java.io.*;

/**
 * 문자열 - 다이얼
 * https://www.acmicpc.net/problem/5622
 */
public class Q5622 {
    public static void main(String[] args) throws IOException {
        // A : 65 / Z : 90
        BufferedReader br        = new BufferedReader(new InputStreamReader(System.in));
        String[]       alphabets = br.readLine().split("");
        int            time      = 0;

        for (String alphabet : alphabets) {
            int word = alphabet.charAt(0);
            if (65 <= word && word <= 67) time += 3;
            else if (68 <= word && word <= 70) time += 4;
            else if (71 <= word && word <= 73) time += 5;
            else if (74 <= word && word <= 76) time += 6;
            else if (77 <= word && word <= 79) time += 7;
            else if (80 <= word && word <= 83) time += 8;
            else if (84 <= word && word <= 86) time += 9;
            else time += 10;
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(time + "");
        bw.flush();
        bw.close();
    }
}
