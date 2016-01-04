package com.best.activity;

import android.os.Bundle;
import android.view.View;

import com.best.util.BaseActivity;

/**
 * Created by xuguojunjun on 2015/12/18.
 */
public class XiaoxiActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
    public void fanhui(View view){
        finish();
    }
}
