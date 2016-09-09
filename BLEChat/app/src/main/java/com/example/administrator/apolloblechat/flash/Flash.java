package com.example.administrator.apolloblechat.flash;

import android.hardware.Camera;

import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Flash {
    private static Flash mFlash;
    private static Camera mCamera = null;
    private static Camera.Parameters mCameraParameters;

    private static String previousFlashMode = null;
    private static boolean isOpen = false;

    private Flash() {
    }

    public static Flash getInstance() {
        if (mFlash == null) {
            synchronized (Flash.class) {
                if (mFlash == null) {
                    mFlash = new Flash();
                }
            }
        }
        return mFlash;
    }

    public synchronized void open() {

        try {
            mCamera = Camera.open();
//                mCamera.setPreviewTexture(new SurfaceTexture(0));//这一句话很重要，不加的话在nexus 5上灯泡不亮
        } catch (Exception e) {
            close();
            ToastUtil.toaster("打开闪光灯失败");

        }
        if (mCamera != null)

        {
            mCameraParameters = mCamera.getParameters();
            previousFlashMode = mCameraParameters.getFlashMode();
        }

        if (previousFlashMode == null)

        {
            // could be null if no flash, i.e. emulator
            previousFlashMode = Camera.Parameters.FLASH_MODE_OFF;
        }
    }

    public synchronized void close() {//关灯，就是用完之后清除一下camera对象，不然会影响其他设备的正常使用
        if (mCamera != null) {
            mCameraParameters.setFlashMode(previousFlashMode);
            mCamera.setParameters(mCameraParameters);
            mCamera.release();
            mCamera = null;
            isOpen = false;
        }
    }

    public synchronized void onAndOff() {//开\关都在这里调

        try {
            if (isOpen) {
                off();
            } else if (!isOpen) {
                on();
            }
            // send broadcast for widget

            //调完之后可以通知界面更新
        } catch (RuntimeException e) {
            ToastUtil.toaster("设备不可用");

        }

    }

    public synchronized void on() {
        if (mCamera == null) {
            open();
        }
        if (mCamera != null) {
            mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mCameraParameters);
            mCamera.startPreview();
            isOpen = true;
        }

    }

    public synchronized void off() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(mCameraParameters);
            isOpen = false;
        }
        close();

    }

    public void setLight(boolean b) {
        if (b) {
            on();
        } else {
            off();
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        Flash.isOpen = isOpen;
    }
}
