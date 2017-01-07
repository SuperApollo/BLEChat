package com.example.administrator.apolloblechat.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.widgets.MyTittleBar;
import com.example.administrator.apolloblechat.zxing.activity.CaptureActivity;
import com.example.administrator.apolloblechat.zxing.encoding.EncodingHandler;

import static android.app.Activity.RESULT_OK;

/**
 * 扫一扫
 * Created by Administrator on 2016/10/9.
 */

public class ScannerFragment extends BaseFragment {

    private MyTittleBar scanner_tittle;
    private static final int REQUEST_CODE_SCAN = 1;
    private ImageView iv_scanner_show;

    @Override
    protected int getViewId() {
        return R.layout.fragment_scanner;
    }

    @Override
    protected void initView(View view) {
        scanner_tittle = queryViewById(view, R.id.scanner_tittle);
        scanner_tittle.setOnBackListener(new MyTittleBar.OnBackListener() {
            @Override
            public void onLeftClick() {
                FragmentUtils.replace(getActivity(), R.id.ll_fragment_container, new MoreFragment());
            }
        });
        queryViewById(view, R.id.btn_scanner_scan, true);
        queryViewById(view, R.id.btn_scanner_create, true);
        iv_scanner_show = queryViewById(view, R.id.iv_scanner_show);

    }

    @Override
    protected boolean hideBottom() {
        return true;
    }

    @Override
    protected Fragment backTo() {
        return new MoreFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            mToastUtil.toaster(scanResult);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scanner_scan://扫描
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;
            case R.id.btn_scanner_create://生成
                try {
//                    Bitmap mBitmap = EncodingHandler.createQRCode("www.baidu.com", 300);
//                    qrcodeImg.setImageBitmap(mBitmap);
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_blechat_logo);
                    Bitmap www = EncodingHandler.createQRCode("www.baidu.com", 600, 600, bitmap);
                    iv_scanner_show.setImageBitmap(www);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
