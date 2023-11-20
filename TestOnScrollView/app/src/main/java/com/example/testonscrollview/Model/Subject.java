package com.example.testonscrollview.Model;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {
    String id;
    String subject_name;
    String subject_describe;
    String word_count;
    List<Word> wordList;
    String date_create;
    String time_create;

    public  Subject(String subject_name, String subject_describe, String word_count) {
        this.subject_name = subject_name;
        this.subject_describe = subject_describe;
        this.word_count = word_count;
    }
    public  Subject(String subject_name, String subject_describe, String word_count, List<Word> wordList) {
        this.subject_name = subject_name;
        this.subject_describe = subject_describe;
        this.word_count = word_count;
        this.wordList = wordList;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_describe() {
        return subject_describe;
    }

    public void setSubject_describe(String subject_describe) {
        this.subject_describe = subject_describe;
    }

    public String getWord_count() {
        return word_count;
    }

    public void setWord_count(String word_count) {
        this.word_count = word_count;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
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
