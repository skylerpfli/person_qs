package os.szlanyou.com.qzns.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

/**
 * Author: qzns木雨
 * Date:2018/12/12
 * Description: 自定义CorrdinatorLayout，实现键盘弹出/隐藏监听
 */
public class MyCoordinatorLayout extends CoordinatorLayout {

    public static final int KEYBOARD_STATE_INIT = 0;
    public static final int KEYBOARD_STATE_HIDE = 1;
    public static final int KEYBOARD_STATE_SHOW = 2;

    private OnKeyboadChangeListener mOnKeyboadChangeListener;

    private boolean mHasInit;
    private boolean mHasKeybord;
    private int mHeight;

    public MyCoordinatorLayout(@NonNull Context context) {
        super(context);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (!mHasInit) {
            //初始布局
            mHasInit = true;
            mHeight = b;
            if (null != mOnKeyboadChangeListener) {
                mOnKeyboadChangeListener.onKeyBoardStateChange(KEYBOARD_STATE_INIT);
            }
        } else {
            mHeight = mHeight < b ? b : mHeight;
        }

        //显示键盘
        if (mHasInit && mHeight > b) {
            mHasKeybord = true;
            if (null != mOnKeyboadChangeListener) {
                mOnKeyboadChangeListener.onKeyBoardStateChange(KEYBOARD_STATE_SHOW);
            }
        }

        //隐藏键盘
        if (mHasInit && mHasKeybord && mHeight == b) {
            mHasKeybord = false;
            if (null != mOnKeyboadChangeListener) {
                mOnKeyboadChangeListener.onKeyBoardStateChange(KEYBOARD_STATE_HIDE);
            }
        }

    }

    public void setOnKeyboaddsChangeListener(OnKeyboadChangeListener onKeyboaddsChangeListener) {
        this.mOnKeyboadChangeListener = onKeyboaddsChangeListener;
    }


    public interface OnKeyboadChangeListener {
        public void onKeyBoardStateChange(int state);
    }

}
