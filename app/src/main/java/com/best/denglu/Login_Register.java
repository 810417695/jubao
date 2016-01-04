package com.best.denglu;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.best.activity.R;
import com.best.util.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login__register)
public class Login_Register extends BaseActivity {

    @ViewInject(R.id.backxxx)
    private ImageView backxxx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(backxxx,"translationX",0.0f, 350.0f, 0f);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setDuration(2500).start();

    }

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
