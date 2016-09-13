package com.example.administrator.apolloblechat.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.apolloblechat.R;


public class ToggleView extends View implements View.OnClickListener {

    /**
     * 做为背景的图片
     */
    private Bitmap backgroundBitmap;
    /**
     * 可以滑动的图片
     */
    private Bitmap slideBtn;
    /**
     * 滑动按钮的左边届
     */
    private Paint paint;

    /**
     * 滑动按钮的左边届
     */
    private float slideBtn_left;

    private boolean isChecked = true;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public OnClickCallBack onClickCallBack;

    public void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        this.onClickCallBack = onClickCallBack;
    }

    /**
     * 在代码里面创建对象的时候，使用此构造方法
     */
    public ToggleView(Context context) {
        super(context);
    }

    /**
     * 在布局文件中声名的view，创建时由系统自动调用。
     *
     * @param context 上下文对象
     * @param attrs   属性集
     */
    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }

    /**
     * 初始化图片
     */
    private void initView() {
        backgroundBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.icon_toggle_green_oval);
        slideBtn = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.icon_toggle_green_circle);
        this.initSlidingLeft();
        paint = new Paint();
        // 打开抗锯齿
        paint.setAntiAlias(true);
        this.setOnClickListener(this);
    }

    /**
     * 设置可滑动按钮的左边距
     */
    private void initSlidingLeft() {
        if (isChecked)
            slideBtn_left = backgroundBitmap.getWidth() - slideBtn.getWidth();
        else
            slideBtn_left = 0;
    }

    /**
     * 测量尺寸时的回调方法
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 设置当前view的大小 width :view的宽度 height :view的高度 （单位：像素）
         */
        setMeasuredDimension(backgroundBitmap.getWidth(), backgroundBitmap.getHeight());

    }

    // 确定位置的时候调用此方法
    // 自定义view的时候，作用不大
    // @Override
    // protected void onLayout(boolean changed, int left, int top, int right,
    // int bottom) {
    // super.onLayout(changed, left, top, right, bottom);
    // }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制 背景
        /*
         * backgroundBitmap 要绘制的图片 left 图片的左边届 top 图片的上边届 paint 绘制图片要使用的画笔
		 */
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        // 绘制 可滑动的按钮
        canvas.drawBitmap(slideBtn, slideBtn_left, 0, paint);
    }

    @Override
    public void onClick(View v) {
        this.flushState();
        if (onClickCallBack != null) {
            onClickCallBack.onClick(v, isChecked);
        }

    }

    public interface OnClickCallBack {
        public void onClick(View toggleView, boolean isChanged);
    }

    /**
     * 判断是否发生拖动， 如果拖动了，就不再响应 onclick 事件
     */
    private boolean isDrag = false;

    /**
     * down 事件时的x值
     */
    private int firstX;
    /**
     * touch 事件的上一个x值
     */
    private int lastX;

    /**
     * 去除滑动
     */
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = lastX = (int) event.getX();
                isDrag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 判断是否发生拖动
                if (Math.abs(event.getX() - firstX) > 5) {
                    isDrag = true;
                }
                // 计算 手指在屏幕上移动的距离
                int dis = (int) (event.getX() - lastX);
                // 将本次的位置 设置给lastX
                lastX = (int) event.getX();
                // 根据手指移动的距离，改变slideBtn_left 的值
                slideBtn_left = slideBtn_left + dis;
                break;
            case MotionEvent.ACTION_UP:
                // 在发生拖动的情况下，根据最后的位置，判断当前开关的状态
                if (isDrag) {
                    int maxLeft = backgroundBitmap.getWidth() - slideBtn.getWidth(); // slideBtn
                    // 左边届最大值
                *//*
                 * 根据 slideBtn_left 判断，当前应是什么状态
				 *//*
                    if (slideBtn_left > maxLeft / 2) { // 此时应为 打开的状态
                        isChecked = true;
                    } else {
                        isChecked = false;
                    }
                    flushState();
                }
                break;
        }
        flushView();
        return true;
    }
*/

    /**
     * 刷新当前状态
     */
    public void flushState() {
        isChecked = !isChecked;
        setState(isChecked);
    }


    public void setState(boolean isChecked) {
        if (isChecked) {
            backgroundBitmap = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_toggle_green_oval);
            slideBtn = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_toggle_green_circle);
            slideBtn_left = backgroundBitmap.getWidth() - slideBtn.getWidth();
        } else {
            backgroundBitmap = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_toggle_gray_oval);
            slideBtn = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_toggle_gray_circle);
            slideBtn_left = 0;
        }
        flushView();
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (isChecked) {
                slideBtn_left -= 10;
            } else {
                slideBtn_left -= 10;
            }
        }
    };

    /**
     * 刷新当前视力
     */
    private void flushView() {
        /*
         * 对 slideBtn_left 的值进行判断 ，确保其在合理的位置 即 0<=slideBtn_left <= maxLeft
		 */

        int maxLeft = backgroundBitmap.getWidth() - slideBtn.getWidth(); // slideBtn
        // 左边届最大值

        // 确保 slideBtn_left >= 0
        slideBtn_left = (slideBtn_left > 0) ? slideBtn_left : 0;

        // 确保 slideBtn_left <=maxLeft
        slideBtn_left = (slideBtn_left < maxLeft) ? slideBtn_left : maxLeft;

		/*
         * 刷新当前视图 导致 执行onDraw执行
		 */
        invalidate();
        //onClickCallBack.onClick(this,isChecked);
    }
}
