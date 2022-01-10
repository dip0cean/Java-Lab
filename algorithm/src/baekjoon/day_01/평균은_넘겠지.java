package baekjoon.day_01;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 평균은_넘겠지 {
    public static void main(String[] args) throws IOException {
        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문자열 Builder
        StringBuilder sb = new StringBuilder();

        // 학급 개수
        int classes = Integer.parseInt(br.readLine());
        // 학급 개수만큼 반복
        for (int i = 0; i < classes; i++) {
            String input = br.readLine();
            // 0 번째 인덱스는 학생수 N, 나머지 인덱스는 학생 별 점수
            String[] arr = input.split(" ");
            // 학생수
            int count = Integer.parseInt(arr[0]);
            // 각 학생 별 점수 리스트
            List<Integer> students = IntStream.range(1, arr.length).mapToObj(index -> Integer.parseInt(arr[index])).collect(Collectors.toList());
            // 해당 학급의 평균 점수
            BigDecimal avgScore = new BigDecimal(students.stream().reduce(Integer::sum).orElse(0)).divide(BigDecimal.valueOf(count), 3, RoundingMode.HALF_UP);
            // 평균점수를 넘은 학생 수
            long overScoreStudentCount = students.stream().filter(score -> score > avgScore.intValue()).count();
            // 결과
            sb.append(new BigDecimal(overScoreStudentCount).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(count), 3, RoundingMode.HALF_UP)).append("%\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
