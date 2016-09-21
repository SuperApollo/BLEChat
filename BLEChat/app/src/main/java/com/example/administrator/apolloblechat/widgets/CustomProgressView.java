package com.example.administrator.apolloblechat.widgets;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;


/**
 * Created by guozhk on 16-4-26.
 */
public class CustomProgressView {
    private static final long DAY = 3 * 1000;
    private CustomProgress customProgress;
    private TextView tvLoadMsg;
    private ImageView imgProgress;
    private Context mContext;

    private DialogInterface.OnCancelListener cancelListener;

    private int timeout = 0;


    public CustomProgressView(Context context) {
        this.mContext = context;
        customProgress = new CustomProgress(context);
    }


    public CustomProgressView setCancelListener(DialogInterface.OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

    public CustomProgressView setLoadMsg(String msg) {
        tvLoadMsg.setText(msg);
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void showProgressDialog() {
        try {
            customProgress.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                customProgress.setOnCancelListener(cancelListener);
                dissDialog();
            }
        }, (timeout == 0) ? DAY : (timeout * 1000L));

    }

    public void dissDialog() {
        if (customProgress != null && customProgress.isShowing()) {
            customProgress.cancel();
        }
    }


    private class CustomProgress extends Dialog {
        public CustomProgress(Context context) {
            this(context, R.style.CustomprogressDialog);
        }

        public CustomProgress(Context context, int theme) {
            super(context, R.style.CustomprogressDialog);
            init(context);
        }


        private void init(Context context) {
            customProgress = this;
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.custom_dialog_progress, null);
            imgProgress = (ImageView) layout.findViewById(R.id.custom_dialog_image_progress);
            tvLoadMsg = (TextView) layout.findViewById(R.id.custom_dialog_load_msg);

            setContentView(layout);
            setCanceledOnTouchOutside(false);
            setOnShowListener(new OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {

                    RotateAnimation animation = new RotateAnimation(0, 359f,
                            Animation.RELATIVE_TO_SELF,
                            0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(1000);//设置动画持续时间
                    animation.setRepeatCount(Animation.INFINITE);//设置重复次数
                    animation.setFillAfter(true);//设置重复次数
                    animation.setStartOffset(0);
                    imgProgress.setAnimation(animation);
                    animation.startNow();
                }
            });
        }

    }

}
