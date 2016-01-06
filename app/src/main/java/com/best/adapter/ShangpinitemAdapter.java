package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.best.activity.R;
import com.best.bean.ShangPin;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xuguojunjun on 2015/12/22.
 */
public class ShangpinitemAdapter extends BaseAdapter {
    ViewHolder viewHolder;
    List<ShangPin> list;
    Context context;
    public ShangpinitemAdapter(Context context, List list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shangpin,null);
            viewHolder = new ViewHolder();
            viewHolder.tupian = (ImageView) convertView.findViewById(R.id.baobei_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.baobei_name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.baobei_jiage);
            viewHolder.number = (TextView) convertView.findViewById(R.id.baobei_kucun_shuliang);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        ShangPin shangPin =list.get(position);
//        Picasso.with(context).load(shangPin.getGoodsImg()).into(viewHolder.tupian);
        viewHolder.title.setText(shangPin.getGoodsName());
        viewHolder.number.setText(shangPin.getGoodsStock());
        viewHolder.price.setText(shangPin.getMarketPrice());

        return convertView;
    }
    class ViewHolder{
        ImageView tupian;
        TextView title,number,price;
    }

}
