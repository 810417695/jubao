package com.best.util;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 基类Activity
 *
 * Created by guanluocang on 15/11/17.
 * Company copyright Dahi
 */


public abstract class BaseActivity extends AppCompatActivity {

    protected List<Activity> mActivities = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managerActivities(this);
        x.view().inject(this);
        initView();
        initData();
    }
    /**
     * 初始化视图
     * */
    public abstract  void initView();
    /**
     * 初始化数据
     * */
    public abstract void initData();

    /*
     * 添加所有的activity方面整体退出
     * */
    protected void managerActivities(Activity activity){
        mActivities.add(activity);
    }
    /*
    *
    * 退出整个应用
    * */
    protected void exitApplaction(){
        for (Activity a:mActivities){
            if (a !=null){
                a.finish();
            }
        }
    }
}
