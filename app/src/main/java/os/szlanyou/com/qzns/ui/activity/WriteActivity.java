package os.szlanyou.com.qzns.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.litepal.LitePal;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.base.BaseActivity;
import os.szlanyou.com.qzns.model.Contants;
import os.szlanyou.com.qzns.model.bean.WriteData;
import os.szlanyou.com.qzns.util.TextUtils;


/**
 * Author: qzns木雨
 * Date:2018/12/6
 * Description: 编辑界面
 */
public class WriteActivity extends BaseActivity implements View.OnClickListener {

    private CoordinatorLayout mCoordinatorLayout;

    private AppBarLayout mAppBarLayout;

    private ImageView backIV;

    private EditText editText;

    WriteData mWriteData;

    //判断是否内容进行了变动
    private boolean isEdit;

    private static final String TAG = "WriteActivity";

    //数据传输的标志位
    private final static String TAG_WRITE_DATA = "writeData";
    private final static long NONE_WRITE_DATA = -1;

    private boolean isKeyboardShow;
    private boolean isInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        init();
    }

    private void init() {
        isEdit = false;
        isInit = true;
        isKeyboardShow = false;
        backIV = (ImageView) findViewById(R.id.back_iv);
        backIV.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.write_et);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.layout_myCoordinatorLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.write_control_bar);

        Drawable background = ContextCompat.getDrawable(this, R.drawable.writebackground);
        background.setColorFilter(ContextCompat.getColor(this, R.color.colorBackgroundFilter), PorterDuff.Mode.LIGHTEN);
//        background.setColorFilter(Color.WHITE, PorterDuff.Mode.LIGHTEN);
        mCoordinatorLayout.setBackground(background);

        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
            }
        }

        //获取WriteData的savetime，新建页面为-1，否则作为数据库查询字段取出数据
        Intent intent = getIntent();
        long saveTime = intent.getLongExtra(TAG_WRITE_DATA, NONE_WRITE_DATA);
        Log.d(TAG, "initConfig saveTime: " + saveTime);
        if (saveTime != NONE_WRITE_DATA) {
            mWriteData = LitePal.where("saveTime = ?", Long.toString(saveTime)).findFirst(WriteData.class);
        }
        if (null == mWriteData) {
            mWriteData = new WriteData();
        }

        Log.d(TAG, "init mWriteData isSaved: " + mWriteData.isSaved());

        String content = mWriteData.getContent();
        if (!TextUtils.isEmpty(content)) {
            editText.setText(content);
        }

        //添加内容变动监听
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isEdit = true;
            }
        });

        /*通过布局改变进行键盘弹出/隐藏判断
         * */
        editText.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                if (isKeyboardShow || isInit) {
                    Log.d(TAG, "onLayoutChange 键盘关闭: ");
                    isInit = false;
                    isKeyboardShow = false;
                    editText.clearFocus();
                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            mAppBarLayout.setExpanded(true);
                        }
                    });
                } else {
                    Log.d(TAG, "onLayoutChange 键盘开启: ");
                    isKeyboardShow = true;
                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            mAppBarLayout.setExpanded(false);
                        }
                    });
                }
            }
        });
    }

    //启动编辑界面
    public static void actionStartForResult(Context context) {
        actionStartForResult(context, NONE_WRITE_DATA);
    }

    //启动编辑界面
    public static void actionStartForResult(Context context, long savaTime) {
        Intent intent = new Intent(context, WriteActivity.class);
        intent.putExtra(TAG_WRITE_DATA, savaTime);
        ((Activity) context).startActivityForResult(intent, Contants.CODE_FOR_WRITE_RESULT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                Log.d(TAG, "onClick back_iv: ");
                saveAndLogout();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        saveAndLogout();
    }

    //检查保存文本数据，并退出
    private void saveAndLogout() {
        String content = editText.getText().toString();
        Intent intent = new Intent();
        boolean isNeedFresh = false;
        if (TextUtils.isEmpty(content)) {
            if (mWriteData.isSaved()) {
                Log.d(TAG, "saveAndLogout: ");
                mWriteData.delete();
                isNeedFresh = true;
            }
        } else {
            mWriteData.setContent(content);
            if (isEdit) {
                //有修改才更改保存时间
                mWriteData.setSaveTime(System.currentTimeMillis());
            }
            mWriteData.save();
            isNeedFresh = true;
        }
        intent.putExtra(Contants.NAME_RESULT_DATA, isNeedFresh);
        setResult(RESULT_OK, intent);
        finish();
    }


}
