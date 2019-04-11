package jdk7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tool.PrintTool;

public class Execution {

    private static List<Student> read(String fileName) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Student> list = new ArrayList<>();
        
        try (BufferedReader reader = 
                Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                
                if (line.startsWith("#")) {
                    continue;
                }
                
                String[] s = line.split(",");
                // 学年,クラス番号,生徒番号,生徒名,誕生日,科目,得点
                Student student = new Student();
                student.grade = s[0];
                student.classNo = Integer.valueOf(s[1]);
                student.studentId = s[2];
                student.studentNm = s[3];
                student.birthday = df.parse(s[4]);
                student.scoreMath = Integer.valueOf(s[5]);
                student.scoreLanguage = Integer.valueOf(s[6]);
                list.add(student);
            }

        } catch (ParseException | IOException e) {
            throw new RuntimeException("failed to parse date or read file.", e);
        }
        return list;
    }

    public void usecase1(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("数学の合計得点");
        int sumMath = 0;
        for (Student student : list) {
            sumMath += student.scoreMath;
        }
        PrintTool.printContent(sumMath);
    }

    public void usecase2(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("6月誕生日の人");
        for (Student student : list) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(student.birthday);
            if (cal.get(Calendar.MONTH) == 5) {
            	PrintTool.printContent(student.studentNm + "," + student.birthday);
            }
        }
    }
    
    public void usecase3(String fileName) {
        List<Student> list = read(fileName);
        PrintTool.printTitle("全員の詳細情報");
        for (Student student : list) {
            PrintTool.printContent(student);
        }
    }

    public static void main(String[] args) {
        Execution execution = new Execution();
        String fileName = System.getProperty("user.dir") + "/src/data.csv";

        execution.usecase1(fileName);
        execution.usecase2(fileName);
        execution.usecase3(fileName);
    }
}