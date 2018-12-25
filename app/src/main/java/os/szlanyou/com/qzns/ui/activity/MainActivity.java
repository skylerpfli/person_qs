package os.szlanyou.com.qzns.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.base.BaseActivity;
import os.szlanyou.com.qzns.model.Contants;
import os.szlanyou.com.qzns.model.bean.WriteData;
import os.szlanyou.com.qzns.ui.fragment.CalendarFragment;
import os.szlanyou.com.qzns.ui.fragment.MainFragment;
import os.szlanyou.com.qzns.ui.fragment.PersonFragment;
import os.szlanyou.com.qzns.ui.fragment.TagFragment;


/**
 * Author: qzns木雨
 * Date:2018/12/3
 * Description: 启动界面，navigationBar通过fragment进行主要界面的切换
 */
public class MainActivity extends BaseActivity {

    //四个界面
    MainFragment mainFragment;
    CalendarFragment calendarFragment;
    TagFragment tagFragment;
    PersonFragment personFragment;
    FragmentManager mFragmentManager;

    private final int NUM_MAIN_FRAGMENT = 0;
    private final int NUM_CALENDAR_FRAGMENT = 1;
    private final int NUM_TAG_FRAGEMENT = 2;
    private final int NUM_PERSON_FRAGMENT = 3;

    //导航栏navigationBar的按钮
    RadioGroup mRadioGroup;
    RadioButton mainRB;
    RadioButton calendarRB;
    RadioButton tagRB;
    RadioButton personRB;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initWidget();
    }

    //初始化控件
    private void initWidget() {
        mFragmentManager = getSupportFragmentManager();

        mRadioGroup = (RadioGroup) findViewById(R.id.navigationBar);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
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
                    default:
                        break;
                }
            }
        });

        mainRB = (RadioButton) findViewById(R.id.table_main);
        calendarRB = (RadioButton) findViewById(R.id.table_calendar);
        tagRB = (RadioButton) findViewById(R.id.table_tag);
        personRB = (RadioButton) findViewById(R.id.table_person);

        //默认打开主界面
        mainRB.setChecked(true);
        showFragment(NUM_MAIN_FRAGMENT);
    }


    //由编辑界面返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Contants.CODE_FOR_WRITE_RESULT && resultCode == RESULT_OK
                && data.getBooleanExtra(Contants.NAME_RESULT_DATA, false) && null != mainFragment) {
            //监听从编辑界面的返回,进行数据刷新
            mainFragment.refreshData();
        }
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


    /**
     * @param isNeedFresh 是否需要进行刷新界面；
     * @description 启动主界面，WriteActivity调用
     */
    public static void actionStart(Context context, boolean isNeedFresh) {
        context.startActivity(new Intent(context, MainActivity.class));
        if (isNeedFresh) {
            List<WriteData> test = LitePal.findAll(WriteData.class);
            for (WriteData t1 : test) {
                Log.d(TAG, " WriteData content: " + t1.getContent());
            }
        }
    }


}


