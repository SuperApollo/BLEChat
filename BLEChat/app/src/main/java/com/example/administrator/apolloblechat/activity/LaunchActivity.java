package com.example.administrator.apolloblechat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by wangpengbo on 16/9/17.
 */
public class LaunchActivity extends Activity implements View.OnClickListener {

    private static final int USR_NAME_ERROR = 0x000001;
    private static final int PASS_WORD_ERROR = 0x000002;
    private static final int LOGIN_SUCCESS = 0x000003;
    private EditText et_username;
    private EditText et_password;
    private Button btn_register;
    private Button btn_login;
    private ToastUtil mToastUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mToastUtil = ToastUtil.getInstance();
        initView();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    private int checkLogin() {
        int flag = LOGIN_SUCCESS;
        String usr_name = et_username.getText().toString();
        String pass_word = et_password.getText().toString();
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
                        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }

                break;
        }

    }
}
