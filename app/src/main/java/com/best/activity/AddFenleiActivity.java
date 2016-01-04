package com.best.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Z on 2015/12/22.
 */
public class AddFenleiActivity extends Activity {
    EditText addfenlei_ET;
    Button addFenlei_tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfenlei);
        addfenlei_ET = (EditText) findViewById(R.id.addfenlei_ET);
        addFenlei_tijiao = (Button) findViewById(R.id.addFenlei_tijiao);

        addFenlei_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(AddFenleiActivity.this,FenleiActivity.class);
                String aa = addfenlei_ET.getText().toString();
                intent.putExtra("zhi",aa);
                startActivity(intent);
                finish();
            }
        });
    }

    public void fanhui(View v){
        finish();
    }
}
