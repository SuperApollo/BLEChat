package com.example.administrator.apolloblechat.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/9/14.
 */
public class AppConfig {
    /**
     * 默认 SharePreferences文件名.
     */
    public static final String SHARED_PATH = "apollo_chat_share";
    public static final String SYN_SHARED_PATH = "syn_apollo_chat_share";
    /**
     * 存储图片的位置
     */
    public static final String FILE_DOWNLOAD = Environment.getExternalStorageDirectory() + File.separator + "apollochat/";
    /**
     * 聊天信息对象名称
     */
    public static final String CHATBEN_NAME = "chatben_name";
    //用户名
    public static final String USER_NAME = "user_name";
    //密码
    public static final String PASSWORD = "password";
    //登录状态
    public static final String IS_LOGIN = "is_login";
    //记住用户
    public static final String REMEMBER_USER = "remember_user";
    //首次运行
    public static final String FIRST_RUN = "first_run";
    //数据库名称
    public static final String DB_NAME = "apollo_chat.db";
    //鉴权码
    public static final String AUTHOR_CODE = "author_code";
}
