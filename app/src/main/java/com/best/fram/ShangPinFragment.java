package com.best.fram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.best.activity.AddShangPinActivity;
import com.best.activity.FenleiActivity;
import com.best.activity.R;
import com.best.adapter.FX_ShangPinAdapter;
import com.best.adapter.ShangpinitemAdapter;
import com.best.bean.ShangPin;
import com.best.denglu.DengluActivity;
import com.best.util.HttpUtils;
import com.jingchen.pulltorefresh.PullToRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShangPinFragment extends Fragment {

    List<String > paixu_list = new ArrayList<>();

    ListView paixu_layout,fenlei_layout,demo_list;
    LinearLayout demo_layout;
    RadioButton chushouzhong,cangkuzhong,wentishangpin;
    LinearLayout paixu,fenlei,add;
    int kaiguan_fenlei = 0;
    int kaiguan_paixu = 0;
    EditText sousuo_Nrirong;
    HashMap<String,String> map = new HashMap<>();
    List<ShangPin> shangpin_list = new ArrayList<>();
    List<String> fenlei_nei = new ArrayList<>();
    int a = 0;
    ImageView sousuo_Img;
    ShangpinitemAdapter shangpinitemAdapter;
    int list_shu = 10;
    int shangti = 0 ;
    String neirong;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shangpin,container,false);

        ((PullToRefreshLayout) view.findViewById(R.id.rotate_header_web_view_frame))
                .setOnRefreshListener(new MyListener());
        demo_list = (ListView) view.findViewById(R.id.content_view);
        chushouzhong = (RadioButton) view.findViewById(R.id.chushouzhong_rb);
        cangkuzhong = (RadioButton) view.findViewById(R.id.cangkuzhong_rb);
        wentishangpin = (RadioButton) view.findViewById(R.id.wentishangpin_rb);
        paixu_layout = (ListView) view.findViewById(R.id.paixu_layout);
        fenlei_layout = (ListView) view.findViewById(R.id.fenlei_layout);
        demo_layout = (LinearLayout) view.findViewById(R.id.demo_layout);
        paixu = (LinearLayout) view.findViewById(R.id.paixu);
        fenlei = (LinearLayout) view.findViewById(R.id.fenlei);
        add = (LinearLayout) view.findViewById(R.id.add_shangpin);
        shangpinitemAdapter = new ShangpinitemAdapter(getActivity(),shangpin_list);
        sousuo_Nrirong = (EditText) view.findViewById(R.id.sousuo_Nrirong);
        sousuo_Img = (ImageView) view.findViewById(R.id.sousuo_Img);

        if ( a== 0 ){
            AddShangPin(null);
        }

        paixu_list.add("按添加事件从近到远");
        paixu_list.add("按添加事件从远到近");
        paixu_list.add("按库存数量从少到多");
        paixu_list.add("按库存数量从多到少");

        fenlei_nei.add("管理分类");
        fenlei_nei.add("全部商品");
        for (int i = 0; i<FenleiActivity.fenLeis.size();i++){
            if (FenleiActivity.fenLeis.size() != 0){
                fenlei_nei.add(FenleiActivity.fenLeis.get(i));
            }
        }

        //商品列表的点击事件
        demo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        fenlei_layout.setAdapter(new FX_ShangPinAdapter(fenlei_nei,getActivity()));
        paixu_layout.setAdapter(new FX_ShangPinAdapter(paixu_list, getActivity()));


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

                list_shu = 10;
                shangti = 0;
                shangpin_list.clear();
                AddShangPin(null);
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

                list_shu = 10;
                shangti = 0;
                shangpin_list.clear();
                AddShangPin(null);
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

                list_shu = 10;
                shangti = 0;
                shangpin_list.clear();
                AddShangPin(null);
            }
        });


        //搜索的点击事件
        sousuo_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(sousuo_Nrirong)){
                    Toast.makeText(getContext(),"搜索内容不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    list_shu = 10;
                    shangti = 0;
                    neirong = sousuo_Nrirong.getText().toString();
                    shangpin_list.clear();
                    SelectShangpin();
                }
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

    /***
     * 下拉刷新 上提加载
     */
    public class MyListener implements PullToRefreshLayout.OnRefreshListener
    {
        //刷新
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout)
        {
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    list_shu = 10;
                    shangti = 0;
                    shangpin_list.clear();
                    AddShangPin(pullToRefreshLayout);
//                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
        //加载
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
        {
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    list_shu = list_shu + 10 ;
                    AddShangPin(pullToRefreshLayout);
//                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }


    //获取商品列表信息
    public void AddShangPin(final PullToRefreshLayout pullToRefreshLayout){
        //属性
        String types = "/Api/extQueryByToken";
//        map.put("url","/Api/register");
        map.put("token",DengluActivity.token);
        map.put("sql","select * from wst_goods order by createTime");
        map.remove("sign");

        HttpUtils.httpPost(types,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                String ct = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ct = jsonObject.getString("code");
                    if(ct.equals("200")){
                        JSONArray list = jsonObject.getJSONArray("data");
                        for (int i = 0; i < list_shu; i++) {
                            JSONObject list_dahu = list.getJSONObject(i);
                            String goodsId = list_dahu.getString("goodsId");
                            String goodsSn = list_dahu.getString("goodsSn");
                            String goodsName = list_dahu.getString("goodsName");
                            String goodsImg = list_dahu.getString("goodsImg");
                            String goodsThums = list_dahu.getString("goodsThums");
                            String marketPrice = list_dahu.getString("marketPrice");
                            String goodsStock = list_dahu.getString("goodsStock");
                            if (i >= shangti){
                                shangpin_list.add(new ShangPin(goodsId, goodsSn, goodsName, goodsImg, goodsThums, marketPrice, goodsStock));
                            }
                        } shangti = list_shu;
//                        demo_list .setAdapter(new ShangpinitemAdapter(getActivity(),shangpin_list));
                        if ( pullToRefreshLayout == null){
                            demo_list.setAdapter(shangpinitemAdapter);
                        }else {
                            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//                            demo_list.setAdapter(shangpinitemAdapter);
                            shangpinitemAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError::::::"+throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //获取商品列表信息
    public void SelectShangpin(){
        //属性
        String types = "/Api/exeQuery";
//        map.put("url","/Api/register");
        map.put("token",DengluActivity.token);
        if ("".equals(neirong)){
            map.put("sql","select * from wst_goods order by createTime");
        }else {
            map.put("sql","select * from wst_goods where GoodsName like '%"+neirong+"%'");
        }
        map.remove("sign");
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> it = set.iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            Log.i("GG",entry.getKey() +"-->" + entry.getValue());
        }
        HttpUtils.httpPost(types,map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.i("GG","onSuccess::::::"+s.toString());
                String ct = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    ct = jsonObject.getString("code");
                    if(ct.equals("200")){
                        JSONArray list = jsonObject.getJSONArray("data");
                        for (int i = 0; i < list_shu; i++) {
                            JSONObject list_dahu = list.getJSONObject(i);
                            String goodsId = list_dahu.getString("goodsId");
                            String goodsSn = list_dahu.getString("goodsSn");
                            String goodsName = list_dahu.getString("goodsName");
                            String goodsImg = list_dahu.getString("goodsImg");
                            String goodsThums = list_dahu.getString("goodsThums");
                            String marketPrice = list_dahu.getString("marketPrice");
                            String goodsStock = list_dahu.getString("goodsStock");
                            if (i >= shangti){
                                shangpin_list.add(new ShangPin(goodsId, goodsSn, goodsName, goodsImg, goodsThums, marketPrice, goodsStock));
                            }
                        } shangti = list_shu;
                          demo_list.setAdapter(shangpinitemAdapter);
                          shangpinitemAdapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                System.out.println("onError::::::"+throwable.getMessage());
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



}
