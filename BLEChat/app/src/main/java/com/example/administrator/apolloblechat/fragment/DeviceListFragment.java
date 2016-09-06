package com.example.administrator.apolloblechat.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.DeviceListAdapter;
import com.example.administrator.apolloblechat.bean.DeviceBean;
import com.example.administrator.apolloblechat.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class DeviceListFragment extends BaseFragment {

    private ListView lv_devicelist;
    private DeviceListAdapter deviceListAdapter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_devicelist;
    }

    @Override
    protected void initView(View view) {
        lv_devicelist = queryViewById(view, R.id.lv_devicelist);
        deviceListAdapter = new DeviceListAdapter(getData(), mContext);
        lv_devicelist.setAdapter(deviceListAdapter);

        lv_devicelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.toaster(deviceListAdapter.getItem(position).getDeviceName());
            }
        });
    }

    private List<DeviceBean> getData() {
        List<DeviceBean> beans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_ble);
            String name = "蓝牙 " + i;
            String id = "10086" + i;
            DeviceBean bean = new DeviceBean(icon, name, id);
            beans.add(bean);
        }

        return beans;
    }
}
