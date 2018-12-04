package os.szlanyou.com.qzns.ui.activity;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.ui.fragment.CalendarFragment;
import os.szlanyou.com.qzns.ui.fragment.MainFragment;
import os.szlanyou.com.qzns.ui.fragment.PersonFragment;
import os.szlanyou.com.qzns.ui.fragment.TagFragment;


/**
 * Author: qzns木雨
 * Date:2018/12/3
 * Description: 启动界面，navigationBar通过fragment进行界面切换
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //四个界面
    Fragment mainFragment;
    Fragment calendarFragment;
    Fragment tagFragment;
    Fragment personFragment;
    FragmentManager mFragmentManager;

    private final int NUM_MAIN_FRAGMENT = 0;
    private final int NUM_CALENDAR_FRAGMENT = 1;
    private final int NUM_TAG_FRAGEMENT = 2;
    private final int NUM_PERSON_FRAGMENT = 3;

    //导航栏navigationBar的按钮
    RadioButton mainRB;
    RadioButton calendarRB;
    RadioButton tagRB;
    RadioButton personRB;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MIUISetStatusBarLightMode(this, true);
        initWidget();
    }

    //初始化控件
    private void initWidget() {
        mFragmentManager = getSupportFragmentManager();

        mainRB = (RadioButton) findViewById(R.id.table_main);
        calendarRB = (RadioButton) findViewById(R.id.table_calendar);
        tagRB = (RadioButton) findViewById(R.id.table_tag);
        personRB = (RadioButton) findViewById(R.id.table_person);

        mainRB.setOnClickListener(this);
        calendarRB.setOnClickListener(this);
        tagRB.setOnClickListener(this);
        personRB.setOnClickListener(this);

        showFragment(NUM_MAIN_FRAGMENT);
    }


    /**
     * @param fragmentNum fragment对应的标志数值；
     * @description 切换界面
     */
    private void showFragment(int fragmentNum) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        //进行其余界面的隐藏
        if (null != mainFragment && !mainFragment.isHidden() && fragmentNum != NUM_MAIN_FRAGMENT) {
            transaction.hide(mainFragment);
        }
        if (null != calendarFragment && !calendarFragment.isHidden() && fragmentNum != NUM_CALENDAR_FRAGMENT) {
            transaction.hide(calendarFragment);
        }
        if (null != tagFragment && !tagFragment.isHidden() && fragmentNum != NUM_TAG_FRAGEMENT) {
            transaction.hide(tagFragment);
        }
        if (null != personFragment && !personFragment.isHidden() && fragmentNum != NUM_PERSON_FRAGMENT) {
            transaction.hide(personFragment);
        }

        //如果为空就new,只添加一次，下次使用采用show和hide的方法，节省资源
        switch (fragmentNum) {
            case NUM_MAIN_FRAGMENT:
                if (null == mainFragment) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.FrameLayout, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case NUM_CALENDAR_FRAGMENT:
                if (null == calendarFragment) {
                    calendarFragment = new CalendarFragment();
                    transaction.add(R.id.FrameLayout, calendarFragment);
                } else {
                    transaction.show(calendarFragment);
                }
                break;
            case NUM_TAG_FRAGEMENT:
                if (null == tagFragment) {
                    tagFragment = new TagFragment();
                    transaction.add(R.id.FrameLayout, tagFragment);
                } else {
                    transaction.show(tagFragment);
                }
                break;
            case NUM_PERSON_FRAGMENT:
                if (null == personFragment) {
                    personFragment = new PersonFragment();
                    transaction.add(R.id.FrameLayout, personFragment);
                } else {
                    transaction.show(personFragment);
                }
                break;
            default:
                Log.d(TAG, "showFragment ERRO!: ");
                break;
        }

        transaction.commit();
    }


    //设置状态栏为白底黑字,控制栏透明
    public boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window = activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if (dark) {
                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    } else {
                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "MIUISetStatusBarLightMode erro: " + e.toString());
            }
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_main:
                showFragment(NUM_MAIN_FRAGMENT);
                break;
            case R.id.table_calendar:
                showFragment(NUM_CALENDAR_FRAGMENT);
                break;
            case R.id.table_tag:
                showFragment(NUM_TAG_FRAGEMENT);
                break;
            case R.id.table_person:
                showFragment(NUM_PERSON_FRAGMENT);
                break;
        }
    }
}


