package com.best.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.best.adapter.FX_ShangPinAdapter;
import com.best.fram.ShangPinFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z on 2015/12/22.
 */
public class FenleiActivity extends Activity {
    ListView list_shangpinfenlei;
    public static List<String> fenLeis= new ArrayList<>();
    FX_ShangPinAdapter fx_shangPinAdapter;
    AlertDialog.Builder builder;
    int shanchu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        fx_shangPinAdapter = new FX_ShangPinAdapter(fenLeis, getApplicationContext());
        list_shangpinfenlei = (ListView) findViewById(R.id.list_shangpinfenlei);

        builder = new AlertDialog.Builder(this);

        list_shangpinfenlei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog(position);
            }
        });

        Intent intent = getIntent();
        String zhi = intent.getStringExtra("zhi");
        if (zhi == null || zhi == ""){
            list_shangpinfenlei.setAdapter(fx_shangPinAdapter);
        } else{
            fenLeis.add(zhi);
            list_shangpinfenlei.setAdapter(fx_shangPinAdapter);
            fx_shangPinAdapter.notifyDataSetChanged();

        }
    }
     public void fanhui(View view){
         Intent intent = new Intent(FenleiActivity.this,ShangPinFragment.class);
         startActivity(intent);
         finish();
     }

    public void tianjia(View view){
        Intent intent = new Intent(FenleiActivity.this,AddFenleiActivity.class);
        startActivity(intent);
        finish();
    }

    public void dialog(final int position){
        builder.setTitle("确认删除");
        builder.setCancelable(false);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shanchu = 1;
                fenLeis.remove(fenLeis.get(position));
                fx_shangPinAdapter.notifyDataSetChanged();
                Log.i("GG",fenLeis.size()+"  ge ");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shanchu = 0;
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
