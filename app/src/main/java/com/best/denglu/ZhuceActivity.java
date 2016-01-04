package com.best.denglu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.best.activity.R;
import com.best.util.BaseActivity;
import com.best.util.HttpUtils;
import com.best.util.Toastshow;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by xuguojunjun on 2015/12/21.
 */
@ContentView(R.layout.activity_register)
public class ZhuceActivity extends BaseActivity {

    private static final String appkey="da10ee28c008"; // 设置成你自己的appkey
    private ProgressDialog mProgressDialog;
    private static final String  appsecret="3fdeb603499d4525490d64e5ca6e9dae";//设置成你自己的appsecret
    HashMap<String,String> map = new HashMap<>();
    private TimeCount time;
    @ViewInject(R.id.user)
    private EditText user;

    @ViewInject(R.id.psw)
    private EditText psw;

    @ViewInject(R.id.yanzhengma_txt)
    private EditText yanzhengma;

    @ViewInject(R.id.yanzhengma_btn)
    private Button yanzhengma_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMSSDK.initSDK(this, appkey, appsecret);
        time = new TimeCount(60000, 1000);
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
        x.view().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    //发送短信
    @Event(value = R.id.yanzhengma_btn)
    private void yanZheng(View v) {
        Log.i("GG","aaaa"+user.getText().toString());
        SMSSDK.getVerificationCode("86", user.getText().toString());
//        SMSSDK.submitVerificationCode("86",
//                username.getText().toString(),
//                yanzhengma.getText().toString());
        time.start();//开始计时
    }


    @Event(R.id.finsh)
    private void fanhuiClick(View view){
        finish();
    }

    @Event(R.id.zhuce)
    private void registerClick(View v) {
        mProgressDialog = ProgressDialog.show(ZhuceActivity.this, "", "正在加载...");

        if (!TextUtils.isEmpty(yanzhengma.getText().toString())) {
            SMSSDK.submitVerificationCode("86", user.getText().toString(), yanzhengma.getText().toString());

        } else {
            Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_LONG).show();
            mProgressDialog.dismiss();
            return;
        }

    }
    public void zhuce(){
        //属性
        String types = "/Api/register";
        //获取用户名和密码
        String usernametext = user.getText().toString();
        String passwordtext = psw.getText().toString();
        String siyao = "sunsai123";

//        map.put("url","/Api/register");
        map.put("userPhone",usernametext);
        map.put("loginPwd", passwordtext);
        map.put("isTure","1");
        HttpUtils.httpPost(types,map, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess::::::"+s.toString());
                String ct = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ct = jsonObject.getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(ct.equals("200")){
                    Toastshow.toastLong(ZhuceActivity.this,"注册成功进入登录页面");
                    startActivity(new Intent(ZhuceActivity.this,DengluActivity.class));
                    mProgressDialog.dismiss();
                    finish();
                }else {
                    Toastshow.toastLong(ZhuceActivity.this,"请填写正确的手机号格式");
                    mProgressDialog.dismiss();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError::::::"+throwable.getMessage());
                Toastshow.toastLong(ZhuceActivity.this,"注册失败：请填写规范的用户名和密码");
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //Handler
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("GG", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "验证成功", Toast.LENGTH_SHORT).show();
                    map.put("isTrue","1");
                    zhuce();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                ((Throwable) data).printStackTrace();
                Toast.makeText(getApplicationContext(), "验证失败", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }

        }

    };

    //获取验证码的时候避免重复点击
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            yanzhengma_btn.setText("获取验证码");
            yanzhengma_btn.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            yanzhengma_btn.setClickable(false);//防止重复点击
            yanzhengma_btn.setText(millisUntilFinished / 1000 + "s");
        }
    }



}
