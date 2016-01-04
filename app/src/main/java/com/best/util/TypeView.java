package com.best.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.best.activity.R;


/**
 * Created by liujie on 2015/12/3.
 */
public class TypeView extends View{
    /**
     * 文本
     */
    private String mTypeText;
    /**
     * 文本的颜色
     */
    private int mTypeColor;
    /**
     * 文本的大小
     */
    private int mTypeTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    public TypeView(Context context) {
        super(context,null);
    }
    public TypeView(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public TypeView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TypeView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.TypeView_typeText:
                    mTypeText = a.getString(attr);
                    break;
                case R.styleable.TypeView_typeColor:
                    // 默认颜色设置为黑色
                    mTypeColor = a.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.TypeView_typeTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTypeTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        a.recycle();  //一定要回收
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTypeTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTypeText, 0, mTypeText.length(), mBound);
    }


    public String getmTypeText() {
        return mTypeText;
    }
    public void setmTypeText(String mTypeText) {
        this.mTypeText = mTypeText;
    }
    public int getmTypeColor() {
        return mTypeColor;
    }
    public void setmTypeColor(int mTypeColor) {
        this.mTypeColor = mTypeColor;
    }
    public int getmTypeTextSize() {
        return mTypeTextSize;
    }
    public void setmTypeTextSize(int mTypeTextSize) {
        this.mTypeTextSize = mTypeTextSize;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(mTypeColor);
        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, getMeasuredWidth()/2, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText(mTypeText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

}
