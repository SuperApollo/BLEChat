package com.example.administrator.apolloblechat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.activity.MainActivity;
import com.example.administrator.apolloblechat.base.BaseApplication;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by Administrator on 2016/8/23.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected ToastUtil mToastUtil;
    private RadioGroup rg_bottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseApplication.getContext();
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
        rg_bottom = (RadioGroup) getActivity().findViewById(R.id.rg_bottom);
        if (null == rg_bottom)
            return;
        if (hideBottom()) {
            rg_bottom.setVisibility(View.GONE);
        } else {
            rg_bottom.setVisibility(View.VISIBLE);
        }
        MainActivity activity = (MainActivity) getActivity();
        activity.setmOnBackKeyPressedListner(new MainActivity.OnBackKeyPressedListner() {
            @Override
            public boolean onPressed() {
                Fragment toFragment = backTo();
                if (null != toFragment) {
                    FragmentUtils.replace(getActivity(), R.id.ll_fragment_container, toFragment);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    protected abstract int getViewId();

    protected abstract void initView(View view);

    /**
     * 是否隐藏底部导航栏
     * @return
     */
    protected abstract boolean hideBottom();

    /**
     * 按下返回键回退到哪个fragment
     *
     * @return
     */
    protected abstract Fragment backTo();

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
