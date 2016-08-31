package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.bean.DeviceBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class DeviceListAdapter<T> extends BaseAdapter {

    private List<DeviceBean> mDatas;
    private Context mContext;

    public DeviceListAdapter(List<DeviceBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.device_list_item, null);
            ImageView iconView = (ImageView) view.findViewById(R.id.iv_devlist_item_icon);
            TextView tvName = (TextView) view.findViewById(R.id.tv_devlist_item_name);
            TextView tvId = (TextView) view.findViewById(R.id.tv_devlist_item_id);
            holder = new ViewHolder(iconView, tvName, tvId);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.ivHolderIcon.setImageBitmap(mDatas.get(i).getDeviceIcon());
        holder.tvHolderName.setText(mDatas.get(i).getDeviceName());
        holder.tvHolderId.setText(mDatas.get(i).getDeviceId());

        return view;
    }

    class ViewHolder {
        private ImageView ivHolderIcon;
        private TextView tvHolderName;
        private TextView tvHolderId;

        public ViewHolder(ImageView ivHolderIcon, TextView tvHolderName, TextView tvHolderId) {
            this.ivHolderIcon = ivHolderIcon;
            this.tvHolderName = tvHolderName;
            this.tvHolderId = tvHolderId;
        }

    }
}
