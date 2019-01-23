package os.szlanyou.com.qzns.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import os.szlanyou.com.qzns.R;


/**
 * Author: qzns木雨
 * Date:2018/12/11
 * Description: 自定义绘制时间显示控件
 */
public class TimeView extends View {

    private Paint mLinePaint;
    private Paint mMonthPaint;
    private Paint mDatePaint;
    private float mViewWidth;
    private float mViewHeight;

    private String mouth;
    private String date;

    SimpleDateFormat mMothDateFormat;
    SimpleDateFormat mDateDateFormat;

    private static final String TAG = "TimeView";


    public TimeView(Context context) {
        super(context);
        initPaint(context);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);

    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);

    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint(context);
    }

    private void initPaint(Context context) {
        Log.d(TAG, "initPaint: ");
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(ContextCompat.getColor(context, R.color.colorTimeDecoration));
        mLinePaint.setStrokeWidth(2);

        mMonthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMonthPaint.setColor(ContextCompat.getColor(context, R.color.colorMoth));
        mMonthPaint.setStrokeWidth(1);

        mDatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDatePaint.setColor(ContextCompat.getColor(context, R.color.colorDate));
        mDatePaint.setStrokeWidth(1);

        mMothDateFormat = new SimpleDateFormat("MM");
        mDateDateFormat = new SimpleDateFormat("dd");

        mouth = "00";
        date = "00";
    }

    public void setTime(long systemTime) {

        mouth = mMothDateFormat.format(new Date(systemTime));
        date = mDateDateFormat.format(new Date(systemTime));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(mViewWidth * 3 / 4, mViewHeight / 12, mViewWidth / 4, mViewHeight * 11 / 12, mLinePaint);

        if (mouth.length() > 1) {
            canvas.drawText(mouth, 0, mViewHeight / 2, mMonthPaint);
        } else if (mouth.length() == 1) {
            canvas.drawText(mouth, mViewHeight / 8, mViewHeight / 2, mMonthPaint);
        }

        canvas.drawText(date, mViewWidth / 2, mViewHeight, mDatePaint);
    }

    //确定View大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mMonthPaint.setTextSize(mViewHeight / 2);
        mDatePaint.setTextSize(mViewHeight * 2 / 5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
