package com.example.administrator.apolloblechat.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by apollo on 16-8-7.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> pagerList;

    public MyPagerAdapter(List<View> pagerList) {
        this.pagerList = pagerList;
    }

    @Override
    public int getCount() {
//                return pagerList.size();
        //取大值，无限滑动
        return pagerList.size()+1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView(pagerList.get(position));
        container.removeView(pagerList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

      /*  position %= pagerList.size();
        if (position < 0) {
            position += pagerList.size();
        }
        ImageView imageView = (ImageView) pagerList.get(position);
        ViewParent vp = imageView.getParent();
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(imageView);
        }*/
        if (position < pagerList.size()){
            View view = pagerList.get(position);
            container.addView(view);
            return view;
        }else return null;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
