package com.best.denglu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.best.activity.R;
import com.best.util.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login__register)
public class Login_Register extends BaseActivity {

    @ViewInject(R.id.backxxx0)
    private ImageView backxxx0;
    @ViewInject(R.id.backxxx1)
    private ImageView backxxx1;
<<<<<<< HEAD

=======
>>>>>>> dev
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.setting);
        ImageView animationTopRightView = (ImageView)this.findViewById(R.id.backxxx0);
        animationTopRightView.startAnimation(anim);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.setting2);
        ImageView animationTopLeftView = (ImageView)this.findViewById(R.id.backxxx1);
        animationTopLeftView.startAnimation(anim2);
<<<<<<< HEAD

=======
>>>>>>> dev
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
