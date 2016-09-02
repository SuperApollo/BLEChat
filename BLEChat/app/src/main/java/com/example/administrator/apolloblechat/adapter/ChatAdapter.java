package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.bean.ChatBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ChatAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChatBean> mDatas;


    public ChatAdapter(Context mContext, List<ChatBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (mDatas.get(position).isFrom())
            type = 1;
        return type;
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
        ChatBean chatBean = mDatas.get(i);
        ViewHolder holder ;

        if (view == null) {
            holder = new ViewHolder();
            if (chatBean.isFrom()) {
                view = LayoutInflater.from(mContext).inflate(R.layout.chat_from_item, null);
                holder.holderTvTime = (TextView) view.findViewById(R.id.chat_from_item_time);
                holder.holderIvIcon = (ImageView) view.findViewById(R.id.chat_from_item_icon);
                holder.holderTvName = (TextView) view.findViewById(R.id.chat_from_item_name);
                holder.holderTvContent = (TextView) view.findViewById(R.id.chat_from_item_content);
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.chat_to_item, null);
                holder.holderTvTime = (TextView) view.findViewById(R.id.chat_to_item_time);
                holder.holderIvIcon = (ImageView) view.findViewById(R.id.chat_to_item_icon);
                holder.holderTvName = (TextView) view.findViewById(R.id.chat_to_item_name);
                holder.holderTvContent = (TextView) view.findViewById(R.id.chat_to_item_content);
            }

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.holderTvTime.setText(chatBean.getTime());
        holder.holderIvIcon.setImageBitmap(chatBean.getIcon());
        holder.holderTvName.setText(chatBean.getName());
        holder.holderTvContent.setText(chatBean.getContent());

        return view;
    }

    class ViewHolder {
        TextView holderTvTime;
        ImageView holderIvIcon;
        TextView holderTvName;
        TextView holderTvContent;
    }

}
