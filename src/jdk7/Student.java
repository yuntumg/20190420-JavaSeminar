package jdk7;

import java.util.Date;

public class Student {

    /** 学年 */
    public String grade;

    /** クラス番号 */
    public Integer classNo;

    /** 生徒番号 */
    public String studentId;

    /** 生徒名 */
    public String studentNm;

    /** 誕生日 */
    public Date birthday;

    /** 得点１（数学） */
    public Integer scoreMath;
    
    /** 得点２（国語） */
    public Integer scoreLanguage;
    

    public Student() {
    }

    public Student(
            String grade, 
            Integer classNo, 
            String studentId,
            String studentNm, 
            Date birthday, 
            Integer scoreMath, 
            Integer scoreLanguage) {
        this.grade = grade;
        this.classNo = classNo;
        this.studentId = studentId;
        this.studentNm = studentNm;
        this.birthday = birthday;
        this.scoreMath = scoreMath;
        this.scoreLanguage = scoreLanguage;
    }

    @Override
    public String toString() {
        return "Student [grade=" + grade + ", classNo=" + classNo
                + ", studentId=" + studentId + ", studentNm=" + studentNm
                + ", birthday=" + birthday + ", scoreMath=" + scoreMath
                + ", scoreLanguage=" + scoreLanguage + "]";
    }

}