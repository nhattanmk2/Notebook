package com.example.notebook.Model;

import android.icu.text.CaseMap;

import org.threeten.bp.LocalDate;
import java.util.Calendar;

public class Item_Header {
    private String author;
    private String Title;
    private int counts;

    private String calendarCreate;
    private String localDateCreate;
    private String calendarFix;
    private String localDateFix;
    public Item_Header() {}
    public Item_Header(String Title) {
        this.Title = Title;
    }

    public Item_Header(String Title, String author, int counts, String localDateCreate, String localDateFix, String calendarCreate, String calendarFix) {
        this.Title = Title;
        this.author = author;
        this.counts = counts;
        this.localDateCreate = localDateCreate;
        this.localDateFix = localDateFix;
        this.calendarCreate = calendarCreate;
        this.calendarFix = calendarFix;
    }

    public String getCalendarCreate() {
        return calendarCreate;
    }

    public void setCalendarCreate(String calendarCreate) {
        this.calendarCreate = calendarCreate;
    }

    public String getCalendarFix() {
        return calendarFix;
    }

    public void setCalendarFix(String calendarFix) {
        this.calendarFix = calendarFix;
    }

    public String getLocalDateCreate() {
        return localDateCreate;
    }

    public void setLocalDateCreate(String localDateCreate) {
        this.localDateCreate = localDateCreate;
    }

    public String getLocalDateFix() {
        return localDateFix;
    }

    public void setLocalDateFix(String localDateFix) {
        this.localDateFix = localDateFix;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getCounts() {
        return counts;
    }


}
