package com.example.administrator.apolloblechat.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.ChatAdapter;
import com.example.administrator.apolloblechat.bean.ChatBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ChatFragment extends BaseFragment {

    private ListView lv_chatlist;

    @Override
    protected int getViewId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView(View view) {
        lv_chatlist = queryViewById(view, R.id.lv_chatlist);
        ChatAdapter chatAdapter = new ChatAdapter(getContext(),getData());
        lv_chatlist.setAdapter(chatAdapter);
    }


    private List<ChatBean> getData() {
        List<ChatBean> chatBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChatBean chatBean = null;
            Bitmap iconFrom = BitmapFactory.decodeResource(getResources(),R.drawable.icon_blechat_logo);
            Bitmap iconTo = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tab_item_chat);
            if (i % 2 == 0){
                chatBean = new ChatBean("2016年9月2日10:04:47",iconFrom,"郭靖","蓉儿，吃了吗？",true);
            }else {
                chatBean = new ChatBean("2016年9月2日10:08:50",iconTo,"黄蓉","靖哥哥，你的降龙十八掌练得怎么样了？",false);
            }
            chatBeans.add(chatBean);
        }

        return chatBeans;
    }
}
