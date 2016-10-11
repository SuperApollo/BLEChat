package com.example.administrator.apolloblechat.fragment;

import android.view.View;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;

/**
 * Created by Administrator on 2016/9/23.
 */

public class MineFragment extends BaseFragment {

    private MyTittleBar title_mine;

    @Override
    protected int getViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        title_mine = queryViewById(view, R.id.title_mine);
        title_mine.setOnBackListener(new MyTittleBar.OnBackListener() {
            @Override
            public void onBackClick() {
                FragmentUtils.replace(getActivity(),R.id.ll_fragment_container,new MoreFragment());
            }
        });
    }
}
