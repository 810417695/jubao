package com.best.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.WheelPicker;

/**
 * Created by Z on 2015/12/23.
 */
public class AddYouHuiQuanActivity extends Activity {

    TextView kaishiTime,jiezhiTime,youxiaoTime;
    Date start,stop,youxiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianjiayouhuijuan);
        kaishiTime = (TextView) findViewById(R.id.kaishitime);
        jiezhiTime = (TextView) findViewById(R.id.jiezhitime);
        youxiaoTime = (TextView) findViewById(R.id.youxiaoTime);
    }

    public void StartTime(View v){
        DateTimePicker picker = new DateTimePicker(AddYouHuiQuanActivity.this);
        picker.setMode(DateTimePicker.Mode.YEAR_MONTH_DAY);
        picker.setRange(2015, 2055);
        picker.setSelectedDate(2015, 11, 27);
        picker.setOnWheelListener(new WheelPicker.OnWheelListener<Date>() {
            @Override
            public void onSubmit(Date result) {
                start = result;
                //Log.i("haha", result.toLocaleString()+"");
                kaishiTime.setText(result.toLocaleString());
            }
        });
        picker.showAtBottom();


    }
    public void StopTime(View v){
        DateTimePicker picker = new DateTimePicker(AddYouHuiQuanActivity.this);
        picker.setMode(DateTimePicker.Mode.YEAR_MONTH_DAY);
        picker.setRange(2015, 2055);
        picker.setSelectedDate(2015, 11,1);
        picker.setOnWheelListener(new WheelPicker.OnWheelListener<Date>() {
            @Override
            public void onSubmit(Date result) {
                stop = result;
                if ( stop.after(start) ==true ){
                    jiezhiTime.setText(result.toLocaleString());
                }else {
                    Toast.makeText(getApplicationContext(),"截至时间不能小于开始时间",Toast.LENGTH_SHORT).show();
                }

            }
        });
        picker.showAtBottom();
    }

    public void YouxiaoTime(View v){
        DateTimePicker picker = new DateTimePicker(AddYouHuiQuanActivity.this);
        picker.setMode(DateTimePicker.Mode.YEAR_MONTH_DAY);
        picker.setRange(2015, 2055);
        picker.setSelectedDate(2015, 11,1);
        picker.setOnWheelListener(new WheelPicker.OnWheelListener<Date>() {
            @Override
            public void onSubmit(Date result) {
                youxiao = result;
                if ( youxiao.after(stop) ==true ){
                    youxiaoTime.setText(result.toLocaleString());
                }else {
                    Toast.makeText(getApplicationContext(),"有效时间不能小于截至时间",Toast.LENGTH_SHORT).show();
                }
            }
        });
        picker.showAtBottom();
    }
}
