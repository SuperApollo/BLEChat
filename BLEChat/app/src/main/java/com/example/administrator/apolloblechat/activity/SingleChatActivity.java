package com.example.administrator.apolloblechat.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.ChatAdapter;
import com.example.administrator.apolloblechat.bean.ChatBean;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.constant.Constants;
import com.example.administrator.apolloblechat.service.BluetoothChatService;
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
    private ListView mLv_chatlist;
    private RadioGroup rg_bottom;
    private MyTittleBar chat_title;
    private EditText mEt_chat_input;
    private ChatAdapter mChatAdapter;
    private List<ChatBean> dataList;
    private int iconFrom;
    private final String TAG = getClass().getSimpleName();
    private int iconTo;
    private BluetoothAdapter mBluetoothAdapter = null;
    private static final int REQUEST_ENABLE_BT = 3;
    private BluetoothChatService mChatService;
    private List<ChatBean> mChatBeans = new ArrayList<>();
    /**
     * 连接上的蓝牙设备的名字
     */
    private String mConnectedDeviceName = null;
    private StringBuffer mOutStringBuffer;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            setStatus(1, mConnectedDeviceName, "连接到  " + mConnectedDeviceName);

                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            setStatus(2, "连接中。。。", null);
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            setStatus(3, "无连接", null);
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    //mConversationArrayAdapter.add("Me:  " + writeMessage);
                    send(writeMessage);
                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    receive(readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != this) {
                        mToastUtil.toaster("Connected to " + mConnectedDeviceName);
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != this) {
                        mToastUtil.toaster(msg.getData().getString(Constants.TOAST));
                    }
                    break;
            }
        }
    };
    private Button mBtnSend;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //获得本地蓝牙BluetoothAdapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //判断有没有蓝牙设备
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "设备没有蓝牙模块");
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** 打开蓝牙设备*/
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else if (mChatService == null) {
            setupChat();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChatService != null) {
            mChatService.stop();
        }
    }

    private void setupChat() {
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 发送消息的相关处理*/
                sendMessage();
            }
        });

        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService = new BluetoothChatService(this, mHandler);

        // Initialize the buffer for outgoing messages
        mOutStringBuffer = new StringBuffer("");


    }

    private void setStatus(int ok, String Title, String subTitle) {
        chat_title.setTitle(Title);
    }

    private void send(String msg) {
        ChatBean bean = new ChatBean();
        bean.setTime(TimeUtils.getCurrentTime());
        bean.setName("黄蓉");
        bean.setIcon(iconTo);
        bean.setContent(msg);
        bean.setIsFrom(false);
        mChatBeans.add(bean);
        mChatAdapter.notifyDataSetChanged();
        mLv_chatlist.setSelection(mChatBeans.size() - 1);

    }

    private void receive(String msg) {
        ChatBean bean = new ChatBean();
        bean.setTime(TimeUtils.getCurrentTime());
        bean.setName("郭靖");
        bean.setIcon(iconFrom);
        bean.setContent(msg);
        bean.setIsFrom(true);
        mChatBeans.add(bean);
        mChatAdapter.notifyDataSetChanged();
        mLv_chatlist.setSelection(mChatBeans.size() - 1);
    }

    private void sendMessage() {
        if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
            Toast.makeText(this, "未连接蓝牙设备！", Toast.LENGTH_SHORT).show();
            mEt_chat_input.setText("");
            return;
        }
        String contString = mEt_chat_input.getText().toString();
        if (contString.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = contString.getBytes();
            mChatService.write(send);
            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
            mEt_chat_input.setText(mOutStringBuffer);
        }
    }

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

        mLv_chatlist = queryViewById(view, R.id.lv_singlechat_list);
        dataList = getData();
        mChatAdapter = new ChatAdapter(mContext, dataList);
        mLv_chatlist.setAdapter(mChatAdapter);
        mLv_chatlist.setSelection(dataList.size() - 1);

        mEt_chat_input = queryViewById(view, R.id.et_singlechat_input);
        mEt_chat_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String send = mEt_chat_input.getText().toString().trim();
                    if (!TextUtils.isEmpty(send)) {
                        saveAndUpdateSend(send);
                    }
                    mEt_chat_input.setText("");

                    return true;
                }
                return false;
            }
        });

        mBtnSend = queryViewById(view, R.id.btn_single_chat_send);
    }

    private List<ChatBean> getData() {

        for (int i = 0; i < 10; i++) {
            ChatBean chatBean = null;
            iconFrom = R.mipmap.icon_gj;
            iconTo = R.mipmap.icon_hr;
            if (i % 2 == 0) {
                chatBean = new ChatBean(TimeUtils.getCurrentTime(), iconFrom, "郭靖", "蓉儿，吃了吗？", true);
            } else {
                chatBean = new ChatBean(TimeUtils.getCurrentTime(), iconTo, "黄蓉", "靖哥哥，你的降龙十八掌练得怎么样了？", false);
            }
            mChatBeans.add(chatBean);
        }

        try {
            ChatBean savedBean = SharedPreferencesUtils.getObject(AppConfig.CHATBEN_NAME);
            if (savedBean != null)
                mChatBeans.add(savedBean);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("ChatFragment", "读取存储聊天信息对象失败==========");
        }

        return mChatBeans;
    }

    private void saveAndUpdateSend(String send) {
        ChatBean sendBean = new ChatBean(TimeUtils.getCurrentTime(), iconTo, "我", send, false);
        dataList.add(sendBean);
        mChatAdapter.notifyDataSetChanged();
        mLv_chatlist.setSelection(dataList.size() - 1);

        try {
            SharedPreferencesUtils.putObject(AppConfig.CHATBEN_NAME, sendBean);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("ChatFragment", "存储发送消息对象失败================");
        }

    }
}
