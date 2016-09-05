package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.bean.ContactBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private List<ContactBean> mData;

    public ContactAdapter(Context mContext, List<ContactBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        ContactBean contactBean = mData.get(i);
        if (view == null) {
            holder = new ViewHolder();

            view = LayoutInflater.from(mContext).inflate(R.layout.contact_item, null);
            holder.tvLetter = (TextView) view.findViewById(R.id.tv_contactitem_letter);
            holder.ivIcon = (ImageView) view.findViewById(R.id.iv_contactitem_icon);
            holder.tvName = (TextView) view.findViewById(R.id.tv_contactitem_name);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (TextUtils.isEmpty(contactBean.getFirstWord())) {
            holder.tvLetter.setVisibility(View.GONE);
        } else {
            holder.tvLetter.setText(contactBean.getFirstWord());
            holder.tvLetter.setVisibility(View.VISIBLE);
        }

        holder.ivIcon.setImageBitmap(contactBean.getIcon());
        holder.tvName.setText(contactBean.getName());

        return view;
    }

    private class ViewHolder {
        ImageView ivIcon;
        TextView tvLetter, tvName;
    }
}
