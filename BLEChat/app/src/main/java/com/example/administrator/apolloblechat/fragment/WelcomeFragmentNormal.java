package com.example.administrator.apolloblechat.fragment;

import android.os.Handler;
import android.view.View;

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
    @Override
    protected int getViewId() {
        return R.layout.fragment_welcome_normal;
    }

    @Override
    protected void initView(View view) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLogin();
            }
        }, 3000);
    }

    private void checkLogin() {
        boolean isLogin = SharedPreferencesUtils.getBoolean(AppConfig.IS_LOGIN, false);
        if (isLogin) {
            IntentUtils.sendIntent(getActivity(), MainActivity.class);
        } else {
            IntentUtils.sendIntent(getActivity(), LoginActivity.class);
        }
        getActivity().finish();
    }
}
