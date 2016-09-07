package com.example.administrator.apolloblechat.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.example.administrator.apolloblechat.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable[] mDrawables;
    private Drawable mClear;
    private boolean mIsFocus;

    public ClearEditText(Context context) {
        this(context, null);
    }

    /**
     * 构造方法很重要，不加这个很多属性不能在XML里面定义
     *
     * @param context
     * @param attrs
     */
    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mDrawables = getCompoundDrawables();//本方法获取控件上下左右四个方位插入的图片
        mClear = mDrawables[2];
        if (mClear == null) {
            mClear = getResources().getDrawable(R.mipmap.icon_delete);
        }
        mClear.setBounds(0, 0, mClear.getIntrinsicWidth(), mClear.getIntrinsicHeight());
        //设置删除图标默认隐藏
        setClearVisible(false);
        //设置焦点改变监听
        setOnFocusChangeListener(this);
        //设置输入内容改变监听
        addTextChangedListener(this);
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    private void setClearVisible(boolean visible) {
        Drawable right = visible ? mClear : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) &&
                        event.getX() < (getWidth() - getPaddingRight());
                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.mIsFocus = hasFocus;
        if (hasFocus) {
            setClearVisible(getText().length() > 0);
        } else {
            setClearVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (mIsFocus) {
            setClearVisible(text.length() > 0);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }


    /**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
