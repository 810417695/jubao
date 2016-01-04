package com.best.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.best.util.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;

/**
 * Created by xuguojunjun on 2015/12/24.
 */
@ContentView(R.layout.frament_add_dongtai)
public class DongtaiActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE =2;
    @ViewInject(R.id.add_img)
    private RelativeLayout add_img;

    @ViewInject(R.id.add_img2)
    private RelativeLayout add_img2;

    @ViewInject(R.id.add_img3)
    private RelativeLayout add_img3;

    @ViewInject(R.id.qu_img)
    private ImageView qu_img;

    @ViewInject(R.id.qu_img2)
    private ImageView qu_img2;

    @ViewInject(R.id.qu_img3)
    private ImageView qu_img3;

    @ViewInject(R.id.imgs)
    private ImageView imgs;

    @ViewInject(R.id.imgs2)
    private ImageView imgs2;

    @ViewInject(R.id.imgs3)
    private ImageView imgs3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qu_img.setOnClickListener(this);
        qu_img2.setOnClickListener(this);
        qu_img3.setOnClickListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Event(R.id.fabu)
    private void fabuClick(View view){

    }

    @Event(R.id.add_imgs)
    private void addimg(View view){

        Intent intent = new Intent(this, MultiImageSelectorActivity.class);

        // whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);

        // max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);

        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

        startActivityForResult(intent, REQUEST_IMAGE);
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
                if(add_img.getVisibility() == View.GONE){
                    add_img.setVisibility(View.VISIBLE);
                    imgs.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }else if(add_img2.getVisibility() == View.GONE){
                    add_img2.setVisibility(View.VISIBLE);
                    imgs2.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }else if(add_img3.getVisibility() == View.GONE){
                    add_img3.setVisibility(View.VISIBLE);
                    imgs3.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.qu_img:
                add_img.setVisibility(View.GONE);
                break;
            case R.id.qu_img2:
                add_img2.setVisibility(View.GONE);
                break;
            case R.id.qu_img3:
                add_img3.setVisibility(View.GONE);
                break;
        }
    }
}
