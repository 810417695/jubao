package com.best.yanzheng;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.best.activity.R;
import com.best.denglu.DengluActivity;
import com.best.util.BaseActivity;
import com.best.cehua.MainActivity;
import com.best.util.Config;
import com.best.util.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;
import song.image.crop.HDApp;

/**
 * Created by xuguojunjun on 2015/12/21.
 */
@ContentView(R.layout.activity_renzhengziliaono)
public class RenzhengActvity extends BaseActivity implements View.OnClickListener {
    int i;
    private static final int REQUEST_IMAGE =2;
    String mFilePath,zFilePath;

    @ViewInject(R.id.zhengmian)
    private ImageView zhengmian;

    @ViewInject(R.id.beimian)
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

    public void tijiao(View view){
        String types = "/Api/uploadImage";
        String folder = zFilePath;
        String folder2 = mFilePath;
        String token = DengluActivity.token;
//        String imgs="http://122.114.62.25:8686/";
        String umgurl=folder2;
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        Log.i("aaa","------renzhengtoken"+token);
        map.put("folder",umgurl);
        Log.i("lujing","----------"+folder);
        HttpUtils.httpPost(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("onSuccess","-----"+s);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("onError","------shibaile-----------"+ex);
            }
  
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        startActivity(new Intent(RenzhengActvity.this,MainActivity.class));
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
                    Log.i("aaa","------file"+zFilePath);
//                    zhengmian.setImageResource(Integer.parseInt(zFilePath));
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
