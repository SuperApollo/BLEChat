package com.example.administrator.apolloblechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.utils.SharedPreferencesUtils;
import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by wangpengbo on 16/9/17.
 */
public class LoginActivity extends BaseActivity {

    private static final int USR_NAME_ERROR = 0x000001;
    private static final int PASS_WORD_ERROR = 0x000002;
    private static final int LOGIN_SUCCESS = 0x000003;
    private EditText et_username;
    private EditText et_password;
    private Button btn_register;
    private Button btn_login;
    private ToastUtil mToastUtil;
    private String usr_name;
    private String pass_word;
    private CheckBox cb_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToastUtil = ToastUtil.getInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean fullScreen() {
        return true;
    }

    @Override
    protected boolean changeSystemBar() {
        return false;
    }

    @Override
    protected void initView(View view) {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);

        btn_login.setOnClickListener(this);
        if (SharedPreferencesUtils.getBoolean(AppConfig.REMEMBER_USER, false)) {
            cb_remember.setChecked(true);
        }
        if (cb_remember.isChecked()) {
            usr_name = SharedPreferencesUtils.getString(AppConfig.USER_NAME);
            pass_word = SharedPreferencesUtils.getString(AppConfig.PASSWORD);
            if (!TextUtils.isEmpty(usr_name) && !TextUtils.isEmpty(pass_word)) {
                et_username.setText(usr_name);
                et_password.setText(pass_word);
            }
        }
        cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferencesUtils.putBoolean(AppConfig.REMEMBER_USER, true);
                } else {
                    SharedPreferencesUtils.putBoolean(AppConfig.REMEMBER_USER, false);
                }

            }
        });
    }


    private int checkLogin() {
        showProgress();
        int flag = LOGIN_SUCCESS;
        usr_name = et_username.getText().toString();
        pass_word = et_password.getText().toString();
        if (!TextUtils.equals("apollo", usr_name)) {
            flag = USR_NAME_ERROR;
        }

        if (!TextUtils.equals("123456", pass_word)) {
            flag = PASS_WORD_ERROR;
        }

        return flag;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                int flag = checkLogin();
                switch (flag) {
                    case USR_NAME_ERROR:
                        mToastUtil.toaster("用户名不存在");
                        break;
                    case PASS_WORD_ERROR:
                        mToastUtil.toaster("密码错误");
                        break;
                    case LOGIN_SUCCESS:
                        mToastUtil.toaster("登录成功");
                        SharedPreferencesUtils.putString(AppConfig.USER_NAME, usr_name);
                        SharedPreferencesUtils.putString(AppConfig.PASSWORD, pass_word);
                        SharedPreferencesUtils.putBoolean(AppConfig.IS_LOGIN, true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        clearProgress();
                        finish();
                        break;
                }

                break;
        }

    }
}
