package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.bean.ContactBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {

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

        if (TextUtils.isEmpty(contactBean.getFirstWord() + "")) {
            holder.tvLetter.setVisibility(View.GONE);
        } else {
            holder.tvLetter.setText(contactBean.getFirstWord());
            holder.tvLetter.setVisibility(View.VISIBLE);
        }

        holder.ivIcon.setImageDrawable(contactBean.getIcon());
        holder.tvName.setText(contactBean.getName());

        return view;
    }

    private class ViewHolder {
        ImageView ivIcon;
        TextView tvLetter, tvName;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    /**
     * 做字母索引的时候常常会用到SectionIndexer这个接口
     * 1. getSectionForPosition() 通过该项的位置，获得所在分类组的索引号
     * 2. getPositionForSection() 根据分类列的索引号获得该序列的首个位置
     *
     * @param sectionIndex
     * @return
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String firstLetter = mData.get(i).getFirstWord();
            if (firstLetter.length()==0)
                continue;
            char firstChar = firstLetter.charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 根据position获取分类的首字母的Char ascii值
     *
     * @param position
     * @return
     */
    @Override
    public int getSectionForPosition(int position) {
        return mData.get(position).getFirstWord().charAt(0);
    }


}
