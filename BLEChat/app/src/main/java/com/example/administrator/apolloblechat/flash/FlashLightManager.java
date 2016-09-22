package com.example.administrator.apolloblechat.flash;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Size;
import android.view.Surface;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/26.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class FlashLightManager {
    private static final String TAG = "FlashlightManager";
    private final CameraManager mCameraManager;
    private Handler mHandler;
    private boolean mFlashlightEnabled;
    private String mCameraId;
    private CameraDevice mCameraDevice;
    private CaptureRequest mFlashlightRequest;
    private CaptureRequest.Builder mBuilder;
    private CameraCaptureSession mSession;
    private SurfaceTexture mSurfaceTexture;
    private Surface mSurface;
    private Context mContext;

    public FlashLightManager(Context context) {
        mContext = context;
        mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        init();
    }

    public void init() {
        try {
            mCameraId = getCameraId();
        } catch (Throwable e) {
            return;
        }

        if (mCameraId != null) {
            startHandler();
        }
    }

    private void showErrorMsg() {
        Toast.makeText(mContext, "don't control camera devices", Toast.LENGTH_LONG).show();
    }

    public synchronized void setFlashlight(boolean enabled) {
        if (mFlashlightEnabled != enabled) {
            mFlashlightEnabled = enabled;
            postUpdateFlashlight();
        }
    }

    public void killFlashlight() {
        boolean enabled;
        synchronized (this) {
            enabled = mFlashlightEnabled;
        }
        if (enabled) {
            mHandler.post(mKillFlashlightRunnable);
        }
    }

    private synchronized void startHandler() {
        if (mHandler == null) {
            HandlerThread thread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();
            mHandler = new Handler(thread.getLooper());
        }
    }


    private void openCameraDevice()  {
        try {
            mCameraManager.openCamera(getCameraId(), mStateCallback, mHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCameraSession() throws Exception {
        mSurfaceTexture = new SurfaceTexture(0, false);
        Size size = getSmallestSize(mCameraDevice.getId());
        mSurfaceTexture.setDefaultBufferSize(size.getWidth(), size.getHeight());
        mSurface = new Surface(mSurfaceTexture);
        ArrayList<Surface> outputs = new ArrayList<>(1);
        outputs.add(mSurface);
        mCameraDevice.createCaptureSession(outputs, mSessionStateCallback, mHandler);
    }

    private Size getSmallestSize(String cameraId) throws Exception {
        Size[] outputSizes = mCameraManager.getCameraCharacteristics(cameraId)
                .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                .getOutputSizes(SurfaceTexture.class);
        if (outputSizes == null || outputSizes.length == 0) {
            throw new IllegalStateException(
                    "doesn't support any outputSize!");
        }
        Size chosen = outputSizes[0];
        for (Size s : outputSizes) {
            if (chosen.getWidth() >= s.getWidth() && chosen.getHeight() >= s.getHeight()) {
                chosen = s;
            }
        }
        return chosen;
    }

    private void postUpdateFlashlight() {
        startHandler();
        mHandler.post(mUpdateFlashlightRunnable);
    }

    /**
     * @author Richard
     * @获得有FLAHS功能的Camera ID。
     */
    private String getCameraId() throws Exception {
        String[] ids = mCameraManager.getCameraIdList();
        for (String id : ids) {
            CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
            Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
            if (flashAvailable != null && flashAvailable
                    && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                return id;
            }
        }
        return null;
    }

    private void updateFlashlight(boolean status) {
        try {
            boolean enabled;
            synchronized (this) {
                enabled = mFlashlightEnabled && !status;
                if (enabled) {
                    if (mCameraDevice == null) {
                        openCameraDevice();
                        return;
                    }
                    if (mSession == null) {
                        openCameraSession();
                        return;
                    }

                    if (mFlashlightRequest == null) {
                        CaptureRequest.Builder builder = mCameraDevice.createCaptureRequest(
                                CameraDevice.TEMPLATE_PREVIEW);
                        builder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);
                        builder.addTarget(mSurface);
                        mFlashlightRequest = builder.build();
                        mSession.capture(mFlashlightRequest, null, mHandler);
                    }

                } else {
                    releaseResource();
                }
            }

        } catch (Exception e) {
            showErrorMsg();
        }
    }


    public void releaseResource() {
        if (mBuilder != null) {
            mBuilder.removeTarget(mSurface);
            mBuilder = null;
        }

        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
        mCameraDevice = null;
        mSession = null;
        mFlashlightRequest = null;
        if (mSurface != null) {
            mSurface.release();
            mSurfaceTexture.release();
        }
        mSurface = null;
        mSurfaceTexture = null;
    }

    private final CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            postUpdateFlashlight();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            if (mCameraDevice == camera) {
                releaseResource();
            }
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            if (camera == mCameraDevice || mCameraDevice == null) {
                showErrorMsg();
            }
        }
    };

    private final CameraCaptureSession.StateCallback mSessionStateCallback =
            new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    if (session.getDevice() == mCameraDevice) {
                        mSession = session;
                    } else {
                        session.close();
                    }
                    postUpdateFlashlight();
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                    if (mSession == null || mSession == session) {
                        showErrorMsg();
                    }
                }
            };

    private final Runnable mUpdateFlashlightRunnable = new Runnable() {
        @Override
        public void run() {
            updateFlashlight(false);
        }
    };

    private final Runnable mKillFlashlightRunnable = new Runnable() {
        @Override
        public void run() {
            synchronized (this) {
                mFlashlightEnabled = false;
            }
            //releaseResource();
            updateFlashlight(true);
        }
    };
}
