package com.best.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.best.activity.R;

/**
 * Created by xuguojunjun on 2015/12/22.
 */
public class ShangpinitemAdapter extends BaseAdapter {

    Context context;
    public ShangpinitemAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_shangpin,null);
        return convertView;
    }
}
