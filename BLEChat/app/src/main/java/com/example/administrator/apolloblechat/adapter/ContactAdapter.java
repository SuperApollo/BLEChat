package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
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
            if (contactBean.getType() == 0) {
                view = LayoutInflater.from(mContext).inflate(R.layout.contact_item_letter, null);
                holder.tvLetter = (TextView) view.findViewById(R.id.tv_contactitem_letter);
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.contact_item_name, null);
                holder.ivIcon = (ImageView) view.findViewById(R.id.iv_contactitem_icon);
                holder.tvName = (TextView) view.findViewById(R.id.tv_contactitem_name);
            }
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (contactBean.getFirstWord()==null){
            holder.tvLetter.setVisibility(View.GONE);
        }else {
            holder.tvLetter.setText(contactBean.getFirstWord());
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
