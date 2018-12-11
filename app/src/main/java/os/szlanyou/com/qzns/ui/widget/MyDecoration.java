package os.szlanyou.com.qzns.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 自定义RecyclerView的分割线，可以设置分割线颜色和高度，并设置缩进
 */
public class MyDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    //缩进长度，仅水平列表有效
    private int mInset;

    //线条宽度
    private int mDividerHeight;

    //列表方向
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private final Rect mBounds = new Rect();

    //构造，设置默认值
    public MyDecoration(Context context, int orientation) {
        super();

        //默认颜色
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        if (this.mDivider == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();

        //默认缩进
        mInset = 0;

        //默认高度
        mDividerHeight = 2;

        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        } else {
            mOrientation = orientation;
        }
    }

    //设置缩进长度
    public void setInset(int inset) {
        mInset = inset;
    }


    //设置线条宽度
    public void setDividerHeight(int dividerHeight) {
        mDividerHeight = dividerHeight;
    }

    //自定义线条样式
    public void setDivider(Drawable divider) {
        mDivider = divider;
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDividerHeight);
        } else {
            outRect.set(0, 0, mDividerHeight, 0);
        }
    }

    //竖直列表绘制水平线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
            int top = bottom - this.mDivider.getIntrinsicHeight();
            //缩进
            this.mDivider.setBounds(left + mInset, top, right, bottom);
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }

    //水平列表，绘制竖直线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int top;
        int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
            int right = this.mBounds.right + Math.round(child.getTranslationX());
            int left = right - this.mDivider.getIntrinsicWidth();
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }

}
