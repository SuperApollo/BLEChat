package com.example.administrator.apolloblechat.bean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/8/31.
 */
public class DeviceBean {
    private Bitmap deviceIcon;
    private String deviceName;
    private String deviceId;

    public DeviceBean(Bitmap deviceIcon, String deviceName, String deviceId) {
        this.deviceIcon = deviceIcon;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
    }

    public Bitmap getDeviceIcon() {
        return deviceIcon;
    }

    public void setDeviceIcon(Bitmap deviceIcon) {
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
