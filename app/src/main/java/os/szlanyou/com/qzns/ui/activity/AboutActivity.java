package os.szlanyou.com.qzns.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.base.BaseActivity;


/**
 * Author: qzns木雨
 * Date:2018/12/5
 * Description: 关于软件界面，在personFragment点击关于软件选项启动
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
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
}
