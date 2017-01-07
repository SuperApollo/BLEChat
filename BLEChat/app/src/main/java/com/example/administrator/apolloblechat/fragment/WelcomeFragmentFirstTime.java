package com.example.administrator.apolloblechat.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.activity.LoginActivity;
import com.example.administrator.apolloblechat.adapter.MyPagerAdapter;
import com.example.administrator.apolloblechat.utils.IntentUtils;
import com.example.administrator.apolloblechat.widgets.LooperViewPager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/20.
 */
public class WelcomeFragmentFirstTime extends BaseFragment {

    private ViewPager vp_splash;
    private LinearLayout point_container;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ArrayList pagerList;
    private ArrayList pagerIds;
    private ImageView[] imageViews;


    @Override
    protected int getViewId() {
        return R.layout.fragment_welcome_first_time;
    }

    @Override
    protected void initView(View view) {
        vp_splash = queryViewById(view, R.id.vp_looper);
        vp_splash.setOffscreenPageLimit(2);
        point_container = queryViewById(view, R.id.point_container);

        prepareData();
        initCircle();

    }

    @Override
    protected boolean hideBottom() {
        return false;
    }

    @Override
    protected Fragment backTo() {
        return null;
    }

    /**
     * 小圆点
     */
    private void initCircle() {
        imageViews = new ImageView[pagerList.size()];
        for (int i = 0; i < pagerList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params);
            imageViews[i] = imageView;
            imageViews[i].setBackgroundResource(i == 0 ? R.mipmap.icon_point_focused : R.mipmap.icon_point_unfocused);
            //将小圆点放入
            point_container.addView(imageViews[i]);
        }
    }

    /**
     * viewpager准备数据
     */
    private void prepareData() {
        pagerList = new ArrayList();
        pagerIds = new ArrayList<>();
        iv1 = new ImageView(mContext);
        iv2 = new ImageView(mContext);
        iv3 = new ImageView(mContext);
        iv4 = new ImageView(mContext);

        iv1.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        iv2.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        iv3.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        iv4.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);
        iv3.setScaleType(ImageView.ScaleType.FIT_XY);
        iv4.setScaleType(ImageView.ScaleType.FIT_XY);

//        iv1.setImageResource(R.mipmap.icon_vp_first);
//        iv2.setImageResource(R.mipmap.icon_vp_second);
//        iv3.setImageResource(R.mipmap.icon_vp_third);
//        iv4.setImageResource(R.mipmap.icon_vp_fourth);

//        LayoutInflater inflater = LayoutInflater.from(mContext);

//        pagerList.add(inflater.inflate(R.layout.welcome_first, null));
//        pagerList.add(inflater.inflate(R.layout.welcome_second, null));
//        pagerList.add(inflater.inflate(R.layout.welcome_third, null));
//        pagerList.add(inflater.inflate(R.layout.welcome_fourth, null));

        pagerList.add(iv1);
        pagerList.add(iv2);
        pagerList.add(iv3);
        pagerList.add(iv4);

        pagerIds.add(R.mipmap.icon_vp_first);
        pagerIds.add(R.mipmap.icon_vp_second);
        pagerIds.add(R.mipmap.icon_vp_third);
        pagerIds.add(R.mipmap.icon_vp_fourth);


        MyPagerAdapter pagerAdapter = new MyPagerAdapter(pagerList, pagerIds,mContext);
        vp_splash.setAdapter(pagerAdapter);
        vp_splash.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int index = position % pagerList.size();
                for (int i = 0; i < imageViews.length; i++) {
                    if (index != i) {
                        imageViews[i].setBackgroundResource(R.mipmap.icon_point_unfocused);
                    } else {
                        imageViews[i].setBackgroundResource(R.mipmap.icon_point_focused);
                    }

                }
                if (position == pagerList.size()) {
                    IntentUtils.sendIntent(getActivity(), LoginActivity.class);
                    getActivity().finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        vp_splash.startLooper();
    }
}
