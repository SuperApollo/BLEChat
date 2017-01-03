package com.example.administrator.apolloblechat.fragment;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.DeviceListAdapter;
import com.example.administrator.apolloblechat.bean.DeviceBean;
import com.example.administrator.apolloblechat.utils.ToastUtil;
import com.example.administrator.apolloblechat.widgets.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class DeviceListFragment extends BaseFragment {
    private XListView lv_devicelist;
    private DeviceListAdapter deviceListAdapter;
    private static final int STOP_REFRESH = 1000;
    private static final int STOP_LOADMORE = 1001;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STOP_REFRESH:
                    lv_devicelist.stopRefresh();
                    break;
                case STOP_LOADMORE:
                    lv_devicelist.stopLoadMore();
                    break;
            }
        }
    };

    @Override
    protected int getViewId() {
        return R.layout.fragment_devicelist;
    }

    @Override
    protected void initView(View view) {
        lv_devicelist = queryViewById(view, R.id.lv_devicelist);
        lv_devicelist.setVerticalScrollBarEnabled(false);
        lv_devicelist.setPullLoadEnable(true);
        lv_devicelist.setPullRefreshEnable(true);
        deviceListAdapter = new DeviceListAdapter(getData(), mContext);
        lv_devicelist.setAdapter(deviceListAdapter);

        lv_devicelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mToastUtil.toaster(deviceListAdapter.getItem(position).getDeviceName());
            }
        });
        lv_devicelist.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(STOP_REFRESH, 3000);
            }

            @Override
            public void onLoadMore() {
                mHandler.sendEmptyMessageDelayed(STOP_LOADMORE, 3000);
            }
        });
    }

    @Override
    protected boolean hideBottom() {
        return false;
    }

    @Override
    protected Fragment backTo() {
        return null;
    }

    private List<DeviceBean> getData() {
        List<DeviceBean> beans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Drawable icon = getResources().getDrawable(R.mipmap.icon_ble);
            String name = "蓝牙 " + i;
            String id = "10086" + i;
            DeviceBean bean = new DeviceBean(icon, name, id);
            beans.add(bean);
        }

        return beans;
    }
}
