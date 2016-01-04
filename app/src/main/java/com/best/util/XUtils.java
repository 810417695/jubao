package com.best.util;

import android.app.Application;

import org.xutils.x;

public class XUtils extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);//输出日志
    }
}
