package com.example.administrator.apolloblechat.fragment;

import android.view.View;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.widgets.ItemView;

/**
 * Created by Administrator on 2016/9/2.
 */
public class MoreFragment extends BaseFragment {
    @Override
    protected int getViewId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView(View view) {
        setItemClick(view, R.id.item_more_setting, R.drawable.icon_item_more_setting, "设置", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mToastUtil.toaster("设置");
            }
        }, false, false);
        setItemClick(view, R.id.item_more_mine, R.drawable.icon_item_more_mine, "我的", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mToastUtil.toaster("我的");
            }
        }, false, false);
        setItemClick(view, R.id.item_more_about, R.drawable.icon_item_more_about, "关于", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mToastUtil.toaster("关于");
            }
        }, true, false);
    }

    private void setItemClick(View view, int viewID, int iconID, String name, View.OnClickListener listener, boolean lineVisibility, boolean badgeViewVisibility) {
        ItemView itemView = (ItemView) view.findViewById(viewID);
        itemView.setTextSize(16);
        itemView.setText(name);
        View line = itemView.findViewById(R.id.line_fragment_more);
        if (lineVisibility) {
            line.setVisibility(View.VISIBLE);
        }
        itemView.setTextDrawable(iconID);
        itemView.setOnClickListener(listener);
    }
}
