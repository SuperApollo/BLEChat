package com.example.administrator.apolloblechat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by Administrator on 2016/8/23.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected ToastUtil mToastUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mToastUtil = ToastUtil.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewId(), container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract int getViewId();
    protected abstract void initView(View view);

    protected <T extends View> T queryViewById(View parentView, int id) {
        return (T) parentView.findViewById(id);
    }

    protected <T extends View> T queryViewById(View parentView, int id, boolean onClickListener) {
        T view = (T) parentView.findViewById(id);
        if (onClickListener) {
            view.setOnClickListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View view) {

    }
}
