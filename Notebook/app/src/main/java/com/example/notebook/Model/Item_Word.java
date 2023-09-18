package com.example.notebook.Model;

public class Item_Word {
    private String id;
    private String name;
    private byte[] imageResourceId;
    public Item_Word(String id) {
        this.id = id;
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
