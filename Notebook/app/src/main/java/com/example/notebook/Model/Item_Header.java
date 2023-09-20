package com.example.notebook.Model;

import android.icu.text.CaseMap;

import java.time.LocalDate;
import java.util.Calendar;

public class Item_Header {
    private String author;
    private String Title;
    private int counts;
    private byte[] imageResourceId;
    private Calendar calendarCreate;
    private LocalDate localDateCreate;
    private Calendar calendarFix;
    private LocalDate localDateFix;
    public Item_Header(String Title) {
        this.Title = Title;
    }

    public Item_Header(String Title, String author, int counts) {
        this.Title = Title;
        this.author = author;
        this.counts = counts;
    }
    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCounts() {
        return counts;
    }

    public byte[] getImageResourceId() {
        return imageResourceId;
    }
}
