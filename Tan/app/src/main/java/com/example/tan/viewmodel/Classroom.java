package com.example.tan.viewmodel;

public class Classroom {
    private String className;

    private String classCount;

    public Classroom(String className, String classCount) {
        this.className = className;
        this.classCount = classCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }
}
