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
 * Description: 关于软件界面，在personFragment点击“关于软件”选项启动
 */
public class AboutActivity extends BaseActivity {

    private TextView titleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText(R.string.title_about);
    }

    //启动关于软件界面
    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }
}
