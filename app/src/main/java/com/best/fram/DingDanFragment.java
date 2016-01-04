package com.best.fram;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.best.activity.R;

/**
 * Created by Z on 2015/12/17.
 */
public class DingDanFragment extends Fragment {

    RadioButton dingdan_rb,shouhou_rb,dd_all,dd_daifukuan,dd_daifahuo,dd_yifahuo,dd_yiwancheng,sh_yishenqing,sh_yituihuo,sh_tongyi,sh_jujue;
    LinearLayout dingdan_ll,shouhou_ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dingdan,container,false);



        dingdan_rb = (RadioButton) view.findViewById(R.id.dingdan_rb);
        shouhou_rb = (RadioButton) view.findViewById(R.id.shouhou_rb);
        dingdan_ll = (LinearLayout) view.findViewById(R.id.dingdan_dingdan);
        shouhou_ll = (LinearLayout) view.findViewById(R.id.shouhou);
        dd_all = (RadioButton) view.findViewById(R.id.dingdan_all_rb);
        dd_daifukuan = (RadioButton) view.findViewById(R.id.dingdan_daifukuan_rb);
        dd_daifahuo = (RadioButton) view.findViewById(R.id.dingdan_daifahuo_rb);
        dd_yifahuo = (RadioButton)view.findViewById(R.id.dingdan_yifahuo_rb);
        dd_yiwancheng = (RadioButton) view.findViewById(R.id.dingdan_yiwancheng_rb);
        sh_yishenqing = (RadioButton)view.findViewById(R.id.shouhou_yishenqing_rb);
        sh_yituihuo = (RadioButton) view.findViewById(R.id.shouhou_yituihuo_rb);
        sh_tongyi = (RadioButton) view.findViewById(R.id.shouhou_tongyi_rb);
        sh_jujue = (RadioButton) view.findViewById(R.id.shouhou_jujue_rb);

        //订单的点击事件
        dingdan_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dingdan_rb.setTextColor(getActivity().getResources().getColor(R.color.white));
                shouhou_rb.setTextColor(getActivity().getResources().getColor(R.color.red));
                dingdan_ll.setVisibility(View.VISIBLE);
                shouhou_ll.setVisibility(View.GONE);
            }
        });

        //售后的点击事件
        shouhou_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dingdan_rb.setTextColor(getActivity().getResources().getColor(R.color.red));
                shouhou_rb.setTextColor(getActivity().getResources().getColor(R.color.white));
                dingdan_ll.setVisibility(View.GONE);
                shouhou_ll.setVisibility(View.VISIBLE);
            }
        });

        //订单---全部
        dd_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd_all.setTextColor(getActivity().getResources().getColor(R.color.red));
                dd_yiwancheng.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifukuan.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //订单---已完成
        dd_yiwancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd_all.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yiwancheng.setTextColor(getActivity().getResources().getColor(R.color.red));
                dd_yifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifukuan.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //订单---已发货
        dd_yifahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd_all.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yiwancheng.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yifahuo.setTextColor(getActivity().getResources().getColor(R.color.red));
                dd_daifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifukuan.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //订单---代发货
        dd_daifahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd_all.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yiwancheng.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifahuo.setTextColor(getActivity().getResources().getColor(R.color.red));
                dd_daifukuan.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //订单--代付款
        dd_daifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd_all.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yiwancheng.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_yifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifahuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                dd_daifukuan.setTextColor(getActivity().getResources().getColor(R.color.red));
            }
        });

        //售后---已申请
        sh_yishenqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh_yishenqing.setTextColor(getActivity().getResources().getColor(R.color.red));
                sh_yituihuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_tongyi.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_jujue.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //售后---已退货
        sh_yituihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh_yishenqing.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_yituihuo.setTextColor(getActivity().getResources().getColor(R.color.red));
                sh_tongyi.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_jujue.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //售后---同意退款
        sh_tongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh_yishenqing.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_yituihuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_tongyi.setTextColor(getActivity().getResources().getColor(R.color.red));
                sh_jujue.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
            }
        });
        //售后---拒绝推荐款
        sh_jujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh_yishenqing.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_yituihuo.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_tongyi.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                sh_jujue.setTextColor(getActivity().getResources().getColor(R.color.red));
            }
        });
        return view;
    }




}
