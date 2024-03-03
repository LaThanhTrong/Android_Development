package com.labs.lab05;

import java.util.ArrayList;

class StudentManager {
    private static StudentManager instance;
    private ArrayList<Student> listStd;

    private StudentManager() {
        listStd = new ArrayList<>();
    }

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }
    public ArrayList<Student> getListStd() {
        return listStd;
    }
    public void addStudent(Student student) {
        listStd.add(student);
    }

    public void removeStudent(Student student) {
        listStd.remove(student);
    }
}
