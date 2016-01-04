package com.best.denglu;

import android.content.Intent;
import android.view.View;

import com.best.activity.R;
import com.best.util.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_login__register)
public class Login_Register extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Event(R.id.zhuce_btn)
    private void zcClick(View view){
        startActivity(new Intent(Login_Register.this,ZhuceActivity.class));

    }

    @Event(R.id.denglu_btn)
    private void dlClick(View view){
        startActivity(new Intent(Login_Register.this,DengluActivity.class));

    }

}
