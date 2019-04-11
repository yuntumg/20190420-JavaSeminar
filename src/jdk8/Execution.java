package jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tool.PrintTool;

public class Execution {

    private static List<Student> read(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
         // 学年,クラス番号,生徒番号,生徒名,誕生日,科目,得点
         return stream
                 .filter(line -> !line.startsWith("#"))
                 .map(line -> {
                     String[] s = line.split(",");
                     Student student = new Student();
                     student.grade = s[0];
                     student.classNo = Integer.valueOf(s[1]);
                     student.studentId = s[2];
                     student.studentNm = s[3];
                     student.birthday = LocalDate.parse(s[4]);
                     student.scoreMath = Integer.valueOf(s[5]);
                     student.scoreLanguage = Integer.valueOf(s[6]);
                     return student;
                 }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("failed to read file.", e);
        }
    }

    public void usecase1(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("数学の合計得点");
        int sumMath = list.stream().mapToInt(student -> student.scoreMath.intValue()).sum();
        PrintTool.printContent(sumMath);
    }

    public void usecase2(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("6月誕生日の人");
        list.stream()
            .filter(student -> student.birthday.getMonth() == Month.JUNE)
            .collect(Collectors.toList())
            .forEach(PrintTool::printContent);
    }
    
    public void usecase3(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("全員の詳細情報");
        list.forEach(PrintTool::printContent);
    }
    
    public void usecase4(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("クラス単位の英語の平均得点");
        Map<String, List<Student>> groupByInfo = list.stream()
                        .collect(Collectors.groupingBy(s -> s.grade));
        groupByInfo.keySet().forEach(grade ->
                        PrintTool.printContent(
                        		grade + ":" + groupByInfo.get(grade).stream()
                                .mapToInt(student -> student.scoreLanguage).average().getAsDouble())
        );
    }

    public static void main(String[] args) {
        Execution execution = new Execution();
        String fileName = System.getProperty("user.dir") + "/src/data.csv";

        execution.usecase1(fileName);
        execution.usecase2(fileName);
        execution.usecase3(fileName);
        execution.usecase4(fileName);
    }
}