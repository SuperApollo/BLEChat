package com.example.administrator.apolloblechat.activity;

import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.utils.IntentUtils;
import com.example.administrator.apolloblechat.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2016/9/30.
 */

public class RegistActivity extends BaseActivity {

    private EditText et_username_reg;
    private EditText et_password_reg;
    private EditText et_password_confirm_reg;
    private boolean passwordOK;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_regist;
    }

    @Override
    protected boolean fullScreen() {
        return false;
    }

    @Override
    protected boolean changeSystemBar() {
        return false;
    }

    @Override
    protected void initView(View view) {
        et_username_reg = queryViewById(view, R.id.et_username_reg);
        et_password_reg = queryViewById(view, R.id.et_password_reg);
        et_password_confirm_reg = queryViewById(view, R.id.et_password_confirm_reg);
        queryViewById(view, R.id.btn_regist, true);
        queryViewById(view, R.id.btn_unregist, true);
        et_password_confirm_reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = et_password_reg.getText().toString();
                if (!TextUtils.equals(s, pass)) {
                    passwordOK = false;
                    return;
                }
                passwordOK = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_regist:
                regist();
                break;
            case R.id.btn_unregist:
                unregist();
                break;
        }

    }

    private void regist() {
        if (TextUtils.isEmpty(et_username_reg.getText().toString())) {
            mToastUtil.toaster("用户名不能为空");
            return;
        } else if (TextUtils.isEmpty(et_password_reg.getText().toString())) {
            mToastUtil.toaster("密码不能为空");
            return;
        } else if (TextUtils.isEmpty(et_password_confirm_reg.getText().toString())) {
            mToastUtil.toaster("确认密码不能为空");
            return;
        } else if (!passwordOK) {
            mToastUtil.toaster("两次密码不一致");
            return;
        }

        String auth = System.currentTimeMillis() / 100000 + "";
        SharedPreferencesUtils.putString(AppConfig.AUTHOR_CODE, auth);
        SharedPreferencesUtils.putString(AppConfig.USER_NAME,et_username_reg.getText().toString());
        SharedPreferencesUtils.putString(AppConfig.PASSWORD,et_password_reg.getText().toString());
        mToastUtil.toaster("注册成功");
        IntentUtils.sendIntent(RegistActivity.this, LoginActivity.class);
        this.finish();
    }

    private void unregist() {
        SharedPreferencesUtils.putString(AppConfig.AUTHOR_CODE, "");
        SharedPreferencesUtils.putString(AppConfig.USER_NAME, "");
        SharedPreferencesUtils.putString(AppConfig.PASSWORD, "");
        mToastUtil.toaster("注销成功");
    }

}
