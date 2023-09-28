package com.example.notebook.Model;

import java.util.List;

public class Item_Word_By_Unit {
    private String Title;
    private int count;
    private List<Item_Word> List_Word;

    public Item_Word_By_Unit() {
    }

    public Item_Word_By_Unit(String Title, int count, List<Item_Word> List_Word) {
        this.Title = Title;
        this.count = count;
        this.List_Word = List_Word;
    }

    public void setList_Word(List<Item_Word> List_Word) {
        this.List_Word = List_Word;
    }
    public List<Item_Word> getList_Word(){
        return this.List_Word;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getTitle(){
        return this.Title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

}

