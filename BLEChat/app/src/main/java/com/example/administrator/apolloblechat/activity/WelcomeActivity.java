package com.example.administrator.apolloblechat.activity;

import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.fragment.WelcomeFragmentFirstTime;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.utils.IntentUtils;
import com.example.administrator.apolloblechat.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2016/9/20.
 */
public class WelcomeActivity extends BaseActivity {

    private FrameLayout container_welcome_activity;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(View view) {
        container_welcome_activity = queryViewById(view, R.id.container_welcome_activity);
        welcome();
    }

    private void welcome() {
        boolean firstRun = SharedPreferencesUtils.getBoolean(AppConfig.FIRST_RUN, true);
        if (firstRun) {
            FragmentUtils.replace(WelcomeActivity.this, R.id.container_welcome_activity, new WelcomeFragmentFirstTime());
            SharedPreferencesUtils.putBoolean(AppConfig.FIRST_RUN, false);
        } else {
            boolean isLogin = SharedPreferencesUtils.getBoolean(AppConfig.IS_LOGIN,false);
            if (isLogin){
                IntentUtils.sendIntent(this,MainActivity.class);
            }else {
                IntentUtils.sendIntent(this,LoginActivity.class);
            }
        }

    }
}
