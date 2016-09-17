package com.example.administrator.apolloblechat.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.widgets.ClearEditText;

/**
 * Created by wangpengbo on 16/9/17.
 */
public class LaunchActivity extends BaseActivity {

    private ClearEditText et_username;
    private ClearEditText et_password;
    private Button btn_register;
    private Button btn_login;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView(View view) {
        et_username = queryViewById(view, R.id.et_username);
        et_password = queryViewById(view, R.id.et_password);
        btn_register = queryViewById(view, R.id.btn_register);
        btn_login = queryViewById(view, R.id.btn_login,true);



    }

    private boolean checkLogin() {
        boolean flag = true;
        String usr_name = et_username.getText().toString();
        String pass_word = et_password.getText().toString();
        if (!TextUtils.equals("apollo", usr_name)) {
            mToastUtil.toaster("用户名错误");
            flag = false;

        }

        if (!TextUtils.equals("123456", pass_word)) {
            mToastUtil.toaster("密码错误");
            flag = false;
        }

        return flag;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                boolean flag = checkLogin();
                if (flag) {
                    Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    mToastUtil.toaster("登录失败");
                }
                break;
        }

    }
}
