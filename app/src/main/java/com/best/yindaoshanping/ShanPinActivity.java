package com.best.yindaoshanping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.best.activity.R;
import com.best.util.BaseActivity;
import com.best.denglu.Login_Register;

public class ShanPinActivity extends BaseActivity {

    public static long currentTiem;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shanping);
        handler.postDelayed(runnable, 300);



    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            //int shiyong = 0;
            currentTiem = System.currentTimeMillis();
            while (true){
                //加载完数据
                long shiyong = System.currentTimeMillis()-currentTiem;
                if(shiyong<2000){
                    try {
                        Thread.sleep(2000-shiyong);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //跳转到引导页面
//                    Intent intent = new Intent(ShanPinActivity.this, StepActivity.class);
//                    ShanPinActivity.this.startActivity(intent);
//                    finish();
                    actionImage();
                    finish();
                    break;
                }
            }
        }
    };
    public void actionImage(){
        preferences = getSharedPreferences("count",MODE_WORLD_READABLE);
        int count = preferences.getInt("count", 0);

        //判断程序与第几次运行，如果是第一次运行则跳转到引导页面
        if (count == 0) {
            Log.i("aaa",count+"count////////////");
            startActivity(new Intent(ShanPinActivity.this, HuadongActivity.class));

            this.finish();
            SharedPreferences.Editor editor = preferences.edit();
            //存入数据
            editor.putInt("count", ++count);
            //提交修改
            editor.commit();
        }else{
            Log.i("aaa",count+"count............");
            //直接跳转到主页
            startActivity(new Intent(ShanPinActivity.this,
                    Login_Register.class));
        }

    }


}
