package com.best.fram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.best.activity.BianjidpActvity;
import com.best.activity.R;
import com.best.activity.YouhuijuanActivity;
import com.best.yanzheng.RenzhengActvity;

/**
 * Created by xuguojunjun on 2015/12/10.
 */
public class Dianpu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frament_dianpu,container,false);

        return view;
    }
    public void bianjidianpu(View view){
        startActivity(new Intent(getActivity(), BianjidpActvity.class));
    }
    public void youhuijuan(View view){
        startActivity(new Intent(getActivity(), YouhuijuanActivity.class));
    }
    public void dianzhangriji(View view){
//        startActivity(new Intent(getActivity(),));
    }
    public void renzhengziliao(View view){
        startActivity(new Intent(getActivity(), RenzhengActvity.class));
    }
    public void yunfeishezhi(View view){

    }
}
