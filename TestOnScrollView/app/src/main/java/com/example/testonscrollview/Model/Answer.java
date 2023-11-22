package com.example.testonscrollview.Model;

public class Answer {
    String id;
    String word;
    String word1;
    String meaning;
    public Answer (String word, String word1, String meaning) {
        this.word = word;
        this.word1 = word1;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
