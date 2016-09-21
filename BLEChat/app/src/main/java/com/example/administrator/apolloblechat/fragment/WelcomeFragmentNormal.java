package com.example.administrator.apolloblechat.fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.activity.LoginActivity;
import com.example.administrator.apolloblechat.activity.MainActivity;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.utils.IntentUtils;
import com.example.administrator.apolloblechat.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2016/9/20.
 */
public class WelcomeFragmentNormal extends BaseFragment {

    private TextView tv_welcome_seconds;
    private CountDownTimer timer;

    @Override
    protected int getViewId() {
        return R.layout.fragment_welcome_normal;
    }

    @Override
    protected void initView(View view) {

        tv_welcome_seconds = queryViewById(view, R.id.tv_welcome_seconds);
        queryViewById(view, R.id.ll_welcome_skip, true);

        tv_welcome_seconds.setText("5");
        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_welcome_seconds.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                checkLogin();
            }
        };
        timer.start();
    }

    private void checkLogin() {
        boolean isLogin = SharedPreferencesUtils.getBoolean(AppConfig.IS_LOGIN, false);
        if (isLogin) {
            IntentUtils.sendIntent(getActivity(), MainActivity.class);
        } else {
            IntentUtils.sendIntent(getActivity(), LoginActivity.class);
        }
        timer.cancel();
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_welcome_skip)
            checkLogin();
    }
}
