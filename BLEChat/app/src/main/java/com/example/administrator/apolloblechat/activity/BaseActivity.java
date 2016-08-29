package com.example.administrator.apolloblechat.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.example.administrator.apolloblechat.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/8/23.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = getLayoutInflater().inflate(getContentViewId(),null);
        setContentView(view);
    }

    protected abstract int getContentViewId();

}
