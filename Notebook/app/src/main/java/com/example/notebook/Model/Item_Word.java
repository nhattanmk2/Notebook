package com.example.notebook.Model;

import java.util.List;

public class Item_Word {

    private String id;
    private String word;
    private String meaning;

    private List<String> Antonyms;
    private List<String> Synonyms;
    private boolean status;
    public Item_Word() {}

    public Item_Word(String id, boolean status) {
        this.id = id; this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }


    public void setAntonyms(List<String> antonyms) {
        this.Antonyms = antonyms;
    }

    public List<String> getAntonyms() {
        return Antonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.Synonyms = synonyms;
    }

    public List<String> getSynonyms() {
        return Synonyms;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
