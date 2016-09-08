package com.example.administrator.apolloblechat.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.ResUtils;
import com.example.administrator.apolloblechat.utils.ViewUtil;


/**
 * Created by Administrator on 2015/12/7.
 */
public class ItemView extends LinearLayout {
    private View view;
    private ImageView img;
    private TextView tv;
    private int defaultWidth = 480;
    private int paddingleft = 40;
    private BadgeView badgeView;

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public ItemView(Context context) {
        this(context, null);
    }


    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        view = LayoutInflater.from(context).inflate(R.layout.base_itemview, null);
        img = (ImageView) view.findViewById(R.id.imageView);
        tv = (TextView) view.findViewById(R.id.textView);
        // addBadgeView(context);
        defaultWidth = ViewUtil.getScreenWidth(context) - img.getWidth() - paddingleft - 20;
        tv.setWidth(defaultWidth);
        tv.setPadding(paddingleft, 0, 0, 0);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        setText("我的消息");
        setTextColor(ResUtils.getColor(R.color.black_5a5));
        setImg(ResUtils.getDrawable(R.drawable.arrow_right));
        this.addView(view);
    }

    /****
     * 设置内容
     *
     * @param text
     */
    public void setText(String text) {
        tv.setText(text);
    }

    /****
     * 设置内容
     *
     * @param size
     */
    public void setTextSize(int size) {
        tv.setTextSize(size);
    }

    /****
     * 设置字体的颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        tv.setTextColor(color);
    }

    public void setImg(Drawable drawable) {
        img.setImageDrawable(drawable);
    }

    /***
     * @param drawable
     */
    public void setTextDrawable(int drawable) {
        Drawable img = ResUtils.getDrawable(drawable);
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        tv.setCompoundDrawables(img, null, null, null);
        tv.setCompoundDrawablePadding(20);
    }

    public void addBadgeView(Context context, int count) {
        badgeView = new BadgeView(context);
        badgeView.setTargetView(tv);

        badgeView.setBackground(12, ResUtils.getColor(R.color.red));
        badgeView.setBadgeCount(count);
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.CENTER);
        badgeView.setPadding(8, 1, 8, 1);
        badgeView.setBadgeMargin(0, 0, 180, 0);
    }

    public void removeBadgeView() {

        badgeView.setHideOnNull(true);
        badgeView.setVisibility(View.GONE);
        invalidate();
    }


}
