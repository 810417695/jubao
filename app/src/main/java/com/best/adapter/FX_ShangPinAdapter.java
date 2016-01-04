package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.best.activity.R;

import java.util.List;

/**
 * Created by Z on 2015/12/17.
 */
public class FX_ShangPinAdapter extends BaseAdapter {

    List<String> neirong;
    Context context;
    ViewHolder viewHolder;

    public FX_ShangPinAdapter(List neirong,Context context){
        this.neirong = neirong;
        this.context = context;
    }

    @Override
    public int getCount() {
        return neirong.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_shangpin_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.wenzi = (TextView) convertView.findViewById(R.id.wenzi);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.wenzi.setText(neirong.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView wenzi;
    }
}
