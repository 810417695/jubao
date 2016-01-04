package com.best.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.best.util.BaseActivity;

/**
 * Created by xuguojunjun on 2015/12/18.
 */
public class MyqianbaoActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianbao);
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
    public void jiesuan(View view){
        startActivity(new Intent(MyqianbaoActivity.this,Myqianbao_jiesuanActivity.class));
        finish();
    }
}