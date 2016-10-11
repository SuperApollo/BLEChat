package com.example.administrator.apolloblechat.widgets;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;


/**
 * 自定义标题栏
 * <p>
 * Created by Administrator on 2016/8/29.
 */
public class MyTittleBar extends RelativeLayout {

    //左侧箭头和文字父布局
    private RelativeLayout leftLayout;
    //是否显示左侧父布局
    private Boolean isShowLeftlay = false;
    //左侧返回箭头布局
    private ImageView ivBack;
    //左侧返回图标
    private Bitmap backBitmap;
    //是否显示返回箭头
    private boolean isShowBack;
    //左侧文字布局
    private TextView tvLeft;
    //左侧文字内容
    private String leftContent;
    //中间标题布局
    private TextView tvTitle;
    //中间标题内容
    private String title;
    //右侧父布局
    private RelativeLayout rightlayout;
    //是否显示右侧父布局
    private Boolean isShowRightLay = false;
    //右侧文字布局
    private TextView tvRight;
    //右侧文字内容
    private String rightContent;
    //右侧文字布局背景
    private Bitmap rightBitmap;


    private OnBackListener onBackListener;
    private OnRightlayoutListener onRightlayoutListener;
    private ImageView ivRight;


    public RelativeLayout getLeftLayout() {
        return leftLayout;
    }

    public void setLeftLayout(RelativeLayout leftLayout) {
        this.leftLayout = leftLayout;
    }

    public Boolean getShowLeftlay() {
        return isShowLeftlay;
    }

    public void setShowLeftlay(Boolean showLeftlay) {
        isShowLeftlay = showLeftlay;
    }

    public ImageView getIvBack() {
        return ivBack;
    }

    public void setIvBack(ImageView ivBack) {
        this.ivBack = ivBack;
    }

    public Bitmap getBackBitmap() {
        return backBitmap;
    }

    public void setBackBitmap(Bitmap backBitmap) {
        this.backBitmap = backBitmap;
    }

    public boolean isShowBack() {
        return isShowBack;
    }

    public void setShowBack(boolean showBack) {
        isShowBack = showBack;
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public void setTvLeft(TextView tvLeft) {
        this.tvLeft = tvLeft;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RelativeLayout getRightlayout() {
        return rightlayout;
    }

    public void setRightlayout(RelativeLayout rightlayout) {
        this.rightlayout = rightlayout;
    }

    public Boolean getShowRightLay() {
        return isShowRightLay;
    }

    public void setShowRightLay(Boolean showRightLay) {
        isShowRightLay = showRightLay;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public void setTvRight(TextView tvRight) {
        this.tvRight = tvRight;
    }

    public String getRightContent() {
        return rightContent;
    }

    public void setRightContent(String rightContent) {
        this.rightContent = rightContent;
    }

    public Bitmap getRightBitmap() {
        return rightBitmap;
    }

    public void setRightBitmap(Bitmap rightBitmap) {
        this.rightBitmap = rightBitmap;
    }

    public String getLeftContent() {
        return leftContent;
    }

    public void setLeftContent(String leftContent) {
        this.leftContent = leftContent;
    }

    public OnBackListener getOnBackListener() {
        return onBackListener;
    }

    public void setOnBackListener(OnBackListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public OnRightlayoutListener getOnRightlayoutListener() {
        return onRightlayoutListener;
    }

    public void setOnRightlayoutListener(OnRightlayoutListener onRightlayoutListener) {
        this.onRightlayoutListener = onRightlayoutListener;
    }

    public MyTittleBar(Context context) {
        this(context, null);
    }

    public MyTittleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTittleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.mytittlebar);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.mytittlebar_showleftlay:
                    isShowLeftlay = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.mytittlebar_showback:
                    isShowBack = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.mytittlebar_backimg:
                    backBitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.mytittlebar_lefttext:
                    leftContent = typedArray.getString(attr);
                    break;
                case R.styleable.mytittlebar_mytitle:
                    title = typedArray.getString(attr);
                    break;
                case R.styleable.mytittlebar_showrightlay:
                    isShowRightLay = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.mytittlebar_righttext:
                    rightContent = typedArray.getString(attr);
                    break;
                case R.styleable.mytittlebar_rightimg:
                    rightBitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                default:
                    break;
            }
        }

        typedArray.recycle();
        initView(context);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView(final Context context) {
        View tittleView = LayoutInflater.from(context).inflate(R.layout.title_layout, this, true);
        leftLayout = (RelativeLayout) tittleView.findViewById(R.id.title_layout_leftlay);
        ivBack = (ImageView) tittleView.findViewById(R.id.title_layout_backimg);
        tvLeft = (TextView) tittleView.findViewById(R.id.title_layout_lefttext);
        tvTitle = (TextView) tittleView.findViewById(R.id.title_layout_title);
        rightlayout = (RelativeLayout) tittleView.findViewById(R.id.title_layout_rightlay);
        tvRight = (TextView) tittleView.findViewById(R.id.title_layout_righttext);
        ivRight = (ImageView) tittleView.findViewById(R.id.title_layout_rightimg);

        tvTitle.setText(title);

        if (isShowLeftlay) {
            leftLayout.setVisibility(VISIBLE);
            if (isShowBack) {
                ivBack.setVisibility(VISIBLE);
                if (backBitmap != null) {
                    ivBack.setImageBitmap(backBitmap);
                }
            }

            tvLeft.setText(leftContent);

            leftLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onBackListener != null) {
                        onBackListener.onLeftClick();
                    } else {
                        ((Activity) context).finish();
                    }
                }
            });
        }

        if (isShowRightLay) {
            rightlayout.setVisibility(VISIBLE);
            tvRight.setText(rightContent);
            if (rightBitmap != null) {
                ivRight.setImageBitmap(rightBitmap);
            }
            rightlayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRightlayoutListener != null) {
                        onRightlayoutListener.onRightClick();
                    }
                }
            });
        }

    }

    public interface OnBackListener {
        void onLeftClick();
    }

    public interface OnRightlayoutListener {
        void onRightClick();
    }
}
