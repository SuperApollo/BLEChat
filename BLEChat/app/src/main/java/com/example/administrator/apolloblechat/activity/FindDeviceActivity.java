package com.example.administrator.apolloblechat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.adapter.NewBleAdapter;
import com.example.administrator.apolloblechat.adapter.PairedBleAdapter;
import com.example.administrator.apolloblechat.constant.Constants;
import com.example.administrator.apolloblechat.widgets.DividerItemDecoration;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zayh_yf20160909 on 2017/1/9.
 */

public class FindDeviceActivity extends BaseActivity {

    private MyTittleBar mTitle;
    private RecyclerView mRvPairedList;
    private FloatingActionButton mBtnSerach;
    private RecyclerView mRvNewList;
    private BluetoothAdapter mBleAdapter;
    private LinearLayout mPbSearch;
    private List<String> mNewDevices = new ArrayList<>();//扫描到的新设备
    private List<String> mPairedDevices = new ArrayList<>();//已配对的设备
    private PairedBleAdapter mPairedAdapter;
    private NewBleAdapter mNewAdapter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null)
            unregisterReceiver(mReceiver);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_find_device;
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
        mTitle = queryViewById(view, R.id.title_find_device);
        mRvPairedList = queryViewById(view, R.id.rv_paired_list);
        mRvPairedList.setLayoutManager(new LinearLayoutManager(this));
        mRvPairedList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mPairedAdapter = new PairedBleAdapter(this, mPairedDevices);
        mRvPairedList.setAdapter(mPairedAdapter);
        mRvNewList = queryViewById(view, R.id.rv_new_list);
        mRvNewList.setLayoutManager(new LinearLayoutManager(this));
        mRvNewList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mNewAdapter = new NewBleAdapter(this, mNewDevices);
        mRvNewList.setAdapter(mNewAdapter);
        mBtnSerach = queryViewById(view, R.id.btn_search, true);
        mPbSearch = queryViewById(view, R.id.pb_search);

        mBleAdapter = BluetoothAdapter.getDefaultAdapter();
        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBleAdapter.getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevices.add(device.getName() + "\n" + device.getAddress());
            }
            mPairedAdapter.notifyDataSetChanged();
        } else {
            String noDevices = "没有设备配对过";
            mPairedDevices.add(noDevices);
            mPairedAdapter.notifyDataSetChanged();
        }


        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        mPairedAdapter.setOnItemClickListner(new PairedBleAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                connect((TextView) view, position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mNewAdapter.setOnItemClickListner(new NewBleAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                connect((TextView) view, position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        mTitle.setOnRightlayoutListener(new MyTittleBar.OnRightlayoutListener() {
            @Override
            public void onRightClick() {
                showDialog();
            }
        });
        doDiscovery();

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FindDeviceActivity.this);
        builder.setTitle("使蓝牙可见");
        builder.setMessage("确定让本机蓝牙可见吗？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ensureDiscoverable();
            }
        });
        builder.show();
    }

    /**
     * 让本设备可见
     */
    private void ensureDiscoverable() {
        if (mBleAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    private void connect(TextView view, int position) {
        mToastUtil.toaster(position + "");
        // Cancel discovery because it's costly and we're about to connect
        mBleAdapter.cancelDiscovery();

        // Get the device MAC address, which is the last 17 chars in the View
        String info = view.getText().toString();
        String address = info.substring(info.length() - 17);

        // Create the result Intent and include the MAC address
        Intent intent = new Intent();
        //address is the Mac address which you want to connect
        intent.putExtra(Constants.EXTRA_DEVICE_ADDRESS, address);

        // Set result and finish this Activity
        FindDeviceActivity.this.setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_search:
                doDiscovery();
                break;
        }
    }

    /**
     * 搜索
     */
    private void doDiscovery() {
        mTitle.getTvTitle().setText("扫描设备中");
        mNewDevices.clear();
        if (mBleAdapter.isDiscovering()) {
            mBleAdapter.cancelDiscovery();
        }
        mBleAdapter.startDiscovery();
        mPbSearch.setVisibility(View.VISIBLE);
    }

    /**
     * 搜索完成后的接受者
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mTitle.getTvTitle().setText("蓝牙列表");
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevices.add(device.getName() + "\n" + device.getAddress());
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                mPbSearch.setVisibility(View.GONE);
                mTitle.setTitle("选择要连接的设备");
                if (mNewDevices.size() == 0) {
                    String noDevices = "没有发现设备";
                    mNewDevices.add(noDevices);
                }
            }
            mNewAdapter.notifyDataSetChanged();
        }
    };
}
