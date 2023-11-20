package com.example.testonscrollview.Model;

import java.io.Serializable;
import java.util.List;

public class Folder implements Serializable {
    String id;
    String folder_name;
    String folder_describe;
    String count_subject;
    List<Subject> subjects;
    String date_create;
    String time_create;


    public Folder(String folder_name, String folder_describe, String date_create, String time_create) {
        this.folder_name = folder_name;
        this.folder_describe = folder_describe;
        this.date_create = date_create;
        this.time_create = time_create;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public String getFolder_describe() {
        return folder_describe;
    }

    public void setFolder_describe(String folder_describe) {
        this.folder_describe = folder_describe;
    }

    public String getCount_subject() {
        return count_subject;
    }

    public void setCount_subject(String count_subject) {
        this.count_subject = count_subject;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getTime_create() {
        return time_create;
    }

    public void setTime_create(String time_create) {
        this.time_create = time_create;
    }
}
