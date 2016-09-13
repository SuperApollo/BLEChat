package com.example.administrator.apolloblechat.fragment;

import android.view.View;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.widgets.ItemView;

/**
 * Created by Administrator on 2016/8/30.
 */
public class TransportFragment extends BaseFragment {
    @Override
    protected int getViewId() {
        return R.layout.fragment_transport;
    }

    @Override
    protected void initView(View view) {

        setItemClick(view, R.id.item_transport_outbox, R.mipmap.icon_item_trans_outbox, "发送", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToastUtil.toaster("发送");
            }
        }, true, false);

        setItemClick(view, R.id.item_transport_inbox, R.mipmap.icon_item_trans_inbox, "接收", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToastUtil.toaster("接收");
            }
        }, false, false);

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
