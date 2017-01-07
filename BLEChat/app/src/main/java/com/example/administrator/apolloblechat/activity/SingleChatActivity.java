package com.example.administrator.apolloblechat.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.ChatAdapter;
import com.example.administrator.apolloblechat.bean.ChatBean;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.utils.SharedPreferencesUtils;
import com.example.administrator.apolloblechat.utils.TimeUtils;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zayh_yf20160909 on 2017/1/7.
 */

public class SingleChatActivity extends BaseActivity {
    private ListView lv_chatlist;
    private RadioGroup rg_bottom;
    private MyTittleBar chat_title;
    private EditText et_chat_input;
    private ChatAdapter chatAdapter;
    private List<ChatBean> dataList;
    private int iconFrom;
    private int iconTo;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_single_chat;
    }

    @Override
    protected boolean fullScreen() {
        return false;
    }

    @Override
    protected boolean changeSystemBar() {
        return true;
    }

    @Override
    protected void initView(View view) {
        chat_title = queryViewById(view, R.id.single_chat_title);
        chat_title.setOnBackListener(new MyTittleBar.OnBackListener() {
            @Override
            public void onLeftClick() {
                SingleChatActivity.this.finish();
            }
        });

        lv_chatlist = queryViewById(view, R.id.lv_singlechat_list);
        dataList = getData();
        chatAdapter = new ChatAdapter(mContext, dataList);
        lv_chatlist.setAdapter(chatAdapter);
        lv_chatlist.setSelection(dataList.size() - 1);

        et_chat_input = queryViewById(view, R.id.et_singlechat_input);
        et_chat_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String send = et_chat_input.getText().toString().trim();
                    if (!TextUtils.isEmpty(send)) {
                        saveAndUpdateSend(send);
                    }
                    et_chat_input.setText("");

                    return true;
                }
                return false;
            }
        });
    }

    private List<ChatBean> getData() {
        List<ChatBean> chatBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChatBean chatBean = null;
            iconFrom = R.mipmap.icon_gj;
            iconTo = R.mipmap.icon_hr;
            if (i % 2 == 0) {
                chatBean = new ChatBean("2016年9月2日10:04:47", iconFrom, "郭靖", "蓉儿，吃了吗？", true);
            } else {
                chatBean = new ChatBean("2016年9月2日10:08:50", iconTo, "黄蓉", "靖哥哥，你的降龙十八掌练得怎么样了？", false);
            }
            chatBeans.add(chatBean);
        }

        try {
            ChatBean savedBean = SharedPreferencesUtils.getObject(AppConfig.CHATBEN_NAME);
            if (savedBean != null)
                chatBeans.add(savedBean);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("ChatFragment", "读取存储聊天信息对象失败==========");
        }

        return chatBeans;
    }

    private void saveAndUpdateSend(String send) {
        ChatBean sendBean = new ChatBean(TimeUtils.getCurrentTime(), iconTo, "我", send, false);
        dataList.add(sendBean);
        chatAdapter.notifyDataSetChanged();
        lv_chatlist.setSelection(dataList.size() - 1);

        try {
            SharedPreferencesUtils.putObject(AppConfig.CHATBEN_NAME, sendBean);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("ChatFragment", "存储发送消息对象失败================");
        }

    }
}
