package os.szlanyou.com.qzns.base;

import android.app.Application;

import org.litepal.LitePal;

import os.szlanyou.com.qzns.model.bean.WriteData;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        LitePal.initialize(this);
        LitePal.getDatabase();
        LitePal.deleteAll(WriteData.class);
    }
}
