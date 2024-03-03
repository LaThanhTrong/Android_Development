package com.labs.lab05;

import java.util.ArrayList;

class Student {
    String id;
    String cls;
    String name;
    String gender;
    String enStatus;

    public Student(String id, String cls, String name, String gender, String enStatus){
        this.id = id;
        this.cls = cls;
        this.name = name;
        this.gender = gender;
        this.enStatus = enStatus;
    }
    public String getName() {
        return name;
    }
}
