package com.example.administrator.apolloblechat.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.apolloblechat.utils.ViewUtil;

/*
* 联系人右侧的字母检索
*
* */
public class SidebarView extends View {
    public String[] arrLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private OnLetterClickedListener listener = null;
    private TextView textView_dialog;
    private int isChoosedPosition = -1;

    public void setTextView(TextView textView) {
        textView_dialog = textView;
    }

    public SidebarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 当前view的宽度
        int width = getWidth();
        // 当前view的高度
        int height = getHeight();
        // 当前view中每个字母所占的高度
        int singleTextHeight = height / arrLetters.length;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(ViewUtil.sp2px(getContext(), 15));
        paint.setTypeface(Typeface.DEFAULT);
        for (int i = 0; i < arrLetters.length; i++) {
            paint.setColor(Color.GRAY);
            if (i == isChoosedPosition) {
                paint.setColor(Color.WHITE);
                paint.setFakeBoldText(true);
            }
            float x = (width - paint.measureText(arrLetters[i])) / 2;
            float y = singleTextHeight * (i + 1);
            canvas.drawText(arrLetters[i], x, y, paint);
//			paint.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        int position = (int) (y / getHeight() * arrLetters.length);
        int lastChoosedPosition = isChoosedPosition;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.WHITE);
                if (textView_dialog != null) {
                    textView_dialog.setVisibility(View.GONE);
                }
                isChoosedPosition = -1;
                invalidate();
                break;
            default:
                setBackgroundColor(Color.parseColor("#c0c0c0"));
                if (lastChoosedPosition != position) {
                    if (position >= 0 && position < arrLetters.length) {
                        if (listener != null) {
                            listener.onLetterClicked(arrLetters[position]);
                        }
                        if (textView_dialog != null) {
                            textView_dialog.setVisibility(View.VISIBLE);
                            textView_dialog.setText(arrLetters[position]);
                        }
                        isChoosedPosition = position;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    public interface OnLetterClickedListener {
        public void onLetterClicked(String str);
    }

    public void setOnLetterClickedListener(OnLetterClickedListener listener) {
        this.listener = listener;
    }

}
