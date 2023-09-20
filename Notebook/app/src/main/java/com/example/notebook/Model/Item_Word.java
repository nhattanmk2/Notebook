package com.example.notebook.Model;

import java.util.List;

public class Item_Word {
    int key;
    private String id;
    private String name;
    private byte[] imageResourceId;
    private List<String> Antonyms;
    private List<String> Synonyms;
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
    public Item_Word(String id, boolean status) {
        this.id = id; this.status = status;
    }
    public Item_Word(String id, int key) {
        this.id = id;
        this.key = key;
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


    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public Item_Word(String id, String name, byte[] imageResourceId) {
        this.id = id;
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImageResourceId() {
        return imageResourceId;
    }
}
