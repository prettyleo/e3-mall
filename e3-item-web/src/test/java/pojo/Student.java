package pojo;

import lombok.Data;

/**
 * FileName: Student
 * DESCRIPTION:
 *
 * @author: SLY
 * @create: 2019/2/5
 */
@Data
public class Student {

    private String name;
    private String num;
    private String clazz;
    private Double score;

    public Student() {
    }

    public Student(String name, String num, String clazz, Double score) {
        this.name = name;
        this.num = num;
        this.clazz = clazz;
        this.score = score;
    }
}
