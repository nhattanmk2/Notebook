package com.example.testonscrollview.Model;

//Local
public class HomeAchievement {
    private int type;
    private int img_id;
    private String title_Achievement;

    public HomeAchievement(int type, int img_id, String title_Achievement) {
        this.img_id = img_id;
        this.type = type;
        this.title_Achievement = title_Achievement;
    }

    public HomeAchievement(int img_id, String title_Achievement) {
        this.img_id = img_id;
        this.title_Achievement = title_Achievement;

    }

    public String getTitle_Achievement() {
        return title_Achievement;
    }

    public void setTitle_Achievement(String title_Achievement) {
        this.title_Achievement = title_Achievement;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
