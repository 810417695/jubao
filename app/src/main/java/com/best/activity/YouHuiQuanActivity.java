package com.best.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.best.util.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Z on 2015/12/23.
 */
@ContentView(R.layout.activity_youhuijuan)
public class YouHuiQuanActivity extends BaseActivity {

    @ViewInject(R.id.youhuiquan_list)
    private EditText youhuiquan_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuijuan);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

//    @Event(value = R.id.add_youhuiquan)
//    private void add_YouHuiQuan(View v) {
//        Log.i("GG","我点击添加了");
//    }

    public void add_YouHuiQuan(View v){
        Intent intent = new Intent(YouHuiQuanActivity.this,AddYouHuiQuanActivity.class);
        startActivity(intent);
    }
}
