package com.example.administrator.apolloblechat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.apolloblechat.utils.BitmapUtils;
import com.example.administrator.apolloblechat.utils.ViewUtil;

import java.util.List;

/**
 * Created by apollo on 16-8-7.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> pagerList;//splash页imageview集合
    private List<Integer> pagerIds;//splash页图片资源id集合
    private Context context;
    private Bitmap bitmap;

    public MyPagerAdapter(List<View> pagerList, List pagerIds, Context context) {
        this.pagerList = pagerList;
        this.pagerIds = pagerIds;
        this.context = context;
    }

    @Override
    public int getCount() {
//                return pagerList.size();
        return pagerList.size() + 1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView(pagerList.get(position));
        if (position < pagerList.size()) {
            container.removeView(pagerList.get(position));
            bitmap.recycle();
        }

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
        if (position < pagerList.size()) {
            ImageView view = (ImageView) pagerList.get(position);
            bitmap = BitmapFactory.decodeResource(context.getResources(), pagerIds.get(position));
//            view.setImageResource(pagerIds.get(position));
            int width = ViewUtil.getScreenWidth(context);
            int height = ViewUtil.getScreenHeight(context);
            view.setImageBitmap(BitmapUtils.ratio(bitmap, width, height));//压缩bitmap
            container.addView(view);
            return view;
        } else return null;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
