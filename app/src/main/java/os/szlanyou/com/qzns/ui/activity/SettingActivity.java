package os.szlanyou.com.qzns.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.base.BaseActivity;


/**
 * Author: qzns木雨
 * Date:2018/12/5
 * Description: 设置界面，在personFragment点击设置选项启动
 */
public class SettingActivity extends BaseActivity {

    private TextView titleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText(R.string.title_setting);
    }

    //启动设置界面
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
