package com.best.fram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.best.activity.AddShangPinActivity;
import com.best.activity.FenleiActivity;
import com.best.activity.R;
import com.best.adapter.FX_ShangPinAdapter;
import com.best.adapter.ShangpinitemAdapter;
import com.best.denglu.DengluActivity;
import com.best.util.HttpUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShangPinFragment extends Fragment {

    List<String > paixu_list = new ArrayList<>();

//    @ViewInject(R.id.demo_layout)
//    private ListView demo_layout;
//    @ViewInject(R.id.paixu_layout)
//    private ListView paixu_layout;
//    @ViewInject(R.id.fenlei_layout)
//    private ListView fenlei_layout;
    //单选
//    @ViewInject(R.id.chushouzhong_rb)
//    private RadioButton chushouzhong;
//    @ViewInject(R.id.cangkuzhong_rb)
//    private RadioButton cangkuzhong;
//    @ViewInject(R.id.wentishangpin_rb)
//    private RadioButton wentishangpin;
    ListView paixu_layout,fenlei_layout,demo_layout;
    RadioButton chushouzhong,cangkuzhong,wentishangpin;
    LinearLayout paixu,fenlei,add;
    int kaiguan_fenlei = 0;
    int kaiguan_paixu = 0;

    List<String> fenlei_nei = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shangpin,container,false);
        chushouzhong = (RadioButton) view.findViewById(R.id.chushouzhong_rb);
        cangkuzhong = (RadioButton) view.findViewById(R.id.cangkuzhong_rb);
        wentishangpin = (RadioButton) view.findViewById(R.id.wentishangpin_rb);
        paixu_layout = (ListView) view.findViewById(R.id.paixu_layout);
        fenlei_layout = (ListView) view.findViewById(R.id.fenlei_layout);
        demo_layout = (ListView) view.findViewById(R.id.demo_layout);
        paixu = (LinearLayout) view.findViewById(R.id.paixu);
        fenlei = (LinearLayout) view.findViewById(R.id.fenlei);
        add = (LinearLayout) view.findViewById(R.id.add_shangpin);

        paixu_list.add("按添加事件从近到远");
        paixu_list.add("按添加事件从远到近");
        paixu_list.add("按库存数量从少到多");
        paixu_list.add("按库存数量从多到少");

        fenlei_nei.add("管理分类");
        fenlei_nei.add("全部商品");
        Log.i("GG", "size:" + FenleiActivity.fenLeis.size());
        for (int i = 0; i<FenleiActivity.fenLeis.size();i++){
            if (FenleiActivity.fenLeis.size() != 0){
                Log.i("GG","fenLeis.get()"+i+"   k "+FenleiActivity.fenLeis.get(i));
                fenlei_nei.add(FenleiActivity.fenLeis.get(i));
            }
        }

        fenlei_layout.setAdapter(new FX_ShangPinAdapter(fenlei_nei,getActivity()));
        paixu_layout.setAdapter(new FX_ShangPinAdapter(paixu_list, getActivity()));
//        String sqll = "select * from wst_user_token where token = '" + "dds" + "'";
        String sql = "select * from wst_goods where token = "+ DengluActivity.token+"";
        String types = "/Api/exeQuery";
        HashMap<String,String> map = new HashMap<>();
        map.put("sql",sql);
        HttpUtils.httpGet(types, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaa","--------onSuccess-------"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("aaa","--------onError-------");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        demo_layout.setAdapter(new ShangpinitemAdapter(getActivity()));


        //字体边颜色
        chushouzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chushouzhong.setTextColor(getActivity().getResources().getColor(R.color.red));
                cangkuzhong.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                wentishangpin.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                demo_layout.setVisibility(View.VISIBLE);
                fenlei_layout.setVisibility(View.GONE);
                paixu_layout.setVisibility(View.GONE);
                kaiguan_paixu = 0;
                kaiguan_fenlei = 0;
            }
        });
        cangkuzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chushouzhong.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                cangkuzhong.setTextColor(getActivity().getResources().getColor(R.color.red));
                wentishangpin.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                demo_layout.setVisibility(View.VISIBLE);
                fenlei_layout.setVisibility(View.GONE);
                paixu_layout.setVisibility(View.GONE);
                kaiguan_paixu = 0;
                kaiguan_fenlei = 0;
            }
        });
        wentishangpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chushouzhong.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                cangkuzhong.setTextColor(getActivity().getResources().getColor(R.color.dingdan_ziti_color));
                wentishangpin.setTextColor(getActivity().getResources().getColor(R.color.red));
                demo_layout.setVisibility(View.VISIBLE);
                fenlei_layout.setVisibility(View.GONE);
                paixu_layout.setVisibility(View.GONE);
                kaiguan_paixu = 0;
                kaiguan_fenlei = 0;
            }
        });

//        排序的点击事件
        paixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( kaiguan_paixu == 0 ){
                    demo_layout.setVisibility(View.GONE);
                    fenlei_layout.setVisibility(View.GONE);
                    paixu_layout.setVisibility(View.VISIBLE);
                    kaiguan_paixu++;
                }else {
                    demo_layout.setVisibility(View.VISIBLE);
                    fenlei_layout.setVisibility(View.GONE);
                    paixu_layout.setVisibility(View.GONE);
                    kaiguan_paixu = 0;
                }
            }
        });

//        分类的点击事件
        fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( kaiguan_fenlei == 0 ){
                    demo_layout.setVisibility(View.GONE);
                    fenlei_layout.setVisibility(View.VISIBLE);
                    paixu_layout.setVisibility(View.GONE);
                    kaiguan_fenlei++;
                }else {
                    demo_layout.setVisibility(View.VISIBLE);
                    fenlei_layout.setVisibility(View.GONE);
                    paixu_layout.setVisibility(View.GONE);
                    kaiguan_fenlei = 0;
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddShangPinActivity.class));
            }
        });

        //分类ListView的点击事件
        fenlei_layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(),FenleiActivity.class);
                    startActivity(intent);
                }
            }
        });
        //分类ListView的点击事件
        paixu_layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                demo_layout.setVisibility(View.VISIBLE);
                fenlei_layout.setVisibility(View.GONE);
                paixu_layout.setVisibility(View.GONE);
                kaiguan_fenlei = 0;
            }
        });
        return view;
    }
}
