package com.example.administrator.apolloblechat.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ChatBean {
    private String time;
    private Drawable icon;
    private String name;
    private String content;
    private boolean isFrom;

    public ChatBean(String time, Drawable icon, String name, String content, boolean isFrom) {
        this.time = time;
        this.icon = icon;
        this.name = name;
        this.content = content;
        this.isFrom = isFrom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFrom() {
        return isFrom;
    }

    public void setIsFrom(boolean isFrom) {
        this.isFrom = isFrom;
    }

    @Override
    public String toString() {
        return "time: " + time + ",icon: " + icon + ",name: " + name + ",content: " + content;
    }
}
