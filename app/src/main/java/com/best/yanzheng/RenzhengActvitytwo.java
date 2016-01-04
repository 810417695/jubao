package com.best.yanzheng;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.best.activity.R;
import com.best.util.BaseActivity;
import com.best.cehua.MainActivity;
import com.best.util.Config;
import com.best.util.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;

/**
 * Created by xuguojunjun on 2015/12/21.
 */
@ContentView(R.layout.activity_renzhengziliao)
public class RenzhengActvitytwo extends BaseActivity implements View.OnClickListener {
    int i;
    private static final int REQUEST_IMAGE =2;
    String mFilePath,zFilePath;

    @ViewInject(R.id.zhengmianno)
    private ImageView zhengmian;

    @ViewInject(R.id.beimianno)
    private ImageView beimian;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        zhengmian.setOnClickListener(this);
        beimian.setOnClickListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Event(R.id.tijiao)
    private void tijiaoClick(View view){

    }

    public void tijiao(View view){
        startActivity(new Intent(RenzhengActvitytwo.this, MainActivity.class));
        finish();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (String s : path){
                    Log.i("branddd",s);
                }
                if(i == 1){
                    zFilePath = path.get(0);
                    zhengmian.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                    i=0;
                }else{
                    mFilePath = path.get(0);
                    beimian.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }

            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.zhengmian){
            Intent intent = new Intent(this, MultiImageSelectorActivity.class);

            // whether show camera
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

            // max select image amount
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

            // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
            i=1;
            startActivityForResult(intent, REQUEST_IMAGE);

        }else{
            Intent intent = new Intent(this, MultiImageSelectorActivity.class);

            // whether show camera
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

            // max select image amount
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

            // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

            startActivityForResult(intent, REQUEST_IMAGE);
        }
    }
}
