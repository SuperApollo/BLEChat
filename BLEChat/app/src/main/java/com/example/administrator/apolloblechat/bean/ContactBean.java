package com.example.administrator.apolloblechat.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ContactBean {
    private Drawable icon;
    private String name;
    private String id;
    private String firstWord;//首字母

    public ContactBean(Drawable icon, String name, String id, String firstWord) {
        this.icon = icon;
        this.name = name;
        this.id = id;
        this.firstWord = firstWord;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
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


    @Override
    public String toString() {
        return "bitmap: " + icon + " name: " + name + " id: " + id + " firstWord: " + firstWord;
    }
}
