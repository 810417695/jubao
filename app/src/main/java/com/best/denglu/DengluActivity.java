package com.best.denglu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.best.activity.R;
import com.best.util.BaseActivity;
import com.best.cehua.MainActivity;
import com.best.util.Config;
import com.best.util.HttpUtils;
import com.best.util.Toastshow;
import com.best.yanzheng.RenzhengActvity;
import com.best.yanzheng.RenzhengActvitytwo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * Created by xuguojunjun on 2015/12/18.
 */
@ContentView(R.layout.activity_login)
public class DengluActivity extends BaseActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private ProgressDialog mProgressDialog;
    public static String token;

    @ViewInject(R.id.passwd)
    private EditText pas;

    @ViewInject(R.id.phone)
    private EditText user;
    //记住密码
    @ViewInject(R.id.textView)
    private TextView textView;

    @ViewInject(R.id.radioButton)
    private RadioButton radioButton;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        radioButton.setOnClickListener(this);
        textView.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("text",MODE_WORLD_READABLE);
        editor = sharedPreferences.edit();
        String radio = sharedPreferences.getString("radio","0");
        if(radio.equals("1")){
            String phones = sharedPreferences.getString("phone","");
            String pass = sharedPreferences.getString("psd","");
            user.setText(phones);
            pas.setText(pass);
            radioButton.setChecked(true);
            textView.setTextColor(this.getResources().getColor(R.color.red));
        }else {
            user.setText("");
            pas.setText("");
        }



    }

    @Event(R.id.denglu_fanhui)
    private void fanhuiClick(View view){
        finish();
    }

    @Event(R.id.login)
    private void dengluClick(View view){
        mProgressDialog = ProgressDialog.show(DengluActivity.this, "", "正在加载...");

        if(radioButton.isChecked() == true){
            editor.putString("phone", String.valueOf(user.getText()));
            editor.putString("psd", String.valueOf(pas.getText()));
            editor.putString("radio","1");
//            editor.commit();
        }else{
            editor.putString("phone", "");
            editor.putString("psd", "");
            editor.putString("radio","0");
//            editor.commit();
        }
        //属性
        String types = "/Api/login";
        //获取用户名和密码
        String usernametext = user.getText().toString();
        String passwordtext = pas.getText().toString();
        final String cilentType = "android";
        HashMap<String,String> map = new HashMap<>();
        map.put("loginName",usernametext);
        map.put("loginPwd", passwordtext);
        map.put("clientType", cilentType);
        //http请求
        HttpUtils.httpPost(types, map, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess::::::" + s);
                try {
                    JSONObject data = new JSONObject(s.toString());
                    String code = data.get("code").toString();
                    String message = data.get("message").toString();
                    JSONObject datas =data.getJSONObject("data");
                    String ct = datas.get("userType").toString();
                    token = datas.get("token").toString();
                    String sql = "insert into sp_shops(userId) values ('"+69+"')";
                    String types = "/Api/exeQuery";
//                    String sql = "select * from wst_user_token where token = '" + token + "'";
                    HashMap<String,String> map = new HashMap<>();
                    map.put("sql",sql);
                    HttpUtils.httpGet(types, map, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Log.i("aaa","onSuccessbbbbb------"+s);
                        }

                        @Override
                        public void onError(Throwable throwable, boolean b) {
                            Log.i("aaa","onError-------");
                        }

                        @Override
                        public void onCancelled(CancelledException e) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
//                    SharedPreferences sharedPreferencess = getSharedPreferences("token", Context.MODE_WORLD_READABLE);
//                    SharedPreferences.Editor editors = sharedPreferencess.edit();
                    editor.putString("token",token);
                    Log.i("aaa","-------token----"+token);
                    editor.commit();
                    Log.i("aaa","-----------"+ct+"--ttttttt-"+code);
                    if(code.equals("200")){
                        if (ct.equals("0")) {
                                Toastshow.toastLong(DengluActivity.this, "由于第一次登录请认证");
                                Intent intent = new Intent();
                                startActivity(new Intent(DengluActivity.this, RenzhengActvity.class));
                                mProgressDialog.dismiss();
                                finish();
                        } else {
                            startActivity(new Intent(DengluActivity.this, MainActivity.class));
                            finish();
                        }
                    }else{
                        Toastshow.toastLong(DengluActivity.this, "用户名或密码错误");
                        mProgressDialog.dismiss();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError::::::" + throwable.getMessage());
                Toastshow.toastLong(DengluActivity.this,"登录失败：用户名或密码错误");
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

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.textView){
            if(radioButton.isChecked() == true){
                radioButton.setChecked(false);
                textView.setTextColor(this.getResources().getColor(R.color.textcolor));
            }else{
                radioButton.setChecked(true);
                textView.setTextColor(this.getResources().getColor(R.color.red));
            }
        }else{
            if(radioButton.isChecked() == true){
                radioButton.setChecked(false);
                textView.setTextColor(this.getResources().getColor(R.color.textcolor));
            }else{
                radioButton.setChecked(true);
                textView.setTextColor(this.getResources().getColor(R.color.red));
            }
        }
    }
}
