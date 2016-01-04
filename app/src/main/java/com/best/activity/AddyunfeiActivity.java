package com.best.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.best.bean.Areas;
import com.best.util.BaseActivity;
import com.best.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.WheelPicker;

/**
 * Created by xuguojunjun on 2015/12/25.
 */

@ContentView(R.layout.activity_add_yunfei)
public class AddyunfeiActivity extends BaseActivity implements View.OnClickListener {

    private ProgressDialog mProgressDialog;

    @ViewInject(R.id.quyu1)
    private TextView quyu1;

    @ViewInject(R.id.zhidingquyu1)
    private LinearLayout zhidingquyu1;

    @ViewInject(R.id.zhidingquyu2)
    private LinearLayout zhidingquyu2;

    @ViewInject(R.id.zhidingquyu3)
    private LinearLayout zhidingquyu3;

    //删除区域
    @ViewInject(R.id.shanchuquyu1)
    private ImageView shanchuquyu1;

    @ViewInject(R.id.shanchuquyu2)
    private ImageView shanchuquyu2;

    @ViewInject(R.id.shanchuquyu3)
    private ImageView shanchuquyu3;

    //运费设置
    @ViewInject(R.id.yfsz1)
    private LinearLayout yfsz1;

    @ViewInject(R.id.yfsz2)
    private LinearLayout yfsz2;

    @ViewInject(R.id.yfsz3)
    private LinearLayout yfsz3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shanchuquyu1.setOnClickListener(this);
        shanchuquyu2.setOnClickListener(this);
        shanchuquyu3.setOnClickListener(this);
        yfsz1.setOnClickListener(this);
        yfsz2.setOnClickListener(this);
        yfsz3.setOnClickListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @ViewInject(R.id.quyu2)
    private TextView quyu2;

    @ViewInject(R.id.quyu3)
    private TextView quyu3;

    @Event(R.id.yunfeishezhi)
    private void yfszClick(View view){
        startActivity(new Intent(AddyunfeiActivity.this,YunfeiActivity.class));
    }

    @Event(R.id.add_quyu)
    private void addquyuClick(View view){
        if(zhidingquyu1.getVisibility() == View.GONE){
            zhidingquyu1.setVisibility(View.VISIBLE);
        }else if(zhidingquyu2.getVisibility() == View.GONE){
            zhidingquyu2.setVisibility(View.VISIBLE);
        }else if(zhidingquyu3.getVisibility() == View.GONE){
            zhidingquyu3.setVisibility(View.VISIBLE);
        }
    }

    @Event(R.id.xuanzequyu2)
    private void xzqy2Click(View view){
        diquxuanze(quyu2);
    }

    @Event(R.id.xuanzequyu3)
    private void xzqy3Click(View view){
        diquxuanze(quyu3);
    }

    @Event(R.id.xuanzequyu1)
    private void xzqyClick(View view){
        diquxuanze(quyu1);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.shanchuquyu1:
                zhidingquyu1.setVisibility(View.GONE);
                break;
            case R.id.shanchuquyu2:
                zhidingquyu2.setVisibility(View.GONE);
                break;
            case R.id.shanchuquyu3:
                zhidingquyu3.setVisibility(View.GONE);
                break;
            case R.id.yfsz1:
                startActivity(new Intent(AddyunfeiActivity.this,YunfeiActivity.class));
                break;
        }
    }

    private void diquxuanze(final TextView textView){
        //areaType = "4";
        //parentId = "370102001000";
        //地区选择接口
        mProgressDialog = ProgressDialog.show(AddyunfeiActivity.this, "", "正在加载...");

        String types = "/KittyApi/areas";
        HashMap<String,String> map = new HashMap<>();
        map.put("parentId","0");
        map.put("areaType","0");
        HttpUtils.httpGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("aa", "1+fanhui" + s.toString());
                try {
                    org.json.JSONObject data = new org.json.JSONObject(s);
//                    JSONObject data = new JSONObject(s);
                    String code = data.get("code").toString();
                    String message = data.get("message").toString();
                    JSONArray datas = data.getJSONArray("data");
                    //Log.i("aa","soss+"+datas.toString());
                    List<Areas> areasList = new ArrayList<Areas>();
                    for (int i = 0; i < datas.length(); i++) {
                        org.json.JSONObject datas2 = datas.getJSONObject(i);
                        Areas areas = new Areas();
                        areas.setAreaId(datas2.get("areaId").toString());
                        areas.setAreaName(datas2.get("areaName").toString());
                        areas.setParentId(datas2.get("parentId").toString());
                        areas.setAreaType(datas2.get("areaType").toString());
                        areasList.add(areas);
                        //Log.i("aa","解析中");
                    }
                    //Log.i("aa","完成");
                    final ArrayList<String> dataset = new ArrayList<String>();
                    for (Areas areas : areasList) {
                        String areaName = areas.getAreaName();
                        dataset.add(areaName);
                    }
                    OptionPicker picker = new OptionPicker(AddyunfeiActivity.this);
                    picker.setOptions(dataset);
                    picker.setOnWheelListener(new WheelPicker.OnWheelListener<int[]>() {
                        @Override
                        public void onSubmit(int[] result) {
                            String province = dataset.get(result[0]);
//                            String city = option2.get(result[0]).get(result[1]);
//                            district = option3.get(result[0]).get(result[1]).get(result[2]);
//                            //Log.i("haha",""+(province + "-" + city + "-" + district));
//                            et_jobrecruit_lucos.setText(province + "-" + city + "-" + district);
                            textView.setText(province);
                        }
                    });
                    picker.showAtBottom();
                    mProgressDialog.dismiss();
                    //List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
//                    niceSpinner.attachDataSource(dataset);
                } catch (JSONException e) {
                    //System.out.println("yichang"+e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.i("aa", "2+fanhui");
            }

            @Override
            public void onCancelled(CancelledException e) {
                Log.i("aa", "3+fanhui");
            }

            @Override
            public void onFinished() {
                Log.i("aa", "4+fanhui");
            }
        });
    }
}
