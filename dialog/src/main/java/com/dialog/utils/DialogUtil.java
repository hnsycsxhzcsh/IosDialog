package com.dialog.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by HARRY on 2018/9/4 0004.
 */

public class DialogUtil {


    //获取的是dp值
    public static int getWindowHei(Context mContext) {
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public static int getWindowWid(Context mContext) {
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
}
