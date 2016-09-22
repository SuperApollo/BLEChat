package com.example.administrator.apolloblechat.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by apollo on 16-9-22.
 */
public class LooperViewPager extends ViewPager {
    private static final int LOOPER = 0;
    private static final long cycleTime = 3 * 1000L;
    private int mItemCount = 0;
    private int mCurrentItem = 1;

    private boolean isStart = false;

    private IPageChangeListener pageChangeListener;

    public LooperViewPager(Context context) {
        this(context, null);
    }

    public LooperViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (pageChangeListener != null) {
                    pageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (pageChangeListener != null) {
                    // setStart(false);
                    pageChangeListener.onPageSelected(position);
                    //startLooper();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (pageChangeListener != null) {
                    pageChangeListener.onPageScrollStateChanged(state);

                }
            }
        });
    }


    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        mItemCount = adapter.getCount();
    }

    public void startLooper() {
        if (mItemCount == 0) {
            return;
        }
        isStart = true;
        mHandler.sendEmptyMessageDelayed(LOOPER, cycleTime);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == LOOPER && isStart) {
                mCurrentItem = getCurrentItem();
                setCurrentItem((mCurrentItem + 1) % mItemCount);
                mHandler.sendEmptyMessageDelayed(LOOPER, cycleTime);
            }

        }
    };

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setPageChangeListener(IPageChangeListener pageChangeListener) {
        this.pageChangeListener = pageChangeListener;
    }

    public interface IPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);

    }
}
