package baekjoon.day_01;

import java.io.*;

public class 빠른A_B {
    public static void main(String[] args) throws IOException {
        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문자열 버퍼
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String value = br.readLine();
            int a = Integer.parseInt(value.split(" ")[0]);
            int b = Integer.parseInt(value.split(" ")[1]);

            sb.append(a + b);
            if (i < (n - 1)) {
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
