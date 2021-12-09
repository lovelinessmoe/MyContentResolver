package xyz.javaee.mycontentresolver;

import java.io.Serializable;

public class Student implements Serializable {

    private String stuNum;
    private String name;
    /**
     * 1男
     * 0女
     */
    private int sex;
    private String classRoom;

    public Student() {
    }

    public Student(String name, String stuNum, int sex, String classRoom) {
        this.name = name;
        this.stuNum = stuNum;
        this.sex = sex;
        this.classRoom = classRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
