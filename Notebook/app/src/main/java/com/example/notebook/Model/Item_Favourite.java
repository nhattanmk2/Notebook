package com.example.notebook.Model;

import java.util.List;

public class Item_Favourite {
    private String Title;
    private int count;
    private List<Item_Word> List_Favourite;

    public Item_Favourite(String Title, int count, List<Item_Word> List_Favourite) {
        this.Title = Title;
        this.count = count;
        this.List_Favourite = List_Favourite;
    }

    public void setList_Favourite(List<Item_Word> list_Favourite) {
        List_Favourite = list_Favourite;
    }
    public List<Item_Word> getList_Favourite(){
        return this.List_Favourite;
    }
    public String getTitle(){
        return this.Title;
    }
    public int getCount(){
        return this.count;
    }

}
