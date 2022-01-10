package baekjoon.day_01;

import java.io.*;

public class while_A_B {
    public static void main(String[] args) throws IOException {
        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문자열 Buffer
        StringBuilder sb = new StringBuilder();

        String str;
        while ((str = br.readLine()) != null) {
            int a = str.charAt(0) - 48;
            int b = str.charAt(2) - 48;
            sb.append(a + b).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
