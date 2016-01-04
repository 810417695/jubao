package com.best.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.best.util.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by xuguojunjun on 2015/12/24.
 */
@ContentView(R.layout.actvity_yunfeishezhi)
public class YunfeiActivity extends BaseActivity implements View.OnClickListener {

    //商品多少件之内多少钱
    @ViewInject(R.id.jianshu)
    private EditText jianshu;

    @ViewInject(R.id.jianshujia)
    private ImageView jianshujia;

    @ViewInject(R.id.jianshujian)
    private ImageView jianshujian;

    //运费多少元

    @ViewInject(R.id.yunfei1)
    private EditText yunfei1;

    @ViewInject(R.id.yunfeijia)
    private ImageView yunfeijia;

    @ViewInject(R.id.yunfeijian)
    private ImageView yunfeijian;

    //每增加几件商品

    @ViewInject(R.id.zengjia)
    private EditText zengjia;

    @ViewInject(R.id.zengjiajia)
    private ImageView zengjiajia;

    @ViewInject(R.id.zengjiajian)
    private ImageView zengjiajian;

    //运费增加多少元

    @ViewInject(R.id.yunfeizengjia)
    private EditText yunfeizengjia;

    @ViewInject(R.id.yunfeizengjiajia)
    private ImageView yunfeizengjiajia;

    @ViewInject(R.id.yunfeizengjiajian)
    private ImageView yunfeizengjiajian;

    //满多少钱包邮

    @ViewInject(R.id.man)
    private EditText man;

    @ViewInject(R.id.manjia)
    private ImageView manjia;

    @ViewInject(R.id.manjian)
    private ImageView manjian;

    //满几件包邮

    @ViewInject(R.id.yunfei)
    private EditText yunfei;

    @ViewInject(R.id.yunfeijia1)
    private ImageView yunfeijia1;

    @ViewInject(R.id.yunfeijian1)
    private ImageView yunfeijian1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jianshujia.setOnClickListener(this);
        jianshujian.setOnClickListener(this);
        yunfeijia.setOnClickListener(this);
        yunfeijian.setOnClickListener(this);
        zengjiajia.setOnClickListener(this);
        zengjiajian.setOnClickListener(this);
        yunfeizengjiajia.setOnClickListener(this);
        yunfeizengjiajian.setOnClickListener(this);
        manjia.setOnClickListener(this);
        manjian.setOnClickListener(this);
        yunfeijia1.setOnClickListener(this);
        yunfeijian1.setOnClickListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    //公共的方法
    private void jia(EditText editText){
        String b = String.valueOf(editText.getText());
        if(b.equals("")){
            editText.setText("0");
        }else {
            editText.setText(b);
        }
        int i = Integer.parseInt(editText.getText().toString());
        Log.i("aaa","----------------"+i);
        i++;
        String a = String.valueOf(i);
        Log.i("aaa","-----------i++以后-----"+i);
        editText.setText(a);
    }

    private void jian(EditText editText){
        String b = String.valueOf(editText.getText());
        if(b.equals("")){
            editText.setText("0");
        }else {
            editText.setText(b);
        }
        int i = Integer.parseInt(editText.getText().toString());
        Log.i("aaa","----------------"+i);
        if(i>0){
            i--;
        }
        String a = String.valueOf(i);
        Log.i("aaa","-----------i--以后-----"+i);
        editText.setText(a);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.jianshujia:
                jia(jianshu);
                break;
            case R.id.jianshujian:
                jian(jianshu);
                break;
            case R.id.yunfeijia:
                jia(yunfei1);
                break;
            case R.id.yunfeijian:
                jian(yunfei1);
                break;
            case R.id.zengjiajia:
                jia(zengjia);
                break;
            case R.id.zengjiajian:
                jian(zengjia);
                break;
            case R.id.yunfeizengjiajia:
                jia(yunfeizengjia);
                break;
            case R.id.yunfeizengjiajian:
                jian(yunfeizengjia);
                break;
            case R.id.manjia:
                jia(man);
                break;
            case R.id.manjian:
                jian(man);
                break;
            case R.id.yunfeijia1:
                jia(yunfei);
                break;
            case R.id.yunfeijian1:
                jian(yunfei);
                break;
        }
    }
}
