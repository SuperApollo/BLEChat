package com.example.administrator.apolloblechat.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/8/31.
 */
public class DeviceBean {
    private Drawable deviceIcon;
    private String deviceName;
    private String deviceId;

    public DeviceBean(Drawable deviceIcon, String deviceName, String deviceId) {
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
    }

    public Drawable getDeviceIcon() {
        return deviceIcon;
    }

    public void setDeviceIcon(Drawable deviceIcon) {
        this.deviceIcon = deviceIcon;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
