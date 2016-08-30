package com.example.administrator.apolloblechat.widgets;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.apolloblechat.R;


/**
 * Created by Administrator on 2016/8/29.
 */
public class MyTittleBar extends RelativeLayout {

    private RelativeLayout backLayout;
    private TextView tvTitle;
    private ImageView backImg;
    private RelativeLayout rightlayout;
    private TextView rightText;

    private String title;
    private Bitmap bitmap;
    private Boolean isShowBacklay = false;

    private String rightContent;
    private Bitmap rightBitmap;
    private Boolean isShowRight = false;


    private OnBackListener onBackListener;
    private OnRightlayoutListener onRightlayoutListener;

    public RelativeLayout getBackLayout() {
        return backLayout;
    }

    public void setBackLayout(RelativeLayout backLayout) {
        this.backLayout = backLayout;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public ImageView getBackImg() {
        return backImg;
    }

    public void setBackImg(ImageView backImg) {
        this.backImg = backImg;
    }

    public RelativeLayout getRightlayout() {
        return rightlayout;
    }

    public void setRightlayout(RelativeLayout rightlayout) {
        this.rightlayout = rightlayout;
    }

    public TextView getRightText() {
        return rightText;
    }

    public void setRightText(TextView rightText) {
        this.rightText = rightText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Boolean getIsShowBacklay() {
        return isShowBacklay;
    }

    public void setIsShowBacklay(Boolean isShowBacklay) {
        this.isShowBacklay = isShowBacklay;
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

    public Boolean getIsShowRight() {
        return isShowRight;
    }

    public void setIsShowRight(Boolean isShowRight) {
        this.isShowRight = isShowRight;
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
                case R.styleable.mytittlebar_title:
                    title = typedArray.getString(attr);
                    break;
                case R.styleable.mytittlebar_showbacklay:
                    isShowBacklay = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.mytittlebar_backimg:
                    bitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.mytittlebar_showrightlay:
                    isShowRight = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.mytittlebar_righttext:
                    rightContent = typedArray.getString(attr);
                    break;
                case R.styleable.mytittlebar_righttextbg:
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
        backLayout = (RelativeLayout) tittleView.findViewById(R.id.title_layout_backlay);
        tvTitle = (TextView) tittleView.findViewById(R.id.title_layout_title);
        backImg = (ImageView) tittleView.findViewById(R.id.title_layout_backimg);
        rightlayout = (RelativeLayout) tittleView.findViewById(R.id.title_layout_rightlay);
        rightText = (TextView) tittleView.findViewById(R.id.title_layout_righttext);

        tvTitle.setText(title);
        if (isShowBacklay) {
            backLayout.setVisibility(VISIBLE);
            if (bitmap != null) {
                backImg.setImageBitmap(bitmap);
            }
            backLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onBackListener != null) {
                        onBackListener.onBackClick();
                    } else {
                        ((Activity) context).finish();
                    }
                }
            });
        }
        if (isShowRight) {
            rightlayout.setVisibility(VISIBLE);
            rightText.setText(rightContent);
            if (rightBitmap != null) {
                rightText.setBackground(new BitmapDrawable(rightBitmap));
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
        void onBackClick();
    }

    public interface OnRightlayoutListener {
        void onRightClick();
    }
}
