package jdk5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static tool.PrintTool.printTitle;
import static tool.PrintTool.printContent;

public class Execution {

    private static List<Student> read(String fileName) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Student> list = new ArrayList<Student>();
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));

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

        } catch (ParseException e) {
            throw new RuntimeException("failed to parse date", e);
        } catch (IOException e) {
            throw new RuntimeException("failed to read file: " + fileName, e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // ignoer
            }
        }
        return list;
    }

    public void usecase1(String fileName) {
        List<Student> list = read(fileName);
        printTitle("数学の合計得点");
        int sumMath = 0;
        for (Student student : list) {
            sumMath += student.scoreMath;
        }
        printContent(sumMath);
    }

    public void usecase2(String fileName) {
        List<Student> list = read(fileName);
        printTitle("6月誕生日の人");
        for (Student student : list) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(student.birthday);
            if (cal.get(Calendar.MONTH) == 5) {
            	printContent(student.studentNm + "," + student.birthday);
            }
        }
    }
    
    public void usecase3(String fileName) {
        List<Student> list = read(fileName);
        printTitle("全員の詳細情報");
        for (Student student : list) {
            printContent(student);
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