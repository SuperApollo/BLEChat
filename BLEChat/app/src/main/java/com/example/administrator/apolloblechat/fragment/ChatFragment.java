package com.example.administrator.apolloblechat.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.ChatAdapter;
import com.example.administrator.apolloblechat.bean.ChatBean;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ChatFragment extends BaseFragment {

    private ListView lv_chatlist;
    private RadioGroup rg_bottom;
    private MyTittleBar chat_title;

    @Override
    protected int getViewId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView(View view) {
        //隐藏底部
        rg_bottom = (RadioGroup) getActivity().findViewById(R.id.rg_bottom);
        rg_bottom.setVisibility(View.GONE);

        chat_title = queryViewById(view, R.id.chat_title);
        chat_title.setOnBackListener(new MyTittleBar.OnBackListener() {
            @Override
            public void onBackClick() {
                rg_bottom.setVisibility(View.VISIBLE);
                FragmentUtils.replace(getActivity(), R.id.ll_fragment_container, new ContactFragment());
            }
        });

        lv_chatlist = queryViewById(view, R.id.lv_chatlist);
        ChatAdapter chatAdapter = new ChatAdapter(getContext(), getData());
        lv_chatlist.setAdapter(chatAdapter);



    }


    private List<ChatBean> getData() {
        List<ChatBean> chatBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChatBean chatBean = null;
            int iconFrom = R.mipmap.icon_gj;
            int iconTo = R.mipmap.icon_hr;
            if (i % 2 == 0) {
                chatBean = new ChatBean("2016年9月2日10:04:47", iconFrom, "郭靖", "蓉儿，吃了吗？", true);
            } else {
                chatBean = new ChatBean("2016年9月2日10:08:50", iconTo, "黄蓉", "靖哥哥，你的降龙十八掌练得怎么样了？", false);
            }
            chatBeans.add(chatBean);
        }

        return chatBeans;
    }
}
