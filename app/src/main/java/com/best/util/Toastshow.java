package com.best.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xuguojunjun on 2015/12/22.
 */
public class Toastshow {

    public static void toastLong(Context context, String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }
    public static void toastshort(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
