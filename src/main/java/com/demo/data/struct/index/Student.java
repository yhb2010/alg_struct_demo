package com.demo.data.struct.index;

public class Student implements Comparable {

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int no;
    public String name;

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        if (no < other.no) //这里比较的是什么 sort方法实现的就是按照此比较的东西从小到大排列
            return -1;
        if (no > other.no)
            return 1;
        return 0;
    }

}