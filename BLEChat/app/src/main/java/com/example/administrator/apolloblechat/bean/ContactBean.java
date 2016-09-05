package com.example.administrator.apolloblechat.bean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ContactBean {
    private Bitmap icon;
    private String name;
    private String id;
    private String firstWord;//首字母
    private int type;//0为首字母显示行，1为姓名显示行

    public ContactBean(Bitmap icon, String name, String id, String firstWord, int type) {
        this.icon = icon;
        this.name = name;
        this.id = id;
        this.firstWord = firstWord;
        this.type = type;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "bitmap: " + icon + " name: " + name + " id: " + id + " typ: " + type + " firstWord: " + firstWord;
    }
}
